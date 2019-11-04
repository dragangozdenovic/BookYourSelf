package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Friendship;
import com.example.goBookYourself.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAllByFirstFriendOrSecondFriend(User sender, User receiver);
    List<Friendship> findAllBySecondFriendAndValid(User receiver, boolean valid);
    Friendship findByFirstFriendAndSecondFriend(User sender, User receiver);
    Integer deleteByFirstFriendAndSecondFriend(User sender, User receiver);
}
