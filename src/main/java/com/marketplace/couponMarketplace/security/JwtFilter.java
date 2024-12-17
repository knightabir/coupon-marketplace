/**
 * This is a custom filter class that extends the OncePerRequestFilter class.
 * This class is responsible for validating the JWT token sent in the Authorization header.
 * If the token is valid, it sets the authentication in the SecurityContextHolder.
 * 
 * The class is annotated with @Component and @Slf4j.
 * 
 * The UserDetailsService is autowired in this class.
 * 
 * The doFilterInternal method is overridden to validate the JWT token.
 * It gets the Authorization header from the request.
 * It extracts the username from the JWT token.
 * It gets the UserDetails object from the UserDetailsService.
 * It validates the JWT token using the JwtUtil class.
 * If the token is valid, it sets the authentication in the SecurityContextHolder.
 * 
 * The class is written by Abir Sarkar.
 */
package com.marketplace.couponMarketplace.security;

import com.marketplace.couponMarketplace.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil; 
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                log.info("Requesting endpoint {} from {}", request.getRequestURI(), request.getRemoteAddr());
                String authorizationHeader = request.getHeader("Authorization");
                String username = null;
                String jwt = null;

                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    jwt = authorizationHeader.substring(7);
                    username = jwtUtil.extractUsername(jwt);
                    log.info("Extracted username: {}", username);
                }


                if (username != null){
                    log.info("Validating token for user: {}", username);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    log.info("User Details: {}", userDetails);
                    if (jwtUtil.validateToken(jwt)){
                        log.info("Token is valid");
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        log.info("Authenticated user: {}", username);
                    }else {
                        log.info("Invalid token");
                    }
                }
                filterChain.doFilter(request, response);
    }
}