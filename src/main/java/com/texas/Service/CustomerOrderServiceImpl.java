package com.texas.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.dto.CustomerOrderDTO;
import com.texas.dto.DishDTO;
import com.texas.entity.Billing;
import com.texas.entity.CustomerOrder;
import com.texas.entity.Dish;
import com.texas.entity.Locations;
import com.texas.exception.CustomerOrderServiceException;
import com.texas.repository.CustomerOrderRepository;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	CustomerOrderRepository customerOrderRepository;
	@Override
	public CustomerOrderDTO placeOrder(CustomerOrderDTO orderDTO) {
		
		CustomerOrderDTO cdto = new CustomerOrderDTO();
		try {
			CustomerOrder order = new CustomerOrder();
			
			BeanUtils.copyProperties(orderDTO, order);
			order.setOrderStatus(orderDTO.getOrderStatus());
			
			Billing bill = new Billing();
			
			BeanUtils.copyProperties(orderDTO.getBill(), bill);
			order.setBill(bill);
			
			Locations loc=new  Locations();
			
			BeanUtils.copyProperties(orderDTO.getLocation(), loc);
			order.setLocation(loc);
			
			List<Dish> dishes = new ArrayList<Dish>();
			
			for(DishDTO disDto: orderDTO.getDishes()) {
				Dish d = new Dish();
				BeanUtils.copyProperties(disDto, d);
				dishes.add(d);
			}
			order.setDishes(dishes);
			CustomerOrder cus= new CustomerOrder();
			cus=customerOrderRepository.save(order);
			//indvidua;ly set all getters and setters --TODO
			BeanUtils.copyProperties(cus, cdto);
			
			
		}catch(Exception e) {
			throw new CustomerOrderServiceException("Failed while Saving order", e);
		}
		
		return cdto;
	}

	@Override
	public CustomerOrderDTO getOrderByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrderDTO> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
