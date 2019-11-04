package com.example.goBookYourself.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"seat_number", "flight"})})
public class Ticket {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight")
    private Flight flight;

    @Version
    private Long version;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @Column(name = "passenger_username", length = 25)
    private String passengerUsername;

    @Column(name = "passenger_name", length = 25)
    private String passengerName;

    @Column(name = "passenger_surname",length = 25)
    private String passengerLastName;

    @Column(name = "passport_number", length = 50)
    private String passportNumber;

    @Column(name = "fast_reservation_discount", columnDefinition = "Decimal(3, 0) default '0'")
    private int fastReservationDiscount;

    @Column(name = "reserved", columnDefinition = "boolean default false")
    private boolean reserved;

    public Ticket(){}

    public Ticket(Flight flight, int seatNumber) {
        this.flight = flight;
        this.seatNumber = seatNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
}
