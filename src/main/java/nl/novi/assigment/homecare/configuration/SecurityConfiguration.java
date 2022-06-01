package nl.novi.assigment.homecare.configuration;

import nl.novi.assigment.homecare.filter.JwtRequestFilter;
import nl.novi.assigment.homecare.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity Deze aan?
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtService jwtService;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("camielvilla@homecare.nl")
                .password("{noop}AdminPassword")
                .roles("ADMIN")
                .and()
                .withUser("nurse@homecare.nl")
                .password("{noop}NursePassword")
                .roles("NURSE")
                .and()
                .withUser("patient@gmail.com")
                .password("{noop}PatientPassword")
                .roles("PATIENT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/inlog/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/nurses/**").hasAnyRole("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/home").permitAll()
                .and()
                .authorizeRequests().antMatchers( "/patients/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/wounds/**").hasAnyRole("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/wounds/**").hasAnyRole("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/wounds/**").hasAnyRole("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/wounds/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/woundphotos/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/woundphotos/**").hasAnyRole("NURSE", "PATIENT")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/woundphotos/**").hasRole("NURSE")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
