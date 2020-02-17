package com.example.ims.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "product")
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
    @OneToOne
    @JoinColumn(name = "manufacturer", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Enterprice manufacturerObj;

    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private User createdUser;
    private Date created_date;

    @OneToOne
    @JoinColumn(name = "modified_by")
    @NotFound(action = NotFoundAction.IGNORE)
    private User  modifiedUser;
    private Date modified_date;

    @ManyToMany
    @JoinTable(
            name = "location_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

    public Product() {
    }

//    public int getCreated_by() {
//        return createdUser.getUserid();
//    }
//
//    public int getModified_by() {
//        return modifiedUser.getUserid();
//    }
//
//    public int getManufacturer(){
//        return  manufacturerObj.getId();
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsn_code() {
        return hsn_code;
    }

    public void setHsn_code(String hsn_code) {
        this.hsn_code = hsn_code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getMeasure() {
        return measure;
    }

    public void setMeasure(float measure) {
        this.measure = measure;
    }

    public Enterprice getManufacturerObj() {
        return manufacturerObj;
    }

    public void setManufacturerObj(Enterprice manufacturerObj) {
        this.manufacturerObj = manufacturerObj;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public User getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(User modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
