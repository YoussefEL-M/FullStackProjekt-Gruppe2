package com.example.fullstackprojekt.Service;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) throws EmptyResultDataAccessException {
        return userRepository.getUserById(id);
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }
    public User getUserByUsername(String name) throws EmptyResultDataAccessException{
        return userRepository.getUserByUsername(name);
    }
    public User getUserByNameAndPassword(String name, String password) throws EmptyResultDataAccessException{
        return userRepository.getUserByUsernameAndPassword(name, password);
    }
    public List<User> getFriendRequests(int user_id){
        return userRepository.getFriendRequests(user_id);
    }

    public boolean checkIfFriends(int user1, int user2){
        return userRepository.checkIfFriends(user1, user2);
    }

    public void sendFriendRequest(int user_id, int friend_id){
        userRepository.sendFriendRequest(user_id, friend_id);
    }
    public void updateFriendRequest(int user_id, int friend_id, boolean accepted){
        userRepository.updateFriendRequest(user_id, friend_id, accepted);
    }

    public void removeFriend(int user1, int user2){
        userRepository.removeFriend(user1, user2);
    }
}
