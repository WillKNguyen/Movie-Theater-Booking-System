package ensf614.project.team6.cinema.api.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/registered/**")
                .authenticated()
                .antMatchers("/api/user/**")
                .permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}