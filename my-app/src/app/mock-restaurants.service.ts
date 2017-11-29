import {EventEmitter, Injectable, Output} from '@angular/core';
import {Restaurant} from "./Restaurant";
import {PaginatedListWrapper} from "./PaginatedListWrapper";
import {Subject} from "rxjs/Subject";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Filter} from './Filter';

@Injectable()
export class MockRestaurantsService {
  restaurants : Restaurant[] = [];

  filter : Filter = {
    disponibility : [],
    day : [],
    speciality : [],
    nb_person : null,
  };

  private filterAddedSource = new Subject<string>();
  filterAdded = this.filterAddedSource.asObservable();

  constructor(private http: HttpClient) {}

  getRestaurants(url): Observable<PaginatedListWrapper> {
    return(this.http.get<PaginatedListWrapper>(url))

  }
  addFilter(type, value): void {
    console.log(type);
    console.log(value);
    if (type == "disponibility"){
      this.filter.disponibility = value;
    } else if( type == "nb_person"){
      this.filter.nb_person = value;
    } else if (type == "day"){
      this.filter.day = value;
    } else if (type == "speciality"){
      this.filter.speciality = value;
    }

    console.log(this.filter);
    var url = 'http://localhost:8080/javaee7-angular/resources/restaurants/search';
    this.getRestaurants(url);
    this.filterAddedSource.next(url);
  }
}
