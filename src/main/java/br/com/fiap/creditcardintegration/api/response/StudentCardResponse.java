package br.com.fiap.creditcardintegration.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StudentCardResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCardResponse   {

  @JsonProperty("registrationsNumberCard")
  private String registrationsNumberCard = null;

  @Schema(description = "")
  @JsonProperty("fullName")
  private String fullName = null;

  @Schema(description = "")
  @JsonProperty("registration")
  private String registration = null;

  @Schema(description = "")
  @JsonProperty("mail")
  private String mail = null;

  @Schema(description = "")
  @JsonProperty("active")
  private Boolean active = null;

  @Schema(description = "")
  @JsonProperty("createdAt")
  private String createdAt = null;

  @Schema(description = "")
  @JsonProperty("updatedAt")
  private String updatedAt = null;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentCardResponse {\n");
    
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
