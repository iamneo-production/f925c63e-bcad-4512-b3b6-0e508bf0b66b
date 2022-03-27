import { Component, OnInit } from '@angular/core';
import { Employee, EmployeeService } from 'src/app/services/employee.service';
import { User, UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class AdminHomeComponent implements OnInit {
  public employees: Employee[];
  public users: User[];

  public addEmployeeHeading = 'Add Employee';
  public editEmployeeHeading = 'Edit Employee';
  public viewEmployeeHeading = 'View Employee';
  public viewUserHeading = 'View User';

  public addSelector = 'adddrop';
  public editSelector = 'editdrop';
  public viewSelector = 'viewdrop';

  public on = 'true';

  public selectedPerson: Employee[] = [];

  constructor(
    private employeeService: EmployeeService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    document.body.style.backgroundColor = 'white';

    this.employeeService.getEmployees().subscribe((data) => {
      this.employees = data.data;
    });

    this.userService.getUsers().subscribe((data) => {
      this.users = data.data;
      console.log(data.data);
    });
  }

  public addEmployee(data: Employee): void {
    this.employeeService.addEmployee(data).subscribe((d) => {
      data.id = +d.msg;
      this.employees.push(data);
    });
  }

  public updateEmployee(data: Employee): void {
    this.employeeService.updateEmployee(data).subscribe(() => {
      let ind = this.employees.findIndex((d) => d.id === data.id);
      this.employees[ind] = data;
    });
  }

  public deleteEmployee(data: Employee): void {
    this.employeeService.deleteEmployee(data).subscribe(() => {
      this.employees = this.employees.filter((e) => e.id !== data.id);
    });
  }
}
