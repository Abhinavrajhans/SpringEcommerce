
package com.example.ecommercespring.entity;

import com.example.ecommercespring.dto.CategoryDTO;
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
    //and one category ca have many products
    @ManyToOne
    @JoinColumn(name="category_id",nullable=false)
    private Category category;
}