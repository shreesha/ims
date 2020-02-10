package com.example.ims.views;

import lombok.Data;

import java.util.Date;
@Data
public class UserView {
    private int userid;
    private String name;
    private String displayName;
    private String role;
    private String contactNumber;
    private String email;
    private boolean isActive;
    private String createdBy;
    private Date createdDate;
    private String  modifiedBy;
    private Date modifiedDate;
    private String password;
}
