package com.ourapp.salonapp.repositoryTest;
 
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ourapp.salonapp.entity.Customer;
import com.ourapp.salonapp.repository.CustomerRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private CustomerRepository customerRepository;
    
    Customer customer = new Customer();
    
    @BeforeEach 
    void init() {
    	customer.setEmail("ravikumasr@gmail.com");
        customer.setPassword("ravsi2020");
        customer.setName("Ravsi");
        customer.setNumber(32533871L);
        customer.setAddress("ABC Street,KNM Nagar,Tamil Nadu");
    }

    @Test
    public void testCreateCustomer() {
        
        Customer savedCustomer = customerRepository.save(customer);         
        Customer existCustomer = entityManager.find(Customer.class, savedCustomer.getId());         
        assertThat(customer).isEqualTo(existCustomer);  
    }
    
    @Test
    public void testfindByEmail() {
    	
        customerRepository.save(customer);        
        Customer mailCustomer=customerRepository.findByEmail(customer.getEmail()); 
        assertThat(customer).isEqualTo(mailCustomer);
    }
    @Test
    public void testfindByNumber() {
    	
        customerRepository.save(customer);        
        Customer mailCustomer=customerRepository.findByNumber(customer.getNumber()); 
        assertThat(customer).isEqualTo(mailCustomer);
    }
}