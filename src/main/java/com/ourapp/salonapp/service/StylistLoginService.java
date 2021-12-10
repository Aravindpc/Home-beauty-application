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
import com.ourapp.salonapp.dto.Stylistdto;
import com.ourapp.salonapp.entity.Role;
import com.ourapp.salonapp.entity.Stylist;
import com.ourapp.salonapp.repository.StylistRepository;

@Service
public class StylistLoginService implements UserDetailsService{

	    @Autowired
        private Environment env;
	    
	    @Autowired
	    private StylistRepository stylistRepository;
	   	   
	    @Autowired
		private BCryptPasswordEncoder passwordEncoder;
	    
	    public String createStylist(Stylistdto stylistdto) throws UserRegisteredException{
	    	
	           if(stylistRepository.findByEmail(stylistdto.getEmail())!=null)
	           {
	        	throw new UserRegisteredException(env.getProperty("exception.alreadyregistered"));
	           }
	            Stylist newstylist = new Stylist();
	            newstylist.setPassword(passwordEncoder.encode(stylistdto.getPassword()));
	            newstylist.setName(stylistdto.getName());
	            newstylist.setEmail(stylistdto.getEmail());
	            newstylist.setNumber(stylistdto.getNumber());
	            Set<Role> roles=new HashSet<Role>();
	            roles.add(new Role("ROLE_STYLIST"));
	            newstylist.setRoles(((roles)));
	            stylistRepository.save(newstylist);
	            return "Registered";
	    }

	    @Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Stylist stylist = stylistRepository.findByEmail(username);
			if(stylist == null) {
				throw new UsernameNotFoundException(env.getProperty("exception.invaliduser"));
			}
			boolean enabled =true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
			return new org.springframework.security.core.userdetails.User(stylist.getEmail(), stylist.getPassword(), enabled, accountNonExpired,
			          credentialsNonExpired, accountNonLocked, mapRolesToAuthorities(stylist.getRoles()));		
		}
		
		private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
			return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		}
}
