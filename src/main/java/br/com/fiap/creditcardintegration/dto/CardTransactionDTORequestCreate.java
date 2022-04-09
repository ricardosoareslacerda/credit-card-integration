package br.com.fiap.creditcardintegration.dto;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * CardTransactionResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardTransactionDTORequestCreate {

  @Schema(description = "Número do cartão de crédito")
  @JsonProperty("registrationNumberCard")
  private String registrationNumberCard;

  @Schema(description = "Nome do estabelecimento")
  @JsonProperty("establishmentName")
  private String establishmentName;

  @Schema(description = "Valor da transação")
  @JsonProperty("value")
  private BigDecimal value;

  @Schema(description = "Status da transação")
  @JsonProperty("installments")
  private BigDecimal installments;

  public CardTransaction toCardTransaction() {
    return CardTransaction.builder()
            .establishmentName(establishmentName)
            .registrationNumberCard(registrationNumberCard)
            .value(value)
            .status(CardTransaction.Status.TRANSACTION_SUCCESS)
            .installments(installments)
            .createdAt(Long.toString(System.currentTimeMillis()))
            .build();
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CardTransactionResponse: {\n");
    
    sb.append("    establishmentName: ").append(toIndentedString(establishmentName)).append("\n");
    sb.append("    registrationNumberCard: ").append(toIndentedString(registrationNumberCard)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    installments: ").append(toIndentedString(installments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
