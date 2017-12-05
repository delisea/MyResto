import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";
import {MockRestaurantsService} from "../mock-restaurants.service";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";

@Component({
  selector: 'app-speciality-filter',
  templateUrl: './speciality-filter.component.html',
  styleUrls: ['./speciality-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SpecialityFilterComponent implements OnInit {

  constructor(private restaurantService: MockRestaurantsService, private http: HttpClient) { }

  
  specialities = new FormControl();
  
  specialityList = [];

  url = "http://localhost:8080/javaee7-angular/resources/specialities";

  ngOnInit() {
    this.specialities.valueChanges.subscribe(
      form => {
        this.restaurantService.addFilter("speciality",form)
      }     
    )
    this.getSpecialities()
    .subscribe(list => this.specialityList = list)

    console.log(this.specialityList);
  }

  getSpecialities():Observable<String[]>{
    return this.http.get<String[]>(this.url);
  }
}
