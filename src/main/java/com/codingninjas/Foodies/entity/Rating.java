package com.codingninjas.Foodies.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
    /*

     • int id

     • double rating

     • Restaurant restaurant (Many To One mapping)

     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private double rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference(value = "restaurant-rating")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "customer-rating")
    private Customer customer;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
