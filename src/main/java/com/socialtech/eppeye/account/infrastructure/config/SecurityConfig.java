package com.socialtech.eppeye.account.infrastructure.config;

import com.socialtech.eppeye.account.application.services.CustomUserDetailsService;
import com.socialtech.eppeye.account.domain.jwt.filter.TokenAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity //habilita la seguridad a nivel de aplicación
@EnableMethodSecurity //habilita la seguridad a nivel de método
public class SecurityConfig {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public SecurityConfig(
            TokenAuthenticationFilter tokenAuthenticationFilter,
            CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) -> {
            //envía el error 401 (unauthorized)
            log.error("[!] UNAUTHORIZED ERROR -> {}", authException.getMessage());
            response.sendError(401, authException.getMessage());
        }));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers(
                            "/swagger-ui/**",
                            "/api/v1/accounts/**",
                            "/api/v1/plans/**",
                            "/api/v1/roles/**",
                            "/v3/api-docs/**",
                            "/error",
                            "/favicon.ico"
                    )
                    .permitAll();

            authorize
                    .anyRequest()
                    .authenticated();
        });

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}