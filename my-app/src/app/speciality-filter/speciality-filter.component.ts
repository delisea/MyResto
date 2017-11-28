import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-speciality-filter',
  templateUrl: './speciality-filter.component.html',
  styleUrls: ['./speciality-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SpecialityFilterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    this.specialities.valueChanges.subscribe(
      form => console.log(form)
    )
  }

  specialities = new FormControl();

  specialityList = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
}
