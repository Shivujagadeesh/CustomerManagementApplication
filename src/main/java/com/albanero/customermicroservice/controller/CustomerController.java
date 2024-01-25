package com.albanero.customermicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albanero.customermicroservice.entity.CustomerEntity;
import com.albanero.customermicroservice.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	
	public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer){
		
		CustomerEntity createCustomer=customerService.createCustomer(customer);
		return new ResponseEntity<>(createCustomer,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Long customerId){
		CustomerEntity customer=customerService.getCustomer(customerId);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerEntity customer){
		CustomerEntity updatedcustomer=customerService.updateCustomer(customerId, customer);
		return new ResponseEntity<>(updatedcustomer,HttpStatus.OK);
    }
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId){
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/bulk-load")
	public ResponseEntity<List<CustomerEntity>> bulkLoadCustomers(@RequestBody List<CustomerEntity> customers){
		List<CustomerEntity> loadedCustomers=customerService.bulkLoadCustomer(customers);
		return new ResponseEntity<>(loadedCustomers,HttpStatus.CREATED);
	}
	
	@PutMapping("/bulk-update")
	public ResponseEntity<List<CustomerEntity>> bulkUpdateCustomers(@RequestBody List<CustomerEntity> customers){
		List<CustomerEntity> updatedCustomers=customerService.bulkLoadCustomer(customers);
		return new ResponseEntity<>(updatedCustomers,HttpStatus.OK);
	}
	

}


