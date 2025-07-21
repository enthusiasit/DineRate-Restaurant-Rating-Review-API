package com.codingninjas.Foodies.controller;

import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    /*

    a. POST "/Restaurant/add" (@RequestBody Restaurant restaurant): to add a new restaurant.

    b. POST "/Customer/add" (@RequestBody Customer customer): to add new customers.

    c. POST "/Rating/{customerId}/add/{restaurantName}" (@RequestBody Rating rating,@PathVariable Integer customerId, @PathVariable String restaurantName): to add rating for restaurant by customer.

    d. GET "/ratings": to fetch all the ratings.

    e. GET "/customers": to fetch the list of all the customers registered.

     */

    @PostMapping("/Restaurant/add")
    public void addRestaurant(@RequestBody Restaurant restaurant){
        mainService.addRestaurant(restaurant);
    }

    @PostMapping("/Customer/add")
    public void addCustomer(@RequestBody Customer customer){
        mainService.addCustomer(customer);
    }

    @PostMapping("/Rating/{customerId}/add/{restaurantName}")
    public Rating addRatingForRestaurantByCustomer(@RequestBody Rating rating, @PathVariable Integer customerId,
                                                 @PathVariable String restaurantName){
       return mainService.addRatingForRestaurantByCustomer(rating, customerId, restaurantName);
    }

    @GetMapping("/ratings")
    public List<Rating> getAllRatings(){
        return mainService.getAllRatings();
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return mainService.getAllCustomers();
    }

    @GetMapping( "/customers/restaurant/{restaurantName}" )
    public List<Customer> getAllCustomerByVisitedRestaurants(@PathVariable String restaurantName){
        return mainService.getAllCustomerByVisitedRestaurants(restaurantName);
    }

    @GetMapping("/customers/restaurant/{restaurantName}/{rating}")
    public List<Customer> getCustomersByRatingGreaterThan(
            @PathVariable String restaurantName,
            @PathVariable double rating) {
        return mainService.getCustomersByRestaurantAndRatingGreaterThan(restaurantName, rating);
    }

    @GetMapping("/restaurant/{restaurantName}/average")
    public Double getAverageRatingsByRestaurantName(@PathVariable String restaurantName){
        return mainService.getAverageRatingsByRestaurantName(restaurantName);
    }


}
