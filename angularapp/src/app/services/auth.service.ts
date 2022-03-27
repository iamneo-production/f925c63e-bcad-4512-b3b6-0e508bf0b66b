import { Injectable } from '@angular/core';
import { roles } from '../utils/values';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { tokenGetter } from '../app.module';

interface LoginRequest {
  email: string;
  password: string;
}

interface SignupRequest extends LoginRequest {
  name: string;
  mobileNumber: string;
}

export type ROLE = keyof typeof roles;

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) {}

  public role: ROLE;

  public LoginAction(loginRequest: LoginRequest): void {
    this.httpClient
      .post<{ jwt: string }>('/login', loginRequest)
      .subscribe((data) => {
        localStorage.setItem('jwt', data.jwt);

        const { role }: { role: ROLE } = this.jwtHelper.decodeToken(data.jwt);

        if (role === roles.ROLE_USER) {
          this.router.navigate(['/']);
        }

        if (role === roles.ROLE_ADMIN) {
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

  public Logout(): void {
    this.role = null;
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
