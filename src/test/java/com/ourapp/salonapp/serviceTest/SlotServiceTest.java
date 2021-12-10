package com.ourapp.salonapp.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ourapp.salonapp.Exception.UserRegisteredException;
import com.ourapp.salonapp.dto.Customerdto;
import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.service.CustomerLoginService;
import com.ourapp.salonapp.service.CustomerService;
import com.ourapp.salonapp.service.SlotService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SlotServiceTest {
	 
	    @Autowired
        private SlotService slotService;
	    @Autowired
        private CustomerService customerService;	
	    @Autowired
        private CustomerLoginService customerLoginService;	
	    Slot slot = new Slot();
	    Customerdto customerdto = new Customerdto();  
	    @BeforeEach 
	    void init() {
	    	customerdto.setEmail("ravikumarsdsa@gmail.com");
	        customerdto.setPassword("ravia2020");
	        customerdto.setName("Ravsi");
	        customerdto.setNumber(32538471L);
	        customerdto.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
		        
	    	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 12, 13, 0));
	    	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 12, 12, 0));
	    	slot.setStatus(0);
	    }
	    @Test
	    public void testFindByCustomerId() {            

	    	String output;
			try {
				   output = customerLoginService.createCustomer(customerdto);
	               assertThat(output).isEqualTo("Registered");
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
			   Customer customer=customerService.findByEmail(customerdto.getEmail());
	    	    slot.setCustomer(customer);
	    	    slotService.save(slot);
	    	    Slot slotoutput =slotService.getSlotbyCustomerId(customer.getId());
	    	    assertThat(slot.getSlotStartDateTime()).isEqualTo(slotoutput.getSlotStartDateTime());
	    	    assertThat(slot.getSlotEndDateTime()).isEqualTo(slotoutput.getSlotEndDateTime());
	    }
        
}
