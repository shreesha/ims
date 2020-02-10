package com.example.ims.views;

import lombok.Data;

@Data
public class IndentLineItemView {

    private String productId;
    private Long indentId;
    private int quantity;
    private Double unitPrice;

    public IndentLineItemView() {
    }


}
