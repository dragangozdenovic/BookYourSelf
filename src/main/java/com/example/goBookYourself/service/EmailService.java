package com.example.goBookYourself.service;

import com.example.goBookYourself.model.Reservation;
import com.example.goBookYourself.model.Ticket;
import com.example.goBookYourself.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment enviroment;

    @Async
    public void sendMail(User user, String tokenValue) throws MailException {
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String controller = "/api/users/completeRegister?token=" + tokenValue;

        String url = uri + controller;

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(enviroment.getProperty("spring.mail.username"));
        mail.setSubject("noreply");
        mail.setText("You have recently made an account on our website." +
                "\r\n\r\nYour credentials are: " +
                "\r\n   username: " + user.getUsername() +
                "\r\n   name: " + user.getName() +
                "\r\n   last name: " + user.getSurname() +
                "\r\n\r\nPlease verify you account by clicking on the link: \r\n" + url
        );
        mailSender.send(mail);
    }

    @Async
    public void sendMailFlightReservation(User user, Reservation reservation)  throws MailException {
        String text = "You have recently made a reservation on our website." +
                "\r\n\r\nYour reservation is: ";

        for(Ticket ticket : reservation.getTickets()){
            text += "\r\n   Ticket: " +
                    "\r\n       Flight: " + ticket.getFlight().getCompany() +
                    "\r\n       Price: " + ticket.getFlight().getTicketPrice() +
                    "\r\n       Destination: " + ticket.getFlight().getDestination().getAirport() + ", "
                    + ticket.getFlight().getDestination().getCity() + ", " + ticket.getFlight().getDestination().getCountry() +
                    "\r\n       Seat No: " + ticket.getSeatNumber() +
                    "\r\n       Passenger: " + ticket.getPassengerName() + " " + ticket.getPassengerLastName() +
                    "\r\n       Passport: " + ticket.getPassportNumber();
        }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(enviroment.getProperty("spring.mail.username"));
        mail.setSubject("noreply");
        mail.setText(text);
        mailSender.send(mail);
    }

    @Async
    public void sendMailFlightReservationAccept(User user, Reservation reservation, String tokenValue)  throws MailException {
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String controller = "/api/reservations/rejectReservation?token=" + tokenValue;

        String url = uri + controller;


        String text = "You have recently been added to a reservation on our website." +
                "\r\n\r\nYour reservation is: ";

        for(Ticket ticket : reservation.getTickets()){
            text += "\r\n   Ticket: " +
                    "\r\n       Flight: " + ticket.getFlight().getCompany() +
                    "\r\n       username: " + ticket.getFlight().getTicketPrice() +
                    "\r\n       username: " + ticket.getFlight().getDestination().getAirport() + ", "
                    + ticket.getFlight().getDestination().getCity() + ", " + ticket.getFlight().getDestination().getCountry() +
                    "\r\n       Seat No: " + ticket.getSeatNumber() +
                    "\r\n       Passenger: " + ticket.getPassengerName() + " " + ticket.getPassengerLastName() +
                    "\r\n       Passport: " + ticket.getPassportNumber();
        }

        text += "\r\nTo reject reservation click on the following link: " +
                "\r\n" + url;

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(enviroment.getProperty("spring.mail.username"));
        mail.setSubject("noreply");
        mail.setText(text);
        mailSender.send(mail);
    }
}
