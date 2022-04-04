package br.com.fiap.creditcardintegration.dto;

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

  @Schema(description = "")
  @JsonProperty("name")
  private String name;

  @Schema(description = "")
  @JsonProperty("registrationNumberCard")
  private String registrationNumberCard;

  @Schema(description = "")
  @JsonProperty("value")
  private BigDecimal value;

  @Schema(description = "")
  @JsonProperty("installments")
  private BigDecimal installments;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardTransactionResponse {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
