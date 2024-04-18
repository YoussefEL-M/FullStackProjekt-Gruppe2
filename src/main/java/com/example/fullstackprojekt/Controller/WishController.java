package com.example.fullstackprojekt.Controller;

import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Model.Wishlist;
import com.example.fullstackprojekt.Model.Wishlist;
import com.example.fullstackprojekt.Model.User;
import com.example.fullstackprojekt.Model.Wish;
import com.example.fullstackprojekt.Service.UserService;
import com.example.fullstackprojekt.Service.WishService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fullstackprojekt.Repository.WishlistRepo;
import com.example.fullstackprojekt.Service.WishService;
import com.example.fullstackprojekt.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class WishController {

    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private WishService wishService;

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String displayFrontpage(Model model, HttpSession session) {

        User user = (User) session.getAttribute("User");
        if(user == null)
            user = new User(0, "No user", "No user", "null", false);
        model.addAttribute("user", user);
        return "forside";
    }

    @GetMapping("/login")
    public String login() {

        return "/login";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("User");
        return "redirect:/login";
    }

    @PostMapping("/loggingIn")
    public String loggingIn(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {
        try {
            User user = userService.getUserByUsername(username);
            if (user.getPassword().equals(password)) {
                session.setAttribute("User", user);
                return "redirect:/userpage";
            } else {
                model.addAttribute("passwordMismatch", true);
                return "login";
            }
        } catch (EmptyResultDataAccessException E){
            model.addAttribute("usernameMissing", true);
            return "login";
        }

    }

    @GetMapping("/userpage")
    public String userpage(Model model, HttpSession session){
        User user = (User) session.getAttribute("User");
        if(user == null) {
            return "denied";
        }
        int userId = user.getId();
        List<Wishlist> list = wishlistService.getWishlistsForUser(userId);
        List<Wishlist> followingList = wishlistService.getFollowedWishlists(userId);
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        model.addAttribute("following", followingList);


        return "/userpage";
    }

    @GetMapping("/WishForm")
    public String create(Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("User");
            int userId = user.getId();

            List<Wishlist> wishlists = wishlistService.getWishlistsForUser(userId);

            model.addAttribute("wishlists", wishlists);
            return "WishForm";
        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @PostMapping("/createWish")
    public String createWish(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("wishlistId") int wishlistId,
            @RequestParam("img-url") String imgUrl
    ) {
        Wish newWish = new Wish(name, price, amount, description, url, imgUrl);
        newWish.setWishlist_id(wishlistId);
        wishService.createWish(newWish);

        return "redirect:/wishlist?id=" + wishlistId;
    }
    @GetMapping("/WishlistForm")
    public String createWishlistForm(Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("User");
            int userId = user.getId();

            List<Wishlist> wishlists = wishlistService.getWishlistsForUser(userId);

            model.addAttribute("wishlists", wishlists);
            return "WishlistForm";
        } catch (EmptyResultDataAccessException e) {
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @PostMapping("/createWishlist")
    public String createWishlist(
            @RequestParam("name") String name, @RequestParam(value = "isPrivate", required = false) boolean isPrivate,
            HttpSession session) {
            User user = (User) session.getAttribute("User");
            int userId = user.getId();

            Wishlist newWishlist = new Wishlist(name, isPrivate);
            newWishlist.setUserId(userId);

            wishlistService.createWishlist(newWishlist);

            return "redirect:/userpage";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model, HttpSession session) {
        try {
            Wish wish = wishService.getWishById(id);
            model.addAttribute("Wish", wish);
            return "wishUpdateForm";
        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @PostMapping("/updateWish")
    public String updateWish(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("amount") int amount,
            @RequestParam("description") String description,
            HttpSession session,
            Model model

    ){
        try {
            Wish wishToUpdate = wishService.getWishById(id);

            wishToUpdate.setName(name);
            wishToUpdate.setPrice(price);
            wishToUpdate.setAmount(amount);
            wishToUpdate.setDescription(description);

            wishService.updateWish(wishToUpdate);

            return "redirect:/wishlist?id=" + wishToUpdate.getWishlist_id();
        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @GetMapping("/updateList/{id}")
    public String showWishlistUpdateForm(@PathVariable("id") int id, Model model, HttpSession session){
        try {
            Wishlist wishlist = wishlistService.getWishlistById(id);
            model.addAttribute("Wishlist", wishlist);
            return "wishlistUpdateForm";
        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @PostMapping("/updateWishlist")
    public String updateWishlist(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam(value = "isPrivate", required = false) boolean isPrivate,
            HttpSession session,
            Model model
    ){
        try{
            Wishlist wishlistToUpdate = wishlistService.getWishlistById(id);

            wishlistToUpdate.setName(name);
            wishlistToUpdate.setPrivate(isPrivate);

            wishlistService.updateWishlist(wishlistToUpdate);
            return "redirect:/userpage?id="+wishlistToUpdate.getUserId();
        }catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){

        int wishlistId = wishService.getWishById(id).getWishlist_id();

        wishService.deleteWishById(id);

        return "redirect:/wishlist?id="+wishlistId;
    }

    @GetMapping("/deleteList/{id}")
    public String deleteWishlist(@PathVariable("id") int id){

        int userId = wishlistService.getWishlistById(id).getUserId();

        wishlistService.deleteWishlist(id);

        return "redirect:/userpage?id="+userId;
    }

    @GetMapping("/reserve/{id}")
    public String reserve(@PathVariable("id") int id, HttpSession session, Model model){
        try {
            Wish wish = wishService.getWishById(id);
            User user = (User) session.getAttribute("User");
            if(user != null){
                wish.setReserved(true);
                wish.setReserved_by(user.getId());
                wishService.updateWish(wish);
            }

        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }

        return "redirect:/wishlist?id="+id;
    }
    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") int id, HttpSession session, Model model){
        try {
            Wish wish = wishService.getWishById(id);
            User user = (User) session.getAttribute("User");
            if(user.getId() == wish.getReserved_by()){
                wish.setReserved_by(0);
                wish.setReserved(false);
                wishService.updateWish(wish);
            }

        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }

        return "redirect:/wishlist?id="+id;
    }

    @GetMapping("/createUser")
    public String createUser() {

        return "createUser";
    }

    @PostMapping("/createUser")
    public String createAnAccount(@RequestParam("navn")String navn,
                                  @RequestParam("brugernavn")String brugernavn,
                                  @RequestParam("adgangskode") String adgangskode,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        User existingUser;
        try {
            existingUser = userService.getUserByUsername(brugernavn);
        } catch (EmptyResultDataAccessException E){
            existingUser=null;
        }
        if(existingUser != null){
            model.addAttribute("usernameExists", true);
            return "createUser";
        }else {

        redirectAttributes.addAttribute("name", navn);
        redirectAttributes.addAttribute("username", brugernavn);
        redirectAttributes.addAttribute("password", adgangskode);

        User newUser = new User(navn, brugernavn, adgangskode);
        userService.createUser(newUser);

            return "redirect:/login";
        }
    }

    @GetMapping("/wishlist")
    public String wishlist(@RequestParam("id") int id, Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            Wishlist wishlist = wishlistService.getWishlistById(id);
            if(!wishlist.isPrivate()) {
                model.addAttribute("wishlistObject", wishlist);
                model.addAttribute("wishlist", wishService.getWishesInWishlist(wishlist.getId()));
                model.addAttribute("user", user);
                boolean following = wishlistService.getFollowedWishlists(user.getId()).stream().anyMatch(wl -> wl.getId() == wishlist.getId());
                model.addAttribute("following", following);
                return "wishlist";
            }
            else if(user.getId() == wishlist.getUserId() || userService.checkIfFriends(user.getId(), wishlist.getUserId())){
                model.addAttribute("wishlistObject", wishlist);
                model.addAttribute("wishlist", wishService.getWishesInWishlist(wishlist.getId()));
                model.addAttribute("user", user);
                boolean following = wishlistService.getFollowedWishlists(user.getId()).stream().anyMatch(wl -> wl.getId() == wishlist.getId());
                model.addAttribute("following", following);
                return "wishlist";
            }
            else return "denied";

        } catch (EmptyResultDataAccessException E){
            User user = (User) session.getAttribute("User");
            if(user == null)
                user = new User(0, "No user", "No user", "null", false);
            model.addAttribute("user", user);
            return "404";
        }
    }

    @GetMapping("/unfollow")
    public String unfollow(@RequestParam("id") int id, HttpSession session){
        User user = (User) session.getAttribute("User");
        wishlistService.removeFollowedWishlist(user.getId(), id);

        return "redirect:/userpage";
    }

    @GetMapping("/follow")
    public String follow(@RequestParam("id")int id, HttpSession session){
        User user = (User) session.getAttribute("User");
        wishlistService.addFollowedWishlist(user.getId(), id);

        return "redirect:/userpage";
    }
}

