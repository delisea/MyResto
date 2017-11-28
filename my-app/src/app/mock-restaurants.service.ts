import {EventEmitter, Injectable, Output} from '@angular/core';
import {Restaurant} from "./Restaurant";
import {Subject} from "rxjs/Subject";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";


@Injectable()
export class MockRestaurantsService {
  restaurants : Restaurant[] = [];

  private filterAddedSource = new Subject<string>();
  filterAdded = this.filterAddedSource.asObservable();

  constructor(private http: HttpClient) {}

  getRestaurants(url): Observable<Restaurant[]> {
    return(this.http.get<Restaurant[]>(url))

  }

  addFilter(url): void {
    console.log(url);
    this.getRestaurants(url);
    this.filterAddedSource.next(url);
  }






}
