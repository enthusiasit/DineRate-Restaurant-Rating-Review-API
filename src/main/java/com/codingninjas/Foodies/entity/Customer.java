package com.codingninjas.Foodies.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    /*
     • int id

     • String name

     • List< Rating > ratings (One To Many mapping)

     • List< Restaurant > visitedRestaurants (Many To Many mapping)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-rating")
    private List<Rating> ratings;

    @ManyToMany
    @JoinTable(
            name = "customer_visited_restaurants",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    List<Restaurant> visitedRestaurants;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Restaurant> getVisitedRestaurants() {
        return visitedRestaurants;
    }

    public void setVisitedRestaurants(List<Restaurant> visitedRestaurants) {
        this.visitedRestaurants = visitedRestaurants;
    }
}
