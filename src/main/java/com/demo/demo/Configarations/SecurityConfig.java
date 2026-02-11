package com.demo.demo.Configarations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.demo.Utils.JWTAuthFilter;

@Configuration
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;

    public SecurityConfig(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/api/saveUser").hasRole("ADMIN")
                        .requestMatchers("/api/createMovie/**", "/api/getMovie/**", "/api/getAllMovies/**")
                        .hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This is for basic auth testing by using in-memoryuserdetailsmanager
    // If you want JWT login with DB users, remove this method. Otherwise Spring
    // will still use the in‑memory user instead of your DB.

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    // {
    // return new InMemoryUserDetailsManager(User.withUsername("admin")
    // .password(passwordEncoder.encode("admin@123"))
    // .roles("USER")
    // .build());
    // }
}
