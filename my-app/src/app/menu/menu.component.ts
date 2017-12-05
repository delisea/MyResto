import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {Restaurant} from "../Restaurant";
import {PaginatedListWrapper} from "../PaginatedListWrapper";
import {MockRestaurantsService} from "../mock-restaurants.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MenuComponent implements OnInit {


  public restaurants : Restaurant[];
  public paginatedListWrapper : PaginatedListWrapper;

  constructor(private restaurantService: MockRestaurantsService, private router: Router) {
    restaurantService.filterAdded.subscribe(
      url => {
        this.restaurantService.getRestaurants(url)
          .subscribe(paginatedListWrapper => this.restaurants = paginatedListWrapper.restaurants)
      });
  }

  ngOnInit() {
    this.restaurantService.getRestaurants('http://myresto-myresto.193b.starter-ca-central-1.openshiftapps.com/javaee7-angular/resources/restaurants/search')
      .subscribe(paginatedListWrapper => this.restaurants = paginatedListWrapper.restaurants);
    console.log(this.restaurants)
  }

}
