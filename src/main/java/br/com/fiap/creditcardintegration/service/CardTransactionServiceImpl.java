package br.com.fiap.creditcardintegration.service;

import br.com.fiap.creditcardintegration.api.response.NotFoundException;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTO;
import br.com.fiap.creditcardintegration.dto.CardTransactionDTORequestCreate;
import br.com.fiap.creditcardintegration.model.CardTransaction;
import br.com.fiap.creditcardintegration.model.StudentCard;
import br.com.fiap.creditcardintegration.repository.CardTransactionRepository;
import br.com.fiap.creditcardintegration.repository.StudentsCardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class CardTransactionServiceImpl implements CardTransactionService {

    private static final String PATH_MAIL_EXTRACT_CARD_TRANSACTION = "classpath:templates/mail-extract-card-transaction.html";
    private static final String PATH_MAIL_EXTRACT_CARD_TRANSACTION_HEADER = "classpath:templates/mail-extract-card-transaction-header.html";
    private static final String PATH_MAIL_EXTRACT_CARD_TRANSACTION_BODY = "classpath:templates/mail-extract-card-transaction-body.html";
    private static final String PATH_MAIL_EXTRACT_CARD_TRANSACTION_FOOTER = "classpath:templates/mail-extract-card-transaction-footer.html";

    public static final String MAIL_EXTRACT_CARD_TRANSACTION_BODY = "{{#mail-extract-card-transaction-body}}";
    public static final String MAIL_EXTRACT_CARD_TRANSACTION_HEADER = "{{#mail-extract-card-transaction-header}}";

    private enum MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM {
        REGISTRATION_NUMBERCARD("{{registrationsNumberCard}}"),
        ESTABLISHMENT_NAME("{{establishmentName}}"),
        VALUE("{{value}}"),
        CREATED_AT("{{createdAt}}"),
        UPDATED_AT("{{updatedAt}}"),
        INSTALLMENTS("{{installments}}"),
        STATUS("{{status}}");

        private String value;

        MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private StudentsCardRepository studentsCardRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private MailService mailService;

    @Override
    public CardTransactionDTO createCardtransaction(final CardTransactionDTORequestCreate dto) {
        try {
            this.getStudentCard(dto.getRegistrationNumberCard());

            CardTransaction cardTransaction = cardTransactionRepository.save(dto.toCardTransaction());
            return CardTransactionDTO.from(cardTransaction);
        }
        catch (Exception e) {
            throw new NotFoundException(1, "Transaction not found");
        }
    }

    private StudentCard getStudentCard(final String registrationsNumberCard) {
        return studentsCardRepository.findByRegistrationNumberCardEquals(registrationsNumberCard).orElseThrow(() -> new NotFoundException("StudentsCard not found"));
    }

    @Override
    public CardTransactionDTO deleteCardtransaction(final String registrationNumberCard) throws Exception{
        Optional<CardTransaction> savedCardTransaction = cardTransactionRepository.findByRegistrationNumberCardAndStatusEquals(registrationNumberCard, CardTransaction.Status.TRANSACTION_SUCCESS);
        AtomicReference<CardTransactionDTO> cardTransactionDTO = null;
        savedCardTransaction.ifPresentOrElse(savedCard -> {

            savedCard.setUpdatedAt(Long.toString(System.currentTimeMillis()));
            savedCard.setStatus(CardTransaction.Status.TRANSACTION_FAILED);
            cardTransactionDTO.set(CardTransactionDTO.from(cardTransactionRepository.save(savedCard)));
        }, () -> {
            throw new NotFoundException(1, "Transaction not found");
        });
        return cardTransactionDTO.get();
    }

    @Override
    public void extractCardTransaction(final String registrationsNumberCard) throws Exception {
        StudentCard studentCard = this.getStudentCard(registrationsNumberCard);

        final Optional<List<CardTransaction>> savedCardTransactions = cardTransactionRepository.findByRegistrationNumberCardEquals(studentCard.getRegistrationNumberCard());

        savedCardTransactions.ifPresentOrElse(cardTransactions -> {
            List<CardTransactionDTO> cardTransactionDTOS = cardTransactions.stream().map(CardTransactionDTO::from).collect(Collectors.toList());
            mailService.sendMail("ricardosoareslacerda@gmail.com", "Extrato", Normalizer.normalize(this.parseTemplateToStringMail(cardTransactionDTOS), Normalizer.Form.NFD), studentCard.getMail());
        },
        () -> new NotFoundException(1, "Transaction not found"));
    }

    private String parseTemplateToStringMail(List<CardTransactionDTO> dtos) {
        Resource resource = resourceLoader.getResource(PATH_MAIL_EXTRACT_CARD_TRANSACTION);
        try(InputStream mainInputStream = resource.getInputStream()) {
            String mainTemplate = this.parseMainToHeaderTemplate(mainInputStream, PATH_MAIL_EXTRACT_CARD_TRANSACTION_HEADER);
            mainTemplate = mainTemplate.replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY, this.parseMainToBodyTemplate(PATH_MAIL_EXTRACT_CARD_TRANSACTION_BODY, dtos));
            return mainTemplate;
        }
        catch (Exception e) {
            throw new NotFoundException(1, "Parse Main Template Error!");
        }
    }

    private String parseMainToHeaderTemplate(InputStream mainTemplateInputStream, final String pathTarget) throws IOException {
        String mainTemplate = IOUtils.toString(mainTemplateInputStream, Charset.defaultCharset());

        Resource resource = resourceLoader.getResource(pathTarget);
        try(InputStream inputStream = resource.getInputStream()) {
            mainTemplate = mainTemplate.replace(MAIL_EXTRACT_CARD_TRANSACTION_HEADER, IOUtils.toString(inputStream, Charset.defaultCharset()));
            return mainTemplate;
        }
        catch (Exception e) {
            throw new NotFoundException(1, "Parse Main Template Error!");
        }
    }

    private String parseMainToBodyTemplate(final String pathTarget, final List<CardTransactionDTO> dtos) {
        StringBuilder bodyTemplateItem = new StringBuilder();

        Resource resource = resourceLoader.getResource(pathTarget);
        try(InputStream inputStream = resource.getInputStream()) {
            final String bodyTemplate = IOUtils.toString(inputStream, Charset.defaultCharset());

            AtomicReference<String> bodyItemTemplate = new AtomicReference<>();

            dtos.forEach(dtoTransaction -> {
                bodyItemTemplate.set(bodyTemplate);
                bodyItemTemplate.set(bodyItemTemplate.get().replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM.REGISTRATION_NUMBERCARD.value, dtoTransaction.getRegistrationNumberCard()));
                bodyItemTemplate.set(bodyItemTemplate.get().replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM.ESTABLISHMENT_NAME.value, dtoTransaction.getEstablishmentName()));
                bodyItemTemplate.set(bodyItemTemplate.get().replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM.VALUE.value, dtoTransaction.getValue().toString()));
                bodyItemTemplate.set(bodyItemTemplate.get().replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM.UPDATED_AT.value, StringUtils.isNotBlank(dtoTransaction.getUpdatedAt()) ? dtoTransaction.getUpdatedAt() : dtoTransaction.getCreatedAt()));
                bodyItemTemplate.set(bodyItemTemplate.get().replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM.INSTALLMENTS.value, dtoTransaction.getInstallments().toString()));
                bodyItemTemplate.set(bodyItemTemplate.get().replace(MAIL_EXTRACT_CARD_TRANSACTION_BODY_ITEM.STATUS.value, dtoTransaction.getStatus()));
                bodyTemplateItem.append(bodyItemTemplate.get());
            });
        }
        catch (Exception e) {
            throw new NotFoundException(1, "Parse Main Template Error!");
        }
        return bodyTemplateItem.toString();
    }

    public void setCardTransactionRepository(CardTransactionRepository cardTransactionRepository) {
        this.cardTransactionRepository = cardTransactionRepository;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
