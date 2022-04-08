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

    @Override
    public ResponseEntity<StudentCardDTO> createStudentCard(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @RequestBody StudentCardDTORequestCreate studentCardDTORequestCreate) {
        log.info("Creating student card");
        return ResponseEntity.ok(studentsCardService.createStudentCard(studentCardDTORequestCreate));
    }

    @Override
    public ResponseEntity<StudentCardDTO> updatStudentCard(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody StudentCardDTO studentCardDTO) {
        log.info("Updating student card");
        return ResponseEntity.ok(studentsCardService.updateStudentCard(registrationsNumberCard, studentCardDTO));
    }

    @Override
    public ResponseEntity deleteStudentCard(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) {
        log.info("Deleting student card");
        studentsCardService.deleteStudentCard(registrationsNumberCard);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<StudentCardDTO> getStudentCard(@DecimalMin("20") @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("registrationsNumberCard") String registrationsNumberCard) {
        log.info("Getting student card");
        return ResponseEntity.ok(studentsCardService.getStudentCard(registrationsNumberCard));
    }

    @Override
    public ResponseEntity<StudentsCardsDTO> findAllByActive(@Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "active", required = false) boolean active) {
        return ResponseEntity.ok(studentsCardService.findAllByActive(active));
    }
}
