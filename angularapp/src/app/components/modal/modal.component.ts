import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
})
export class ModalComponent implements OnInit {
  @Input() heading: string;
  @Input() drop: string;

  constructor() {}

  ngOnInit(): void {}
}
