import { Injectable } from '@angular/core';
import { roles } from './values';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

interface LoginRequest {
  email: string;
  password: string;
}

interface SignupRequest extends LoginRequest {
  name: string;
  mobileNumber: string;
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

  constructor(private httpClient: HttpClient, private router: Router) {
    this.setJwt = localStorage.getItem('jwt');
    this.setRole = localStorage.getItem('role') as ROLE;
  }

  public LoginAction(loginRequest: LoginRequest): void {
    this.httpClient
      .post<AuthService>('/login', loginRequest)
      .subscribe((data) => {
        localStorage.setItem('jwt', data.jwt);
        localStorage.setItem('role', data.role);
        this.setJwt = data.jwt;
        this.setRole = data.role;

        if (this.getRole === roles.ROLE_USER) {
          this.router.navigate(['/']);
        }

        if (this.getRole === roles.ROLE_ADMIN) {
          this.router.navigate(['/admin']);
        }
      });
  }

  public SignupAction(signupRequest: SignupRequest): void {
    this.httpClient.post<any>('/signup', signupRequest).subscribe(() => {
      alert('Account created successfully');
      this.router.navigate(['/login']);
    });
  }
}
