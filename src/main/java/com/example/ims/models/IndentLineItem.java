package com.example.ims.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "indent_line_item")
@Data
public class IndentLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="indent_id")
    Indent indent;

    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;
    private int quantity;
    private float unit_price;
}
