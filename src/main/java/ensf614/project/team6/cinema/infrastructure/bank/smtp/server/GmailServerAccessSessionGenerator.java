package ensf614.project.team6.cinema.infrastructure.bank.smtp.server;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class GmailServerAccessSessionGenerator implements ServerAccessSessionGenerator {

  protected static final String HOST_LABEL = "mail.smtp.host";
  protected static final String PORT_LABEL = "mail.smtp.port";
  protected static final String AUTH_REQUIRED_LABEL = "mail.smtp.auth";
  protected static final String SOCKET_FACTORY_PORT_LABEL = "mail.smtp.socketFactory.port";
  protected static final String SOCKET_FACTORY_CLASS_LABEL = "mail.smtp.socketFactory.class";

  protected static final String HOST = "smtp.gmail.com";
  protected static final String PORT = "465";
  protected static final String AUTH_REQUIRED = "true";
  protected static final String SOCKET_FACTORY_PORT = PORT;
  protected static final String SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";

  protected static final String SOURCE_USERNAME = "evulution.equipe1@gmail.com";
  protected static final String SOURCE_PASSWORD = "!ENSF-614?team6?project!";

  @Override
  public Session generateSession() {
    Properties prop = new Properties();
    prop.put(HOST_LABEL, HOST);
    prop.put(PORT_LABEL, PORT);
    prop.put(AUTH_REQUIRED_LABEL, AUTH_REQUIRED);
    prop.put(SOCKET_FACTORY_PORT_LABEL, SOCKET_FACTORY_PORT);
    prop.put(SOCKET_FACTORY_CLASS_LABEL, SOCKET_FACTORY_CLASS);

    return Session.getInstance(
      prop,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(SOURCE_USERNAME, SOURCE_PASSWORD);
        }
      }
    );
  }
}
