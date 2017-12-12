import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { FiltersComponent } from './filters/filters.component';
import {InscriptionConnexionComponent} from "./inscription-connexion/inscription-connexion.component";
import {ValidationComponent} from "./validation/validation.component";

const appRoutes: Routes = [
    { path: 'restaurant/:id',
      component: RestaurantDetailsComponent,
      data: {title:'restaurant details'}
    },
    { path: 'inscription',
      component: InscriptionConnexionComponent,
      data: {title:'Inscription'}
    },
  { path: 'validation',
    component: ValidationComponent,
    data: {title:'Validation'}
  },
    {
      path: 'restaurants',
      component: RestaurantsListComponent,
      data: { title: 'Restaurants List' }
    },
    { path: '',
      redirectTo: 'restaurants',
      pathMatch: 'full'
    }
    //{ path: '**', component: RestaurantsListComponent }
];
@NgModule({
    imports: [
      RouterModule.forRoot(
        appRoutes,
        {
          enableTracing: true, // <-- debugging purposes only
		  useHash: true
        }
      )
    ],
    exports: [
      RouterModule
    ]
})

export class AppRoutingModule { }
