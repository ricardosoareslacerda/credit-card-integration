package br.com.fiap.creditcardintegration.service.batch;

import br.com.fiap.creditcardintegration.model.StudentCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class StudentCardItemProcessor implements ItemProcessor<StudentCard, StudentCard> {

    @Override
    public StudentCard process(StudentCard item) throws Exception {
        StudentCard studentCard = new StudentCard(item.getRegistrationNumberCard(),
                                                  item.getFullName().toLowerCase(),
                item.getRegistration(),
                item.getNumberCard(),
                item.getMail(),
                item.getActive(),
                item.getCreatedAt());
        log.info("Registring ... " + studentCard.toString());
        return studentCard;
    }

}
