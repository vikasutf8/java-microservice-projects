package com.paypal.user_service.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    private final JwtUtils jwtUtils;

    public JwtRequestFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain chain) throws  ServletException, IOException {
       final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            try{
                username = jwtUtils.extractUsername(jwt);
            }catch (Exception e){
                //log
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //username = null not authenticated
            if (jwtUtils.validateToken(jwt, username)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, null);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            if (jwt == null || jwt.isBlank()) {
                chain.doFilter(request, response);
                return; // skip processing if token empty
            }
            try {
                username = jwtUtils.extractUsername(jwt);
                // only extract role if JWT is valid and present
                String role = jwtUtils.extractRole(jwt);
                // use role for authorities as needed
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(new SimpleGrantedAuthority(role))
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //add authentication to security context
                SecurityContextHolder.getContext().setAuthentication(authToken);


                chain.doFilter(request, response);
            } catch (Exception e) {
                // log error if you want
                System.out.printf(e.getMessage(),"Error on jwtRequestFilter");
            }
        } else {
            chain.doFilter(request, response);
            return;
        }

    }


}
