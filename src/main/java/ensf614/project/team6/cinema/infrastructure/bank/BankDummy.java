package ensf614.project.team6.cinema.infrastructure.bank;

import ensf614.project.team6.cinema.domain.bank.Bank;
import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.bank.exceptions.BankTransactionNotFound;
import ensf614.project.team6.cinema.domain.bank.exceptions.InvalidCreditCardNumber;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class BankDummy implements Bank {

    private final Set<String> processedPayment;

    public BankDummy() {
        this.processedPayment = new HashSet<>();
    }

    @Override
    public Payment processPayment(Double amount, String creditCardNumber) {
        validateCreditCard(creditCardNumber);

        String referenceNumber = "trans-"+amount+"$-"+(new Random().nextInt(Integer.MAX_VALUE - 100000000) + 100000000);

        processedPayment.add(referenceNumber);
        //TODO send email
        //TODO create the payment
        return referenceNumber;
    }

    @Override
    public void cancelPayment(String referenceNumber) {
        if(!processedPayment.contains(referenceNumber)) throw new BankTransactionNotFound();

        processedPayment.remove(referenceNumber);
    }

    private void validateCreditCard(String creditCardNumber){
        if(creditCardNumber.isBlank()) throw new InvalidCreditCardNumber();
    }
}
