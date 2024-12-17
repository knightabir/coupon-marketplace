package com.marketplace.couponMarketplace.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.logging.Logger;

/**
 * Security configuration class for defining the security filter chain.
 * <p>
 * This class defines the security filter chain and authorization rules for the application.
 * It configures the security filter chain to authorize requests based on the roles of the user.
 * It also sets the session management policy to STATELESS.
 * <p>
 * The class also provides a reference to the AuthenticationManager.
 * <p>
 * The class is annotated with @Configuration and @EnableWebSecurity.
 *
 * @author Abir Sarkar
 */
@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    public static final Logger log = Logger.getLogger(SecurityFilterChainConfig.class.getName());

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring Security Filter Chain");
        return http.authorizeHttpRequests(request -> request

                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/seller/**").hasRole("SELLER")
                        .requestMatchers("/api/v1/buyer/**").hasRole("BUYER")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        log.info("Retrieving Authentication Manager");
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Creating BCrypt Password Encoder");
        return new BCryptPasswordEncoder();
    }
}