package com.example.ecommercespring.repository;

import com.example.ecommercespring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Find All the Expensive Products > find all products which have a price > minPrice
    // for that we have to use @Query
    //This is a custom query method using HQL.
    // here we have to explicitly define the query logic in HQL (NOT SQL)
    //here in hql we deals with Entity Which is Product Entity not product table.
    @Query("SELECT p from Product p WHERE p.price > :minPrice")
    List<Product> findExpensiveProduct(@Param("minPrice") double minPrice);

    //For more complex queries
    //if we want to apply Limit , we want to do Regex matching , ilike
    // for such cases we cannot use the HQL
    // We want to search for the products whose name or description has a word "keyword"
    //but we don't know the exact name of the product
    //for such cases we have to make a query
    //nativeQuery = true as it is a raw sql query

    @Query(value="Select * FROM product WHERE MATCH(title, description) AGAINST (:keyword)", nativeQuery = true)
    List<Product> searchFullText(@Param("keyword") String keyword);


    //we want to find products by brand and price
    @Query("Select p from Product p WHERE p.price>:minPrice AND p.brand= :brand")
    List<Product> findByBrandAndPrice(
            @Param("brand") String brand,
            @Param("minPrice") double minPrice
    );



    //we want to get all the products of a specific category
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> getAllProductsOfACategory(@Param("categoryId") Long categoryId);
}


//productRepository.save()