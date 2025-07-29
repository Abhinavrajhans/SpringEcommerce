package com.example.ecommercespring.entity;

// Importing JPA annotations like @Id, @Column used for mapping class to a db structure
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Instant class from Java Time API > represents the timestamp in UTC
import java.time.Instant;

// what is mapped superclass?
// is JPA annotation used to define the base class that contains the common fields
//which will be inherted and mapped into child entity tables
// what is the difference between mapped superclass and Entity
//Unlike @entity >  baseentity does not get its own table
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // the primary key is auto generated and auto incremented.
    //Id is the primary key and we are updating the primary key using the Strategy above mentioned.
    private long id;

    @CreatedDate
    @Column(nullable=false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    // This method is automatically called before the entity is saved for the first time.
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

}