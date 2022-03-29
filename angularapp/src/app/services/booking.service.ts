import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DriverRoutes } from './routes.service';
import { User } from './user.service';

export interface Booking {
  bookingId: number;
  route: DriverRoutes;
  user: User;
}

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  constructor(private httpClient: HttpClient) {}

  public saveBooking(booking: { route: DriverRoutes }) {
    return this.httpClient.post<{ msg: string }>('/saveBooking', booking);
  }

  public deleteBooking(bookingId: number) {
    return this.httpClient.delete<any>(`/deleteBooking/${bookingId}`);
  }

  public getBookingStatus() {
    return this.httpClient.get<Booking | undefined>('/bookingStatus');
  }
}
