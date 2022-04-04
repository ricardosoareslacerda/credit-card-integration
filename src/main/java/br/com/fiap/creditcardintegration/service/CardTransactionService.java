package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionsDTO;

public interface CardTransactionService {

    CardTransactionDTO createCardtransaction(final CardTransactionDTO cardTransaction);

    void deleteCardtransaction(final String registrationNumberCard);

    CardTransactionsDTO extractCardTransaction(final String registrationsNumberCard);
}
