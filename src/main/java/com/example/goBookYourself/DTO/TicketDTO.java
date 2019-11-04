package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Flight;
import com.example.goBookYourself.model.Ticket;

public class TicketDTO {

    private Integer id;
    private int seatNumber;
    private String passengerUsername;
    private String passengerName;
    private String passengerLastName;
    private String passportNumber;
    private int fastReservationDiscount;
    private boolean reserved;
    private FlightDTO flight;
    private Long version;

    public TicketDTO() {

    }

    public TicketDTO(Integer id, int seatNumber, String passengerName, String passengerUsername, String passengerLastName,
                     String passportNumber, int fastReservationDiscount, boolean reserved, Flight flight, Long version) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.passengerUsername = passengerUsername;
        this.passengerName = passengerName;
        this.passengerLastName = passengerLastName;
        this.passportNumber = passportNumber;
        this.fastReservationDiscount = fastReservationDiscount;
        this.reserved = reserved;
        this.flight = new FlightDTO(flight);
        this.version = version;
    }

    public TicketDTO(Ticket ticket) {
        this(ticket.getId(), ticket.getSeatNumber(), ticket.getPassengerUsername(), ticket.getPassengerName(), ticket.getPassengerLastName(),
                ticket.getPassportNumber(), ticket.getFastReservationDiscount(), ticket.isReserved(), ticket.getFlight(), ticket.getVersion());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassengerUsername() {
        return passengerUsername;
    }

    public void setPassengerUsername(String passengerUsername) {
        this.passengerUsername = passengerUsername;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getFastReservationDiscount() {
        return fastReservationDiscount;
    }

    public void setFastReservationDiscount(int fastReservationDiscount) {
        this.fastReservationDiscount = fastReservationDiscount;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
