package com.ourapp.salonapp.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ourapp.salonapp.Exception.UserRegisteredException;
import com.ourapp.salonapp.dto.Customerdto;
import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.entity.Role;
import com.ourapp.salonapp.repository.CustomerRepository;

@Service
public class CustomerLoginService implements UserDetailsService{

	    @Autowired
        private Environment env;
	    
	    @Autowired
	    private CustomerRepository customerRepository;
	   	    
	    @Autowired
		private BCryptPasswordEncoder passwordEncoder;
	    
	    public String createCustomer(Customerdto customerdto) throws UserRegisteredException{
	    	
	           if(customerRepository.findByEmail(customerdto.getEmail())!=null)
	           {
	        	throw new UserRegisteredException(env.getProperty("exception.alreadyregistered"));
	           }
	            Customer newcustomer = new Customer();
	            newcustomer.setPassword(passwordEncoder.encode(customerdto.getPassword()));
	            newcustomer.setName(customerdto.getName());
	            newcustomer.setAddress(customerdto.getAddress());
	            newcustomer.setEmail(customerdto.getEmail());
	            newcustomer.setNumber(customerdto.getNumber());
	            Set<Role> roles=new HashSet<Role>();
	            roles.add(new Role("ROLE_USER"));
	            newcustomer.setRoles(((roles)));
	            customerRepository.save(newcustomer);
	            return "Registered";
	    }
	    
	    @Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Customer customer = customerRepository.findByEmail(username);
			if(customer == null) {
				throw new UsernameNotFoundException(env.getProperty("exception.invaliduser"));
			}
			return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), mapRolesToAuthorities(customer.getRoles()));		
		}
		
		private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
			return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		}
}
