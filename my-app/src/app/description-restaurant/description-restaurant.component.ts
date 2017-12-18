import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MockRestaurantsService } from "../mock-restaurants.service";
import { Restaurant } from '../Restaurant';
import { MatDatepickerInputEvent } from "@angular/material";
import { HttpClient, HttpHeaders } from "@angular/common/http";


@Component({
  selector: 'app-description-restaurant',
  templateUrl: './description-restaurant.component.html',
  styleUrls: ['./description-restaurant.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DescriptionRestaurantComponent implements OnInit {

  private restaurant_id: number;
  public restaurant: Restaurant = new Restaurant();
  constructor(private http: HttpClient,private restaurantService: MockRestaurantsService, private router: Router, private route: ActivatedRoute) {
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


  book(){
    console.log("bruh");
    console.log(this.favoriteDispo);
    console.log(this.nbPersons)
    console.log(this.date);
    let headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    headers.set('accept','application/json');
    headers.set('person_id','2');
    headers.set('restaurant_id',this.restaurant_id.toString());
    headers.set('Access-Control-Allow-Origin','*');
    headers.set('Access-Control-Allow-Methods','Access-Control-Allow-Methods');
    
    let reservation = {
      'id':0,
      'nbcouverts':this.nbPersons,
      'periode':this.favoriteDispo,
      'date':this.date
    }
    let url = 'http://myresto-myresto.193b.starter-ca-central-1.openshiftapps.com/javaee7-angular/resources/reservations';
    this.http.post(url,reservation,{headers:headers}).subscribe(
      res => {
        this.router.navigate(['/inscription']);
      },
      err => console.log(err)
    );
  }
}
