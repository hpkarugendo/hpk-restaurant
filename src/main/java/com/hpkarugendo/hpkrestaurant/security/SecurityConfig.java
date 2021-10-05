package com.hpkarugendo.hpkrestaurant.security;

import com.hpkarugendo.hpkrestaurant.management.services.EmployeeService;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final EmployeeService uService;

    public SecurityConfig(EmployeeService uService) {
        this.uService = uService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/manage/**").hasAuthority("MANAGER")
                .antMatchers("/supervisor/**").hasAuthority("SUPERVISOR")
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/errors?error")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
