package com.examly.springapp.repository;

import com.examly.springapp.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepsository extends CrudRepository<Booking, Integer> {
  boolean existsByUserId(String id);
  Booking findByUserId(String id);
}
