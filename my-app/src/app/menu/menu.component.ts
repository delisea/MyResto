import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {Restaurant} from "../Restaurant";
import {PaginatedListWrapper} from "../PaginatedListWrapper";
import {MockRestaurantsService} from "../mock-restaurants.service";
import { Router } from '@angular/router';
import {Menu} from "../Menu";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MenuComponent implements OnInit {

  menus:Menu[];

  models = [];

  public restaurants : Restaurant[];
  public paginatedListWrapper : PaginatedListWrapper;

  constructor(private restaurantService: MockRestaurantsService, private router: Router) {

  }

  ngOnInit() {
    this.restaurantService.getMenus("38")
      .subscribe(
        menus => {this.menus=menus;
        console.log(menus);
          for (let menu of menus){
            this.models.push({value:0});
          }
        }
    )

  }

}
