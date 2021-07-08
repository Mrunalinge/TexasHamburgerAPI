package com.texas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texas.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
	
	

}
