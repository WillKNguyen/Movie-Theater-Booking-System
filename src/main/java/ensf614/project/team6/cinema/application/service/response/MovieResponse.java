package ensf614.project.team6.cinema.application.service.response;

import ensf614.project.team6.cinema.domain.cinema.Movie;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class MovieResponse {
    private final String id;
    private final String title;

    public MovieResponse(Movie movie) {
        this.id = String.valueOf(movie.getId());
        this.title = movie.getTitle();
    }
}
