insert into jdbc.avio_company (name,image,promo_description)
                             values ('Air France','airfrance.jpg','Promo Opis AirFrance');
insert into jdbc.avio_company (name,image,promo_description)
values ('Lufthansa','lufthansa.jpg','The Lufthansa Group is an aviation company with operations worldwide. In the financial year 2017, the Lufthansa Group generated revenue of EUR 35.6bn and employed an average of 128,856 staff.');
insert into jdbc.avio_company (name,image,promo_description)
values ('Air Serbia','airserbia.png','Er Srbija već leti do ukupno 40 destinacije u Evropi, na Mediteranu, Bliskom istoku i u Severnoj Americi, kako u putničkom, tako i u teretnom saobraćaju.');
insert into jdbc.avio_company (name,image,promo_description)
values ('Swiss','swiss.png','SWISS is The Airline of Switzerland. It serves over 100 destinations all over the world from Zurich, Geneva and Lugano. SWISS is part of the Lufthansa Group and a member of the Star Alliance.');

insert into jdbc.user (username, email, pass, first_name, last_name, city, phone_number, enabled, pass_change_req)
values ('user1', 'neshkekv96@gmail.com', '$2a$10$uT0lZ2AQG2qNEVZn5zQfP.tng7Di09OEH4OwHK4bx4gdpYSqLQds2', 'Nenad', 'Marković', 'kraljevo', '069-5257007', true , true);

insert into jdbc.user (username, email, pass, first_name, last_name, city, phone_number, enabled, pass_change_req,hotel)
values ('user2', 'svarli1@gmail.com', '$2a$10$uT0lZ2AQG2qNEVZn5zQfP.tng7Di09OEH4OwHK4bx4gdpYSqLQds2', 'Nikola', 'Nikolic', 'kraljevo', '069-5257008', true, true,2);

insert into jdbc.user (username, email, pass, first_name, last_name, city, phone_number, enabled, pass_change_req, avio_company)
values ('user3', 'svarli2@gmail.com', '$2a$10$uT0lZ2AQG2qNEVZn5zQfP.tng7Di09OEH4OwHK4bx4gdpYSqLQds2', 'Marko', 'Djenovic', 'kraljevo', '069-52570098', true, true, 3);

insert into jdbc.user (username, email, pass, first_name, last_name, city, phone_number, enabled,pass_change_req)
values ('user4', 'kg@gmail.com', '$2a$10$uT0lZ2AQG2qNEVZn5zQfP.tng7Di09OEH4OwHK4bx4gdpYSqLQds2', 'Alex', 'Vas', 'kragujevac', '069-5257001', true,true);

insert into jdbc.avio_prices (name, price, avio_company) values ('Luggage <= 5kg', 50, 3);
insert into jdbc.avio_prices (name, price, avio_company) values ('Luggage <= 10kg', 100, 3);
insert into jdbc.avio_prices (name, price, avio_company) values ('Luggage <= 20kg', 150, 3);

insert into jdbc.authority (name) values ('USER');
insert into jdbc.authority (name) values ('AVIO_ADMIN');
insert into jdbc.authority (name) values ('HOTEL_ADMIN');
insert into jdbc.authority (name) values ('RENTACAR_ADMIN');
insert into jdbc.authority (name) values ('ADMIN_ADMIN');


insert into jdbc.user_authority(user_id, authority_id) values (1, 1);
insert into jdbc.user_authority(user_id, authority_id) values (2, 1);

insert into jdbc.user_authority(user_id, authority_id) values (3, 1);
insert into jdbc.user_authority(user_id, authority_id) values (3, 2);

insert into jdbc.user_authority(user_id, authority_id) values (2, 3);
insert into jdbc.user_authority(user_id, authority_id) values (4, 5);

insert into jdbc.friendship(sender, receiver, valid) values (1, 2, true);

insert into jdbc.hotel (name , address , income,image)
values ('wow_hotel','Zlatibor 25','100 000','hotel-1.jpg');

insert into jdbc.hotel (name , address , income,image)
values ('grand_hotel','Kopaonik bb','250 000','hotel-3.jpg');

