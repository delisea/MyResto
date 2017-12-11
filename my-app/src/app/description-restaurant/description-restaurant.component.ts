import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MockRestaurantsService } from "../mock-restaurants.service";
import { Restaurant } from '../Restaurant';

@Component({
  selector: 'app-description-restaurant',
  templateUrl: './description-restaurant.component.html',
  styleUrls: ['./description-restaurant.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DescriptionRestaurantComponent implements OnInit {

  private restaurant_id: number;
  public restaurant: Restaurant = new Restaurant();
  constructor(private restaurantService: MockRestaurantsService, private router: Router, private route: ActivatedRoute) {
      this.route.params.subscribe(params =>
      this.restaurant_id = params['id'])  
      this.restaurant.description = "";
   }

  ngOnInit() {
    this.restaurantService.getRestaurant(this.restaurant_id)
    .subscribe(restaurant => this.restaurant = restaurant)
  }
}
