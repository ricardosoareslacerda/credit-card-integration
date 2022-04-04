package br.com.fiap.creditcardintegration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

/**
 * CardTransactions
 */
@Data
@NoArgsConstructor
public class CardTransactionsDTO extends ArrayList<CardTransactionDTO>  {

  @Override
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
}
