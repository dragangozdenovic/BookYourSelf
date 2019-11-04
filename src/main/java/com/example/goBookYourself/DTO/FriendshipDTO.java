package com.example.goBookYourself.DTO;

import com.example.goBookYourself.model.Friendship;
import com.example.goBookYourself.model.User;

public class FriendshipDTO {

    private UserDTO sender;
    private UserDTO receiver;
    private boolean valid;
    private Long version;

    public FriendshipDTO(){}

    public FriendshipDTO(User sender, User receiver, boolean valid, Long version){
        this.sender = new UserDTO(sender);
        this.receiver = new UserDTO(receiver);
        this.valid = valid;
        this.version = version;
    }

    public FriendshipDTO(Friendship friendship){
        this(friendship.getFirstFriend(), friendship.getSecondFriend(), friendship.isValid(), friendship.getVersion());
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDTO receiver) {
        this.receiver = receiver;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
