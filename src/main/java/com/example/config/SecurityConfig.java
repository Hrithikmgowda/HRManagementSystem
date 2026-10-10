package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


@Bean
public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

    UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("Admin@123"))
            .roles("ADMIN")
            .build();

    UserDetails hr = User.builder()
            .username("hr")
            .password(passwordEncoder.encode("Hr@123"))
            .roles("HR")
            .build();

    UserDetails employee = User.builder()
            .username("employee")
            .password(passwordEncoder.encode("Employee@123"))
            .roles("EMPLOYEE")
            .build();

    return new InMemoryUserDetailsManager(admin, hr, employee);
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",
                "/actuator/health"
            ).permitAll()

            .requestMatchers("/api/users/**", "/api/roles/**")
                .hasRole("ADMIN")

            .requestMatchers(HttpMethod.DELETE, "/api/**")
                .hasAnyRole("ADMIN", "HR")

            .requestMatchers(HttpMethod.POST, "/api/leave-requests/**")
                .hasAnyRole("ADMIN", "HR", "EMPLOYEE")

            .requestMatchers(HttpMethod.PATCH, "/api/leave-requests/**")
                .hasAnyRole("ADMIN", "HR")

            .requestMatchers("/api/**")
                .hasAnyRole("ADMIN", "HR", "EMPLOYEE")

            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());

    return http.build();
}


}
