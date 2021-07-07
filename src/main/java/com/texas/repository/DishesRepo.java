package com.texas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texas.dto.DishDTO;
import com.texas.entity.Dish;

public interface DishesRepo extends JpaRepository<Dish, Long> {

	DishDTO findDishBydishName(String name);
}
