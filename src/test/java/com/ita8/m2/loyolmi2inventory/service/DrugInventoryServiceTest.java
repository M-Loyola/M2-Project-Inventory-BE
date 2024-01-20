package com.ita8.m2.loyolmi2inventory.service;

import com.ita8.m2.loyolmi2inventory.entity.DrugInventory;
import com.ita8.m2.loyolmi2inventory.repository.DrugInventoryRepository;
import com.ita8.m2.loyolmi2inventory.service.dto.DrugInventoryDto;
import com.ita8.m2.loyolmi2inventory.service.mapper.DrugInventoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrugInventoryServiceTest {
    @Mock
    private DrugInventoryRepository drugInventoryRepository;
    @InjectMocks
    private DrugInventoryService drugInventoryService;

    private DrugInventoryDto drugDto;
    private DrugInventory drug;

    @BeforeEach
    public void setUp() {
        drugDto = new DrugInventoryDto();
        drugDto.setProductId(1);
        drugDto.setProductName("Test Drug");
        drugDto.setCategory("Test Category");
        drugDto.setQuantityOnHand(10);
        drugDto.setExpiryDate("2022-12-31");
        drugDto.setUnitPrice(100.0);

        drug = DrugInventoryMapper.toEntity(drugDto);
    }

    @Test
    public void should_return_drug_dto_when_create_drug_given_valid_drug_dto() {
        when(drugInventoryRepository.save(any(DrugInventory.class))).thenReturn(drug);

        DrugInventoryDto createdDrugDto = drugInventoryService.createDrug(drugDto);

        assertThat(createdDrugDto).isNotNull();
        assertThat(createdDrugDto.getProductId()).isEqualTo(drugDto.getProductId());
        assertThat(createdDrugDto.getProductName()).isEqualTo(drugDto.getProductName());
        assertThat(createdDrugDto.getCategory()).isEqualTo(drugDto.getCategory());
        assertThat(createdDrugDto.getQuantityOnHand()).isEqualTo(drugDto.getQuantityOnHand());
        assertThat(createdDrugDto.getExpiryDate()).isEqualTo(drugDto.getExpiryDate());
        assertThat(createdDrugDto.getUnitPrice()).isEqualTo(drugDto.getUnitPrice());

        verify(drugInventoryRepository, times(1)).save(any(DrugInventory.class));
    }

    @Test
    public void should_throw_illegal_argument_exception_when_create_drug_given_null_drug_dto() {
        assertThatThrownBy(() -> drugInventoryService.createDrug(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_return_list_of_drugs_when_get_all_drugs() {
        List<DrugInventory> drugs = new ArrayList<>();
        drugs.add(drug);
        when(drugInventoryRepository.findAll()).thenReturn(drugs);

        List<DrugInventoryDto> drugList = drugInventoryService.getAllDrugs();

        assertThat(drugList).isNotNull();
        assertThat(drugList.size()).isEqualTo(1);
        assertThat(drugList.get(0).getProductId()).isEqualTo(drugDto.getProductId());
    }

    @Test
    public void should_return_drug_dto_when_get_drugs_by_id_given_valid_id() {
        when(drugInventoryRepository.findById(drugDto.getProductId())).thenReturn(Optional.of(drug));

        DrugInventoryDto foundDrugDto = drugInventoryService.getDrugById(drugDto.getProductId());

        assertThat(foundDrugDto).isNotNull();
        assertThat(foundDrugDto.getProductId()).isEqualTo(drugDto.getProductId());
    }

    @Test
    public void should_throw_runtime_exception_when_get_drugs_by_id_given_invalid_id() {
        when(drugInventoryRepository.findById(drugDto.getProductId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> drugInventoryService.getDrugById(drugDto.getProductId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Drug not found");
    }

    @Test
    public void should_delete_drug_when_delete_drug_given_valid_product_id() {
        when(drugInventoryRepository.findById(drugDto.getProductId())).thenReturn(Optional.of(drug));
        doNothing().when(drugInventoryRepository).deleteById(drugDto.getProductId());
        drugInventoryService.deleteDrug(drugDto.getProductId());
        verify(drugInventoryRepository, times(1)).deleteById(drugDto.getProductId());
    }

    @Test
    public void should_update_drug_dto_when_update_drug_given_valid_product_id_and_drug_dto() {
        when(drugInventoryRepository.findById(drugDto.getProductId())).thenReturn(Optional.of(drug));
        when(drugInventoryRepository.save(drug)).thenReturn(drug);

        DrugInventoryDto updatedDrugDto = drugInventoryService.updateDrug(drugDto);

        assertThat(updatedDrugDto).isNotNull();
        assertThat(updatedDrugDto.getProductId()).isEqualTo(drugDto.getProductId());
        assertThat(updatedDrugDto.getProductName()).isEqualTo(drugDto.getProductName());
        assertThat(updatedDrugDto.getCategory()).isEqualTo(drugDto.getCategory());
        assertThat(updatedDrugDto.getQuantityOnHand()).isEqualTo(drugDto.getQuantityOnHand());
        assertThat(updatedDrugDto.getExpiryDate()).isEqualTo(drugDto.getExpiryDate());
        assertThat(updatedDrugDto.getUnitPrice()).isEqualTo(drugDto.getUnitPrice());
    }

    @Test
    public void should_throw_null_pointer_exception_when_update_drug_given_null_dto() {
        assertThatThrownBy(() -> drugInventoryService.updateDrug(null))
                .isInstanceOf(NullPointerException.class);
    }
}