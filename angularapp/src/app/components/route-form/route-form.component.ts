import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee, EmployeeService } from 'src/app/services/employee.service';
import { DriverRoutes } from 'src/app/services/routes.service';

@Component({
  selector: 'app-route-form',
  templateUrl: './route-form.component.html',
})
export class RouteFormComponent implements OnInit {
  @Output() addRoute = new EventEmitter<DriverRoutes>();
  @Input() employees: Employee[];
  @Input() route: DriverRoutes | undefined;
  @Input() reset: string | undefined;
  public routeForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    if (this.route) {
      const emps = this.employees.filter(
        (e) => e.id !== this.route.employee.id
      );
      emps.unshift(this.route.employee);
      this.employees = emps;
    }
    this.routeForm = this.formBuilder.group({
      startPoint: [
        this.route ? this.route.startPoint : '',
        [Validators.required],
      ],
      endPoint: [this.route ? this.route.endPoint : '', [Validators.required]],
      distance: [
        this.route ? this.route.distance : '',
        [Validators.required, Validators.min(1)],
      ],
      seats: [
        this.route ? this.route.seats : '',
        [Validators.required, Validators.min(2), Validators.max(6)],
      ],
      employee: [
        this.route ? this.route.employee : null,
        [Validators.required],
      ],
    });
    if (this.employees.length > 0) {
      this.routeForm.controls['employee'].setValue(this.employees[0].id);
    }
  }

  public onSubmit() {
    this.addRoute.emit({
      ...this.routeForm.value,
      employee: {
        id: this.routeForm.value['employee'],
      },
      routeid: this.route ? this.route.routeid : null,
    });
    if (this.reset) {
      this.routeForm.reset();
    }
    this.routeForm.controls['employee'].setValue(this.employees[0].id);
  }
}
