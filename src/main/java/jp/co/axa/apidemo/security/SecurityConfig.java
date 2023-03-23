package jp.co.axa.apidemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures HTTP security settings.
     *
     * @param http the HttpSecurity object to configure
     * @throws Exception if an error occurs while configuring HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Allow public access to retrieve all employees
                .antMatchers("/api/v1/employees").permitAll()
                // Require authentication for all other endpoints
                .antMatchers("/api/v1/employees/**").authenticated()
                // Allow public access to H2 console
                .antMatchers("/h2-console/**").permitAll()
                .and()
                // Disable CSRF protection and X-Frame-Options for H2 console
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // Use HTTP Basic authentication
                .httpBasic();
    }

    /**
     * Configures in-memory authentication for a single user with ADMIN role.
     *
     * @param auth the AuthenticationManagerBuilder object to configure
     * @throws Exception if an error occurs while configuring AuthenticationManagerBuilder
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                // Define a single user with username "admin" and password "admin12345"
                .withUser("admin").password("{noop}admin12345").roles("ADMIN");
    }
}
