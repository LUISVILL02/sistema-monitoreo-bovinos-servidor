package org.server.apimonitoreo.security.config;

import lombok.RequiredArgsConstructor;
import org.server.apimonitoreo.security.filter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebConfigSecurity {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private  final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/Api-Monitoreo/V1.0.0/bovinos/**").permitAll()
                                .requestMatchers("/Api-Monitoreo/V1.0.0/auth/**").permitAll()
                                .requestMatchers("/Api-Monitoreo/V1.0.0/sensor/**").permitAll()
                                .requestMatchers("/Api-Monitoreo/V1.0.0/potrero/**").permitAll()
                                .requestMatchers("/Api-Monitoreo/V1.0.0/finca/**").permitAll()
                                .requestMatchers("/ws-datos/**").permitAll()
                                .requestMatchers("/datos/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
