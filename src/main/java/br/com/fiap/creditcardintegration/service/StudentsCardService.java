package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
import br.com.fiap.creditcardintegration.dto.StudentCardDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.StudentsCardsDTO;

public interface StudentsCardService {

    StudentCardDTO createStudentCard(final StudentCardDTORequestCreate studentCard);

    StudentsCardsDTO findAllByActive(final boolean active);

    void deleteStudentCard(final String registrationsNumberCard);

    StudentCardDTO getStudentCard(final String registrationsNumberCard);

    StudentCardDTO updateStudentCard(final String registrationsNumberCard, final StudentCardDTO studentCard);
}
