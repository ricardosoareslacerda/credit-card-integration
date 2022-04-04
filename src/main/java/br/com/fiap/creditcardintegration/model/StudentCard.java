package br.com.fiap.creditcardintegration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * StudentCard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student_card")
public class StudentCard   {

  @MongoId(FieldType.STRING)
  @Field("id")
  @EqualsAndHashCode.Include
  private String id;

  @Field("registrationsNumberCard")
  @EqualsAndHashCode.Include
  private String registrationsNumberCard;

  @Field("full_name")
  private String fullName;

  @Field("registration")
  private String registration;

  @Field("numberCard")
  private String numberCard;

  @Field("mail")
  private String mail;

  @Field("active")
  private Boolean active;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @Field("createdAt")
  private String createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @Field("updatedAt")
  private String updatedAt;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentCard {\n");
    
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    registration: ").append(toIndentedString(registration)).append("\n");
    sb.append("    registrationsNumberCard: ").append(toIndentedString(registrationsNumberCard)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
