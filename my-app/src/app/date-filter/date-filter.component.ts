import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import {FormControl} from "@angular/forms";
import {MatDatepickerInputEvent} from "@angular/material";

const now = new Date();


@Component({
  selector: 'app-date-filter',
  templateUrl: './date-filter.component.html',
  styleUrls: ['./date-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DateFilterComponent implements OnInit {

  ngOnInit() {
  }
  events: string[] = [];

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.events.push(`${type}: ${event.value}`);
  }
}


