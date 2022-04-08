package br.com.fiap.creditcardintegration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

/**
 * CardTransaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "card_transaction")
public class CardTransaction   {

  public enum Status {
    TRANSACTION_SUCCESS("Transacao realizada com sucesso"),
    TRANSACTION_FAILED("Transacao nao realizada");

    private String description;

    Status(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }
  }

  @MongoId(FieldType.STRING)
  @Field("registrationsNumberCard")
  @EqualsAndHashCode.Include
  private String registrationNumberCard;

  @Field("establishmentName")
  private String establishmentName;

  @Field("value")
  private BigDecimal value;

  @Field("status")
  private String status;

  @Field("installments")
  private BigDecimal installments;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @Field("createdAt")
  private String createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @Field("updatedAt")
  private String updatedAt;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CardTransaction: {\n");
    
    sb.append("    name: ").append(toIndentedString(establishmentName)).append("\n");
    sb.append("    registrationNumberCard: ").append(toIndentedString(registrationNumberCard)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    installments: ").append(toIndentedString(installments)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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
