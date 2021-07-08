package com.texas.Service;

import java.util.List;

import com.texas.dto.CustomerOrderDTO;

public interface CustomerOrderService {
	
	CustomerOrderDTO placeOrder(CustomerOrderDTO orderDTO);

	CustomerOrderDTO getOrderByID(Long id);
	
	List<CustomerOrderDTO> getAllOrders();
}
