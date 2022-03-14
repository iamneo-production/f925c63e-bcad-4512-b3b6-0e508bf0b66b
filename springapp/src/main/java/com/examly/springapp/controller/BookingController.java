package com.examly.springapp.controller;

import com.examly.springapp.model.Booking;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.services.BookingService;
import com.examly.springapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

  @Autowired private BookingService bookingService;
  @Autowired private JwtUtil jwtUtil;
  @Autowired private UserRepository userRepository;

  @PostMapping("/saveBooking")
  public ResponseEntity<?>
  saveBooking(@RequestHeader(value = "Authorization") String bearer,
              @RequestBody Booking booking) {
    try {
      String email = jwtUtil.extractEmail(bearer.substring(7));
      User user = userRepository.findByEmail(email).get();

      if (!booking.user.getId().equals(user.getId()))
        throw new IllegalArgumentException("Unauthorized access");

      bookingService.saveBooking(booking);
      return new ResponseEntity<>("Booking Created Successfully",
                                  HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/deleteBooking")
  public ResponseEntity<?>
  deleteBooking(@RequestHeader(value = "Authorization") String bearer,
                @RequestBody Booking booking) {
    try {
      String email = jwtUtil.extractEmail(bearer.substring(7));
      User user = userRepository.findByEmail(email).get();

      if (!booking.user.getId().equals(user.getId()))
        throw new IllegalArgumentException("Unauthorized access");

      bookingService.deleteBooking(booking);
      return new ResponseEntity<>("Booking Deleted Successfully",
                                  HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/bookingStatus")
  public ResponseEntity<?>
  bookingStatus(@RequestHeader(value = "Authorization") String bearer) {

    try {
      String email = jwtUtil.extractEmail(bearer.substring(7));
      User user = userRepository.findByEmail(email).get();

      Booking booking = bookingService.getBookingById(user.getId());
      if (booking != null) {
        booking.user = null;
      }

      return new ResponseEntity<>(booking, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/admin/allBooking")
  public ResponseEntity<?> adminAllBooking() {
    try {
      return new ResponseEntity<>(bookingService.getBooking(), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
