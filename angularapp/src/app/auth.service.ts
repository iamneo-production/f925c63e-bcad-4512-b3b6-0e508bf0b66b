import { Injectable } from '@angular/core';
import { roles } from './roles';

type role = keyof typeof roles;

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private jwt: string;
  private ROLE: role;

  public set setJwt(jwt: string) {
    this.jwt = jwt;
  }

  public get getJwt() {
    return this.jwt;
  }

  public set setRole(roles: role) {
    this.ROLE = roles;
  }

  public get getRole() {
    return this.ROLE;
  }

  constructor() {
    this.setJwt = localStorage.getItem('jwt');
    this.setJwt = localStorage.getItem('role') as role;
  }
}
