package com.example.ims.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "enterprice")
@Data
public class Enterprice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String contact_number;
    private String gst;
    private String pan;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdUser;
    private Date created_date;
    @ManyToOne
    @JoinColumn(name = "modified_by", nullable = true)
    private User modifiedUser;
    private Date modified_date;
}
