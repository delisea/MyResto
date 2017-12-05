import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FiltersComponent} from "../filters/filters.component";
import { RestaurantsListComponent} from "../restaurants-list/restaurants-list.component";
import {MockRestaurantsService} from "../mock-restaurants.service";

@Component({
  selector: 'app-price-filter',
  templateUrl: './price-filter.component.html',
  styleUrls: ['./price-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PriceFilterComponent implements OnInit {

  model = {
    euro1: false,
    euro2: false,
    euro3: false
  };

  constructor(private restaurantService: MockRestaurantsService) { }

  ngOnInit() {
  }

  onChange(event): void {
    console.log(event);
    //this.restaurantService.addFilter([{address : "test", email : "test", id : 42, name : "test", tel_number : "test", url_img: "test"}])
    //this.restaurantService.addFilter("http://localhost:8080/javaee7-angular/resources/restaurants/search?disponibility=MORNING&day=MONDAY" )
  }

}
