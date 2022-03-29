import { Component, OnInit } from '@angular/core';
import { Booking, BookingService } from 'src/app/services/booking.service';
import { DriverRoutes, RoutesService } from 'src/app/services/routes.service';
import { User, UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  user: User;
  routes: DriverRoutes[];
  bookStatus: Booking;
  isBooked: boolean = false;
  profile = 'Profile';
  drop="userProfileBox";
  constructor(
    private routesService: RoutesService,
    private bookingService: BookingService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.routesService.getUserRoutes().subscribe((data) => {
      this.routes = data.data;
    });

    this.bookingService.getBookingStatus().subscribe((data) => {
      this.bookStatus = data;
      this.isBooked = !!data;
    });

    this.userService.getUser().subscribe((data) => {
      this.user = data;
    });
  }

  public book(route: DriverRoutes) {
    this.bookingService.saveBooking({ route: route }).subscribe((data) => {
      route.seats = route.seats - 1;
      this.isBooked = true;
      this.bookStatus = {
        bookingId: +data.msg,
        route,
        user: null,
      };

      const routeIndex = this.routes.findIndex(
        (r) => r.routeid === route.routeid
      );

      this.routes[routeIndex] = route;
    });
  }

  public cancel(booking: Booking) {
    this.bookingService.deleteBooking(booking.bookingId).subscribe(() => {
      booking.route.seats = booking.route.seats + 1;
      this.bookStatus = null;
      this.isBooked = false;
      const routeIndex = this.routes.findIndex(
        (r) => r.routeid === booking.route.routeid
      );
      this.routes[routeIndex] = booking.route;
    });
  }

  public updateUser(user: User) {
    this.userService.updateUser(user).subscribe(() => {
      this.user = user;
    });
  }
}
