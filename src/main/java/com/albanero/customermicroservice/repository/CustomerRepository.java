package com.albanero.customermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albanero.customermicroservice.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>{

}
