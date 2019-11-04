import { Component, OnInit } from '@angular/core';
import {RentaCarService} from "./renta-car.service";

@Component({
  selector: 'app-renta-car',
  templateUrl: '../rentaCar.html',
  styleUrls: ['./renta-car.component.css',
  '../home/css/slicknav.min.css',
  '../home/css/vegas.min.css']
})
export class RentaCarComponent implements OnInit {

  public car={};
  public viewCar;
  constructor(private rentCar : RentaCarService) { }

  ngOnInit() {
      this.rentCar.getAllCars();
      this.car = JSON.parse(localStorage.getItem('cars'));
      console.log(this.car);
  }

  rentaPrev(ca)
  {
    this.viewCar=ca;
    this.rentCar.getRentaCar(ca);
  }
}
