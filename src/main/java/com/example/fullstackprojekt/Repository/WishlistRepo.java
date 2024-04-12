package com.example.fullstackprojekt.Repository;

import com.example.fullstackprojekt.Model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wishlist> getWishlistsForUser(int user_id){
        String sql = "SELECT * FROM wishlists WHERE user_id = ?";
        RowMapper<Wishlist> rowMapper = new BeanPropertyRowMapper<>(Wishlist.class);
        return jdbcTemplate.query(sql, rowMapper, user_id);
    }

    public void updateName(String name){

    }
}

/*public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }*/