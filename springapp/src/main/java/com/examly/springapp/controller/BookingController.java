package com.examly.springapp.controller;

import com.examly.springapp.model.Booking;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.services.BookingService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

  @Autowired private BookingService bookingService;
  @Autowired private UserRepository userRepository;

  @PostMapping("/saveBooking")
  public ResponseEntity<?> saveBooking(HttpServletRequest httpServletRequest,
                                       @RequestBody Booking booking) {
    try {
      String email = (String)httpServletRequest.getAttribute("email");
      User user = userRepository.findByEmail(email).get();

      if (user == null) {
        throw new IllegalArgumentException("Unauthorised Access");
      }

      booking.user = user;

      bookingService.saveBooking(booking);
      return new ResponseEntity<>("Booking Created Successfully",
                                  HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/deleteBooking")
  public ResponseEntity<?> deleteBooking(HttpServletRequest httpServletRequest,
                                         @RequestBody Booking booking) {
    try {
      String email = (String)httpServletRequest.getAttribute("email");
      User user = userRepository.findByEmail(email).get();

      if (user == null) {
        throw new IllegalArgumentException("Unauthorised Access");
      }

      booking.user = user;

      bookingService.deleteBooking(booking);
      return new ResponseEntity<>("Booking Deleted Successfully",
                                  HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/bookingStatus")
  public ResponseEntity<?>
  bookingStatus(HttpServletRequest httpServletRequest) {

    try {
      String email = (String)httpServletRequest.getAttribute("email");
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
