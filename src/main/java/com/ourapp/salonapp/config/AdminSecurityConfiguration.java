package com.ourapp.salonapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(3)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin@gmail.com").password(passwordEncoder.encode("admin")).roles("ADMIN");
	}
        @Override
    	protected void configure(HttpSecurity http) throws Exception {
    		 http.csrf().disable();
    		 
    		 http.antMatcher("/admin/**").authorizeRequests()
   		     .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/stylistRegistration",
                		"/stylistRegistration/stylistRegister",
                		"/registration/register/**",
    	                "/landing**",
    	                "/outservices**",
    	                "/registration**","/js/**",
    	                "/css/**",
    	                "/img/**").permitAll()
                .anyRequest().authenticated()
        		.and()
        		.formLogin()
                .loginPage("/admin/adminLogin")
                .loginProcessingUrl("/admin/loginSecure")
                .defaultSuccessUrl("/admin/adminassignedwork", true)
                .permitAll()
                .usernameParameter("username").passwordParameter("password")
                .and()
            .logout()
            .invalidateHttpSession(true)
    		.clearAuthentication(true)
    		.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
    		.logoutSuccessUrl("/admin/adminLogin?logout")
                .permitAll();
    	}
}