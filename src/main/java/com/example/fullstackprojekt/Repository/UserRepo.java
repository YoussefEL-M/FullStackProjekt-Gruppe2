package com.example.fullstackprojekt.Repository;

/*import com.example.fullstackprojekt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;*/
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

   /* @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createUser(User user) {
        String sql = "INSERT INTO user (name, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
    }

    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ?, username = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getId());
    }

    public void deleteUserById(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username, password);
    }*/
}
