package com.examly.springapp.services;

import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Route;
import com.examly.springapp.repository.BookingRepsository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
  @Autowired private BookingRepsository bookingRepsository;
  @Autowired private RouteService routeService;

  public List<Booking> getBooking() {
    Iterable<Booking> books = bookingRepsository.findAll();
    List<Booking> bookList = new ArrayList<>();

    books.forEach(bookList::add);
    return bookList;
  }

  public Booking getBookingById(String userId) {
    return bookingRepsository.findByUserId(userId);
  }

  public void saveBooking(Booking booking) {
    if (bookingRepsository.existsByUserId(booking.user.getId()))
      throw new IllegalArgumentException("User already booked on some route");

    Route route = routeService.getRouteById(booking.route.getRouteId());

    if (route.getSeats() <= 0)
      throw new IllegalArgumentException("Seats are unavailable");

    bookingRepsository.save(booking);

    route.setSeats(route.getSeats() - 1);

    routeService.editRoute(route);
  }

  public void deleteBooking(int bookingId) {
    if (!bookingRepsository.existsById(bookingId))
      throw new IllegalArgumentException(
          "User does not have booking to be deleted");

    Booking booking = bookingRepsository.findById(bookingId).get();
    Route route = booking.getRoute();
    bookingRepsository.deleteById(booking.getBookingId());

    route.setSeats(route.getSeats() + 1);

    routeService.editRoute(route);
  }
}
