package com.ita8.m2.loyolmi2inventory.service;

import com.ita8.m2.loyolmi2inventory.entity.DrugInventory;
import com.ita8.m2.loyolmi2inventory.repository.DrugInventoryRepository;
import com.ita8.m2.loyolmi2inventory.service.dto.DrugInventoryDto;
import com.ita8.m2.loyolmi2inventory.service.mapper.DrugInventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DrugInventoryService {

    private final DrugInventoryRepository drugInventoryRepository;
    @Autowired
    public DrugInventoryService(DrugInventoryRepository drugInventoryRepository) {
        this.drugInventoryRepository = drugInventoryRepository;
    }

    public DrugInventoryDto createDrug(DrugInventoryDto drugDto) {
        DrugInventory drug = DrugInventoryMapper.toEntity(drugDto);
        DrugInventory savedDrug = drugInventoryRepository.save(drug);
        return DrugInventoryMapper.toDto(savedDrug);
    }

    public DrugInventoryDto updateDrug(DrugInventoryDto drugDto) {
        Integer productId = drugDto.getProductId();
        DrugInventory existingDrug = drugInventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Drug not found"));

        existingDrug.setProductName(drugDto.getProductName());
        existingDrug.setCategory(drugDto.getCategory());
        existingDrug.setQuantityOnHand(drugDto.getQuantityOnHand());
        existingDrug.setExpiryDate(drugDto.getExpiryDate());
        existingDrug.setUnitPrice(drugDto.getUnitPrice());

        DrugInventory updatedDrug = drugInventoryRepository.save(existingDrug);
        return DrugInventoryMapper.toDto(updatedDrug);
    }

    public void deleteDrug(Integer productId) {
        try {
            drugInventoryRepository.findById(productId).ifPresent(drug -> drugInventoryRepository.deleteById(productId));
        } catch (NullPointerException exception){
            throw new NullPointerException();
        }
    }

    public DrugInventoryDto getDrugById(Integer productId) {
        DrugInventory drug = drugInventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Drug not found"));
        return DrugInventoryMapper.toDto(drug);
    }

    public List<DrugInventoryDto> getAllDrugs() {
        List<DrugInventory> drugs = drugInventoryRepository.findAll();
        return drugs.stream()
                .map(DrugInventoryMapper::toDto)
                .collect(Collectors.toList());
    }

}