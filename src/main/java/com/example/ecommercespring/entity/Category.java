package com.example.ecommercespring.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;

    //one category has many products
    //List does not mean that you're trying to store a list of products inside category table
    //Telling JPA , the relationship is already owned by the product entity. so , just read from there.
    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Product> products;
}