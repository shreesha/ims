package com.example.ims.models;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity(name ="return_line_item")
@Data
public class ReturnLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @ManyToOne
    @JoinColumn(name = "indent_id", nullable = false)
    ReturnIndent indent;
    private int quantity;

    public ReturnLineItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ReturnIndent getIndent() {
        return indent;
    }

    public void setIndent(ReturnIndent indent) {
        this.indent = indent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
