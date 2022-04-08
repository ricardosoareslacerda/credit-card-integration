package br.com.fiap.creditcardintegration.dto;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CardTransactions
 */
@Data
@NoArgsConstructor
public class CardTransactionsDTO extends ArrayList<CardTransactionDTO>  {

  private static final long serialVersionUID = 1L;

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  public CardTransactionsDTO(List<CardTransaction> cardTransactions) {
    cardTransactions.forEach(cardTransaction -> this.add(CardTransactionDTO.from(cardTransaction)));
  }

  public CardTransactionsDTO from(final List<CardTransaction> cardTransactions) {

    cardTransactions.forEach(cardTransaction -> add(CardTransactionDTO.from(cardTransaction)));
    return this;
  }
}
