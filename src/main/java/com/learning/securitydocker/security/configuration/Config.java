package com.learning.securitydocker.security.configuration;

import com.learning.securitydocker.jwtauth.JwtConfig;
import com.learning.securitydocker.jwtauth.JwtTokenVerifier;
import com.learning.securitydocker.jwtauth.JwtUserCredentialsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    @Qualifier("FromDatabase")
    private UserDetailsService userEntityService;

    public Config(@Autowired JwtConfig jwtConfig, @Autowired SecretKey secretKey){
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUserCredentialsFilter(jwtConfig, secretKey, authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUserCredentialsFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasAuthority("WRITE")
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userEntityService);
    }
}
