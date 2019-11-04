import { Component, OnInit } from '@angular/core';
import {RentaCarService} from "../renta-car/renta-car.service";

@Component({
  selector: 'app-prev-car',
  templateUrl:'./prevCar.html',
  styleUrls: ['./prev-car.component.css']
})
export class PrevCarComponent implements OnInit {

  public car={};
  constructor(private rentCar : RentaCarService) { }

  ngOnInit() {
    this.rentCar.newData1.subscribe(data=>{
      this.car = data;
  });
  }
}
