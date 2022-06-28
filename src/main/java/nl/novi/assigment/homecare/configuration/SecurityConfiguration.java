package nl.novi.assigment.homecare.configuration;

import nl.novi.assigment.homecare.filter.JwtRequestFilter;
import nl.novi.assigment.homecare.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.sql.DataSource;
import java.util.Arrays;


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
                .usersByUsernameQuery("select email, password, enabled from users where email=?")
                .authoritiesByUsernameQuery("select email, role from users where email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/users/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/wounds/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/woundexaminations/**").permitAll()
                .and()
                .authorizeRequests().antMatchers( "/admin/addwound/**").hasAnyAuthority("ADMIN", "NURSE")
                .and()
                .authorizeRequests().antMatchers("/admin/patients/").hasAnyAuthority("ADMIN", "NURSE")
                .and()
                .authorizeRequests().antMatchers("/admin/addpatient").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/admin/addnurse").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/admin/nurses").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers( HttpMethod.GET, "/wounds/toassess").hasAuthority("NURSE")
                .and()
                .authorizeRequests().antMatchers( HttpMethod.POST, "/wounds/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/wounds/**").hasAnyAuthority("ADMIN", "NURSE")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/download/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/patients/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
