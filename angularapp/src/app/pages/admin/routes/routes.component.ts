import { Component, OnInit } from '@angular/core';
import { RoutesService, DriverRoutes } from 'src/app/services/routes.service';

@Component({
  selector: 'app-routes',
  templateUrl: './routes.component.html',
  styleUrls: ['./routes.component.css'],
})
export class AdminRoutesComponent implements OnInit {
  constructor(private routesService: RoutesService) {}

  public routes: DriverRoutes[] = [];

  public addRouteHeading = 'Add Route';
  public editRouteHeading = 'Edit Route';
  public viewRouteHeading = 'View Route';

  public addSelector = 'adddrop';
  public editSelector = 'editdrop';
  public viewSelector = 'viewdrop';

  ngOnInit(): void {
    this.routesService.getAdminRoutes().subscribe((data) => {
      this.routes = data.data;
    });
  }

  public deleteAdminRoutes(id: number) {
    this.routesService.deleteAdminRoute(id).subscribe(() => {
      this.routes = this.routes.filter((el) => el.routeid !== id);
    });
  }

  public addAdminRoutes(route: DriverRoutes) {
    this.routesService.addAdminRoute(route).subscribe((data) => {
      route.routeid = +data.msg;
      this.routes.push(route);
    });
  }

  public editAdminRoutes(route: DriverRoutes) {
    this.routesService.editAdminRoute(route).subscribe(() => {
      const index = this.routes.findIndex((el) => el.routeid == route.routeid);
      this.routes[index] = route;
    });
  }
}
