package com.ita8.m2.loyolmi2inventory.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "drug_inventory_list")
public class DrugInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity_on_hand", nullable = false)
    private Integer quantityOnHand;

    @Column(name = "expiry_date", nullable = false)
    private String expiryDate;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    public DrugInventory() {

    }

}