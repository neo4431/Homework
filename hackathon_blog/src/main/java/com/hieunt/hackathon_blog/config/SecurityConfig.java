package com.hieunt.hackathon_blog.config;

import com.hieunt.hackathon_blog.service.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomUserDetailService customUserDetailService;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
       http.authorizeRequests()
       .mvcMatchers("/admin/**").hasAuthority("admin")
    //    .mvcMatchers("/manager/").hasAnyAuthority("admin","editor","author")
       .mvcMatchers("/editor/**").hasAnyAuthority("admin","editor")
       .mvcMatchers("/posts/**").authenticated()
       .anyRequest().permitAll()
       .and().exceptionHandling().accessDeniedPage("/access-denied");
       http.formLogin()
       .loginPage("/login")
       .loginProcessingUrl("/perform_login")
       .defaultSuccessUrl("/index", true)
       .failureUrl("/login-error")
       .and()
       .logout()
       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
       .logoutSuccessUrl("/index");
    }
}
