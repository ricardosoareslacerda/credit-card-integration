package br.com.fiap.creditcardintegration.repository;

import br.com.fiap.creditcardintegration.model.StudentCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsCardRepository extends MongoRepository<StudentCard, String> {

    List<StudentCard> findAllByActiveEquals(final String active);
    /*StudentCard createStudentCard(final StudentCard studentCard);

    void deleteStudentCard(final String registrationsNumberCard);

    StudentCard getStudentCard(final String registrationsNumberCard);

    StudentCard updateStudentCard(final String registrationsNumberCard, final StudentCard studentCard);*/
}