package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.CardTransactionsDTO;
import br.com.fiap.creditcardintegration.model.CardTransaction;
import br.com.fiap.creditcardintegration.repository.CardTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTransactionServiceImpl implements CardTransactionService {

    private final ObjectMapper objectMapper;
    private final CardTransactionRepository cardTransactionRepository;

    @Override
    public CardTransactionDTO createCardtransaction(final CardTransactionDTORequestCreate cardTransactionDTORequestCreate) {
        try {
            final CardTransaction cardTransaction = objectMapper.convertValue(cardTransactionDTORequestCreate, CardTransaction.class);
            cardTransaction.setStatus("PENDING");
            cardTransaction.setCreatedAt(Long.toString(System.currentTimeMillis()));

            final CardTransaction savedTransaction = cardTransactionRepository.save(cardTransaction);

            return objectMapper.convertValue(savedTransaction, CardTransactionDTO.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCardtransaction(final String registrationNumberCard) {
        cardTransactionRepository.deleteById(registrationNumberCard);
    }

    @Override
    public CardTransactionsDTO extractCardTransaction(final String registrationsNumberCard) {
        //final List<CardTransaction> savedStudentCard = cardTransactionRepository.extractCardTransaction((registrationsNumberCard);
        final List<CardTransaction> savedStudentCard = cardTransactionRepository.findAll();
        return objectMapper.convertValue(savedStudentCard, CardTransactionsDTO.class);
    }
}
