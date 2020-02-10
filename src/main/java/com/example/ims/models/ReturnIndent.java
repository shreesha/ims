package com.example.ims.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "return_indent")
@Data
public class ReturnIndent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private Date created_date;
    private String remarks;
    private String status;
    private Date delivery_date;
    // private String location
    //created_by
    //modified_by
    //indent_id
    //private Date modified_date;
}
