import { Component, OnInit } from '@angular/core';
import {HotelService} from "../hotel/hotel.service";
import {AvioCompanyService} from "../avio-company/avio-company.service";
import {RentaCarService} from "../renta-car/renta-car.service";
import {RoomService} from "../room/room.service";
import {FlightService} from "../flight/flight.service";


@Component({
  selector: 'app-home1',
  templateUrl: '../home1.html',
  styleUrls: ['/home1.component.css',
    '../home/css/animate.css',
    '../home/css/aos.css',
    '../home/css/bootstrap/bootstrap-grid.css',
    '../home/css/bootstrap/bootstrap-reboot.css',
    '../home/css/flaticon.css',
    '../home/css/icomoon.css',
    '../home/css/ionicons.min.css',
    '../home/css/jquery.timepicker.css',
    '../home/css/magnific-popup.css',
    '../home/css/open-iconic-bootstrap.min.css',
    '../home/css/owl.theme.default.min.css',
    '../home/css/style.css']
})
export class Home1Component implements OnInit {

  HotName;
  public hotels = {};
  public avio = {};
  public room = {};
  public rentaCars={};

  public hotelView ;
  public avioView;
  public carView;

  public hotelSearc;
  public hotelSer;

  public roomSearc;
  public roomSer;

  public flightSearc;
  public flightSer;
  public flight;

  public location;
  public locationSer;
  public locationSearch;

  public destinacija={};

  public rentaSer;
  public rentaSearc;
  public branch;
  public car;
  public AllHotels;
  public reservedRoom;

  constructor(private service : HotelService,private serviceAvio : AvioCompanyService,
              private serviceRentaCar : RentaCarService,private roomService : RoomService,private flightServ : FlightService) { }

  ngOnInit() {
    this.service.getAllHotels();
    this.hotels = JSON.parse(localStorage.getItem("hotel"));
    this.AllHotels=this.hotels;
    this.hotelSearc=this.hotels;
    this.hotelSer=this.hotels;
    localStorage.removeItem("hotel");
    this.serviceAvio.getAllAvioCompany();
    this.avio = JSON.parse(localStorage.getItem("avioCompany"));
    localStorage.removeItem("avioCompany");
    this.serviceRentaCar.getAllRentaCar();
    this.roomService.getAllRoom();
    this.room = JSON.parse(localStorage.getItem('room'));
    localStorage.removeItem('room');
    this.serviceRentaCar.getAllRentaCar();
    this.rentaCars = JSON.parse(localStorage.getItem('rentaCar'));
    localStorage.removeItem('rentaCar');
    console.log(this.rentaCars);
    this.serviceRentaCar.getBranchAll();
    this.branch=JSON.parse(localStorage.getItem('branch'));
    console.log(this.branch);
    localStorage.removeItem('branch');
    this.serviceRentaCar.getAllCars();
    this.car = JSON.parse(localStorage.getItem('cars'));
    console.log(this.car);
    localStorage.removeItem('cars');
    this.flightServ.getAllFlight();
    this.flight = JSON.parse(localStorage.getItem('flights'));
    localStorage.removeItem('flights');
    this.flightServ.getLocation();
    this.location = JSON.parse(localStorage.getItem('location'));
    console.log(this.location);
    localStorage.removeItem('location');
  }

