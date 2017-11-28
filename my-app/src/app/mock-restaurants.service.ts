import {EventEmitter, Injectable, Output} from '@angular/core';
import {Restaurant} from "./Restaurant";
import {PaginatedListWrapper} from "./PaginatedListWrapper";
import {Subject} from "rxjs/Subject";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";


@Injectable()
export class MockRestaurantsService {
  restaurants : Restaurant[] = [];

  private filterAddedSource = new Subject<string>();
  filterAdded = this.filterAddedSource.asObservable();

  constructor(private http: HttpClient) {}

  getRestaurants(url): Observable<PaginatedListWrapper> {
    return(this.http.get<PaginatedListWrapper>(url))

  }
  addFilter(url): void {
    console.log(url);
    this.getRestaurants(url);
    this.filterAddedSource.next(url);
  }
}
