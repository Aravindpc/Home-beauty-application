package com.ourapp.salonapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ourapp.salonapp.service.StylistLoginService;

@Configuration
@Order(1)
public class StylistSecurityConfiguration extends WebSecurityConfigurerAdapter {

	    @Autowired
      	private BCryptPasswordEncoder passwordEncoder;
    	@Autowired
		private StylistLoginService stylistLoginService;
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(stylistLoginService).passwordEncoder(passwordEncoder);
		}

        protected void configure(HttpSecurity http) throws Exception {
        	http.csrf().disable();
        	http.antMatcher("/stylist/**").authorizeRequests()
        	.antMatchers("/stylist/**").access("hasRole('ROLE_STYLIST')")                            
        	.antMatchers(
            		"/stylistRegistration",
            		"/stylistRegistration/stylistRegister",
	                "/js/**",
	                "/css/**",
	                "/img/**").permitAll()
        	.anyRequest().authenticated()
        	.and()
       		.formLogin()
               .loginPage("/stylist/stylistLogin")
               .loginProcessingUrl("/stylist/loginSecure")
               .defaultSuccessUrl("/stylist/stylistprofile", true)
               .permitAll()
               .and()
           .logout()
           .invalidateHttpSession(true)
   		.clearAuthentication(true)
   		.logoutRequestMatcher(new AntPathRequestMatcher("/stylist/logout"))
   		.logoutSuccessUrl("/stylist/stylistLogin?logout");
        }
}