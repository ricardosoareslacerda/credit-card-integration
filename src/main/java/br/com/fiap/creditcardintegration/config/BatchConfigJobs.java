package br.com.fiap.creditcardintegration.config;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import br.com.fiap.creditcardintegration.model.StudentCard;
import br.com.fiap.creditcardintegration.repository.CardTransactionRepository;
import br.com.fiap.creditcardintegration.service.batch.StudentCardItemProcessor;
import br.com.fiap.creditcardintegration.service.batch.listener.JobCompletionNotificationListener;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Configuration
@EnableBatchProcessing
public class BatchConfigJobs {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Value("${spring.batch.job.student.resource.name}")
    private String jobStudentResourceName;

    @Value("${spring.batch.job.transaction.resource.name}")
    private String jobTransactionResourceName;

    @Bean(name = "readerStudent")
    public FlatFileItemReader<StudentCard> readerStudent() {
        return new FlatFileItemReaderBuilder<StudentCard>()
                .name("studentCardItemReader")
                .resource(new ClassPathResource(jobStudentResourceName))
                .delimited()
                .delimiter(";")
                .names("full_name", "registration", "numberCard", "registrationNumberCard", "mail", "createdAt")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(StudentCard.class);
                }})
                .build();
    }

    @Bean(name = "processorStudent")
    public StudentCardItemProcessor processorStudent() {
        return new StudentCardItemProcessor();
    }

    @Bean(name = "writerStudent")
    public MongoItemWriter<StudentCard> writerStudent(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<StudentCard>()
                .collection("student_card")
                .template(mongoOperations)
                .build();
    }

    @Bean(name = "importStudentJob")
    public Job importStudentJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importStudentJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(taskletNextStep())
                .build();
    }

    @Bean
    public Step taskletNextStep() {
        return stepBuilderFactory.get("taskletStep")
                .tasklet(tasklet())
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            InputStream inputStream = new ClassPathResource(jobTransactionResourceName).getInputStream();
            String transactionsCVS = IOUtils.toString(inputStream, Charset.defaultCharset());

            String[] transactions = transactionsCVS.split(";");
            int position = 0;
            int loops = 0;

            List<CardTransaction> cardTransactions = new ArrayList<CardTransaction>();
            CardTransaction cardTransaction = new CardTransaction();
            for (String field : transactions) {

                if (position > 4) {
                    if (StringUtils.isNotEmpty(field)) {
                        switch (CardTransaction.CARD_TRANSACTION_POSITION.getByPosition(position, loops)) {
                            case POS_REGISTRATION_NUMBERCARD:
                                cardTransaction.setRegistrationNumberCard(field.trim());
                                break;

                            case POS_ESTABLISHMENT_NAME:
                                cardTransaction.setEstablishmentName(field.trim());
                                break;

                            case POS_VALUE:
                                cardTransaction.setValue(new BigDecimal(field.trim()));
                                break;

                            case POS_INSTALLMENTS:
                                cardTransaction.setInstallments(new BigDecimal(field.trim()));
                                break;

                            case POS_CREATED_AT:
                                cardTransaction.setCreatedAt(field.trim());
                                cardTransaction.setStatus(CardTransaction.Status.TRANSACTION_SUCCESS);
                                cardTransactions.add(cardTransaction);
                                loops += 5;

                                cardTransaction = new CardTransaction();
                                break;
                        }
                    }
                }
                position++;
            }

            cardTransactions.forEach(transaction -> {
                cardTransactionRepository.save(transaction);
                transaction.toString();
            });
            return RepeatStatus.FINISHED;
        };
    }

    @Bean(name = "step1")
    public Step step1(MongoItemWriter<StudentCard> writer) {
        return stepBuilderFactory.get("step1")
                .<StudentCard, StudentCard>chunk(10)
                .reader(readerStudent())
                .processor(processorStudent())
                .writer(writer)
                .build();
    }
}
