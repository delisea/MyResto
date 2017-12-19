import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";
import { HttpClient } from "@angular/common/http";
import { Reservation } from '../Reservation';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AdminComponent implements OnInit {

  public reservations: Reservation[];

  constructor(private http: HttpClient) { }

  displayedColumns = ['id','nom_restaurant', 'nbCouverts', 'periode','date'];
  public dataSource:MatTableDataSource<Reservation>;

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  ngOnInit() {
    let url = 'https://18.196.18.169/javaee7-angular/resources/reservations';
    this.http.get<Reservation[]>(url).subscribe(reservations => this.dataSource = new MatTableDataSource(reservations));   
  }

}


