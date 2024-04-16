package com.example.fullstackprojekt.Service;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User getUserById(int id) {
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
    public User getUserByUsername(String name){
        return userRepository.getUserByUsername(name);
    }
    public User getUserByNameAndPassword(String name, String password){
        return userRepository.getUserByUsernameAndPassword(name, password);
    }
}
