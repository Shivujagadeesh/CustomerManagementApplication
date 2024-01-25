package com.albanero.customermicroservice.controller;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.albanero.customermicroservice.entity.CustomerEntity;
import com.albanero.customermicroservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(CustomerController.class)

public class CustomerControllerTest {

	

	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private CustomerService customerService;

	    private ObjectMapper objectMapper;

	    @Before
	    public void setUp() {
	        objectMapper = new ObjectMapper();
	    }

	    @Test
	    public void testGetCustomerById() throws Exception {
	        Long customerId = 1L;
	        CustomerEntity  mockCustomer = new CustomerEntity();
	        mockCustomer.setCustomerId(customerId);
	        mockCustomer.setName("John Doe");
	        mockCustomer.setEmail("john.doe@example.com");

	        Mockito.when(customerService.getCustomer(customerId)).thenReturn(mockCustomer);

	        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", customerId))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
	                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));

	        
	    }

	    @Test
	    public void testCreateCustomer() throws Exception {
	        CustomerEntity newCustomer = new CustomerEntity();
	        newCustomer.setName("Test Customer");
	        newCustomer.setEmail("test.customer@example.com");

	        Mockito.when(customerService.createCustomer(Mockito.any(CustomerEntity.class))).thenReturn(newCustomer);

	        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(newCustomer)))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Customer"))
	                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test.customer@example.com"));

	        
	    }

	    @Test
	    public void testUpdateCustomer() throws Exception {
	        Long customerId = 1L;
	        CustomerEntity updatedCustomer = new CustomerEntity();
	        updatedCustomer.setCustomerId(customerId);
	        updatedCustomer.setName("Updated John Doe");
	        updatedCustomer.setEmail("updated.john.doe@example.com");

	        Mockito.when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(CustomerEntity.class)))
	               .thenReturn(updatedCustomer);

	        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/{id}", customerId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedCustomer)))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated John Doe"))
	                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("updated.john.doe@example.com"));

	        
	    }

	    @Test
	    public void testDeleteCustomer() throws Exception {
	        Long customerIdToDelete = 2L;

	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", customerIdToDelete))
	               .andExpect(MockMvcResultMatchers.status().isOk());

	        
	    }

	    @Test
	    public void testBulkCreateCustomers() throws Exception {
	        List<CustomerEntity> customersToCreate = Collections.singletonList(new CustomerEntity());

	        Mockito.when(customerService.bulkLoadCustomer(Mockito.anyList())).thenReturn(customersToCreate);

	        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/bulk-create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(customersToCreate)))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists()); // Assuming your customer has an 'id' field

	        
	    }

	    @Test
	    public void testBulkUpdateCustomers() throws Exception {
	        List<CustomerEntity> customersToUpdate = Collections.singletonList(new CustomerEntity());

	        Mockito.when(customerService.bulkUpdateCustomers(Mockito.anyList())).thenReturn(customersToUpdate);

	        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/bulk-update")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(customersToUpdate)))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists()); 

	        
	    }
	}

