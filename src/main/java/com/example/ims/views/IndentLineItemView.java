package com.example.ims.views;

import lombok.Data;

@Data
public class IndentLineItemView {

    private Integer productId;
    private Integer indentId;
    private int quantity;
    private Double unitPrice;

    public IndentLineItemView() {
    }


}
