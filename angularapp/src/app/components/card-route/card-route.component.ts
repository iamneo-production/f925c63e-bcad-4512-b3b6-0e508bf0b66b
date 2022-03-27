import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { DriverRoutes } from 'src/app/services/routes.service';

@Component({
  selector: 'app-card-route',
  templateUrl: './card-route.component.html',
  styleUrls: ['./card-route.component.css'],
})
export class CardRouteComponent implements OnInit {
  @Input() route: DriverRoutes;
  @Output() delete = new EventEmitter();
  @Output() edit = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}
}
