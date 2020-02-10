package com.example.ims.views;

import lombok.Data;

import java.util.Date;

@Data
public class LocationView {
    private int id;
    private String type;
    private String description;
    private String createdBy;
    private Date createdDate;
    private Date modifiedDate;
    private String  modifiedBy;
    private String name;
    private boolean isActive;
}
