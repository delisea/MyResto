import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-disponibility-filter',
  templateUrl: './disponibility-filter.component.html',
  styleUrls: ['./disponibility-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DisponibilityFilterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    this.disponibilities.valueChanges.subscribe(
      form => console.log(form)
    )
  }

  disponibilities = new FormControl();

  disponibilityList = ['Matin', 'Midi', 'Soir', 'Nuit'];

}
