package com.albanero.customermicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.albanero.customermicroservice.entity.CustomerEntity;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CustomerServiceTest {

	    @Autowired
	    private CustomerService customerService;
	    

	    @Test
	    public void testCreateCustomer() {
	    	
	    	 CustomerEntity newCustomer = new CustomerEntity();
	         newCustomer.setName("Test Customer");
	         newCustomer.setEmail("test.customer@example.com");
	         newCustomer.setPhone("123-456-7890");

	         CustomerEntity createdCustomer = customerService.createCustomer(newCustomer);

	         Assert.assertNotNull(createdCustomer.getCustomerId());
	         Assert.assertEquals("Test Customer", createdCustomer.getName());
	         Assert.assertEquals("test.customer@example.com", createdCustomer.getEmail());
	         Assert.assertEquals("123-456-7890", createdCustomer.getPhone());
	        
	    }
	    @Test
	    public void testGetCustomer() {
	        
	        Long customerId = 121L;

	        CustomerEntity retrievedCustomer = customerService.getCustomer(customerId);

	        Assert.assertNotNull(retrievedCustomer);
	        Assert.assertEquals(customerId, retrievedCustomer.getCustomerId());
	    }

	    @Test
	    public void testUpdateCustomer() {
	        
	        Long customerId = 122L;

	        CustomerEntity existingCustomer = customerService.getCustomer(customerId);
	        Assert.assertNotNull(existingCustomer);

	        // Update customer details
	        existingCustomer.setName("Updated Test Customer");
	        existingCustomer.setEmail("updated.test.customer@example.com");
	        existingCustomer.setPhone("987-654-3210");

	        CustomerEntity updatedCustomer = customerService.updateCustomer(customerId,existingCustomer);

	        Assert.assertEquals("Updated Test Customer", updatedCustomer.getName());
	        Assert.assertEquals("updated.test.customer@example.com", updatedCustomer.getEmail());
	        Assert.assertEquals("987-654-3210", updatedCustomer.getPhone());
	    }
	    @Test
	    public void testDeleteCustomer() {
	        
	        Long customerIdToDelete = 2L;

	        CustomerEntity existingCustomer = customerService.getCustomer(customerIdToDelete);
	        Assert.assertNotNull(existingCustomer);

	        customerService.deleteCustomer(customerIdToDelete);

	        
	        Assert.assertNull(customerService.getCustomer(customerIdToDelete));
	    }

	    @Test
	    public void testBulkCreateCustomers() {
	        List<CustomerEntity> customersToCreate = createTestCustomers(5);

	        List<CustomerEntity> createdCustomers = customerService.bulkLoadCustomer(customersToCreate);

	        Assert.assertEquals(5, createdCustomers.size());

	        for (CustomerEntity createdCustomer : createdCustomers) {
	            Assert.assertNotNull(createdCustomer.getCustomerId());
	        }
	    }

	    @Test
	    public void testBulkUpdateCustomers() {
	        List<CustomerEntity> customersToUpdate = createTestCustomers(3);
	        List<CustomerEntity> updatedCustomers = customerService.bulkUpdateCustomers(customersToUpdate);

	        Assert.assertEquals(3, updatedCustomers.size());

	        for (CustomerEntity updatedCustomer : updatedCustomers) {
	            Assert.assertNotNull(updatedCustomer.getCustomerId());
	            
	        }
	    }

	    private List<CustomerEntity> createTestCustomers(int count) {
	        
	        List<CustomerEntity> customers = new ArrayList<>();
	        for (int i = 1; i <= count; i++) {
	            CustomerEntity customer = new CustomerEntity();
	            customer.setName("Test Customer " + i);
	            customer.setEmail("test" + i + ".customer@example.com");
	            customer.setPhone("123-456-789" + i);
	            customers.add(customer);
	        }
	        return customers;
	    }
	}
	   
	

