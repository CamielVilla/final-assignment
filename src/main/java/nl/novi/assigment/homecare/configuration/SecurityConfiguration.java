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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtService jwtService;

    @Autowired
    private DataSource dataSource;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Autowired
    protected void configure (AuthenticationManagerBuilder authentication) throws Exception{
        authentication
                .jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from nurse where email=?")
                .authoritiesByUsernameQuery("select email, role from nurse where email=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/inlog/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/nurses/**").hasAnyAuthority("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/home").permitAll()
                .and()
                .authorizeRequests().antMatchers( "/patients/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/wounds/**").hasAnyAuthority("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/wounds/**").hasAnyAuthority("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/wounds/**").hasAnyAuthority("NURSE", "ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/wounds/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/woundphotos/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/woundphotos/**").hasAnyAuthority("NURSE", "PATIENT")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/woundphotos/**").hasAuthority("NURSE")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