  searchHotel(event)
  {
    event.preventDefault();
    const target = event.target;
    const date_in = target.querySelector('#dateIn').value;
    const date_out = target.querySelector('#dateOut').value;

    if(this.AllHotels && date_in && date_out)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.AllHotels || hotels.address === this.AllHotels))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_in === date_in) && (room.date_out === date_out));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";

    }else if(this.AllHotels && date_in)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.AllHotels || hotels.address === this.AllHotels))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_in === date_in));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";
    }else if(this.AllHotels && date_out)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.AllHotels || hotels.address === this.AllHotels))
      localStorage.setItem("hotel",JSON.stringify(this.hotelSearc));
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_out === date_out));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";

    }else if(date_in && date_out)
    {
      this.roomSearc = this.room;
      this.roomSer = this.room;
      this.roomSearc = this.roomSer.filter((room) => (room.date_in >= date_in && room.date_out <= date_out));
      localStorage.setItem("roomSearch",JSON.stringify(this.roomSearc));
      location.href = "http://localhost:4200/hotel";
    }else if(this.AllHotels)
    {
      this.hotelSearc = this.hotelSer.filter((hotels) => (hotels.name === this.AllHotels || hotels.address === this.AllHotels))
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
      location.reload()
    }
  }

  searchFlight(event)
  {
    event.preventDefault();
    const target = event.target;
    const from = target.querySelector('#dest').value;
    const date_in = target.querySelector('#checkin_date1').value;
    const date_out = target.querySelector('#checkout_date2').value;
    const price = target.querySelector('#price').value;
    this.flightSer = this.flight;
    this.flightSearc = this.flight;
    if(from && date_in && date_out && price)
    {
      this.destinacija = from.split(",");
      this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1])&& date_out == JSON.stringify(flight.arrivalTime).substr(1, 16) && date_in == JSON.stringify(flight.departureTime).substr(1, 16) && price === flight.ticketPrice);
      localStorage.setItem('flights',JSON.stringify(this.flightSearc));
      location.href = "http://localhost:4200/flight";
    }else if(from && date_out && price)
  {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1])&& date_out == JSON.stringify(flight.arrivalTime).substr(1, 16) && price === flight.ticketPrice);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
    }else if(date_in && date_out && price)
    {
    this.flightSearc=this.flightSer.filter((flight) => date_out == JSON.stringify(flight.arrivalTime).substr(1, 16) && date_in == JSON.stringify(flight.departureTime).substr(1, 16) && price === flight.ticketPrice);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
    }else if(from && date_in && date_out)    {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1]) && date_out == JSON.stringify(flight.arrivalTime).substr(1, 16) && date_in == JSON.stringify(flight.departureTime).substr(1, 16));
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(from && date_in && price)
  {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1]) && date_in == JSON.stringify(flight.departureTime).substr(1, 16) && price === flight.ticketPrice);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(from && date_in)
  {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1]) && date_in == JSON.stringify(flight.departureTime).substr(1, 16));
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";

  }else if(from && date_out)
  {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1]) && date_out == JSON.stringify(flight.arrivalTime).substr(1, 16));
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(from && price)
  {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter((flight) => (flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1])&& price == flight.ticketPrice);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(date_in && date_out)
  {
    this.flightSearc=this.flightSer.filter((flight) => date_out == JSON.stringify(flight.arrivalTime).substr(1, 16) && date_in == JSON.stringify(flight.departureTime).substr(1, 16));
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(date_in && price)
  {
    this.flightSearc=this.flightSer.filter((flight) => date_in == JSON.stringify(flight.arrivalTime).substr(1, 16) && flight.ticketPrice === price);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(date_out && price)
  {
    this.flightSearc=this.flightSer.filter((flight) => date_out == JSON.stringify(flight.arrivalTime).substr(1, 16) && flight.ticketPrice === price);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(from)
  {
    this.destinacija = from.split(",");
    this.flightSearc=this.flightSer.filter( (flight) => flight.locationDTO.city === this.destinacija[0] && flight.locationDTO.country === this.destinacija[1]);
    console.log(this.flightSearc);
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(date_in)
  {
    console.log(date_in);
    this.flightSearc=this.flightSer.filter((flight) => date_in == JSON.stringify(flight.departureTime).substr(1, 16));
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";

  }else if(date_out)
  {
    this.flightSearc=this.flightSer.filter((flight) => date_out == JSON.stringify(flight.arrivalTime).substr(1, 16));
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }else if(price)
  {
    this.flightSearc=this.flightSer.filter((flight) => flight.ticketPrice === price)
    localStorage.setItem('flights',JSON.stringify(this.flightSearc));
    location.href = "http://localhost:4200/flight";
  }
  }

  searchRenta(event)
  {
    event.preventDefault();
    const target = event.target;
    const model = target.querySelector('#model').value;
    const year = target.querySelector('#year').value;
    const type = target.querySelector('#type').value;
    const producer = target.querySelector('#producer').value;

    this.rentaSer = this.car;
    this.rentaSearc = this.car;
    if(model && year && type && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.productionYear == year && car.type === type && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model && year && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.productionYear == year && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(year && type && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.productionYear == year && car.type === type && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model && type && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.type === type && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model && year && type )
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.productionYear == year && car.type === type));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(type && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.type === type && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(year && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.productionYear == year && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(year && type)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.productionYear == year && car.type === type));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model && producer)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.producer === producer));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model && type)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.type === type));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model && year)
    {
      this.rentaSearc = this.rentaSer.filter((car) => (car.model === model && car.productionYear == year));
      localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
      location.href = "http://localhost:4200/rentaCar";
    }else if(model)
  {
    this.rentaSearc = this.rentaSer.filter((car) => (car.model === model));
    localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
    location.href = "http://localhost:4200/rentaCar";
  }else if(year)
  {
    this.rentaSearc = this.rentaSer.filter((car) => (car.productionYear == year));
    localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
    location.href = "http://localhost:4200/rentaCar";
  }else if(type)
  {
    this.rentaSearc = this.rentaSer.filter((car) => (car.type === type));
    localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
    location.href = "http://localhost:4200/rentaCar";

  }else if(producer)
  {
    this.rentaSearc = this.rentaSer.filter((car) => (car.producer === producer));
    localStorage.setItem("cars",JSON.stringify(this.rentaSearc));
    location.href = "http://localhost:4200/rentaCar";
  }
  }

  avioComp(av)
  {
    this.avioView=av;
    this.serviceAvio.getAvioCompany(av);
  }

  hotelPrev(hot)
  {
    this.hotelView=hot;
    //prosledjuje komponentu
    this.service.getHotel(hot);

  }
  rentaPrev(car)
  {
    this.carView=car;
    this.serviceRentaCar.getCars(car);

  }

}
