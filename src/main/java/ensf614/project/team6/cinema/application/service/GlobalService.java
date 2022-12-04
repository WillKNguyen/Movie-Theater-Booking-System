package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.domain.bank.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class GlobalService {
    @Autowired
    private Bank bank;

    protected Bank getBank(){
        return bank;
    }
}
