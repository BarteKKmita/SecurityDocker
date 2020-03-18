package com.learning.securitydocker.security.configuration;

import com.learning.securitydocker.user.DummyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {
    @Autowired
    private DummyUserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasAuthority("WRITE")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/login-success", true)
                .failureUrl("/login-failure")
                .passwordParameter("password")
                .usernameParameter("username");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(repository.getUsers().stream()
                .map(userEntity -> User.builder()
                        .username(userEntity.getName() + " " + userEntity.getSurname())
                        .password(passwordEncoder.encode(String.valueOf(userEntity.getPassword())))
                        .authorities(userEntity.getRole().getRolePermissions())
                        .build())
                .collect(Collectors.toList()));
    }
}
