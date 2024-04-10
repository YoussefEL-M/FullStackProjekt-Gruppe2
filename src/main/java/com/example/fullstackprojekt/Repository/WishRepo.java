package com.example.fullstackprojekt.Repository;

/*import com.example.demo.model.Wish;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;*/
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class WishRepo {

    /*@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wish> getAllWishes() {
        String sql = "SELECT * FROM wish";
        RowMapper<Wish> rowMapper = new BeanPropertyRowMapper<>(Wish.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createWish(Wish wish) {
        String sql = "INSERT INTO wish (name, price, amount, description, url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, wish.getName(), wish.getPrice(), wish.getAmount(), wish.getDescription(), wish.getUrl());
    }

    public void deleteWishById(int id) {
        String sql = "DELETE FROM wish WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Wish getWishById(int id) {
        String sql = "SELECT * FROM wish WHERE id = ?";
        RowMapper<Wish> rowMapper = new BeanPropertyRowMapper<>(Wish.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public void updateWish(Wish wish) {
        String sql = "UPDATE wish SET name = ?, price = ?, amount = ?, description = ?, url = ? WHERE id = ?";
        jdbcTemplate.update(sql, wish.getName(), wish.getPrice(), wish.getAmount(), wish.getDescription(), wish.getUrl(), wish.getId());
    }*/
}
