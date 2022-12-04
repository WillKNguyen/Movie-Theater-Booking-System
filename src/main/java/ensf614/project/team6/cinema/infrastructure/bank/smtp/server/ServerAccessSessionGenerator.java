package ensf614.project.team6.cinema.infrastructure.bank.smtp.server;

import javax.mail.Session;

public interface ServerAccessSessionGenerator {
    Session generateSession();
}
