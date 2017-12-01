import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";
import {MockRestaurantsService} from "../mock-restaurants.service";

@Component({
  selector: 'app-disponibility-filter',
  templateUrl: './disponibility-filter.component.html',
  styleUrls: ['./disponibility-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DisponibilityFilterComponent implements OnInit {

  constructor(private restaurantService: MockRestaurantsService) { }

  disponibilities = new FormControl();
  
  disponibilityList = ['MORNING', 'MIDDAY', 'EVENING', 'NIGHT'];

  ngOnInit() {
    this.disponibilities.valueChanges.subscribe(
      form => {
        console.log({"disponibility":form});
        this.restaurantService.addFilter("disponibility",form)
      }
    )
  }
}
