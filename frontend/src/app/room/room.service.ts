import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http : HttpClient) { }

  getAllRoom()
  {
    return this.http.get('api/room/all').subscribe(data => {
      console.log(data);
      localStorage.setItem("room", JSON.stringify(data))});
  }

  deleteRoom(room)
  {
    return this.http.put('api/room/deleteRoom',room).subscribe(data=>alert('Uspesno obrisana soba'));
  }

  editRoom(id,name,dateI,dateO,price,number_of_room,reserved,bed,floor)
  {
    return this.http.put('api/room/editRoom',{
      "id":id,"name":name,"date_in":dateI,"date_out":dateO,"price":price,"number_of_room":number_of_room,"reserved":reserved,
      "number_bed":bed,"number_floor":floor
    }).subscribe(data=>{alert('Uspesno editovao sobu'); location.reload();});
  }

  addR(name,dateI,dateO,price,number_room,reserved,hot,bed,floor)
  {
    return this.http.post('api/room/addRoom',
      {
        "name":name,"number_of_room":number_room,"reserved":reserved,"date_in":dateI,"date_out":dateO,"hot":hot,"price":price,
        "number_bed":bed,"number_floor":floor}).subscribe(data => {alert('Uspesno dodavanje soba'); location.reload();});
  }

  addFavour(id,name,price,discount)
  {
    return this.http.post('api/price/addService',{"id":id,"name":name,"price":price,"discount":discount}).subscribe(data=>{alert('Uspesno dodat');location.reload();});
  }
  ReservedRoom(room,service)
  {
    return this.http.post('api/hotelReservation/addReservation',{"rooms":room,"hotelServicePrice":service}).subscribe(data=>{alert('Uspesno rezervisan hotel');location.reload();})
  }
  /*getReservedRoom()
  {
    return this.http.get('api/hotelReservation/all').subscribe(data => localStorage.setItem('reservedRoom',JSON.stringify(data)));
  }*/


}
