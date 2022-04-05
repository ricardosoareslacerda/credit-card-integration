package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
import br.com.fiap.creditcardintegration.dto.StudentCardDTORequestCreate;
import br.com.fiap.creditcardintegration.dto.StudentsCardsDTO;
import br.com.fiap.creditcardintegration.model.StudentCard;
import br.com.fiap.creditcardintegration.repository.StudentsCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsCardServiceImpl implements StudentsCardService {

    private final ObjectMapper objectMapper;
    private final StudentsCardRepository studentsCardRepository;

    @Override
    public StudentCardDTO createStudentCard(final StudentCardDTORequestCreate studentCardDTO) {
        final StudentCard studentCard = objectMapper.convertValue(studentCardDTO, StudentCard.class);
        studentCard.setRegistrationNumberCard(studentCardDTO.getRegistration().concat(studentCardDTO.getNumberCard()));
        studentCard.setCreatedAt(Long.toString(System.currentTimeMillis()));
        studentCard.setActive(true);

        final StudentCard savedStudentCard = studentsCardRepository.save(studentCard);
        return objectMapper.convertValue(savedStudentCard, StudentCardDTO.class);
    }

    @Override
    public void deleteStudentCard(final String registrationsNumberCard) {
        studentsCardRepository.deleteById(registrationsNumberCard);

        Optional<StudentCard> savedStudentCard = studentsCardRepository.findById(registrationsNumberCard);
        if (savedStudentCard.isPresent()) {
            savedStudentCard.get().setActive(false);
            savedStudentCard.get().setUpdatedAt(Long.toString(System.currentTimeMillis()));
        }

        final StudentCard updatedStudentCard = studentsCardRepository.save(savedStudentCard.get());
        if (updatedStudentCard.getActive().equals(true)) {
            throw new RuntimeException("StudentCard not deleted");
        }
    }

    @Override
    public StudentCardDTO getStudentCard(final String registrationsNumberCard) {
        final Optional<StudentCard> savedStudentCard = studentsCardRepository.findById(registrationsNumberCard);
        if (savedStudentCard.isPresent())
            return objectMapper.convertValue(savedStudentCard.get(), StudentCardDTO.class);
        return null;
    }

    @Override
    public StudentsCardsDTO listAllStudentCard(final String active) {
        final List<StudentCard> savedStudentCard = studentsCardRepository.findAllByActiveEquals(active);
        return objectMapper.convertValue(savedStudentCard, StudentsCardsDTO.class);
    }

    @Override
    public StudentCardDTO updateStudentCard(final String registrationsNumberCard, final StudentCardDTO studentCard) {
        Optional<StudentCard> savedStudentCard = studentsCardRepository.findById(registrationsNumberCard);
        if (savedStudentCard.isPresent()) {
            savedStudentCard.get().setRegistration(studentCard.getRegistration());
            savedStudentCard.get().setNumberCard(studentCard.getNumberCard());
            savedStudentCard.get().setMail(studentCard.getMail());
            savedStudentCard.get().setFullName(studentCard.getFullName());
            savedStudentCard.get().setActive(studentCard.getActive());
            savedStudentCard.get().setUpdatedAt(Long.toString(System.currentTimeMillis()));
        }

        final StudentCard updatedStudentCard = studentsCardRepository.save(savedStudentCard.get());
        return objectMapper.convertValue(updatedStudentCard, StudentCardDTO.class);
    }
}
