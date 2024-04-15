package com.example.fullstackprojekt.Repository;

import com.example.fullstackprojekt.Model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    private static final Logger logger = LoggerFactory.getLogger(UserRepo.class);

   @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
            if (rowsAffected > 0) {
                logger.info("User created successfully: {}", user.getUsername());
            } else {
                logger.error("Failed to create user: {}", user.getUsername());
            }
        } catch (DataAccessException e) {
            logger.error("Error creating user: {}", user.getUsername(), e);
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, username = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getId());
    }

    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username, password);
    }

    public void showReservation(int id, boolean owner) {



    }
}
