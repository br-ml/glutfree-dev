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
                authorizeRequests().
                // allow access to static resources to anyone
                        antMatchers("/js/**", "/css/**", "/img/**").permitAll().
                // allow access to index, user login and registration to anyone
                        antMatchers("/roles/delete","/roles/add","/food/add", "/food/delete/*", "/receipt/add","/receipt/delete/*", "/feedback/add", "/feedback/delete/*", "/users/delete", "/users/delete/*").hasRole("ADMIN").

                         antMatchers("/", "/users/login", "/users/register", "/blog", "/food/tested", "/food/api", "/food/api-tested", "/food/", "/food/details/*","/receipt/", "/feedback/", "/aboutus").permitAll().
                                        // protect all other pages
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