package eu.glutfree.glutfree.config;

import eu.glutfree.glutfree.service.impl.GlutfreeDBUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final GlutfreeDBUserService glutfreeDBUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(GlutfreeDBUserService glutfreeDBUserService,
                                     PasswordEncoder passwordEncoder) {
        this.glutfreeDBUserService = glutfreeDBUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                cors().and().
                authorizeRequests().
                antMatchers("/js/**", "/css/**", "/img/**", "/img/categories/**").permitAll().
                antMatchers("/roles/delete", "/roles/add", "/food/add", "/food/delete/*", "/receipt/add", "/receipt/delete/*", "/feedback/add", "/feedback/delete/*", "/users/delete", "/users/delete/*").hasRole("ADMIN").
                antMatchers("/", "/users/login", "/blog", "/food/**", "/food/category/*", "/food/api", "/food/api-tested", "/food/", "/feedback/api", "/food/details/*", "/receipt/", "/receipt/api", "/receipt/**", "/feedback/", "/aboutus", "/store/anystore").permitAll().
                // public REST API endpoints
                antMatchers(org.springframework.http.HttpMethod.GET, "/food/api/**", "/food/api-tested", "/feedback/api/**", "/receipt/api/**", "/store/api/**").permitAll().
                antMatchers(org.springframework.http.HttpMethod.POST, "/users/api/register").permitAll().
                antMatchers("/**").authenticated().

                and().
                // configure login with HTML form
                        formLogin().
                // our login page will be served by the controller with mapping /users/login
                        loginPage("/users/login").
                // the name of the user name input field in OUR login form is username (optional)
                        usernameParameter("username").
                // the name of the user password input field in OUR login form is password (optional)
                        passwordParameter("password").
                // on login success redirect here
                        defaultSuccessUrl("/home").
                // on login failure redirect here
                        failureForwardUrl("/users/login-error").
                and().
                logout().
                // which endpoint performs logout, e.g. http://localhost:8080/logout (!this should be POST request)
                        logoutUrl("/logout").
                // where to land after logout
                        logoutSuccessUrl("/").
                // remove the session from the server
                        invalidateHttpSession(true).
                // delete the session cookie
                        deleteCookies("JSESSIONID");//bye! :-);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(glutfreeDBUserService).
                passwordEncoder(passwordEncoder);
    }
}