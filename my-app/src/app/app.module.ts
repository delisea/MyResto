import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { HeroService } from './hero.service';
import { MessageService } from './message.service';
import { MessagesComponent } from './messages/messages.component';

import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LocalisationFilterComponent } from './localisation-filter/localisation-filter.component';
import { FiltersComponent } from './filters/filters.component';
import { PriceFilterComponent } from './price-filter/price-filter.component';
import { DateFilterComponent } from './date-filter/date-filter.component';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';
import { RestaurantPreviewComponent } from './restaurant-preview/restaurant-preview.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { MockRestaurantsService } from './mock-restaurants.service';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { FlexLayoutModule } from "@angular/flex-layout";

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
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatStepperModule,
} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailComponent,
    MessagesComponent,
    LocalisationFilterComponent,
    FiltersComponent,
    PriceFilterComponent,
    DateFilterComponent,
    RestaurantsListComponent,
    RestaurantPreviewComponent,
    RestaurantDetailsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
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
  ],
  providers: [ HeroService, MessageService, MockRestaurantsService, HttpClientModule ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
