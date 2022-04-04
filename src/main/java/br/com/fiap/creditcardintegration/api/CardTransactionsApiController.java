package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.CardTransactionsDTO;
import br.com.fiap.creditcardintegration.service.CardTransactionService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;

@RestController
@RequiredArgsConstructor
public class CardTransactionsApiController implements CardTransactionsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardTransactionsApiController.class);

    private final CardTransactionService cardTransactionService;

    public ResponseEntity<CardTransactionDTO> createCardtransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @RequestBody final CardTransactionDTORequestCreate cardTransactionDTORequestCreate) {
        return new ResponseEntity<CardTransactionDTO>(cardTransactionService.createCardtransaction(cardTransactionDTORequestCreate), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteCardtransaction(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) {
        cardTransactionService.deleteCardtransaction(registrationsNumberCard);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<CardTransactionsDTO> extractCardtransaction(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) {
        return new ResponseEntity<CardTransactionsDTO>(cardTransactionService.extractCardTransaction(registrationsNumberCard), HttpStatus.OK);
    }
}
