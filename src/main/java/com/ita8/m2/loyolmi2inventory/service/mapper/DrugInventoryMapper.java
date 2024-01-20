package com.ita8.m2.loyolmi2inventory.service.mapper;

import com.ita8.m2.loyolmi2inventory.entity.DrugInventory;
import com.ita8.m2.loyolmi2inventory.service.dto.DrugInventoryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DrugInventoryMapper {

    public static DrugInventoryDto toDto(DrugInventory product) {
        DrugInventoryDto productDto = new DrugInventoryDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static DrugInventory toEntity(DrugInventoryDto productDto) {
        DrugInventory productEntity = new DrugInventory();
        BeanUtils.copyProperties(productDto, productEntity);
        return productEntity;
    }
}
