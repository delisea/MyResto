import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MockRestaurantsService } from "../mock-restaurants.service";
import { Restaurant } from '../Restaurant';
import { MatDatepickerInputEvent } from "@angular/material";
import { HttpClient, HttpHeaders, HttpParams  } from "@angular/common/http";


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
    headers.append('Content-Type', 'application/json; charset=utf-8')
    .append('accept','application/json')
    .append('Access-Control-Allow-Origin','*')
    .append('Access-Control-Allow-Methods','GET, POST, DELETE, PUT');

    
    let params = new HttpParams().set('restaurant_id', this.restaurant_id.toString());

    let reservation = {
      'id':0,
      'nbCouverts':this.nbPersons,
      'periode':this.favoriteDispo.toUpperCase(),
      'date':this.date
    }
    let url = 'http://18.196.18.169/javaee7-angular/resources/reservations';
    this.http.post(url,reservation,{headers:headers, params:params}).subscribe(
      res => {
        console.log(res);
        this.router.navigate(['/inscription']);
      },
      err => console.log(err)
    );
  }
}
