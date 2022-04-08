package br.com.fiap.creditcardintegration.service;

public interface MailService {

    public void sendMail(String from, String subject, String body, String toAddresses, String ccAddresses, String bccAddresses, String[] attachmentPath);

    public void sendMail(String from, String subject, String body, String toAddresses, String ccAddresses, String bccAddresses);

    public void sendMail(String from, String subject, String body, String toAddresses);
}
