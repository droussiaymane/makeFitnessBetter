package com.makingfitnessbetter.makingfitnessbetter.security;

import com.makingfitnessbetter.makingfitnessbetter.exceptions.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                //ENABLE FOR REACT APPLICATION
                        cors().and()
                .csrf().disable().
                authorizeRequests()
                //All Permited

                //**** User (Permit All) *****
                .antMatchers(HttpMethod.POST, "/user/create").permitAll()
                .antMatchers(HttpMethod.GET, "/user/getUser").permitAll()
//                .antMatchers(HttpMethod.PUT, "/user/updateemail").permitAll()
//                .antMatchers(HttpMethod.PUT, "/user/updatepassword").permitAll()

                //**** User (Admin Only) *****
//                .antMatchers(HttpMethod.GET, "/user/all").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/user/find").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "user/delete").hasRole("ADMIN")


         //       .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Override
    protected CustomAuthenticationManager authenticationManager() throws Exception {
        return new CustomAuthenticationManager();
    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/login");
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return authenticationFilter;
    }
}

