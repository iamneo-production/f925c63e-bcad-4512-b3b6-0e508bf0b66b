import { Injectable } from '@angular/core';
import { roles } from './roles';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

interface LoginRequest {
  email: string;
  password: string;
}

type ROLE = keyof typeof roles;

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private jwt: string;
  private role: ROLE;

  public set setJwt(jwt: string) {
    this.jwt = jwt;
  }

  public get getJwt() {
    return this.jwt;
  }

  public set setRole(roles: ROLE) {
    this.role = roles;
  }

  public get getRole() {
    return this.role;
  }

  constructor(private httpClient: HttpClient) {
    this.setJwt = localStorage.getItem('jwt');
    this.setJwt = localStorage.getItem('role') as ROLE;
  }

  public authDetails(loginRequest: LoginRequest): void {
    this.httpClient
      .post<AuthService>('http://localhost:8080/login', loginRequest)
      .pipe(
        catchError((err) => {
          throw new Error(err.message);
        })
      )
      .subscribe((data) => {
        localStorage.setItem('jwt', data.jwt);
        localStorage.setItem('role', data.role);
        this.setJwt = data.jwt;
        this.setRole = data.role;
      });
  }
}
