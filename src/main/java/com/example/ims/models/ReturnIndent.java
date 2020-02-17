package com.example.ims.models;

import com.example.ims.constants.ImsConstants;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "return_indent")
public class ReturnIndent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ImsConstants.IndentTypes type;
    private Date created_date;
    private String remarks;
    private String status;
    private Date delivery_date;

    // private String location
    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    User createdUser;
    @OneToOne
    @JoinColumn(name = "modified_by", nullable = false)
    User modifiedUser;
    @OneToOne
    @JoinColumn(name = "indent_id", nullable = false)
    Indent indent;

    @OneToMany(mappedBy = "indent")
    List<ReturnLineItem> lineItemList;

    public ReturnIndent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImsConstants.IndentTypes getType() {
        return type;
    }

    public void setType(ImsConstants.IndentTypes type) {
        this.type = type;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public User getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(User modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Indent getIndent() {
        return indent;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }

    public List<ReturnLineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<ReturnLineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }
}
