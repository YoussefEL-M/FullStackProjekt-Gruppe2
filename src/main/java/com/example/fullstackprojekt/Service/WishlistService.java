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
        wishlistRepository.creatWishlist(wishlist);
    }
    public List<Wishlist> getFollowedWishlists(int user_id){
        return wishlistRepository.getFollowedWishlists(user_id);
    }
    public void addFollowedWishlist(int user_id, int wishlist_id){
        wishlistRepository.addFollowedWishlist(user_id, wishlist_id);
    }
    public void removeFollowedWishlist(int user_id, int wishlist_id){
        wishlistRepository.removeFollowedWishlist(user_id, wishlist_id);
    }
}
