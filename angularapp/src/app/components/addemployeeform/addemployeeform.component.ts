import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Employee } from 'src/app/services/employee.service';

@Component({
  selector: 'app-addemployeeform',
  templateUrl: './addemployeeform.component.html',
})
export class AddemployeeformComponent implements OnInit {
  addEmplooyee: FormGroup;
  @Output() addEmp = new EventEmitter();
  @Input() disabled: string | undefined;
  @Input() employee: Employee | undefined;
  @Input() view: string | undefined;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.addEmplooyee = this.formBuilder.group({
      username: this.employee ? this.employee.username : '',
      email: this.employee ? this.employee.email : '',
      mobileNumber: this.employee ? this.employee.mobileNumber : '',
      vehicleNumber: this.employee ? this.employee.vehicleNumber : '',
      vehicleModel: this.employee ? this.employee.vehicleModel : '',
      verified: this.employee ? this.employee.verified : true,
      active: this.employee ? this.employee.active : true,
    });

    if (this.view) {
      let controls = this.addEmplooyee.controls;
      controls['username'].disable();
      controls['email'].disable();
      controls['mobileNumber'].disable();
      controls['vehicleNumber'].disable();
      controls['vehicleModel'].disable();
      controls['verified'].disable();
      controls['active'].disable();
    }
  }

  onSubmit(): void {
    this.addEmp.emit({ ...this.addEmplooyee.value, id: this.employee?.id });
  }
}
