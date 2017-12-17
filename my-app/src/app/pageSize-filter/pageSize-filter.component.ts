import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {MockRestaurantsService} from "../mock-restaurants.service";

@Component({
  selector: 'app-pageSize-filter',
  templateUrl: './pageSize-filter.component.html',
  styleUrls: ['./pageSize-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PageSizeFilterComponent implements OnInit {

  constructor(private restaurantService: MockRestaurantsService) {
  }

  ngOnInit() {
  }

  refreshList(pageSize) {
    this.restaurantService.addFilter("pageSize", pageSize);
  }
}
