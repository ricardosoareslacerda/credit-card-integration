package br.com.fiap.creditcardintegration;

import br.com.fiap.creditcardintegration.config.LocalDateConverter;
import br.com.fiap.creditcardintegration.config.LocalDateTimeConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "br.com.fiap.creditcardintegration", "br.com.fiap.creditcardintegration.api" , "br.com.fiap.creditcardintegration.config"})
public class CreditCardIntegrationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplication(CreditCardIntegrationApplication.class).run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		if (arg0.length > 0 && arg0[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

	@Configuration
	static class CustomDateConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addFormatters(FormatterRegistry registry) {
			registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
			registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		}
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}


}
