import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";
import {MockRestaurantsService} from "../mock-restaurants.service";


@Component({
  selector: 'app-speciality-filter',
  templateUrl: './speciality-filter.component.html',
  styleUrls: ['./speciality-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SpecialityFilterComponent implements OnInit {

  constructor(private restaurantService: MockRestaurantsService) { }

  ngOnInit() {
    this.specialities.valueChanges.subscribe(
      form => {
        console.log({"disponibility":form});
        this.restaurantService.addFilter("speciality",form)
      }
    )
  }

  specialities = new FormControl();

  specialityList = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
}
