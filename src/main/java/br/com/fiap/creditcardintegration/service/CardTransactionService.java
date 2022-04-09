package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;

public interface CardTransactionService {

    CardTransactionDTO createCardtransaction(final CardTransactionDTORequestCreate cardTransactionDTORequestCreate);

    CardTransactionDTO deleteCardtransaction(final String registrationNumberCard) throws Exception;

    void extractCardTransaction(final String registrationsNumberCard) throws Exception;
}
