import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { DriverRoutes } from 'src/app/services/routes.service';

@Component({
  selector: 'app-card-route',
  templateUrl: './card-route.component.html',
})
export class CardRouteComponent implements OnInit {
  @Input() route: DriverRoutes;
  @Output() delete = new EventEmitter();
  @Output() edit = new EventEmitter();
  @Input() editSelector: string;
  @Input() booking: string | undefined;
  @Output() book = new EventEmitter();
  @Input() isBooked: boolean;

  constructor() {}

  ngOnInit(): void {}
}
