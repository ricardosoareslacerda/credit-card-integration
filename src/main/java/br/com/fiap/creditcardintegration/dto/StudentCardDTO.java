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
public class StudentCardDTO {

  @Schema(description = "Nome completo do aluno")
  @JsonProperty("fullName")
  private String fullName;

  @Schema(description = "Metrícula do aluno")
  @JsonProperty("registration")
  private String registration;

  @Schema(description = "Número de série do cartão")
  @JsonProperty("numberCard")
  private String numberCard;

  @Schema(description = "Número do cartão de crédito")
  @JsonProperty("registrationNumberCard")
  private String registrationNumberCard;

  @Schema(description = "E-mail do aluno")
  @JsonProperty("mail")
  private String mail;

  @Schema(description = "Situação do aluno")
  @JsonProperty("active")
  private Boolean active;

  @Schema(description = "Data de cadastro do cartão")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @JsonProperty("createdAt")
  private String createdAt;

  @Schema(description = "Data de atualização do cartão")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  @JsonProperty("updatedAt")
  private String updatedAt;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("StudentCardResponse: {\n");
    
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    registration: ").append(toIndentedString(registration)).append("\n");
    sb.append("    registrationsNumberCard: ").append(toIndentedString(registrationNumberCard)).append("\n");
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
