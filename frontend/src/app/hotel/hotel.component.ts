import { Component, OnInit } from '@angular/core';
import {HotelService} from "./hotel.service";
import {FlightService} from "../flight/flight.service";
import {RoomService} from "../room/room.service";
import {DatePipe} from "@angular/common";
import {HttpClient} from "@angular/common/http";

declare var $:any;

@Component({
  selector: 'app-hotel',
  templateUrl: '../hotel.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  HotName;
  DateIn;
  priceF = 0;
  priceT = 0;
  SelectRoom;
  dateIn : Date;
  public typeOfRoom=[];
  public AllHotels;
  public roomSel=[];
  public hotels={};
  public favourAdd=[];
  public room={};
  public HotelFav;
  public HotelFav1;
  public HotelFav2;
  public hotelSearc;
  public hotelSer;
  public roomChecked=[];
  public roomSearc;
  public roomSer;
  public ukupnoSelektovanihSoba=0;
  public roomType;
  public roomType1;
  public roomType2;
  public gosti;
  public hot=true;
  public info=true;
  public rooms=true;
  public favours=true;
  public ticket;
  public hotId;
  constructor(private http : HttpClient, private hotelService : HotelService,private flightService : FlightService,private roomService:RoomService){}

  ngOnInit() {
      this.hot=null;
      this.info=true;
      this.rooms=true;
      this.favours=true;
      this.flightService.getReservation();
      this.ticket = JSON.parse(localStorage.getItem('reservedFlight'));
      console.log("Rezervisani tiketi");
      console.log(this.ticket);
      this.hotelService.getAllHotels();
      this.hotels = JSON.parse(localStorage.getItem("hotel"));
      this.hotelService.getAllHotels();
      this.http.get('api/hotel/all').subscribe(data=> this.AllHotels=data);
      this.hotelSearc = this.hotels;
      this.hotelSer = this.hotels;
      this.room = JSON.parse(localStorage.getItem("roomSearch"));
      localStorage.removeItem("roomSearch");
    this.changeStepper();
  }

  hotelPrev(hot)
  {
    //prosledjuje komponentu
    this.hotelService.getHotel(hot);

  }
  searchHotel(event)
  {

    event.preventDefault();
    const target = event.target;
    const date_in = target.querySelector('#dateIn').value;
    const date_out = target.querySelector('#dateOut').value;
    console.log(this.DateIn);
    if(this.HotName && date_in && date_out)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.HotName || hotels.address === this.HotName))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_in >= date_in) && (room.date_out <= date_out));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";

    }else if(this.HotName && date_in)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.HotName || hotels.address === this.HotName))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_in >= date_in));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";
    }else if(this.HotName && date_out)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.HotName || hotels.address === this.HotName))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_out <= date_out));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";

    }else if(date_in && date_out)
    {
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_in >= date_in && room.date_out <= date_out));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";
    }else if(this.HotName)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.HotName || hotels.address === this.HotName))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      location.href = "http://localhost:4200/hotel";
    }else if(date_in)
    {
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => room.date_in >= date_in);
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";

    }else if(date_out)
    {
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => room.date_out <= date_out);
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";
    }
  }
  hotel()
  {
    this.hot=null;
    this.info=true;
    this.rooms=true;
    this.favours=true;
    this.hotelService.getAllHotels();
    this.hotels = JSON.parse(localStorage.getItem("hotel"));
  }
  information()
  {
    this.hot=true;
    this.info=null;
    this.rooms=true;
    this.favours=true;
  }
  showRoom()
  {
    this.hot=true;
    this.info=true;
    this.rooms=null;
    this.favours=true;
  }
  favour()
  {
    this.hot=true;
    this.info=true;
    this.rooms=true;
    this.favours=null;
    this.hotelService.getAllHotels();
    this.HotelFav2=JSON.parse(localStorage.getItem('hotel'));
    this.HotelFav1 = this.HotelFav2;
    this.HotelFav1 = this.HotelFav2.filter((HotelFav) => HotelFav.id ==this.hotels[0].id);
    this.HotelFav = this.HotelFav1[0].priceList;

  }
  chooseHotel(event)
  {
    event.preventDefault();
    this.hotId = event.target.querySelector("input[name='hotelId']:checked").value;
    if(this.ticket.length == 0)
    {
      alert('Nije rezervisan let');
      this.hotel();
    }else
    {
      this.hotelSearc=this.hotels;
      this.hotelSer = this.hotels;
      this.hotels = this.hotelSer.filter((hotels) => hotels.id == this.hotId);
      this.roomType=this.hotels[0].rooms;
      console.log("Sobe u hotelu");
      console.log(this.roomType);
      this.typeOfRoom=[];
      for(var i=0;i<this.roomType.length;i++)
      {
        if(this.typeOfRoom.indexOf(this.roomType[i].name) > -1)
        {

        }else
        {
          this.typeOfRoom.push(this.roomType[i].name);
        }
      }
      console.log(this.typeOfRoom);
    }
  }
  infoData(event)
  {
    event.preventDefault();
    this.http.get('api/hotel/all').subscribe(data => this.hotels=data);
    this.hotelSearc=this.hotels;
    this.hotelSer = this.hotels;
    this.hotels = this.hotelSer.filter((hotels) => hotels.id == this.hotId);
    this.roomType=this.hotels[0].rooms;

    var dateIn=event.target.querySelector("#dat").value;
    this.dateIn = new Date(event.target.querySelector("#dat").value);
    var night = event.target.querySelector("#night").value;
    this.gosti = event.target.querySelector("#guest").value;
    var count = event.target.querySelector("#count").value;

    this.dateIn.setDate(this.dateIn.getDate()+(+night));
    var DateTransform = new DatePipe("en-US");
    var addDate = DateTransform.transform(this.dateIn.toDateString(),'yyyy-MM-dd');

    this.roomType2=this.roomType;
    this.roomType1=this.roomType;
    console.log(this.roomType1);
    if(this.priceF==0 && this.priceT==0)
    {
      this.roomType1=this.roomType2.filter((roomType1) => roomType1.date_in == dateIn && roomType1.date_out >=addDate && roomType1.discountPrice == 0);
        console.log("SOBE NAKON BEZ CENE");
        console.log(this.roomType1);
    }else if(this.priceF > 0)
    {
      this.roomType1=this.roomType2.filter((roomType1) => roomType1.date_in == dateIn && roomType1.date_out >=addDate &&
        roomType1.price >= this.priceF && roomType1.discountPrice == 0);
      console.log("SOBE NAKON FILTRIRANJA OD");
      console.log(this.roomType1);
    }else if(this.priceT > 0)
    {
      this.roomType1=this.roomType2.filter((roomType1) => roomType1.date_in == dateIn && roomType1.date_out >=addDate &&
        roomType1.price <= this.priceT &&roomType1.discountPrice == 0);
      console.log("SOBE NAKON FILTRIRANJA DO");
      console.log(this.roomType1);
    }else
    {
      this.roomType1=this.roomType2.filter((roomType1) => roomType1.date_in == dateIn && roomType1.date_out >=addDate &&
        (roomType1.price >= this.priceF && roomType1.price <= this.priceT && roomType1.discountPrice == 0));
      console.log("SOBE NAKON BEZ CENE");
      console.log(this.roomType1);
    }
    this.roomSel=[];
    for(var j=0;j<this.SelectRoom.length;j++)
    {
      this.roomType2 = this.roomType1.filter((roomType1) => roomType1.name == this.SelectRoom[j]);
      if(this.roomType2.length>count)
      {
        for(var k=0;k<count;k++)
        {
          this.roomSel.push(this.roomType2[k]);
        }
      }else
      {
        for(var k=0;k<this.roomType2.length;k++)
        {
          this.roomSel.push(this.roomType2[k]);
        }
      }
    }
    this.roomType1=this.roomSel;
    if(this.roomType1.length > 0)
    {
      //uvecanje cene nocenja u zavisnosti od broja noci
      for(var i=0;i<this.roomType1.length;i++)
      {
        this.roomType1[i].price = this.roomType1[i].price*(+night);
      }
    }
  }
  RoomReserved(event)
  {
    event.preventDefault();
  }
  RoomSelect(room)
  {

    if(this.ticket.length >= this.gosti+1)
    {
      alert("Broj kreveta premasuje broj karata");
    }else
    {
      if(this.roomChecked.indexOf(room) > -1)
      {
        var i = this.roomChecked.indexOf(room);
        this.roomChecked[i]={};
      }else
      {
        for(var i=0;i<this.roomChecked.length;i++)
        {
           this.ukupnoSelektovanihSoba += this.roomChecked[i].number_bed;
           if(this.ukupnoSelektovanihSoba>this.ticket.length)
           {
             alert('Previse kreveta je seletovano!');
             break;
           }
        }
        this.roomChecked.push(room);
      }
    }

    //sobe koje su cekirane
    console.log("Cekirane sobe");
    console.log(this.roomChecked);
  }
  HotelFavour(event)
  {
    event.preventDefault();
    //slanje rezervacije na back :D
    console.log("SOBE KOJE SE SALJU NA REZERVACIJU");
    console.log(this.roomChecked);
    console.log("DODATAK HOTEL");
    console.log(this.favourAdd);
    this.roomService.ReservedRoom(this.roomChecked,this.favourAdd);
  }
  AddFavour(favour)
  {
    if(this.favourAdd.indexOf(favour) > -1)
    {
      var i = this.favourAdd.indexOf(favour);
      this.favourAdd[i]={};

    }else
    {
      this.favourAdd.push(favour);
    }
    //usluge koje su izabrane od strane korisnika
    console.log(this.favourAdd);
  }
  changeStepper()
  {
    if(this.ticket.length == 0)
    {
      $("#2").removeClass("btn-primary");
      $("#1").removeClass("btn-default");
      $("#1").addClass("btn-primary");
    }else
    {
      $("#next1").click(function(){
        $("#1").removeClass("btn-primary");
        $("#2").removeClass("btn-default");
        $("#2").addClass("btn-primary");
      });
      $("#prev1").click(function(){
        $("#2").removeClass("btn-primary");
        $("#1").removeClass("btn-default");
        $("#1").addClass("btn-primary");
      });
      $("#next2").click(function(){
        $("#2").removeClass("btn-primary");
        $("#3").removeClass("btn-default");
        $("#3").addClass("btn-primary");
      });
      $("#prev2").click(function(){
        $("#3").removeClass("btn-primary");
        $("#2").removeClass("btn-default");
        $("#2").addClass("btn-primary");
      });
      $("#next3").click(function(){
        $("#3").removeClass("btn-primary");
        $("#4").removeClass("btn-default");
        $("#4").addClass("btn-primary");
      });
      $("#prev3").click(function(){
        $("#4").removeClass("btn-primary");
        $("#3").removeClass("btn-default");
        $("#3").addClass("btn-primary");
      });
    }
  }
}
