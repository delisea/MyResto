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

  url = "http://myresto-myresto.193b.starter-ca-central-1.openshiftapps.com/javaee7-angular/resources/specialities";

  ngOnInit() {
    this.specialities.valueChanges.subscribe(
      form => {
        this.restaurantService.addFilter("speciality",form)
      }
    );
    this.getSpecialities()
    .subscribe(list => this.specialityList = list.sort(function(a,b){
      if(a<b) return -1;
      if(a>b) return 1;
      return 0;
    }));

    console.log(this.specialityList);
  }

  getSpecialities():Observable<String[]>{
    return this.http.get<String[]>(this.url);
  }
}
