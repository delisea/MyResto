import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { Router} from '@angular/router';
import { AppRoutingModule }        from './app-routing.module';
import { AppComponent } from './app.component';
import {NavbarComponent} from "./navbar/navbar.component";
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LocalisationFilterComponent } from './localisation-filter/localisation-filter.component';
import { FiltersComponent } from './filters/filters.component';
import { PriceFilterComponent } from './price-filter/price-filter.component';
import { DateFilterComponent } from './date-filter/date-filter.component';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';
import { RestaurantPreviewComponent } from './restaurant-preview/restaurant-preview.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { MockRestaurantsService } from './mock-restaurants.service';
import { GeoCodingService } from './geocoding.service';
import { AgmCoreModule } from '@agm/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import {DisponibilityFilterComponent} from './disponibility-filter/disponibility-filter.component';
import {SpecialityFilterComponent} from './speciality-filter/speciality-filter.component';
import { FlexLayoutModule } from "@angular/flex-layout";
import {PersonFilterComponent} from './person-filter/person-filter.component';
import {PageSizeFilterComponent} from './pageSize-filter/pageSize-filter.component';

import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSidenavContainer,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatStepperModule,
  MatOptionModule,
} from '@angular/material';
import { DescriptionRestaurantComponent } from './description-restaurant/description-restaurant.component';
import { MenuComponent } from './menu/menu.component';
import { InscriptionConnexionComponent } from './inscription-connexion/inscription-connexion.component';
import 'hammerjs';
import { ValidationComponent } from './validation/validation.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';


@NgModule({
  declarations: [
    AppComponent,
    LocalisationFilterComponent,
    FiltersComponent,
    PriceFilterComponent,
    DateFilterComponent,
    RestaurantsListComponent,
    RestaurantPreviewComponent,
    RestaurantDetailsComponent,
    DisponibilityFilterComponent,
    SpecialityFilterComponent,
    NavbarComponent,
    PersonFilterComponent,
    PageSizeFilterComponent,
    DescriptionRestaurantComponent,
    MenuComponent,
    InscriptionConnexionComponent,
    ValidationComponent,
    PrivacyPolicyComponent
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: "AIzaSyAMAiC8zck-0vAdoaFnZx9Y2e-Z-TK1PVU",
      libraries: ["places"]
    }),
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    MatSliderModule,
    MatIconModule,
    MatNativeDateModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatListModule,
    FlexLayoutModule,
    MatCardModule,
    MatToolbarModule,
    MatOptionModule,
    MatSelectModule,
    MatSidenavModule,
    MatTabsModule,
    MatRadioModule
  ],
  providers: [ MockRestaurantsService, GeoCodingService, HttpClientModule ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}
