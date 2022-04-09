package br.com.fiap.creditcardintegration.repository;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardTransactionRepository extends MongoRepository<CardTransaction, String> {

    Optional<CardTransaction> findByRegistrationNumberCardAndStatusEquals(final String registrationNumberCard, final CardTransaction.Status status);

    Optional<List<CardTransaction>> findByRegistrationNumberCardEquals(final String registrationNumberCard);
}
