import { Component, ViewEncapsulation, ElementRef, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { } from 'googlemaps';
import { MapsAPILoader } from '@agm/core';
import {MockRestaurantsService} from "../mock-restaurants.service";
import {Coordinates} from '../Coordinates';
import { $ } from 'protractor';

@Component({
  selector: 'app-localisation-filter',
  templateUrl: './localisation-filter.component.html',
  styleUrls: ['./localisation-filter.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LocalisationFilterComponent implements OnInit {

 
  public latitude: number;
  public longitude: number;
  public searchControl: FormControl;
  public zoom: number;
  public hideMap: boolean;
  private coordinates : Coordinates;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private restaurantService: MockRestaurantsService

  ) {}

  showMapBool(){
    this.hideMap = false;
  }
  hideMapBool(){
    this.hideMap = true;
  }

  ngOnInit() {
    //set google maps defaults
    this.zoom = 4;
    this.latitude = 39.8282;
    this.longitude = -98.5795;
    this.hideMap = false;

    //create search FormControl
    this.searchControl = new FormControl();
    this.coordinates = new Coordinates();

    //set current position
    this.setCurrentPosition();

    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.setComponentRestrictions(
        {'country': ['fr']});
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 12;

          // call search
          this.coordinates.latitude = this.latitude;
          this.coordinates.longitude = this.longitude;
          this.restaurantService.addFilter("coordinates",this.coordinates);

        });
      });
    });
  }

  private setCurrentPosition() {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 12;
      });
    }
  }

}