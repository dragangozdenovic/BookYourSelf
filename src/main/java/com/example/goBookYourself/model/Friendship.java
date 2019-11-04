package com.example.goBookYourself.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"id", "sender", "receiver"})})
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private boolean valid;

    @Version
    Long version;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender", nullable = false)
    private User firstFriend;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver", nullable = false)
    private User secondFriend;

    public Friendship(){};

    public Friendship(User firstFriend, User secondFriend) {
        this.firstFriend = firstFriend;
        this.secondFriend = secondFriend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public User getFirstFriend() {
        return firstFriend;
    }

    public void setFirstFriend(User firstFriend) {
        this.firstFriend = firstFriend;
    }

    public User getSecondFriend() {
        return secondFriend;
    }

    public void setSecondFriend(User secondFriend) {
        this.secondFriend = secondFriend;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
