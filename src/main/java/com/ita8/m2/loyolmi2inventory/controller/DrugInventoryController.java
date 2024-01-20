package com.ita8.m2.loyolmi2inventory.controller;

import com.ita8.m2.loyolmi2inventory.service.DrugInventoryService;
import com.ita8.m2.loyolmi2inventory.service.dto.DrugInventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug-inventory")
public class DrugInventoryController {
    private final DrugInventoryService drugInventoryService;

    @Autowired
    public DrugInventoryController(DrugInventoryService drugInventoryService) {
        this.drugInventoryService = drugInventoryService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public DrugInventoryDto createDrug(@RequestBody DrugInventoryDto drugDto) throws IllegalArgumentException{
        return drugInventoryService.createDrug(drugDto);
    }

    @PutMapping("/update/{productId}")
    public DrugInventoryDto updateDrug(@PathVariable Integer productId, @RequestBody DrugInventoryDto drugDto) {
        drugDto.setProductId(productId);
        return drugInventoryService.updateDrug(drugDto);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteDrug(@PathVariable Integer productId) {
        drugInventoryService.deleteDrug(productId);
    }

    @GetMapping("/{productId}")
    public DrugInventoryDto getDrugById(@PathVariable Integer productId) {
        return drugInventoryService.getDrugById(productId);
    }

    @GetMapping
    public List<DrugInventoryDto> getAllDrugs() {
        return drugInventoryService.getAllDrugs();
    }
}
