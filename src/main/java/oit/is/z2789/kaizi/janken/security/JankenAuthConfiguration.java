package oit.is.z2789.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .formLogin(login -> login.permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken/**")
            .authenticated()
            .anyRequest()
            .permitAll());
    // .csrf(csrf -> csrf.ignoringRequestMatchers("/sample2*/**"));

    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user1 = User
        .withUsername("user1")
        .password("{bcrypt}$2y$05$7n2BtvvtN0h2Ukmcw4egTOxOFn713pLwXcB9IgEZ37oL6QrMk1sq.")
        .roles("USER")
        .build();
    UserDetails user2 = User
        .withUsername("user2")
        .password("{bcrypt}$2y$05$vsqrnqYgOAOzh/ArYt8jjua.yz8t0CA61ET.Gj0j8FPeuZdd7jrte")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user1, user2);
  }
}
