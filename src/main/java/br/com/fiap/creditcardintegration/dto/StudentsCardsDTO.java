package br.com.fiap.creditcardintegration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

/**
 * StudentsCards
 */
@Data
@NoArgsConstructor
public class StudentsCardsDTO extends ArrayList<StudentCardDTO>  {

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
