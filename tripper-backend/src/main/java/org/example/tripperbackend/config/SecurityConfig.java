package org.example.tripperbackend.config;

import org.example.tripperbackend.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allow public access to authentication endpoints
                        .requestMatchers("/api/trips", "/api/trips/{tripId}", "/api/trips/owner/{userId}").permitAll() // Explicitly allow public access to these trips endpoints
                        .requestMatchers("/api/trips/**").authenticated() // Require authentication for other trips endpoints
                        .requestMatchers("/api/user/**").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session; session won't be used to store user's state.
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable) // Disable HTTP Basic Authentication
                .formLogin(AbstractHttpConfigurer::disable);  // Disable form login

        /*
            - CSRF Disabled by Default:
                In a REST API where you authenticate primarily with tokens (like JWT), it's common to disable CSRF protection because the token itself is secure enough.
                If CSRF protection is needed, configure it specifically for your session cookies or other scenarios where it is necessary.

            - HTTP Basic and Form Login Disabled:
                These are disabled because you're planning to use JWT for authentication, which does not require server-side session management or form-based authentication.
         */

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
