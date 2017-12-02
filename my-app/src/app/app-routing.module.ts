import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantsListComponent } from './restaurants-list/restaurants-list.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { FiltersComponent } from './filters/filters.component';

const appRoutes: Routes = [
    { path: 'restaurant/:id', 
      component: RestaurantDetailsComponent,
      data: {title:'restaurant details'}
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
        }
      )
    ],
    exports: [
      RouterModule
    ]
})

export class AppRoutingModule { }