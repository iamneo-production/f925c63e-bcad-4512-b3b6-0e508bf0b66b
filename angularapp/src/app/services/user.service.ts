import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export interface User {
  id: string;
  name: string;
  email: string;
  mobileNumber: string;
  status: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private httpCleint: HttpClient,
    private authService: AuthService
  ) {}

  public getUsers(): Observable<{ data: User[] }> {
    return this.httpCleint.get<any>('/admin/getUsers', {
      headers: new HttpHeaders().set(
        'Authorization',
        `Bearer ${this.authService.getJwt}`
      ),
    });
  }
}
