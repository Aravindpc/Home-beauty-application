package com.ourapp.salonapp.repositoryTest;
 
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.entity.Slot;
import com.ourapp.salonapp.repository.CustomerRepository;
import com.ourapp.salonapp.repository.SlotRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SlotRepositoryTest {
     
    @Autowired
    private SlotRepository slotRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    Slot slot = new Slot();
    Customer customer = new Customer();  
    @BeforeEach 
    void init() {
    	customer.setEmail("ravikumarw@gmail.com");
        customer.setPassword("raviw2020");
        customer.setName("Raviw");
        customer.setNumber(32532871L);
        customer.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
	    slot.setCustomer(customer);	    
    	slot.setSlotEndDateTime(LocalDateTime.of(2021, 9, 12, 13, 0));
    	slot.setSlotStartDateTime(LocalDateTime.of(2021, 9, 12, 12, 0));
    	slot.setStatus(0);
    }
    @Test
    public void testFindByCustomerId() {            

    	    Customer savedCustomer = customerRepository.save(customer);
    	    slot.setCustomer(savedCustomer);
    	    slotRepository.save(slot);
    	    Slot slotoutput =slotRepository.getbyCustomerId(savedCustomer.getId());
    	    assertThat(slot).usingRecursiveComparison().isEqualTo(slotoutput);
    }
}