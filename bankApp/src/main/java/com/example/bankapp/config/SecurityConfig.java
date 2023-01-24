package com.example.bankapp.config;

import com.example.bankapp.filter.AuthoritiesLoggingAfterFilter;
import com.example.bankapp.filter.AuthoritiesLoggingAtFilter;
import com.example.bankapp.filter.CsrfCookieFilter;
import com.example.bankapp.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import static java.util.List.of;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(of("http://localhost:4200"));
                    config.setAllowedMethods(of("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(of("*"));
                    config.setMaxAge(3600L);
                    return config;
                }).and()
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/myLoans").hasRole("USER")
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/contact", "/notices", "/register").permitAll();
        http.formLogin();
        http.httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
