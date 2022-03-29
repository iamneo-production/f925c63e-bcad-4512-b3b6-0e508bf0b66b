import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/services/user.service';

@Component({
  selector: 'app-userview',
  templateUrl: './userview.component.html',
})
export class UserviewComponent implements OnInit {
  userForm: FormGroup;
  @Input() user: User;
  @Input() view: string;
  @Output() update = new EventEmitter();
  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      name: [this.user.name, [Validators.required]],
      email: [this.user.email, [Validators.required, Validators.email]],
      mobileNumber: [
        this.user.mobileNumber,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10),
          Validators.pattern('(^$|[0-9]{10})'),
        ],
      ],
    });
    if (this.view) {
      this.userForm.controls['name'].disable();
      this.userForm.controls['email'].disable();
      this.userForm.controls['mobileNumber'].disable();
    }
  }

  onSubmit() {
    this.update.emit(this.userForm.value);
  }
}
