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

  public getUsers(): Observable<{ data: User[] }> {
    return this.httpCleint.get<any>('/admin/getUsers');
  }
}
