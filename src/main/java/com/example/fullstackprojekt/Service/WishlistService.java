package com.example.fullstackprojekt.Service;

import com.example.fullstackprojekt.Model.Wishlist;
import com.example.fullstackprojekt.Repository.WishRepo;
import com.example.fullstackprojekt.Repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepo wishlistRepository;

    public List<Wishlist> getWishlistsForUser(int user_id){
        return wishlistRepository.getWishlistsForUser(user_id);
    }
    public Wishlist getWishlistById(int id) throws EmptyResultDataAccessException {
        return wishlistRepository.getWishlistById(id);
    }

    public void createWishlist(Wishlist wishlist){
        wishlistRepository.createWishlist(wishlist);
    }

    public void deleteWishlist(int id){
        wishlistRepository.deleteWishlistsById(id);
    }

    public void updateWishlist(Wishlist wishlist){
        wishlistRepository.updateWishlist(wishlist);
    }

}
