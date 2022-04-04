package br.com.fiap.creditcardintegration.api;

import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
import br.com.fiap.creditcardintegration.dto.StudentCardDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.StudentsCardsDTO;
import br.com.fiap.creditcardintegration.service.StudentsCardService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

@RestController
@RequiredArgsConstructor
public class StudentsCardsApiController implements StudentsCardsApi {

    private static final Logger log = LoggerFactory.getLogger(StudentsCardsApiController.class);

    private final StudentsCardService studentsCardService;

    public ResponseEntity<StudentCardDTO> createStudentCard(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @RequestBody StudentCardDTORequestCreate studentCardDTORequestCreate) {

        return new ResponseEntity<StudentCardDTO>(studentsCardService.createStudentCard(studentCardDTORequestCreate), HttpStatus.OK);
        //return new ResponseEntity<StudentCardDTO>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"id\" : \"id\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCardDTO.class), HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteStudentCard(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) {
        studentsCardService.deleteStudentCard(registrationsNumberCard);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<StudentCardDTO> getStudentCard(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) {

        //return new ResponseEntity<StudentCardDTO>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"id\" : \"id\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCardDTO.class), HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<StudentCardDTO>(studentsCardService.getStudentCard(registrationsNumberCard), HttpStatus.OK);
    }

    public ResponseEntity<StudentsCardsDTO> listAllStudentCard(@Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "active", required = false) String active) {

        //return new ResponseEntity<StudentsCards>((StudentsCards) objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentsCards.class), HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<StudentsCardsDTO>(studentsCardService.listAllStudentCard(active), HttpStatus.OK);
    }

    public ResponseEntity<StudentCardDTO> updatStudentCard(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody StudentCardDTO studentCardDTO) {
        //return new ResponseEntity<StudentCardDTO>(objectMapper.readValue("{\n  \"createdAt\" : \"createdAt\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"mail\" : \"mail\",\n  \"fullName\" : \"fullName\",\n  \"active\" : true,\n  \"registration\" : \"registration\",\n  \"registrationsNumberCard\" : \"registrationsNumberCard\",\n  \"updatedAt\" : \"updatedAt\"\n}", StudentCardDTO.class), HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<StudentCardDTO>(studentsCardService.updateStudentCard(registrationsNumberCard, studentCardDTO), HttpStatus.OK);
    }
}
