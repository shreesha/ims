package com.example.ims.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String description;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdUser;
    private Date created_date;
    private Date modified_date;
    @ManyToOne
    @JoinColumn(name = "modified_by", nullable = true)
    private User  modifiedUser;
    private String  name;
    private boolean is_active;
    @ManyToMany(mappedBy = "locations")
    @JsonIgnore //  prevent back serialization for many to many
    private List<Product> products;
}
