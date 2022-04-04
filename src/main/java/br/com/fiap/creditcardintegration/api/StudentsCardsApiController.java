package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.api.response.StudentCardResponse;
import br.com.fiap.creditcardintegration.model.StudentCard;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.io.IOException;

@Api(value = "/v1")
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class StudentsCardsApiController implements StudentsCardsApi {

    private static final Logger log = LoggerFactory.getLogger(StudentsCardsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    /*public StudentsCardsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }*/

    public ResponseEntity<StudentCardResponse> createStudentCard(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody StudentCard body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<StudentCardResponse>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"id\" : \"id\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCardResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<StudentCardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<StudentCardResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteStudentCard(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<StudentCardResponse> getStudentCard(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<StudentCardResponse>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"id\" : \"id\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCardResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<StudentCardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<StudentCardResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<StudentCard> listAllStudentCard(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "ative", required = false) String ative) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<StudentCard>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCard.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<StudentCard>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<StudentCard>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<StudentCardResponse> updatStudentCard(@DecimalMin("20")@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody StudentCard body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<StudentCardResponse>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"id\" : \"id\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCardResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<StudentCardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<StudentCardResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
