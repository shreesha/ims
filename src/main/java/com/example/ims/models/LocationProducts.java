package com.example.ims.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "location_products")
@Data
public class LocationProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "indent_id", nullable = false)
    private Indent indent;

    @OneToOne
    @JoinColumn(name = "modified_by", nullable = false)
    private User modifiedUser;
    private Date modified_date;

    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdUser;
    private Date created_date;

    public LocationProducts() {
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Location getLocation() {
        return location;
    }

    public int getQuantity() {
        return quantity;
    }

    public Indent getIndent() {
        return indent;
    }

    public User getModifiedUser() {
        return modifiedUser;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public int getProduct_id(){
        return product.getId();
    }

    public int getLocation_id(){
        return location.getId();
    }
    public int getIndent_id(){
        return indent.getId();
    }
}
