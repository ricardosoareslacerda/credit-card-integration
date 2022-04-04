package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.dto.StudentCardDTO;
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
    public StudentCardDTO createStudentCard(final StudentCardDTO studentCardDTO) {
        final StudentCard studentCard = objectMapper.convertValue(studentCardDTO, StudentCard.class);
        final StudentCard savedStudentCard = studentsCardRepository.save(studentCard);
        return objectMapper.convertValue(savedStudentCard, StudentCardDTO.class);
    }

    @Override
    public void deleteStudentCard(final String registrationsNumberCard) {
        studentsCardRepository.deleteById(registrationsNumberCard);
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
            savedStudentCard.get().setActive(studentCard.getActive());
        }

        final StudentCard updatedStudentCard = studentsCardRepository.save(savedStudentCard.get());
        return objectMapper.convertValue(updatedStudentCard, StudentCardDTO.class);
    }
}
