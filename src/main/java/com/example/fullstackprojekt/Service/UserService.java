package com.example.fullstackprojekt.Service;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder; //Cryptere password

    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.createUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    public boolean authenticateUser(String username, String password){
        User user = userRepository.getUserByUsername(username);

        //Verificer
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return userRepository.getUserByUsernameAndPassword(username,password);
    }
}
