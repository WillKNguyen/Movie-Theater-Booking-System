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

    private Boolean wasRefunded;

    private LocalDate creationDate;

    private LocalDate modificationDate;

    public Payment(Integer id, String creditCardNumber, Double amount) {
        this.id = id;
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;

        wasRefunded=false;

        LocalDate now=LocalDate.now();
        this.creationDate = now;
        this.modificationDate = now;
    }

    public Payment() {
    }

    public Boolean getWasRefunded() {
        return wasRefunded;
    }

    public void markAsRefunded(){
        if(wasRefunded) throw new PaymentAlreadyRefunded();
        wasRefunded=true;
        modificationDate=LocalDate.now();
    }
}
