import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { Restaurant } from "../Restaurant";
import { PaginatedListWrapper } from "../PaginatedListWrapper";
import { MockRestaurantsService } from "../mock-restaurants.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Menu } from "../Menu";
import { Meal } from "../Meal";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";


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
  public total_price: number;

  constructor(private restaurantService: MockRestaurantsService, private router: Router, private route: ActivatedRoute, private http: HttpClient) {
    this.route.params.subscribe(params =>
    this.restaurant_id = params['id'])
  }

  ngOnInit() {
    this.total_price = 0;
    this.restaurantService.getMenus(this.restaurant_id)
      .subscribe(
      menus => {
        this.menus = menus;
        for (let menu of menus) {
          this.getMealsByMenuId(menu.id).subscribe(meals => menu.meals = meals);
          this.models.push({ value: 0 });
        }
      }
      )   
  }

  getMealsByMenuId(menu_id):Observable<Meal[]>{
    return this.http.get<Meal[]>("http://myresto-myresto.193b.starter-ca-central-1.openshiftapps.com/javaee7-angular/resources/meal/getMealsByMenuId?menu_id="+menu_id);
  }

  calculatePrice(){
    this.total_price = 0;
    if(this.menus !== undefined){
      for(let menu of this.menus){
        if(menu.numberOfCommand !== undefined)
          this.total_price+=menu.price*menu.numberOfCommand;
      }
    }
  }
}
