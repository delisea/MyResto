import { Component, ViewEncapsulation, ElementRef, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { } from 'googlemaps';
import { MapsAPILoader } from '@agm/core';
import {MockRestaurantsService} from "../mock-restaurants.service";
import {LocalisationFilter} from '../LocalisationFilter';
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
  public rayon: number;
  public searchControl: FormControl;
  public zoom: number;
  public hideMap: boolean;
  private localisationFilter : LocalisationFilter;

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
    this.zoom = 5;
    this.hideMap = false;

    //create search FormControl
    this.searchControl = new FormControl();
    this.localisationFilter = new LocalisationFilter();

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
          this.localisationFilter.latitude = this.latitude;
          this.localisationFilter.longitude = this.longitude;
          this.restaurantService.addFilter("coordinates",this.localisationFilter);

        });
      });
    });
  }

  rayonAdded(){
    this.localisationFilter.rayon = this.rayon;
    this.restaurantService.addFilter("coordinates",this.localisationFilter);
  }

  clearSearch(){
    this.rayon = null;
    this.searchElementRef.nativeElement.value = null;
    this.localisationFilter.rayon = null;
    this.localisationFilter.latitude = null;
    this.localisationFilter.longitude = null;
    this.restaurantService.addFilter("coordinates",this.localisationFilter);
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