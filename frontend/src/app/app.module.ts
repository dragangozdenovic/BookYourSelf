import { BrowserModule } from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import { RouterModule } from "@angular/router";
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { RegistrationComponent } from './registration/registration.component';
import { HotelComponent } from './hotel/hotel.component';
import { Home1Component } from './home1/home1.component';
import { RentaCarComponent } from './renta-car/renta-car.component';
import {HotelService} from "./hotel/hotel.service";
import { AvioCompanyComponent } from './avio-company/avio-company.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RoomComponent } from './room/room.component';
import {SafePipe, ViewHotelComponent} from './view-hotel/view-hotel.component';
import { ViewAvioComponent } from './view-avio/view-avio.component';
import { ViewCarComponent } from './view-car/view-car.component';
import { FlightComponent } from './flight/flight.component'
import {IntercepterService} from "./intercepter.service";
import { PrevCarComponent } from './prev-car/prev-car.component';
import {MatSliderModule} from '@angular/material/slider';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from "@angular/material";
import {MatInputModule,MatAutocompleteModule,MatNativeDateModule} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ChangePassComponent } from './change-pass/change-pass.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HotelComponent,
    Home1Component,
    RentaCarComponent,
    AvioCompanyComponent,
    UserProfileComponent,
    RoomComponent,
    ViewHotelComponent,
    ViewAvioComponent,
    ViewCarComponent,
    FlightComponent,
    PrevCarComponent,
    ChangePassComponent,
    SafePipe
  ],schemas:[NO_ERRORS_SCHEMA],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    MatSliderModule,
    FormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatInputModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [HotelService,
    {
    provide : HTTP_INTERCEPTORS,
    useClass: IntercepterService,
    multi   : true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
