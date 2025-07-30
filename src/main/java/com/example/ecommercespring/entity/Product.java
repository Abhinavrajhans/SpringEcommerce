
package com.example.ecommercespring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product  extends BaseEntity{

    private String image;
    private String color;
    private int price;
    private String description;
    private int discount;
    private String model;
    private String title;
    private String brand;
    private boolean popular;


    //Each Product Belong to one category
    //and one category and have many products
    @ManyToOne(fetch=FetchType.LAZY)
    //There are 2 types FetchType.EAGER and FetchType.LAZY
    @JoinColumn(name="category_id",nullable=false)
    private Category category;
}