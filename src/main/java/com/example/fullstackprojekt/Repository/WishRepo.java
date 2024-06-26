package com.example.fullstackprojekt.Repository;

import com.example.fullstackprojekt.Model.Wish;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class WishRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wish> getAllWishes() {
        String sql = "SELECT * FROM wishes";
        RowMapper<Wish> rowMapper = new BeanPropertyRowMapper<>(Wish.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

        public List<Wish> getWishesInWishlist(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM wishes WHERE wishlist_id = ?";
        RowMapper<Wish> rowMapper = new BeanPropertyRowMapper<>(Wish.class);
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public void createWish(Wish wish) {
        String sql = "INSERT INTO wishes (name, price, amount, description, url, wishlist_id, image_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, wish.getName(), wish.getPrice(), wish.getAmount(), wish.getDescription(), wish.getUrl(), wish.getWishlist_id(), wish.getImage_url());
    }

    public void deleteWishById(int id){
        String sql = "DELETE FROM wishes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Wish getWishById(int id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM wishes WHERE id = ?";
        RowMapper<Wish> rowMapper = new BeanPropertyRowMapper<>(Wish.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void updateWish(Wish wish) {
        String sql = "UPDATE wishes SET name = ?, price = ?, amount = ?, description = ?, url = ?, reserved = ?, reserved_by = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, wish.getName(), wish.getPrice(), wish.getAmount(), wish.getDescription(), wish.getUrl(), wish.isReserved(), wish.getReserved_by(), wish.getImage_url(), wish.getId());
    }

}
