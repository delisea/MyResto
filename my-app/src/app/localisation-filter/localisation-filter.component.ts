import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {GeoCodingService} from '../geocoding.service'
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/distinctUntilChanged';

const states = ['Alabama', 'Alaska', 'American Samoa', 'Arizona', 'Arkansas', 'California', 'Colorado',
  'Connecticut', 'Delaware', 'District Of Columbia', 'Federated States Of Micronesia', 'Florida', 'Georgia',
  'Guam', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine',
  'Marshall Islands', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana',
  'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Northern Mariana Islands', 'Ohio', 'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virgin Islands', 'Virginia',
  'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];

@Component({
  selector: 'app-localisation-filter',
  templateUrl: './localisation-filter.component.html',
  styleUrls: ['./localisation-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LocalisationFilterComponent implements OnInit {

  constructor(private geocodeService: GeoCodingService) { }

  ngOnInit() {
  }
  public model: any;
  public lat: number;
  public lng: number;

  geocode(){
    this.geocodeService.geocodeAddress(this.model).then((results) => {
      console.log('results', results);
      const result   = results[0],
      location = result.geometry.location;

      // @types/googlemaps describe the Javascript API not the JSON object on the response
      // there a sublte difference like lat/lng beeing number not functions, making this `<any>` cast necessary
        this.lat = <any>location.lat;
        this.lng = <any>location.lng;
    });
  }
  
  

  search = (text$: Observable<string>) =>
    text$
      .debounceTime(200)
      .distinctUntilChanged()
      .map(term => term.length < 2 ? []
        : states.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10));
}

