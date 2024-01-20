package com.ita8.m2.loyolmi2inventory.service.dto;

import lombok.Data;

@Data
public class DrugInventoryDto {
    private Integer productId;
    private String productName;
    private String category;
    private Integer quantityOnHand;
    private String expiryDate;
    private Double unitPrice;
}
