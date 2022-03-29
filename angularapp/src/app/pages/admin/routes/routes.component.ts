import { Component, OnInit } from '@angular/core';
import { Employee, EmployeeService } from 'src/app/services/employee.service';
import { RoutesService, DriverRoutes } from 'src/app/services/routes.service';

@Component({
  selector: 'app-routes',
  templateUrl: './routes.component.html',
  styleUrls: ['./routes.component.css'],
})
export class AdminRoutesComponent implements OnInit {
  constructor(
    private routesService: RoutesService,
    private employeeService: EmployeeService
  ) {}

  public employees: Employee[];
  public routes: DriverRoutes[];

  public addRouteHeading = 'Add Route';
  public editRouteHeading = 'Edit Route';

  public addSelector = 'adddrop';
  public editSelector = 'editdrop';

  ngOnInit(): void {
    this.routesService.getAdminRoutes().subscribe((data) => {
      this.routes = data.data;
    });

    this.employeeService.getEmployees().subscribe((data) => {
      this.employees = data.data;
    });
  }

  public deleteAdminRoutes(id: number) {
    this.routesService.deleteAdminRoute(id).subscribe(() => {
      this.routes = this.routes.filter((el) => el.routeid !== id);
    });
  }

  public addAdminRoutes(route: DriverRoutes) {
    this.routesService.addAdminRoute(route).subscribe((data) => {
      this.routes.push(data);
    });
  }

  public editAdminRoutes(route: DriverRoutes) {
    this.routesService.editAdminRoute(route).subscribe((data) => {
      const index = this.routes.findIndex((el) => el.routeid == data.routeid);
      this.routes[index] = data;
    });
  }
}
