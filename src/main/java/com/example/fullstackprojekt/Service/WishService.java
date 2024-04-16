package com.example.fullstackprojekt.Service;

import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Repository.WishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishRepo wishRepository;

    public List<Wish> getAllWishes() {
        return wishRepository.getAllWishes();
    }
    public List<Wish> getWishesInWishlist(int wishlist_id)  {
        return wishRepository.getWishesInWishlist(wishlist_id);
    }

    public void createWish(Wish wish) {
        wishRepository.createWish(wish);
    }

    public void updateWish(Wish wish) {
        wishRepository.updateWish(wish);
    }

    public void deleteWishById(int id) {
        wishRepository.deleteWishById(id);
    }

    public Wish getWishById(int id) {
        return wishRepository.getWishById(id);
    }

}
