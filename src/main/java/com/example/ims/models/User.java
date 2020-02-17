package com.example.ims.models;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String name;

    private String display_name;

    private String role;

    private String contact_number;

    @Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;
    @Length(min = 8)
    private String password;
    private String token;
    private boolean is_active;

    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdUser;
    private Date created_date;

    @OneToOne
    @JoinColumn(name = "modified_by", nullable = false)
    private User  modifiedUser;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Department  department;

    private Date modified_date;

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getRole() {
        return role;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public User getModifiedUser() {
        return modifiedUser;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setModifiedUser(User modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
