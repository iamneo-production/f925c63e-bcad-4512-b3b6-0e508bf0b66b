import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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
  constructor(private httpCleint: HttpClient) {}

  public getUser() {
    return this.httpCleint.get<User>('/getUser');
  }

  public getAdminUsers(): Observable<{ data: User[] }> {
    return this.httpCleint.get<any>('/admin/getUsers');
  }

  public updateUser(user: User) {
    return this.httpCleint.put<User>('/editCustomer', user);
  }
}
