package br.com.fiap.creditcardintegration.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * CardTransaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "card_transaction")
public class CardTransaction   {

  @MongoId(FieldType.STRING)
  @Field("registrationsNumberCard")
  @EqualsAndHashCode.Include
  private String registrationNumberCard;

  @Field("name")
  private String name;

  @Field("value")
  private BigDecimal value;

  @Field("status")
  private String status;

  @Field("installments")
  private BigDecimal installments;

  @Field("createdAt")
  private String createdAt;

  @Field("updatedAt")
  private String updatedAt;

  @Field("tags")
  private List<String> tags;

  public CardTransaction addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<String>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardTransaction {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    registrationNumberCard: ").append(toIndentedString(registrationNumberCard)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    installments: ").append(toIndentedString(installments)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
