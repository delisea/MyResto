import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { FiltersComponent } from './filters/filters.component';
import {InscriptionConnexionComponent} from "./inscription-connexion/inscription-connexion.component";
import {AdminComponent} from "./admin/admin.component";
import {ValidationComponent} from "./validation/validation.component";
import {PrivacyPolicyComponent} from "./privacy-policy/privacy-policy.component";
import {FrenchComponent} from "./french/french.component";
import {FrenchvalidationComponent} from "./frenchvalidation/frenchvalidation.component";

const appRoutes: Routes = [
    { path: 'restaurant/:id',
      component: RestaurantDetailsComponent,
      data: {title:'restaurant details'}
    },
    { path: 'inscription',
      component: InscriptionConnexionComponent,
      data: {title:'Inscription'}
    },
    { path: 'admin',
    component: AdminComponent,
    data: {title:'Administration'}
  },
  { path: 'privacy',
    component: PrivacyPolicyComponent,
    data: {title:'Privacy policy'}
  },
  { path: 'validation',
    component: ValidationComponent,
    data: {title:'Validation'}
  },
  { path: 'french',
    component: FrenchComponent,
    data: {title:'French'}
  },
  { path: 'frenchvalidation',
    component: FrenchvalidationComponent,
    data: {title:'French validation'}
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
