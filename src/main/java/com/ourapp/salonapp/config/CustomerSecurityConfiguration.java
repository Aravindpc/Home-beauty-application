package com.ourapp.salonapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ourapp.salonapp.service.CustomerLoginService;

@Configuration
@Order(2)
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

		@Autowired
		private CustomerLoginService customerLoginService;
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customerLoginService).passwordEncoder(passwordEncoder);
		}

        @Override
    	protected void configure(HttpSecurity http) throws Exception {
    		 http.csrf().disable();
    		 
    		 http.antMatcher("/user/**").authorizeRequests()
   		     .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers(
                		"/registration/register/**",
    	                "/landing**",
    	                "/outservices**",
    	                "/registration**",
    	                "/js/**",
    	                "/css/**",
    	                "/img/**").permitAll()
                .anyRequest().authenticated()
        		.and()
        		.formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/loginSecure")
                .defaultSuccessUrl("/user/services", true)
                .permitAll()
                .usernameParameter("username").passwordParameter("password")
                .and()
            .logout()
            .invalidateHttpSession(true)
    		.clearAuthentication(true)
    		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
    		.logoutSuccessUrl("/user/login?logout")
                .permitAll();
    	}
}