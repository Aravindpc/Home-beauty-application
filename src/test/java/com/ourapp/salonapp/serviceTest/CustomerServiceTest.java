package com.ourapp.salonapp.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ourapp.salonapp.Exception.UserRegisteredException;
import com.ourapp.salonapp.dto.Customerdto;
import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.service.CustomerLoginService;
import com.ourapp.salonapp.service.CustomerService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {
	 
	    @Autowired
        private CustomerLoginService customerLoginService;	
	    
	    @Autowired
        private CustomerService customerService;	
    
	    Customerdto customerdto = new Customerdto();
	    
	    @BeforeEach 
	    void init() {
	    	customerdto.setEmail("ravikumarr@gmail.com");
	        customerdto.setPassword("ravir2020");
	        customerdto.setName("Ravri");
	        customerdto.setNumber(32536871L);
	        customerdto.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
	    }
        @Test
	    public void testCreateCustomer(){
	        String output;
			try {
				   output = customerLoginService.createCustomer(customerdto);
	               assertThat(output).isEqualTo("Registered");
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
	        
	    }
        
        @Test
	    public void testfindByEmail(){
	        String output;
			try {
				   output = customerLoginService.createCustomer(customerdto); 
				   assertThat(output).isEqualTo("Registered");
				   Customer customer=customerService.findByEmail(customerdto.getEmail());
				   assertThat(customer.getAddress()).isEqualTo(customerdto.getAddress());  
				   assertThat(customer.getName()).isEqualTo(customerdto.getName());
				   assertThat(customer.getNumber()).isEqualTo(customerdto.getNumber());
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
			
	        
	    }
        @Test
	    public void testfindByNumber(){
	        String output;
			try {
				   output = customerLoginService.createCustomer(customerdto); 
				   assertThat(output).isEqualTo("Registered");
				   Customer customer=customerService.findByNumber(customerdto.getNumber());
				   assertThat(customer.getAddress()).isEqualTo(customerdto.getAddress());  
				   assertThat(customer.getName()).isEqualTo(customerdto.getName());
				   assertThat(customer.getEmail()).isEqualTo(customerdto.getEmail());
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
			
	        
	    }
        
}
