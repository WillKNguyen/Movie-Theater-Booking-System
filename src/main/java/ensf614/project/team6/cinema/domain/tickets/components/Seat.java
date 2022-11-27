package ensf614.project.team6.cinema.domain.tickets.components;

public class Seat {
    private final String id;
    private final String number;

    public Seat(String id, String number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
