package ensf614.project.team6.cinema.infrastructure.bank.smtp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class SMTPEmailSenderThread extends Thread {

    private static final String FROM_EMAIL = "ensf614-team6@gmail.com";
    private static final String FROM_NAME = "ENSF 614 Term Project Team 6 Payment";
    private static final String SUBJECT = "Payment ENSF614";

    private final String email;
    private final String message;
    private final Session mailSession;

    public SMTPEmailSenderThread(Session mailSession, String email, String message) {
        this.mailSession = mailSession;
        this.email = email;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            processEmail();
        } catch (MessagingException | UnsupportedEncodingException ignored) {
        }
    }

    private void processEmail() throws UnsupportedEncodingException, MessagingException {
        MimeMessage mailMessage = new MimeMessage(mailSession);
        mailMessage.setFrom(new InternetAddress(FROM_EMAIL, FROM_NAME));
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mailMessage.setSubject(SUBJECT);
        mailMessage.setText(message);
        Transport.send(mailMessage);
    }
}
