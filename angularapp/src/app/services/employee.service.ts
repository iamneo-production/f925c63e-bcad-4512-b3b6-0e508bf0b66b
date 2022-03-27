import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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
  constructor(private httpClient: HttpClient) {}

  public getEmployees(): Observable<{ data: Employee[] }> {
    return this.httpClient.get<any>('/admin/getEmployee');
  }
  public addEmployee(employee: Employee) {
    return this.httpClient.post<{ msg: string }>(
      '/admin/saveEmployee',
      employee
    );
  }

  public updateEmployee(employee: Employee) {
    return this.httpClient.put<any>('/admin/editEmployee', employee);
  }

  public deleteEmployee(employee: Employee) {
    return this.httpClient.delete<any>(`/admin/delete/${employee.id}`);
  }
}
