import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";
import {MockRestaurantsService} from "../mock-restaurants.service";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";

@Component({
  selector: 'app-disponibility-filter',
  templateUrl: './disponibility-filter.component.html',
  styleUrls: ['./disponibility-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DisponibilityFilterComponent implements OnInit {

  constructor(private restaurantService: MockRestaurantsService, private http: HttpClient) { }

  disponibilities = new FormControl();
  days = new FormControl();

  periodeList = [];

  dayList = [];

  ngOnInit() {
    this.disponibilities.valueChanges.subscribe(
      form => {
        console.log(form);
        this.restaurantService.addFilter("disponibility",form)
      }
    )

    this.days.valueChanges.subscribe(
      form => {
        this.restaurantService.addFilter("day",form)
      }
    )

    this.periodeList = ["Morning","Midday","Evening"];
    this.dayList = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
  }
}
