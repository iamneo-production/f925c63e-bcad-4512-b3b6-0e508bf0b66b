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
    });
  }
}
