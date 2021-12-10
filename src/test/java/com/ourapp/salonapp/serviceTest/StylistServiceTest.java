package com.ourapp.salonapp.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ourapp.salonapp.Exception.UserRegisteredException;
import com.ourapp.salonapp.dto.Customerdto;
import com.ourapp.salonapp.dto.Stylistdto;
import com.ourapp.salonapp.dto.Stylistsubdto;
import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.service.CustomerLoginService;
import com.ourapp.salonapp.service.CustomerService;
import com.ourapp.salonapp.service.SlotService;
import com.ourapp.salonapp.service.StylistLoginService;
import com.ourapp.salonapp.service.StylistService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StylistServiceTest {
	 
	    @Autowired
        private StylistLoginService stylistLoginService;	
	    
	    @Autowired
        private StylistService stylistService;
	    
	    @Autowired
        private SlotService slotService;
	    
	    @Autowired
        private CustomerLoginService customerLoginService;	
	    
	    @Autowired 
	    private CustomerService customerService;
    
	    Stylistdto stylistdto = new Stylistdto();
	    
	    
	    @BeforeEach
	    void init() {
	    	stylistdto.setEmail("ramkumeaar@gmail.com");
	    	stylistdto.setPassword("rama2020");
	    	stylistdto.setName("Ram");
	    	stylistdto.setNumber(382533871L);
	    }
	    
        @Test
	    public void testCreateStylist(){
	        String output;
			try {
				   output = stylistLoginService.createStylist(stylistdto);
	               assertThat(output).isEqualTo("Registered");
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
	        
        }
        
        @Test
	    public void testgetAllStylists(){	
        	try {
				stylistLoginService.createStylist(stylistdto);
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
			List<Stylistsubdto> output = stylistService.getAllStylists();
			List<Stylistsubdto> stylistdtos=new ArrayList<Stylistsubdto>();
			Stylistsubdto stylistd=new Stylistsubdto(stylistdto.getName(),stylistdto.getEmail());
			stylistdtos.add(stylistd);
			for(int i=0;i<stylistdtos.size();i++)
		    {
				 assertThat(output.get(i)).usingRecursiveComparison().isEqualTo(stylistdtos.get(i));
		    }
	       
	    }
        
        @Test
	    public void testIsNotSlotAvailable(){	        	
        	Slot slot = new Slot();
            Customerdto customerdto = new Customerdto();
            customerdto.setEmail("ravikumar@gmail.com");
            customerdto.setPassword("ravi2020");
            customerdto.setName("Ravi");
            customerdto.setNumber(3253871L);
            customerdto.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
    	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));	    
        	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 10, 13, 0));
        	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 10, 12, 0));
        	slot.setStatus(0);
        	try {
				customerLoginService.createCustomer(customerdto);
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");
			}
    	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));
    	    slotService.save(slot);
    	    List<Slot> slots=new ArrayList<Slot>();
    	    slots.add(slot);
    	    try {
				stylistLoginService.createStylist(stylistdto);
				String outputstylist=stylistService.isSlotAvailable(slot.getSlotStartDateTime(),slot.getSlotEndDateTime(),stylistdto.getEmail());	    
    	    assertThat(outputstylist).isEqualTo("NotAvailable");
			} catch (UserRegisteredException e) {
				assertThat(e)
	            .isInstanceOf(UserRegisteredException.class)
	            .hasMessage("Already Registered");    	    
	         }
          }
    	    
    	    @Test
    	    public void testIsSlotAvailable(){	        	
            	Slot slot = new Slot();
                Customerdto customerdto = new Customerdto();
                customerdto.setEmail("ravikumark@gmail.com");
                customerdto.setPassword("ravi2020");
                customerdto.setName("Ravi");
                customerdto.setNumber(32538871L);
                customerdto.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
        	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));	    
            	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 18, 13, 0));
            	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 18, 12, 0));
            	slot.setStatus(0);
            	try {
    				customerLoginService.createCustomer(customerdto);
    			} catch (UserRegisteredException e) {
    				assertThat(e)
    	            .isInstanceOf(UserRegisteredException.class)
    	            .hasMessage("Already Registered");
    			}
        	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));
        	    slotService.save(slot);
        	    List<Slot> slots=new ArrayList<Slot>();
        	    slots.add(slot);
        	    try {
    				stylistLoginService.createStylist(stylistdto);
    				String outputstylist=stylistService.isSlotAvailable(slot.getSlotStartDateTime(),slot.getSlotEndDateTime(),stylistdto.getEmail());	    
        	    assertThat(outputstylist).isEqualTo("Available");
    			} catch (UserRegisteredException e) {
    				assertThat(e)
    	            .isInstanceOf(UserRegisteredException.class)
    	            .hasMessage("Already Registered");
        	   
    	    }
          }
    	    
    	    @Test
    	    public void testChangeSlotStatus(){	        	
            	Slot slot = new Slot();
                Customerdto customerdto = new Customerdto();
                customerdto.setEmail("ravikumarn@gmail.com");
                customerdto.setPassword("ravin2020");
                customerdto.setName("Ravin");
                customerdto.setNumber(32538881L);
                customerdto.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
        	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));	    
            	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 18, 13, 0));
            	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 18, 12, 0));
            	slot.setStatus(0);
            	try {
    				customerLoginService.createCustomer(customerdto);
    			} catch (UserRegisteredException e) {
    				assertThat(e)
    	            .isInstanceOf(UserRegisteredException.class)
    	            .hasMessage("Already Registered");
    			}
        	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));
        	    slotService.save(slot);
        	    List<Slot> slots=new ArrayList<Slot>();
        	    slots.add(slot);
        	    try {
    				stylistLoginService.createStylist(stylistdto);
    				String outputstylist=stylistService.changeSlotStatus(customerdto.getNumber());	    
        	        assertThat(outputstylist).isEqualTo("successcompleted");
    			} catch (UserRegisteredException e) {
    				assertThat(e)
    	            .isInstanceOf(UserRegisteredException.class)
    	            .hasMessage("Already Registered");        	    
    	    }
          }
    	    
    	    @Test
    	    public void testChangenotSlotStatus(){	        	
            	Slot slot = new Slot();
                Customerdto customerdto = new Customerdto();
                customerdto.setEmail("ravikumarm@gmail.com");
                customerdto.setPassword("ravim2020");
                customerdto.setName("Ravin");
                customerdto.setNumber(32538981L);
                customerdto.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
        	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));	    
            	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 18, 13, 0));
            	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 18, 12, 0));
            	slot.setStatus(1);
            	try {
    				customerLoginService.createCustomer(customerdto);
    			} catch (UserRegisteredException e) {
    				assertThat(e)
    	            .isInstanceOf(UserRegisteredException.class)
    	            .hasMessage("Already Registered");
    			}
        	    slot.setCustomer(customerService.findByEmail(customerdto.getEmail()));
        	    slotService.save(slot);
        	    List<Slot> slots=new ArrayList<Slot>();
        	    slots.add(slot);
        	    try {
    				stylistLoginService.createStylist(stylistdto);
    				String outputstylist=stylistService.changeSlotStatus(customerdto.getNumber());	    
        	        assertThat(outputstylist).isEqualTo("successnotcompleted");
    			} catch (UserRegisteredException e) {
    				assertThat(e)
    	            .isInstanceOf(UserRegisteredException.class)
    	            .hasMessage("Already Registered");        	    
    	    }
          }
}
