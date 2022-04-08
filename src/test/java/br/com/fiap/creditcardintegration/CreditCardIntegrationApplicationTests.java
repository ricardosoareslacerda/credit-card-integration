package br.com.fiap.creditcardintegration;

import br.com.fiap.creditcardintegration.model.CardTransaction;
import br.com.fiap.creditcardintegration.repository.CardTransactionRepository;
import br.com.fiap.creditcardintegration.service.CardTransactionService;
import br.com.fiap.creditcardintegration.service.CardTransactionServiceImpl;
import br.com.fiap.creditcardintegration.service.MailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RequiredArgsConstructor
@AllArgsConstructor
class CreditCardIntegrationApplicationTests {

	@Autowired
	private CardTransactionServiceImpl cardTransactionService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private MailService mailService;

	@Mock
	private CardTransactionService cardTransactionServiceMock;

	@Mock
	private CardTransactionRepository cardTransactionRepositoryMock;

	@Mock
	private MailService mailServiceMock;

	@Before // executa antes de cada teste
	public void setup() {
		cardTransactionService.setCardTransactionRepository(cardTransactionRepositoryMock);
		cardTransactionService.setMailService(mailServiceMock);
		cardTransactionService.setResourceLoader(resourceLoader);
	}

	private Optional<List<CardTransaction>> getCardTransactions() {
		return Optional.of(List.of(getTransactions(), getTransactions(), getTransactions(), getTransactions(), getTransactions()));
	}

	private CardTransaction getTransactions() {
		return CardTransaction.builder().
				registrationNumberCard("12345679").
				establishmentName("Zé Delivery").
				value(new BigDecimal(42.00)).
				installments(new BigDecimal(1)).
				status(CardTransaction.Status.TRANSACTION_SUCCESS.name()).
				createdAt("08/04/2022").build();
	}

	@Test
	public void testSendoMailExtractTransactionm() throws Exception {

		cardTransactionService.setCardTransactionRepository(cardTransactionRepositoryMock);
		cardTransactionService.setMailService(mailService);
		cardTransactionService.setResourceLoader(resourceLoader);

		Mockito.when(cardTransactionRepositoryMock.findByRegistrationNumberCardEquals(Mockito.any())).thenReturn(this.getCardTransactions());

		cardTransactionService.extractCardTransaction("12345679");

		//verify(mailServiceMock, times(1)).sendMail(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
	}

}
