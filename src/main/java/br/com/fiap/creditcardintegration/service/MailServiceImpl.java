package br.com.fiap.creditcardintegration.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String from, String subject, String body, String toAddresses) {
        this.sendMail(from, subject, body, toAddresses, null, null);
    }

    public void sendMail(String from, String subject, String body, String toAddresses, String ccAddresses, String bccAddresses) {
        this.sendMail(from, subject, body, toAddresses, ccAddresses, bccAddresses, null);
    }

    public void sendMail(String from, String subject, String body, String toAddresses, String ccAddresses, String bccAddresses, String[] attachments) {
         try {
             MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
                message.setTo(toAddresses.split("[,;]"));
                message.setFrom(from, "Credit Card Integration");
                message.setSubject(subject);
                if (StringUtils.isNotBlank(ccAddresses))
                    message.setCc(ccAddresses.split("[;,]"));
                if (StringUtils.isNotBlank(bccAddresses))
                    message.setBcc(bccAddresses.split("[;,]"));
                message.setText(body, true);
            };
            mailSender.send(preparator);
            log.info("Email sent successfully To {},{} with Subject {}", toAddresses, ccAddresses, subject);
        }
         catch (Exception e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
