import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      username: [
        this.employee ? this.employee.username : '',
        [Validators.required],
      ],
      email: [
        this.employee ? this.employee.email : '',
        [Validators.required, Validators.email],
      ],
      mobileNumber: [
        this.employee ? this.employee.mobileNumber : '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10),
          Validators.pattern('(^$|[0-9]{10})'),
        ],
      ],
      vehicleNumber: [
        this.employee ? this.employee.vehicleNumber : '',
        [Validators.required],
      ],
      vehicleModel: [
        this.employee ? this.employee.vehicleModel : '',
        [Validators.required],
      ],
      verified: [
        this.employee ? this.employee.verified : true,
        [Validators.required],
      ],
      active: [
        this.employee ? this.employee.active : true,
        [Validators.required],
      ],
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
    if (this.disabled) {
      this.addEmplooyee.reset();
    }
  }
}
