package com.example.ims.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="return_line_item")
@Data
public class ReturnLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //product_id
    //indent_id
    private int quantity;
}