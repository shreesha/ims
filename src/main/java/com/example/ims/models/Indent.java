package com.example.ims.models;

import com.example.ims.constants.ImsConstants;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="indent")
@Data
public class Indent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ImsConstants.IndentTypes type;
    private Date created_date;
    private String remarks;
    private String status;
    private Date delivery_date;
    @OneToOne
    @JoinColumn(name = "location")
    @NotFound(action = NotFoundAction.IGNORE)
    private Location location;
    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private User createdUser;

    @OneToOne
    @JoinColumn(name = "modified_by")
    @NotFound(action = NotFoundAction.IGNORE)
    private User  modifiedUser;
    @OneToMany(mappedBy = "indent")
    List<IndentLineItem> lineItemList;


    private Date modified_date;

}
