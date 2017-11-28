import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FiltersComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  public filterPrices(model): void{
    if (model.euro1 == true){
      console.log("euro1")
    }
  }

}
