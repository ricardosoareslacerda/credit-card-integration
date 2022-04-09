package br.com.fiap.creditcardintegration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * CardTransaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "card_transaction")
public class CardTransaction {

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

  public enum CARD_TRANSACTION_POSITION {
    POS_REGISTRATION_NUMBERCARD(5),
    POS_ESTABLISHMENT_NAME(6),
    POS_VALUE(7),
    POS_INSTALLMENTS(8),
    POS_CREATED_AT(9);

    private int position;

    CARD_TRANSACTION_POSITION(int position) {
      this.position = position;
    }

    public int getPosition() {
      return position;
    }

    public static CARD_TRANSACTION_POSITION getByPosition(int position, int loops) {
      return Arrays.stream(CARD_TRANSACTION_POSITION.values()).filter(transaction -> (position == transaction.getPosition())
                      || (position == (transaction.getPosition() + loops))).
              findAny().get();
    }
  }

  @MongoId(FieldType.STRING)
  @Field("id")
  @EqualsAndHashCode.Include
  private String id;

  @Field("registrationNumberCard")
  @EqualsAndHashCode.Include
  private String registrationNumberCard;

  @Field("establishmentName")
  private String establishmentName;

  @Field("value")
  private BigDecimal value;

  @Field("status")
  private Status status;

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