insert into jdbc.room (name, number_of_room,reserved,hotel_id,date_in,date_out,price,number_bed,number_floor,version)
value ('jednokrevetna','25',false ,1,'2014-05-07','2014-05-08 ',25,1,1,0);
insert into jdbc.room (name, number_of_room,reserved,hotel_id,date_in,date_out,price,number_bed,number_floor,version)
value ('dvokrevetna','3',false,2,'2019-01-26','2019-01-30',50,2,2,0);
insert into jdbc.room (name, number_of_room,reserved,hotel_id,date_in,date_out,price,number_bed,number_floor,version)
value ('trokrevetna','202',false,1,'2018-01-31 ','2018-02-05',100,3,2,0);
insert into jdbc.room (name, number_of_room,reserved,hotel_id,date_in,date_out,price,discount_price,number_bed,number_floor,version)
value ('trokrevetna','201',false,1,'2018-01-31 ','2018-02-01',75,65,3,4,0);
insert into jdbc.room (name, number_of_room,reserved,hotel_id,date_in,date_out,price,discount_price,number_bed,number_floor,version)
value ('jednokrevetna','101',false,2,'2018-01-26','2018-02-01',35,0,1,2,0);
insert into jdbc.room (name, number_of_room,reserved,hotel_id,date_in,date_out,price,discount_price,number_bed,number_floor,version)
value ('dvokrevetna','205',false,2,'2018-01-26','2018-02-12',40,0,2,4,0);

insert into jdbc.service(name,price,discount)
values ('wifi',50,10);
insert into jdbc.service(name,price,discount)
values ('Swimming pool',100,15);
insert into jdbc.service(name,price,discount)
values ('breakfast',25,15);

insert into jdbc.hotel_price_service (hotel_name, service_name)
values ('grand_hotel','wifi');
insert into jdbc.hotel_price_service (hotel_name, service_name)
values ('wow_hotel','breakfast');
insert into jdbc.hotel_price_service (hotel_name, service_name)
values ('grand_hotel','Swimming pool');

insert into jdbc.flight(arrival_time, departure_time, flight_time, ticket_price, company, destination, version)
values ('2018-02-03 11:00','2018-01-31 12:00',1.5,150,3,1,0);


insert into jdbc.flight(arrival_time, departure_time, flight_time, ticket_price, company, destination, version)
values ('2018-05-15 12:00','2018-06-21 15:00',1.5,100,2,2,0);

insert into jdbc.flight(arrival_time, departure_time, flight_time, ticket_price, company, destination, version)
values ('2018-05-15 12:00','2018-06-21 15:00',1.5,100,1,1,0);


insert into jdbc.location (airport, city, country, connecting_flight)
VALUES ('Nikola Tesla','Kraljevo','Serbia',1);

insert into jdbc.location (airport, city, country, connecting_flight)
VALUES ('Batajnica','Kragujevac','Serbia',2);

insert into jdbc.location(airport, city, country) values ('a', 'a', 'a');
insert into jdbc.location(airport, city, country) values ('b', 'b', 'b');

insert into jdbc.location_avio_company(avio_company_id, location_id) values (3, 1);
insert into jdbc.location_avio_company(avio_company_id, location_id) values (3, 2);


insert into jdbc.branch_office(address,name,rent_service)
values ('Neznanog junaka','enterprise',2);

insert into jdbc.rentacar(name,image,promoting_add,address)
VALUES ('Braca Djukic','rentaCar.jpg','Top renta car service','Stevana Nemanje');

insert into jdbc.rentacar(name,image,promoting_add,address)
VALUES ('Braca Karic','rentaCar1.jpg','Ime sve govori :D','Kraljice Marije,Beograd');


insert into jdbc.car(image,producer,type,model,price_per_day,posessed_car_report,production_year,rent_house,branch_office)
value('car-1.jpg','BMW','limuzina','520d',30,true,2010,1,1);

insert into jdbc.ticket(version, fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0, 10,'','','',true,1,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0, 0,'','','',true,2,1);
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,3,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',true,4,1);
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,5,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,50,'','','',false,6,1);
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,7,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,8,2);

/*DUPLIRAOOO*/
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,9,1);
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,10,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,11,1);
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,12,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,13,1);
insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,14,1);

insert into jdbc.ticket(version,fast_reservation_discount, passenger_surname, passenger_name, passport_number, reserved, seat_number, flight)
VALUES(0,0,'','','',false,15,2);