import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {MockRestaurantsService} from "../mock-restaurants.service";

@Component({
  selector: 'app-person-filter',
  templateUrl: './person-filter.component.html',
  styleUrls: ['./person-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PersonFilterComponent implements OnInit {
    
 public nbPerson: number;

  constructor(private restaurantService: MockRestaurantsService) { }

  ngOnInit() {
  }

  nbPersonAdded(){
    this.restaurantService.addFilter("nb_person",this.nbPerson);
}
