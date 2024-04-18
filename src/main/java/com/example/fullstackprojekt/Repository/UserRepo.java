package com.example.fullstackprojekt.Repository;

import com.example.fullstackprojekt.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword());
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, username = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getPassword(), user.getId());
    }

    public void deleteUserById(int id) throws EmptyResultDataAccessException {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public User getUserById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM users WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public User getUserByUsername(String username) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }

    public User getUserByUsernameAndPassword(String username, String password) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, username, password);
    }

    public List<User> getFriendRequests(int user_id){
        String sql = "SELECT users.name, users.id, users.password, users.username FROM users INNER JOIN ON friend_relations.friend_id = ? WHERE friend_relations.pending = true";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper, user_id);
    }

    public boolean checkIfFriends(int user1, int user2){
        String sql = "SELECT users.name, users.id, users.password, users.username FROM users INNER JOIN ON friend_relations.user_id = ? WHERE friend_relations.friend_id = ? and friend_relations.pending = false" +
                "UNION SELECT * FROM users INNER JOIN ON friend_relations.friend_id = ? WHERE friend_relations.user_id = ? and friend_relations.pending = false";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return !jdbcTemplate.query(sql, rowMapper, user1, user2, user1, user2).isEmpty();
    }
    /*
    public void sendFriendRequest(int user_id, int friend_id){
        String sql = "INSERT INTO friend_relations (user_id, friend_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, user_id, friend_id);
    }
    public void updateFriendRequest(int user_id, int friend_id, boolean accepted){
        String sql;
        if(accepted){
            sql = "UPDATE friend_relations SET"
        }
    }

    public void removeFriend(int user1, int user2){

    }
    */


}
