package ensf614.project.team6.cinema.infrastructure.bank;

import ensf614.project.team6.cinema.domain.bank.Bank;
import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.bank.exceptions.BankTransactionNotFound;
import ensf614.project.team6.cinema.domain.bank.exceptions.InvalidCreditCardNumber;
import ensf614.project.team6.cinema.domain.bank.exceptions.PaymentAlreadyRefunded;
import ensf614.project.team6.cinema.infrastructure.bank.smtp.SMTPEmailSender;
import ensf614.project.team6.cinema.infrastructure.bank.smtp.server.GmailServerAccessSessionGenerator;
import ensf614.project.team6.cinema.infrastructure.bank.smtp.server.ServerAccessSessionGenerator;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class BankDummy implements Bank {

    private final SMTPEmailSender smtpEmailSender;

    public BankDummy() {
        ServerAccessSessionGenerator sessionGenerator = new GmailServerAccessSessionGenerator();
        smtpEmailSender=new SMTPEmailSender(sessionGenerator);
    }

    @Override
    public Payment processPayment(Double amount, String creditCardNumber, String email) {
        validateCreditCard(creditCardNumber);

        System.out.println("Payment processed");

        smtpEmailSender.sendMessage(email,"Your payment made to CINEMA-ENSF614-TEAM6 for "+amount+"$ was processed successfully");

        return new Payment(creditCardNumber,amount);
    }

    @Override
    public Payment cancelPayment(Payment paymentToRefund, String email) {
        validatePayment(paymentToRefund);

        paymentToRefund.markAsRefunded();

        System.out.println("Payment refunded");

        smtpEmailSender.sendMessage(email,"Your payment made to CINEMA-ENSF614-TEAM6 for "+paymentToRefund.getAmount()+"$ was refunded successfully");
        return paymentToRefund;
    }

    private void validateCreditCard(String creditCardNumber){
        if(creditCardNumber.isBlank()) throw new InvalidCreditCardNumber();
    }

    private void validatePayment(Payment payment){
        if(payment.getWasRefunded()) throw new PaymentAlreadyRefunded();
    }
}
