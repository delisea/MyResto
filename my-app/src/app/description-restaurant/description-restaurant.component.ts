import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MockRestaurantsService } from "../mock-restaurants.service";
import { Restaurant } from '../Restaurant';
import {MatDatepickerInputEvent} from "@angular/material";

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

  events: string[] = [];

  date = null;

  change_date(event: MatDatepickerInputEvent<Date>) {
    this.date = event.value;
  }

  ngOnInit() {
    this.restaurantService.getRestaurant(this.restaurant_id)
    .subscribe(restaurant => this.restaurant = restaurant)
  }

  favoriteDispo: string;

  nbPersons = 0;

  dispos = [
    'Morning',
    'Midday',
    'Evening',
  ];

}
