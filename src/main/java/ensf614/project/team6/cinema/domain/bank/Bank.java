package ensf614.project.team6.cinema.domain.bank;

import org.springframework.stereotype.Component;

@Component
public interface Bank {
    Payment processPayment(Double amount, String creditCardNumber);
    void cancelPayment(String referenceNumber);
}
