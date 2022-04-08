package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;
import br.com.fiap.creditcardintegration.model.CardTransaction;

import java.io.IOException;

public interface CardTransactionService {

    CardTransactionDTO createCardtransaction(final CardTransactionDTORequestCreate cardTransactionDTORequestCreate);

    CardTransaction deleteCardtransaction(final String registrationNumberCard) throws Exception;

    void extractCardTransaction(final String registrationsNumberCard) throws Exception;
}
