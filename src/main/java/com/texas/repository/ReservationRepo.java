package com.texas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texas.entity.Reservations;

public interface ReservationRepo extends JpaRepository<Reservations, Long>{

}
