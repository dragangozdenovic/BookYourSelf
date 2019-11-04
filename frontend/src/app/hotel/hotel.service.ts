import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";

@Injectable()
export class HotelService {

  public newData = new Subject<any>();
  constructor(private http : HttpClient) { }

  getAllHotels()
  {
    return this.http.get("api/hotel/all").subscribe(data => { localStorage.setItem("hotel", JSON.stringify(data));});
  }
  getHotel(data)
  {
    this.newData.next(data);
  }
  addHotel(name,address,promo)
  {
    return this.http.post('api/hotel/add',{"name":name,"address":address,"promoDescription":promo}).subscribe(data => alert("Uspesno dodat hotel"))
  }

}
