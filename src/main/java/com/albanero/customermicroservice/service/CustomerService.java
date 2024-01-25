package com.albanero.customermicroservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albanero.customermicroservice.entity.CustomerEntity;
import com.albanero.customermicroservice.exception.CustomerNotFoundException;
import com.albanero.customermicroservice.exception.InvalidIdException;
import com.albanero.customermicroservice.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public CustomerEntity createCustomer(CustomerEntity customer) {
		customer.setCreatedBy(customer.getCreatedBy());
		customer.setCreatedDate(LocalDateTime.now());
		return customerRepository.save(customer);
	}
	
	
//	public CustomerEntity getCustomer(Long customerId) {
//		
//		//check customer existence
//	return customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found with ID:"));
//	}
	
	 public CustomerEntity getCustomer(Long customerId) {
	        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);

	        if (optionalCustomer.isPresent()) {
	            return optionalCustomer.get();
	        } else {
	            throw new CustomerNotFoundException("Customer not found with id: " + customerId);
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
	public CustomerEntity updateCustomer(Long customerId,CustomerEntity updateCustomer) {
		
	    CustomerEntity existingCustomer=customerRepository.findById(customerId)
	    		.orElseThrow(()->new CustomerNotFoundException("Customer not found with ID:"+customerId) );
	    
	    //Update customer with value from updateCustomer
	    
	    existingCustomer.setName(updateCustomer.getName());
	    existingCustomer.setEmail(updateCustomer.getEmail());
	    existingCustomer.setPhone(updateCustomer.getPhone());
	    existingCustomer.setAddress(updateCustomer.getAddress());
	    existingCustomer.setCompanyName(updateCustomer.getCompanyName());
	    existingCustomer.setIndustryType(updateCustomer.getIndustryType());
	    existingCustomer.setCustomerStatus(updateCustomer.getCustomerStatus());
	    existingCustomer.setAccountManager(updateCustomer.getAccountManager());
	    
	    //Audit information
	    existingCustomer.setLastModifiedBy(updateCustomer.getName());
	    existingCustomer.setLastModifiedDate(LocalDateTime.now());
	    
	    //Save updated customer
	    return customerRepository.save(existingCustomer);
	    
		}
	
	public void deleteCustomer(Long customerId) {
			
			CustomerEntity existingCustomer=customerRepository.findById(customerId)
		    		.orElseThrow(()->new CustomerNotFoundException("Customer not found with ID:"+customerId) );
			
			 customerRepository.delete(existingCustomer);
		}
		
	public List<CustomerEntity> bulkLoadCustomer(List<CustomerEntity> customers){
			
			//Audit Information for each customer
			customers.forEach(customer->{
				customer.setCreatedBy(customer.getCreatedBy());
				customer.setCreatedDate(LocalDateTime.now());
			});
			
			 List<CustomerEntity> createdCustomers = customerRepository.saveAll(customers);
			return createdCustomers;
			
	}
		
	 public List<CustomerEntity> bulkUpdateCustomers(List<CustomerEntity> customers) {
		  
		    for (CustomerEntity customer : customers) {
		        	
		        	//Audit Information
		        customer.setLastModifiedBy(customer.getLastModifiedBy());
		        customer.setLastModifiedDate(LocalDateTime.now());
		     	    
		    if (customer.getCustomerId() != null || !customerRepository.existsById(customer.getCustomerId())) {
		          throw new InvalidIdException("Invalid ID provided: " + customer.getCustomerId());
		           }
		      }
		        
		        return customerRepository.saveAll(customers);
		   }
			
     }

