package com.example.springsecuritycode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Amresh Tripathy
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    // User information
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("amresh")
                .password(encoder.encode("pwd1"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("akash")
                .password(encoder.encode("pwd2"))
                .roles("USER", "ADMIN", "HR")
                .build();

        System.out.println("Admin : " + admin);
        System.out.println("User : " + user);

        return new InMemoryUserDetailsManager(admin, user);
    }

    // Second bean : talks abound which all api we need to give security
    // And which all api should not be given security
    // By default all of them are given security

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home/welcome").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/home/**")
                .authenticated().and().formLogin().and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
