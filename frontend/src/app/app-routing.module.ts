import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {HotelComponent} from "./hotel/hotel.component";
import {Home1Component} from "./home1/home1.component";
import {RentaCarComponent} from "./renta-car/renta-car.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {ViewHotelComponent} from "./view-hotel/view-hotel.component";
import {ViewAvioComponent} from "./view-avio/view-avio.component";
import {ViewCarComponent} from "./view-car/view-car.component";
import {AvioCompanyComponent} from "./avio-company/avio-company.component";
import {FlightComponent} from "./flight/flight.component";
import {PrevCarComponent} from "./prev-car/prev-car.component";
import {ChangePassComponent} from "./change-pass/change-pass.component";


const appRoutes: Routes = [
  {path: 'home',component: Home1Component},
  {path:'login', component: LoginComponent},
  {path:'rentaCar',component: RentaCarComponent},
  {path:'registration', component: RegistrationComponent},
  {path:'hotel', component: HotelComponent},
  {path:'',redirectTo:'/home',pathMatch:'full'},
  {path:'userProfile', component : UserProfileComponent},
  {path:'viewHotel',component:ViewHotelComponent},
  {path:'viewAvio',component:ViewAvioComponent},
  {path:'viewCar',component:ViewCarComponent},
  {path:'avioCompany',component:AvioCompanyComponent},
  {path:'flight',component:FlightComponent},
  {path:'prevCar',component:PrevCarComponent},
  {path:'viewChangePass',component:ChangePassComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
