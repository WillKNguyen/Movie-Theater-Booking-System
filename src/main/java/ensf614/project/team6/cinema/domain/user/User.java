package ensf614.project.team6.cinema.domain.user;

import ensf614.project.team6.cinema.domain.bank.Payment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String creditCardNumber;
    private LocalDate endOfSubscription;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToMany
    private List<Payment> membershipPayments;

    public User(String name, String email, String password, String creditCardNumber, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.creditCardNumber = creditCardNumber;
        this.roles = new ArrayList<>(roles);

        this.membershipPayments = new ArrayList<>();

        this.endOfSubscription=LocalDate.of(2000,1,1);
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public LocalDate getEndOfSubscription() {
        return endOfSubscription;
    }

    public void setEndOfSubscription(LocalDate endOfSubscription) {
        this.endOfSubscription=endOfSubscription;
    }
    public List<Role> getRoles() {
        return roles;
    }

    public void addMembershipPayment(Payment payment){
        membershipPayments.add(payment);
    }
}
