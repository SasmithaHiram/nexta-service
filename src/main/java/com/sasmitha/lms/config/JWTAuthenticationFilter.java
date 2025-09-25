package com.sasmitha.lms.config;

import com.sasmitha.lms.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private JWTUtil jwtUtil;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getSubjectFromToken(token);
                userRepository.findByEmail(email).ifPresent(user -> {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), null, Collections.singleton(authority));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } );
            }
        }
        filterChain.doFilter(request,response);
    }
}
