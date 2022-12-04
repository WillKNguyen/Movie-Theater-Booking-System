package ensf614.project.team6.cinema.infrastructure.bank.smtp;


import ensf614.project.team6.cinema.infrastructure.bank.smtp.server.ServerAccessSessionGenerator;

import javax.mail.Session;

public class SMTPEmailSender {

    private final Session mailSession;

    public SMTPEmailSender(ServerAccessSessionGenerator serverAccessSessionGenerator) {
        this.mailSession = serverAccessSessionGenerator.generateSession();
    }

    public void sendMessage(String email, String message) {
        SMTPEmailSenderThread smtpEmailSenderThread = new SMTPEmailSenderThread(mailSession, email, message);
        smtpEmailSenderThread.start();
    }
}
