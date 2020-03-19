package com.learning.securitydocker.jwtauth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtTokenVerifier(@Autowired JwtConfig jwtConfig, @Autowired SecretKey secretKey){
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        var token = getToken(request);
        try {
            Claims jwsPayload = getClaims(token);
            var username = jwsPayload.getSubject();
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, getAuthorities(jwsPayload));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (JwtException e) {
            throw new IllegalStateException("Token cannot be trusted" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        return authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token).getBody();
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Claims body){
        try {
            var auth = (List<Map<String, String>>) body.get("authorities");
            return auth.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                    .collect(Collectors.toSet());
        } catch (ClassCastException e) {
            throw new IllegalStateException("Invalid Jwt token", e);
        }
    }
}
