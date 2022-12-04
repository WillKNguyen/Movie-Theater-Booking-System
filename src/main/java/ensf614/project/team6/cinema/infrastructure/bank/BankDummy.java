package ensf614.project.team6.cinema.infrastructure.bank;

import ensf614.project.team6.cinema.domain.bank.Bank;
import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.bank.exceptions.InvalidCreditCardNumber;
import ensf614.project.team6.cinema.domain.bank.exceptions.PaymentAlreadyRefunded;
import ensf614.project.team6.cinema.infrastructure.bank.smtp.SMTPEmailSender;
import ensf614.project.team6.cinema.infrastructure.bank.smtp.server.GmailServerAccessSessionGenerator;
import ensf614.project.team6.cinema.infrastructure.bank.smtp.server.ServerAccessSessionGenerator;
import org.springframework.stereotype.Component;

@Component
public class BankDummy implements Bank {

    private final SMTPEmailSender smtpEmailSender;

    public BankDummy() {
        ServerAccessSessionGenerator sessionGenerator = new GmailServerAccessSessionGenerator();
        smtpEmailSender=new SMTPEmailSender(sessionGenerator);
    }

    @Override
    public Payment processPayment(Double amount, String creditCardNumber, String email, String description) {
        validateCreditCard(creditCardNumber);

        System.out.println("Payment processed: "+amount+"$");

        smtpEmailSender.sendMessage(email,"Your payment made to CINEMA-ENSF614-TEAM6 for "+amount+"$ was processed successfully.\n\n"+ description);

        return new Payment(creditCardNumber,amount, email);
    }

    @Override
    public Payment cancelPayment(Payment paymentToRefund, Double prcRefunded) {
        validatePayment(paymentToRefund);

        paymentToRefund.markAsRefunded();

        System.out.println("Payment refunded ("+prcRefunded+"%): " +paymentToRefund.getAmount()*prcRefunded/100+"$");

        smtpEmailSender.sendMessage(paymentToRefund.getContactEmail(),"Your payment made to CINEMA-ENSF614-TEAM6 for "+paymentToRefund.getAmount()*prcRefunded/100+"$ ("+prcRefunded+"%) was refunded successfully");
        return paymentToRefund;
    }

    private void validateCreditCard(String creditCardNumber){
        if(creditCardNumber.isBlank()) throw new InvalidCreditCardNumber();
    }

    private void validatePayment(Payment payment){
        if(payment.wasRefunded()) throw new PaymentAlreadyRefunded();
    }
}
