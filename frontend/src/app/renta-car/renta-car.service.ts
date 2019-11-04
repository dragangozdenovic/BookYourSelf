import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RentaCarService {

  public newData = new Subject<any>();
  public newData1 = new Subject<any>();
  constructor(private http : HttpClient) { }

  getAllRentaCar()
  {
    return this.http.get("api/rent/all").subscribe(data => { localStorage.setItem("rentaCar", JSON.stringify(data));});
  }

  getCars(data)
  {
    this.newData.next(data);
  }
  getBranchAll()
  {
    return this.http.get('api/branches/all').subscribe(data=>{localStorage.setItem("branch",JSON.stringify(data));})
  }
  getAllCars()
  {
    return this.http.get('api/car/all').subscribe(data=>{localStorage.setItem("cars",JSON.stringify(data))});
  }

  getRentaCar(data)
  {
    this.newData1.next(data);
  }
  addRenta(name,address,promo)
  {
    return this.http.post('api/rent/add',{"name":name,"address":address,"promoDescription":promo}).subscribe(data=>alert("Uspesno dodat renta car"));
  }
}
