package com.codingninjas.Foodies.repository;

import com.codingninjas.Foodies.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "SELECT AVG(r.rating) FROM rating r JOIN restaurant rest ON r.restaurant_id = rest.id WHERE rest.name = :restaurantName",
    nativeQuery = true)
    Double getAverageRatingsByRestaurantName(@Param("restaurantName") String restaurantName);
}
