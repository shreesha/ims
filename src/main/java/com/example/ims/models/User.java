package com.example.ims.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String name;

    private String display_name;

    private String role;

    private String contact_number;
    private String email;
    private String password;
    private String token;
    private boolean is_active;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdUser;
    private Date created_date;

    @ManyToOne
    @JoinColumn(name = "modified_by", nullable = false)
    private User  modifiedUser;

    private Date modified_date;
}
