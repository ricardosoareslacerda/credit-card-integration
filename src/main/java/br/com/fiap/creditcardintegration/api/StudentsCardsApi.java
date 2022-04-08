/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.33).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.api.response.*;
import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
import br.com.fiap.creditcardintegration.dto.StudentCardDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.StudentsCardsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

public interface StudentsCardsApi {

    @Operation(summary = "Cria um cartão de estudante", description = "Cria um novo cartão de crédito para o estudante", tags={ "students-cards" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Operação efetuada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentCardDTO.class))),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))) })
    @RequestMapping(value = "/students-cards",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<StudentCardDTO> createStudentCard(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody StudentCardDTORequestCreate studentCardDTORequestCreate);


    @Operation(summary = "Atualiza cartão de aluno", description = "Atualizia um  cartão de crédito o estudante", tags={ "students-cards" })
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentCardDTO.class))),
    @ApiResponse(responseCode = "400", description = "Requisição Inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvalidateError.class))),
    @ApiResponse(responseCode = "401", description = "Acesso não permitido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotPermitionError.class))),
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))),
    @ApiResponse(responseCode = "500", description = "Erro Interno", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternError.class))) })
    @RequestMapping(value = "/students-cards/{registrationsNumberCard}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<StudentCardDTO> updatStudentCard(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody StudentCardDTO studentCardDTO);


    @Operation(summary = "Delete um cartão de aluno", description = "Remoção lógica do cartão de crédito do estudante", tags={ "students-cards" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Operação efetuada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Transação não cancelada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))) })
    @RequestMapping(value = "/students-cards/{registrationsNumberCard}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity deleteStudentCard(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard);


    @Operation(summary = "Retorna uma cartão de aluno", description = "Retorna um cartão do estudante", tags={ "students-cards" })
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentCardDTO.class))),
    @ApiResponse(responseCode = "400", description = "Requisição Inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvalidateError.class))),
    @ApiResponse(responseCode = "401", description = "Acesso não permitido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotPermitionError.class))),
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))),
    @ApiResponse(responseCode = "500", description = "Erro Interno", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternError.class))) })
    @RequestMapping(value = "/students-cards/{registrationsNumberCard}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<StudentCardDTO> getStudentCard(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard);

    @Operation(summary = "Retorna todos os cartão", description = "Encontra todos os cartão de crédito de estudante, dada a informação no parâmetro", tags={ "students-cards" })
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentCardDTO.class))),
    @ApiResponse(responseCode = "400", description = "Requisição Inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvalidateError.class))),
    @ApiResponse(responseCode = "401", description = "Acesso não permitido", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotPermitionError.class))),
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundError.class))),
    @ApiResponse(responseCode = "500", description = "Erro Interno", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternError.class))) })
    @RequestMapping(value = "/students-cards",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<StudentsCardsDTO> findAllByActive(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "active", required = false) boolean active);
}

