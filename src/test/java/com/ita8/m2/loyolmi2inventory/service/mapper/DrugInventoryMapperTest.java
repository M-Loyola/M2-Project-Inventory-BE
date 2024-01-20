package com.ita8.m2.loyolmi2inventory.service.mapper;

import com.ita8.m2.loyolmi2inventory.entity.DrugInventory;
import com.ita8.m2.loyolmi2inventory.service.dto.DrugInventoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class DrugInventoryMapperTest {

    @Test
    public void should_return_dto_when_to_dto_given_valid_drug_inventory() {
        DrugInventory product = new DrugInventory();
        product.setProductId(1);
        product.setProductName("Test Product");
        product.setCategory("Test Category");
        product.setQuantityOnHand(10);
        product.setExpiryDate("2022-12-31");
        product.setUnitPrice(100.0);

        DrugInventoryDto productDto = DrugInventoryMapper.toDto(product);

        assertEquals(product.getProductId(), productDto.getProductId());
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getCategory(), productDto.getCategory());
        assertEquals(product.getQuantityOnHand(), productDto.getQuantityOnHand());
        assertEquals(product.getExpiryDate(), productDto.getExpiryDate());
        assertEquals(product.getUnitPrice(), productDto.getUnitPrice());
    }

    @Test
    public void should_return_drug_inventory_when_to_entity_given_valid_drug_inventory_dto() {
        DrugInventoryDto productDto = new DrugInventoryDto();
        productDto.setProductId(1);
        productDto.setProductName("Test Product");
        productDto.setCategory("Test Category");
        productDto.setQuantityOnHand(10);
        productDto.setExpiryDate("2022-12-31");
        productDto.setUnitPrice(100.0);

        DrugInventory product = DrugInventoryMapper.toEntity(productDto);

        assertEquals(productDto.getProductId(), product.getProductId());
        assertEquals(productDto.getProductName(), product.getProductName());
        assertEquals(productDto.getCategory(), product.getCategory());
        assertEquals(productDto.getQuantityOnHand(), product.getQuantityOnHand());
        assertEquals(productDto.getExpiryDate(), product.getExpiryDate());
        assertEquals(productDto.getUnitPrice(), product.getUnitPrice());
    }

    @Test
    public void should_return_null_when_to_entity_given_null_drug_inventory_dto() {
        DrugInventoryDto productDto = new DrugInventoryDto();

        DrugInventory product = DrugInventoryMapper.toEntity(productDto);

        assertNull(product.getProductId());
        assertNull(product.getProductName());
        assertNull(product.getCategory());
        assertNull(product.getQuantityOnHand());
        assertNull(product.getExpiryDate());
        assertNull(product.getUnitPrice());
    }

    @Test
    public void should_return_empty_string_drug_inventory_when_to_entity_given_empty_string_drug_inventory_dto() {
        DrugInventoryDto productDto = new DrugInventoryDto();
        productDto.setProductName("");
        productDto.setCategory("");
        productDto.setExpiryDate("");

        DrugInventory product = DrugInventoryMapper.toEntity(productDto);

        assertEquals("", product.getProductName());
        assertEquals("", product.getCategory());
        assertEquals("", product.getExpiryDate());
    }
}