package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
import br.com.fiap.creditcardintegration.dto.StudentsCardsDTO;

public interface StudentsCardService {

    StudentCardDTO createStudentCard(final StudentCardDTO studentCard);

    void deleteStudentCard(final String registrationsNumberCard);

    StudentCardDTO getStudentCard(final String registrationsNumberCard);

    StudentsCardsDTO listAllStudentCard(final String active);

    StudentCardDTO updateStudentCard(final String registrationsNumberCard, final StudentCardDTO studentCard);
}
