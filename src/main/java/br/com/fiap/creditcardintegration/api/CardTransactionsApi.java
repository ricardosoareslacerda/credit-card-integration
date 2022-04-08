/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.33).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.api.response.InternError;
import br.com.fiap.creditcardintegration.api.response.InvalidateError;
import br.com.fiap.creditcardintegration.api.response.NotFoundError;
import br.com.fiap.creditcardintegration.api.response.NotPermitionError;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.CardTransactionsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

public interface CardTransactionsApi {

    /*
     * create transaction
     */
    @Operation(summary = "Cria uma transaçáo de cartão de crédito", description = "Este recurso cria uma transaçáo com cartão de crédito do estudante, sendo obrigatório o número de registro do cartão do crédito. status = (TRANSAÇÃO EFETUADA COM SUCESSO!.)", tags={ "card-transactions" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação efetuada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvalidateError.class))),
            @ApiResponse(responseCode = "401", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotPermitionError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))),
            @ApiResponse(responseCode = "500", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternError.class)))})
    @RequestMapping(value = "/card-transactions",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.POST)
    ResponseEntity<CardTransactionDTO> createCardtransaction(@Parameter(in = ParameterIn.DEFAULT, description = "Objeto exclusivo para criação de uma transação", schema=@Schema()) @Valid @RequestBody CardTransactionDTORequestCreate cardTransactionDTORequestCreate);

    /*
     * caxncel transaction
     */
    @Operation(summary = "Cancela uma transação do cartão de crédito", description = "Este recurso cancela uma transaçáo com cartão de crédito com Status = 'EFETUADA COM SUCESSO.' (Cancelamento lógico, o registro não será deletado da base!)", tags={ "card-transactions" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transação do cartão cancelada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Transação não cancelada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))) })
    @RequestMapping(value = "/card-transactions/{registrationsNumberCard}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.DELETE)
    ResponseEntity<String> deleteCardtransaction(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "A transação será cancelada através do número do cartão de crédito do estudante (registrationsNumberCard). Setada a data de atualização e o status = 'TRANSAÇÃO CANCELADA'", required=true, schema=@Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) throws Exception;

    /*
     * extract transactions by registrationsNumberCard
     */
    @Operation(summary = "Extrato da transação do cartão de crédito", description = "Este recurso envia um e-mail de extrato das transações do cartão de crédito", tags={ "card-transactions" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extrato de transações do cartão efetuado/enviado com sucesso!", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CardTransactionsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvalidateError.class))),
            @ApiResponse(responseCode = "401", description = "Acesso não permitido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotPermitionError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))),
            @ApiResponse(responseCode = "500", description = "Erro Interno", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternError.class))) })
    @RequestMapping(value = "/card-transactions/{registrationsNumberCard}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.GET)
    ResponseEntity<String> extractCardtransaction(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "Para emissão do extrato de cartão de crédito via e-mail é necessário o número do cartão de crédito do estudante (registrationsNumberCard)", required=true, schema=@Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard);

}

