package br.com.fiap.creditcardintegration.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundError
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(code = HttpStatus.CONFLICT)
public class NotFoundError   {

  @JsonProperty("message")
  private String message;

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
