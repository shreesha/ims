package com.example.ims.views;

import com.example.ims.constants.ImsConstants;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ReturnIndentView {

    private int id;
    private ImsConstants.IndentTypes type;
    private String remarks;
    private String status;
    private String deliveryDate;
    private long indentId;
    private String createdBy;
    private Date createdDate;
    private List<IndentLineItemView> indentLineItemViewList;
}
