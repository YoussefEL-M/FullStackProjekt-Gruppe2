package com.example.fullstackprojekt.Repository;

import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wishlist> getWishlistsForUser(int user_id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM wishlists WHERE user_id = ?";
        RowMapper<Wishlist> rowMapper = new BeanPropertyRowMapper<>(Wishlist.class);
        return jdbcTemplate.query(sql, rowMapper, user_id);
    }
    public Wishlist getWishlistById(int id) throws EmptyResultDataAccessException{
        String sql = "SELECT * FROM wishlists WHERE id = ?";
        RowMapper<Wishlist> rowMapper = new BeanPropertyRowMapper<>(Wishlist.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void creatWishlist(Wishlist wishlist){
        String sql = "INSERT INTO wishlists (id, user_id, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, wishlist.getId(), wishlist.getUserId(), wishlist.getName());
    }

    public void updateName(String name){

    }
}

/*public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }*/