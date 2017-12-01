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
  
  periodeList = [];

  periodeUrl = "http://localhost:8080/javaee7-angular/resources/disponibilities/getPeriodes";

  dayList = [];
  
  dayUrl = "http://localhost:8080/javaee7-angular/resources/disponibilities/getDays";

  ngOnInit() {
    this.disponibilities.valueChanges.subscribe(
      form => {
        console.log({"disponibility":form});
        this.restaurantService.addFilter("disponibility",form)
      }
    )
    this.getPeriodes()
    .subscribe(periodeList => this.periodeList = periodeList)

    this.getDays()
    .subscribe(daylist => this.dayList = daylist)
  }

  getPeriodes():Observable<String[]>{
    return this.http.get<String[]>(this.periodeUrl);
  }

  getDays():Observable<String[]>{
    return this.http.get<String[]>(this.dayUrl);
  }
}
