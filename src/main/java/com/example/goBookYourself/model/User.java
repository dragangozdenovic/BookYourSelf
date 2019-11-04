package com.example.goBookYourself.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotBlank(message = "Please enter your username")
    @Size(min=4,message = "Username must be at least 4 characters long")
    @Column(name = "username", unique = true, nullable = false, length = 25)
    private String username;

    @NotBlank(message = "Please enter your email")
    @Email(message = "Please enter a valid email")
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @JsonIgnore
    @Column(name = "pass", nullable = false, length = 250)
    @NotBlank(message = "Please enter your password")
    @Size(min = 5, message = "Your password must be at least 5 characters long")
    private String password;

    @NotBlank(message = "Please enter your first name")
    @Size(min = 1,message = "Name is too short")
    @Column(name = "firstName", nullable = false, length = 25)
    private String name;

    @NotBlank(message = "Please enter your last name")
    @Size(min = 1,message = "Name is too short" )
    @Column(name = "lastName", nullable = false, length = 25)
    private String surname;

    @NotBlank(message = "Please enter city name")
    @Size(min=1,message = "Name is too short")
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotBlank(message = "Please enter your phone number")
    @Pattern(regexp = "[0-9]{3}-[0-9]{7}",message = "Number must look like: 064-1234567")
    @Column(name = "phoneNumber", unique = true, nullable = false, length = 15)
    private String number;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @Column(name = "pass_change_req", nullable = false)
    private boolean passChanged;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @OneToMany(mappedBy = "firstFriend", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Friendship> friendships = new HashSet<>();

    @OneToMany(mappedBy = "secondFriend", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Friendship> friendshipsCopy = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "avio_company")
    private AvioCompany avioCompany;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "Hotel")
    private Hotel admin_hotel;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_hotel_rating",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_name", referencedColumnName = "name"))
    private Set<Hotel> hotels = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade =CascadeType.PERSIST )
    private Set<CarRating> carRatings = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(Set<Friendship> friendships) {
        this.friendships = friendships;
    }

    public Set<Friendship> getFriendshipsCopy() {
        return friendshipsCopy;
    }

    public void setFriendshipsCopy(Set<Friendship> friendshipsCopy) {
        this.friendshipsCopy = friendshipsCopy;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public AvioCompany getAvioCompany() {
        return avioCompany;
    }

    public void setAvioCompany(AvioCompany avioCompany) {
        this.avioCompany = avioCompany;
    }
    public Set<CarRating> getCarRatings() {
        return carRatings;
    }

    public void setCarRatings(Set<CarRating> carRatings) {
        this.carRatings = carRatings;
    }

    public Hotel getAdmin_hotel() {
        return admin_hotel;
    }

    public void setAdmin_hotel(Hotel admin_hotel) {
        this.admin_hotel = admin_hotel;
    }
    public boolean isPassChanged() {
        return passChanged;
    }

    public void setPassChanged(boolean passChanged) {
        this.passChanged = passChanged;
    }
}
