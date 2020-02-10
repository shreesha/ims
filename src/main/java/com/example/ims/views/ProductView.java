package com.example.ims.views;

import lombok.Data;

import java.util.Date;

@Data
public class ProductView {
    private String name;
    private String hsnCode;
    private String category;
    private String type;
    private String sku;
    private String status;
    private String description;
    private int quantity;
    private String unit;
    private float measure;
    private int manufacturer;
    private String manufacturerName;
    private int location;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
}
