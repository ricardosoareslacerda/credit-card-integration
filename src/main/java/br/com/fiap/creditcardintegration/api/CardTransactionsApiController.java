package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.api.response.InternError;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;
import br.com.fiap.creditcardintegration.service.CardTransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CardTransactionsApiController implements CardTransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(CardTransactionsApiController.class);

    private final CardTransactionService cardTransactionService;

    @Override
    public ResponseEntity<CardTransactionDTO> createCardtransaction(@RequestBody final CardTransactionDTORequestCreate cardTransactionDTORequestCreate) {
        log.info("createCardtransaction: {0}", cardTransactionDTORequestCreate);
        return ResponseEntity.ok(cardTransactionService.createCardtransaction(cardTransactionDTORequestCreate));
    }

    @Override
    public ResponseEntity<String> deleteCardtransaction(@PathVariable("registrationsNumberCard") String registrationsNumberCard) throws Exception {
        log.info("deleteCardtransaction: {0}", registrationsNumberCard);
        cardTransactionService.deleteCardtransaction(registrationsNumberCard);
        return ResponseEntity.ok("Transação do cartão cancelada com sucesso!");
    }

    @Override
    @ResponseStatus
    public ResponseEntity<String> extractCardtransaction(@DecimalMin("20") @PathVariable("registrationsNumberCard") String registrationsNumberCard) {
        log.info("extractCardtransaction: {0}", registrationsNumberCard);
        try {
            cardTransactionService.extractCardTransaction(registrationsNumberCard);
                return ResponseEntity.ok("Extrato de transações do cartão efetuado/enviado com sucesso!");
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("Error: {0}", e.getMessage());
            throw new InternError(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Error: {0}", e.getMessage());
            throw new InternError(e.getMessage());
        }
    }
}
