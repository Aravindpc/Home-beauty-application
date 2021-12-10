package com.ourapp.salonapp.repositoryTest;
 
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.entity.Stylist;
import com.ourapp.salonapp.repository.CustomerRepository;
import com.ourapp.salonapp.repository.SlotRepository;
import com.ourapp.salonapp.repository.StylistRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StylistRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SlotRepository slotRepository;
    
    @Autowired
    private CustomerRepository customerRepository;    
    @Autowired
    private StylistRepository stylistRepository;
    
    Stylist stylist = new Stylist();
    
    @BeforeEach 
    void init() {
    	stylist.setEmail("ramkumaar@gmail.com");
    	stylist.setPassword("ram32020");
    	stylist.setName("Ramc");
    	stylist.setNumber(382538371L);
    }

    @Test
    public void testCreateStylist() {        
    	Stylist savedStylist = stylistRepository.save(stylist);         
    	Stylist existStylist = entityManager.find(Stylist.class, savedStylist.getId());         
        assertThat(stylist).isEqualTo(existStylist);  
    }
    
    @Test
    public void testfindByEmail() {    	
    	stylistRepository.save(stylist);        
    	Stylist mailCustomer=stylistRepository.findByEmail(stylist.getEmail()); 
        assertThat(stylist).isEqualTo(mailCustomer);
    }
    
    @Test
    public void testfindSlotAvailability()
    {
    	Slot slot = new Slot();
        Customer customer = new Customer();
        customer.setEmail("ravikuma3r@gmail.com");
        customer.setPassword("ravi2020");
        customer.setName("Ravi");
        customer.setNumber(32538471L);
        customer.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
	    slot.setCustomer(customer);	    
    	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 12, 13, 0));
    	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 12, 12, 0));
    	slot.setStatus(0);
    	Customer savedCustomer = customerRepository.save(customer);
	    slot.setCustomer(savedCustomer);
	    slotRepository.save(slot);
	    List<Slot> slots=new ArrayList<Slot>();
	    slots.add(slot);
	    stylist.setSlot(slots);
	    stylistRepository.save(stylist);
	    Stylist outputstylist=stylistRepository.isSlotAvailable(slot.getSlotStartDateTime(),slot.getSlotEndDateTime(),stylist.getEmail());	    
	    assertThat(outputstylist).isEqualTo(stylist);
    }
}