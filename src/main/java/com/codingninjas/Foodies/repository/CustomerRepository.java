package com.codingninjas.Foodies.repository;

import com.codingninjas.Foodies.entity.Customer;
import com.codingninjas.Foodies.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByVisitedRestaurants(Restaurant restaurant);

//    @Query("SELECT r.customer from Rating r WHERE r.restaurantName = :restaurantName AND r.rating < :rating ")
//    List<Customer> findCustomersByRestaurantAndRatingLessThan(@Param("restaurantName") String restaurantName,
//                                                              @Param("rating") double rating);

    @Query("SELECT r.customer FROM Rating r WHERE r.restaurant.name = :restaurantName AND r.rating > :rating")
    List<Customer> findCustomersByRestaurantAndRatingGreaterThan(@Param("restaurantName") String restaurantName, @Param("rating") double rating);

}
