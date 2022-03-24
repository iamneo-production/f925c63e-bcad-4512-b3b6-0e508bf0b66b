import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export interface Employee {
  id: number;
  username: string;
  email: string;
  mobileNumber: string;
  vehicleModel: string;
  vehicleNumber: string;
  verified: boolean;
  active: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) {}

  public getEmployees(): Observable<{ data: Employee[] }> {
    return this.httpClient.get<any>('/admin/getEmployee', {
      headers: new HttpHeaders().set(
        'Authorization',
        `Bearer ${this.authService.getJwt}`
      ),
    });
  }
}
