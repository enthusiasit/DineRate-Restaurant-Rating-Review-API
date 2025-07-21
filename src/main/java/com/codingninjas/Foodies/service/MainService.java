package com.codingninjas.Foodies.service;

import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Rating;
import com.codingninjas.Foodies.entity.Restaurant;
import com.codingninjas.Foodies.repository.CustomerRepository;
import com.codingninjas.Foodies.repository.RatingRepository;
import com.codingninjas.Foodies.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    CustomerRepository customerRepository;

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Rating addRatingForRestaurantByCustomer(Rating rating, Integer customerId, String restaurantName) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );

        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found :"+restaurantName);
        }

        rating.setCustomer(customer);
        rating.setRestaurant(restaurant);
        Rating savedRating = ratingRepository.save(rating);

        if (customer.getRatings() == null) customer.setRatings(new ArrayList<>());
        customer.getRatings().add(savedRating);

        if (customer.getVisitedRestaurants() == null) customer.setVisitedRestaurants(new ArrayList<>());
        if (!customer.getVisitedRestaurants().contains(restaurant)) {
            customer.getVisitedRestaurants().add(restaurant);
        }


        customerRepository.save(customer);
        return savedRating;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getAllCustomerByVisitedRestaurants(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found with name: " + restaurantName);
        }
        return customerRepository.findByVisitedRestaurants(restaurant);
    }

    public List<Customer> getCustomersByRestaurantAndRatingGreaterThan(String restaurantName, double rating) {
        return customerRepository.findCustomersByRestaurantAndRatingGreaterThan(restaurantName, rating);
    }

    public Double getAverageRatingsByRestaurantName(String restaurantName) {
        return ratingRepository.getAverageRatingsByRestaurantName(restaurantName);
    }
}
