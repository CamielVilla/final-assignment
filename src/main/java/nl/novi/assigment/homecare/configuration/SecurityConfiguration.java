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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

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
                .authorizeRequests().antMatchers("/admin/patients/").hasAnyAuthority("ADMIN", "NURSE")
                .and()
                .authorizeRequests().antMatchers("/admin/addpatient").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/admin/addnurse").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/admin/{id}/addwound").hasAnyAuthority("ADMIN", "NURSE")
                .and()
                .authorizeRequests().antMatchers("/admin/patients").hasAnyAuthority("ADMIN", "NURSE")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
