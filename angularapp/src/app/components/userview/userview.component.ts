import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/services/user.service';

@Component({
  selector: 'app-userview',
  templateUrl: './userview.component.html',
})
export class UserviewComponent implements OnInit {
  @Input() user: User;
  constructor() {}

  ngOnInit(): void {}
}
