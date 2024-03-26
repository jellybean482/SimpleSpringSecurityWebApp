package com.jia.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class implements the WebMvcConfigurer, an Interface that defines callback methods to customize the Java-based
 * configuration for Spring MVC enabled via @EnableWebMvc.
 * 
 * @EnableWebMvc: Annotated configuration classes may implement this interface to be called back and given a chance to
 *                customize the default configuration.
 */
@EnableWebMvc
@EnableWebSecurity
@Configuration
@ComponentScan("com.jia.security.controller")
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("password")).roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable()).authorizeRequests(auth -> {
            auth.antMatchers("/user").hasRole("USER"); // only users with USER role can access this
            auth.antMatchers("/admin").hasRole("ADMIN"); // only users with ADMIN role can access this
            auth.antMatchers("/").permitAll(); // anyone can access this
        }).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
