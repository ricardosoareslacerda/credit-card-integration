package br.com.fiap.creditcardintegration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StudentCardResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCardDTORequestCreate {

  @JsonProperty("registrationsNumberCard")
  private String registrationsNumberCard;

  @Schema(description = "")
  @JsonProperty("fullName")
  private String fullName;

  @Schema(description = "")
  @JsonProperty("registration")
  private String registration;

  @Schema(description = "")
  @JsonProperty("mail")
  private String mail;

  @Schema(description = "")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @JsonProperty("createdAt")
  private String createdAt;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentCardResponse {\n");
    
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    registration: ").append(toIndentedString(registration)).append("\n");
    sb.append("    registrationsNumberCard: ").append(toIndentedString(registrationsNumberCard)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
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