import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { Restaurant } from "../Restaurant";
import { PaginatedListWrapper } from "../PaginatedListWrapper";
import { MockRestaurantsService } from "../mock-restaurants.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Menu } from "../Menu";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MenuComponent implements OnInit {

  menus: Menu[];

  models = [];

  public restaurants: Restaurant[];
  public paginatedListWrapper: PaginatedListWrapper;
  public restaurant_id: number;

  constructor(private restaurantService: MockRestaurantsService, private router: Router, private route: ActivatedRoute) {
    this.route.params.subscribe(params =>
    this.restaurant_id = params['id'])
  }

  ngOnInit() {
    this.restaurantService.getMenus(this.restaurant_id)
      .subscribe(
      menus => {
        this.menus = menus;
        console.log(menus);
        for (let menu of menus) {
          this.models.push({ value: 0 });
        }
      }
      )
  }
}
