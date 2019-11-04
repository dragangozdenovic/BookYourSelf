import {Component, OnInit} from '@angular/core';
import {HotelService} from "../hotel/hotel.service";
import {RoomService} from "../room/room.service";
import {FlightService} from "../flight/flight.service";
import { DomSanitizer } from '@angular/platform-browser';

import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'safe'
})
export class SafePipe implements PipeTransform {

  constructor(private sanitizer: DomSanitizer) {
  }

  transform(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}

@Component({
  selector: 'app-view-hotel',
  templateUrl:'./viewHotel.html',
  styleUrls: ['./view-hotel.component.css']
})
export class ViewHotelComponent implements OnInit {

  public viewHotel = {};
  public rooms1;
  public roomms;
  public freeRoom;
  public slobodneSobe=[];
  public rooms2;
  public logovan=true;
  public user;
  public rooms;
  public adresa;
  public adresa1;
  public adresa2="";
  public adresa3="";
  public ticket;
  public addFavour;
  public flight;
  public gmapUrl;
  public url = 'https://maps.google.com/maps?q=Kragujevac&t=&z=13&ie=UTF8&iwloc=&output=embed';


  constructor(private hotel : HotelService,private room : RoomService,private flightService : FlightService, public sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.user = localStorage.getItem('user');
    if(this.user!=null)
    {
      this.logovan=null;
    }
    this.room.getAllRoom();
    this.rooms = JSON.parse(localStorage.getItem("room"));
    this.roomms = JSON.parse(localStorage.getItem("room"));
    this.hotel.newData.subscribe(data=> {
      this.viewHotel = data;
      this.addFavour = data.priceList;
      console.log(this.addFavour);

      this.rooms = this.roomms.filter((rooms) => rooms.hot == data.id );
      this.rooms2 = this.rooms;
      this.rooms1 = this.rooms;
      this.adresa = data.address;
      this.adresa1 = this.adresa.split(" ");
      this.adresa2 = this.adresa1[0];
      this.adresa3 = this.adresa1[1];

      this.url = "https://maps.google.com/maps/embed/v1/place?key=AIzaSyBf_ZtT1M3c1fSVOJC_7c8yfRu-wdzidf8&q="+this.adresa2;
      this.gmapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.url);
      console.log(this.gmapUrl);

      this.flightService.getReservation();
      this.ticket = JSON.parse(localStorage.getItem('reservedFlight'));
      this.slobodneSobe = [];
      for (var j = 0; j < this.ticket.length; j++) {
        for (var i = 0; i < this.ticket[j].tickets.length; i++) {
          this.rooms1 = this.rooms2.filter((rooms1) => rooms1.date_in >= this.ticket[j].tickets[i].flight.departureTime.substring(0, 10) && this.ticket[j].tickets[i].flight.arrivalTime.substring(0, 10) >= rooms1.date_out
            && rooms1.discountPrice > 0 && rooms1.reserved == false)
          console.log(this.rooms1);
          this.slobodneSobe.push(this.rooms1);
        }
      }
      console.log(this.slobodneSobe);
      this.freeRoom = this.slobodneSobe[0];
       });
    }
  reserve(r)
  {
      var nizRoom=[];
      nizRoom.push(r);
      console.log(nizRoom);
      this.flightService.getReservation();
      this.flight=JSON.parse(localStorage.getItem('reservedFlight'));
      this.user = localStorage.getItem('user');
      if(this.flight[0].tickets[0].passengerName == this.user)
      {
        if(this.user!=null) {
           this.room.ReservedRoom(nizRoom, this.addFavour);
        }else
          alert('Morate se ulogovati');
      }else
      {
        alert('Nije rezervisan let');
      }
  }
}
