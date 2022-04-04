package br.com.fiap.creditcardintegration.repository;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTransactionRepository extends MongoRepository<CardTransaction, String> {
}
