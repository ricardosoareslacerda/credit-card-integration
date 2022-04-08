package br.com.fiap.creditcardintegration.config;

import br.com.fiap.creditcardintegration.model.StudentCard;
import br.com.fiap.creditcardintegration.service.batch.StudentCardItemProcessor;
import br.com.fiap.creditcardintegration.service.batch.listener.JobCompletionNotificationListener;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;

@RequiredArgsConstructor
@AllArgsConstructor
//@Configuration
//@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${spring.batch.job.resource.name}")
    private String resourceName;

    @Bean
    public FlatFileItemReader<StudentCard> reader() {
        return new FlatFileItemReaderBuilder<StudentCard>()
                .name("studentCardItemReader")
                .resource(new ClassPathResource(resourceName))
                .delimited()
                .delimiter("")
                //names("full_name", "registration", "numberCard", "registrationNumberCard", "mail", "createdAt")
                .names("full_name_", "registration_", "numberCard_", "registrationNumberCard_", "mail_", "createdAt_")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(StudentCard.class);
                }})
                .build();
    }

    @Bean
    public StudentCardItemProcessor processor() {
        return new StudentCardItemProcessor();
    }

    @Bean
    public MongoItemWriter<StudentCard> writer(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<StudentCard>()
                .collection("student_card")
                .template(mongoOperations)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importStudentCardJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(MongoItemWriter<StudentCard> writer) {
        return stepBuilderFactory.get("step1")
                .<StudentCard, StudentCard>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
