package br.com.fiap.creditcardintegration.dto;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CardTransactionDTO {

  @Schema(description = "Nome do estabelecimento")
  @JsonProperty("establishmentName")
  private String establishmentName;

  @Schema(description = "Número do cartão de crédito")
  @JsonProperty("registrationNumberCard")
  private String registrationNumberCard;

  @Schema(description = "Valor da transação")
  @JsonProperty("value")
  private BigDecimal value;

  @Schema(description = "Status da transação")
  @JsonProperty("status")
  private String status;

  @Schema(description = "Parcelas da transação")
  @JsonProperty("installments")
  private BigDecimal installments;

  @Schema(description = "Data da transação")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @JsonProperty("createdAt")
  private String createdAt;

  @Schema(description = "Data da modificação da transação")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @JsonProperty("updatedAt")
  private String updatedAt;

    public static CardTransactionDTO from(final CardTransaction cardTransaction) {
        return CardTransactionDTO.builder()
                .establishmentName(cardTransaction.getEstablishmentName())
                .registrationNumberCard(cardTransaction.getRegistrationNumberCard())
                .value(cardTransaction.getValue())
                .status(cardTransaction.getStatus().getDescription())
                .installments(cardTransaction.getInstallments())
                .createdAt(cardTransaction.getCreatedAt())
                .updatedAt(cardTransaction.getUpdatedAt())
                .build();
    }

    public CardTransaction toCardTransaction() {
              return CardTransaction.builder()
                .establishmentName(establishmentName)
                .registrationNumberCard(registrationNumberCard)
                .value(value)
                .status(status  == null ? null : CardTransaction.Status.valueOf(status))
                .installments(installments)
                .createdAt(createdAt)
                .build();
    }

    @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardTransactionResponse {\n");
    
    sb.append("    establishmentName: ").append(toIndentedString(establishmentName)).append("\n");
    sb.append("    registrationNumberCard: ").append(toIndentedString(registrationNumberCard)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    installments: ").append(toIndentedString(installments)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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
