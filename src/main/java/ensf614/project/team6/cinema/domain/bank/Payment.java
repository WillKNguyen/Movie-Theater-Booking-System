package ensf614.project.team6.cinema.domain.bank;

import ensf614.project.team6.cinema.domain.bank.exceptions.PaymentAlreadyRefunded;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String creditCardNumber;

    private Double amount;
    private String contactEmail;

    private Boolean wasRefunded;

    private LocalDate creationDate;

    private LocalDate modificationDate;

    public Payment(String creditCardNumber, Double amount, String contactEmail) {
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
        this.contactEmail = contactEmail;

        wasRefunded = false;

        LocalDate now = LocalDate.now();
        this.creationDate = now;
        this.modificationDate = now;
    }

    public Payment() {
    }

    public Boolean wasRefunded() {
        return wasRefunded;
    }

    public void markAsRefunded() {
        if (wasRefunded) throw new PaymentAlreadyRefunded();
        wasRefunded = true;
        modificationDate = LocalDate.now();
    }

    public Double getAmount() {
        return amount;
    }

    public String getContactEmail() {
        return contactEmail;
    }
}
