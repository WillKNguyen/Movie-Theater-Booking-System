package ensf614.project.team6.cinema.application.service.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CredentialsRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
