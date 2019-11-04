import { Component, OnInit } from '@angular/core';
import {RentaCarService} from "../renta-car/renta-car.service";

@Component({
  selector: 'app-view-car',
  templateUrl: './viewCar.html',
  styleUrls: ['./view-car.component.css']
})
export class ViewCarComponent implements OnInit {

  public viewCar = {};
  public adresa="";
  public adresa1={};
  public adresa2="";
  public adresa3="";
  public branch = {};
  public cars={};

  constructor(private carService : RentaCarService) { }

  ngOnInit() {
    this.carService.newData.subscribe(data=>{
      this.viewCar = data;
      this.adresa = data.address;
      console.log(""+this.adresa);
      this.adresa1 = this.adresa.split(" ");
      this.adresa2=this.adresa1[0];
      this.adresa3=this.adresa1[1];
      console.log("Adresa: "+this.adresa2+" "+this.adresa3);
      console.log(data);
    });
    this.carService.getBranchAll();
    this.branch = JSON.parse(localStorage.getItem('branch'));
    console.log(this.branch);
    //localStorage.removeItem('branch');
    this.carService.getAllCars();
    this.cars = JSON.parse(localStorage.getItem('cars'));
    console.log(this.cars);
    //localStorage.removeItem('cars');

  }

}
