package com.example.ims.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "location")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String description;
    @ManyToOne
    @JoinColumn(name = "created_by", columnDefinition="integer" , nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private User createdUser;
    private Date created_date;
    private Date modified_date;
    @ManyToOne
    @JoinColumn(name = "modified_by", columnDefinition="integer")
    @NotFound(action = NotFoundAction.IGNORE)
    private User  modifiedUser;
    private String  name;
    private boolean is_active;
    @ManyToMany(mappedBy = "locations")
    @JsonIgnore //  prevent back serialization for many to many
    private List<Product> products;

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
}
