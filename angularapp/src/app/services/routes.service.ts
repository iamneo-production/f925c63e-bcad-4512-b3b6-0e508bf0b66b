import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from './employee.service';

export interface DriverRoutes {
  routeid: number;
  startPoint: string;
  endPoint: string;
  distance: number;
  seats: 4;
  employee: Employee;
}

@Injectable({
  providedIn: 'root',
})
export class RoutesService {
  constructor(private httpClient: HttpClient) {}

  public getAdminRoutes() {
    return this.httpClient.get<{ data: DriverRoutes[] }>('/admin/routes');
  }

  public deleteAdminRoute(id: number) {
    return this.httpClient.delete<any>(`admin/deleteRoutes/${id}`);
  }

  public addAdminRoute(route: DriverRoutes) {
    return this.httpClient.post<{ msg: string }>('/admin/addRoutes', route);
  }

  public editAdminRoute(route: DriverRoutes) {
    return this.httpClient.put<any>('/admin/editRoutes', route);
  }
}
