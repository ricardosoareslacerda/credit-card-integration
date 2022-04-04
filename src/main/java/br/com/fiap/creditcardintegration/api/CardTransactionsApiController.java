package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.api.response.CardTransactions;
import br.com.fiap.creditcardintegration.model.CardTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.io.IOException;

@Api(value = "/v1")
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class CardTransactionsApiController implements CardTransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(CardTransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    /*public CardTransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }*/

    public ResponseEntity<Void> createCardtransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody CardTransaction body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCardtransaction(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CardTransactions> listCardtransaction(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CardTransactions>(objectMapper.readValue("[ {\n  \"createdAt\" : \"createdAt\",\n  \"registrationNumberCard\" : \"registrationNumberCard\",\n  \"installments\" : 6.027456183070403,\n  \"name\" : \"name\",\n  \"value\" : 0.8008281904610115,\n  \"status\" : \"status\",\n  \"updatedAt\" : \"updatedAt\",\n  \"tags\" : [ \"tags\", \"tags\" ]\n}, {\n  \"createdAt\" : \"createdAt\",\n  \"registrationNumberCard\" : \"registrationNumberCard\",\n  \"installments\" : 6.027456183070403,\n  \"name\" : \"name\",\n  \"value\" : 0.8008281904610115,\n  \"status\" : \"status\",\n  \"updatedAt\" : \"updatedAt\",\n  \"tags\" : [ \"tags\", \"tags\" ]\n} ]", CardTransactions.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CardTransactions>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CardTransactions>(HttpStatus.NOT_IMPLEMENTED);
    }
}
