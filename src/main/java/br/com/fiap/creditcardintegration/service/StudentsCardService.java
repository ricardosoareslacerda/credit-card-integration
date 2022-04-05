package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
import br.com.fiap.creditcardintegration.dto.StudentCardDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.StudentsCardsDTO;

public interface StudentsCardService {

    StudentCardDTO createStudentCard(final StudentCardDTORequestCreate studentCard);

    void deleteStudentCard(final String registrationsNumberCard);

    StudentCardDTO getStudentCard(final String registrationsNumberCard);

    StudentsCardsDTO listAllStudentCard(final boolean active);

    StudentCardDTO updateStudentCard(final String registrationsNumberCard, final StudentCardDTO studentCard);
}
