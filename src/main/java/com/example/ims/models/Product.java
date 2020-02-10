package com.example.ims.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String hsn_code;
    private String category;
    private String type;
    private String sku;
    private String status;
    private String description;
    private int quantity;
    private String unit;
    private float measure;
    @ManyToOne
    @JoinColumn(name = "manufacturer", nullable = false)
    private Enterprice manufacturer;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdUser;
    private Date created_date;

    @ManyToOne
    @JoinColumn(name = "modified_by", nullable = true)
    private User  modifiedUser;
    private Date modified_date;

    @ManyToMany
    @JoinTable(
            name = "location_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;
}
