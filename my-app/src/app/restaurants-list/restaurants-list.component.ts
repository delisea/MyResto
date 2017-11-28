import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {Restaurant} from "../Restaurant";
import {MockRestaurantsService} from "../mock-restaurants.service";

@Component({
  selector: 'app-restaurants-list',
  templateUrl: './restaurants-list.component.html',
  styleUrls: ['./restaurants-list.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RestaurantsListComponent implements OnInit {

   public restaurants : Restaurant[];

  // getRestaurants(): void {
  //   this.restaurantService.getRestaurants()
  //     .subscribe(restaurants => this.restaurants = restaurants)
  // }

  constructor(private restaurantService: MockRestaurantsService) {
    restaurantService.filterAdded.subscribe(
      url => {
        this.restaurantService.getRestaurants(url)
          .subscribe(restaurants => this.restaurants = restaurants)
      });
  }

  ngOnInit() {
    this.restaurantService.getRestaurants('http://localhost:8080/javaee7-angular/resources/restaurants/search')
      .subscribe(restaurants => this.restaurants = restaurants);
    console.log(this.restaurants)
  }



}
