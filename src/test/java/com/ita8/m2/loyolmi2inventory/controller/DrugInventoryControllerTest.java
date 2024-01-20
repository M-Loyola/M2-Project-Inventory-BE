package com.ita8.m2.loyolmi2inventory.controller;

import com.ita8.m2.loyolmi2inventory.service.DrugInventoryService;
import com.ita8.m2.loyolmi2inventory.service.dto.DrugInventoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DrugInventoryController.class)
public class DrugInventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DrugInventoryService drugInventoryService;

    @InjectMocks
    private DrugInventoryController drugInventoryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        drugInventoryController = new DrugInventoryController(drugInventoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(drugInventoryController).build();
    }

    @Test
    public void should_add_drug_when_create_drug_given_drug_information_json() throws Exception {
        DrugInventoryDto drug = new DrugInventoryDto();
        drug.setProductId(1);
        drug.setProductName("Drug 1");
        drug.setCategory("Category 1");
        drug.setQuantityOnHand(10);
        drug.setExpiryDate("2022-12-31");
        drug.setUnitPrice(10.0);

        when(drugInventoryService.createDrug(any(DrugInventoryDto.class))).thenReturn(drug);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/drug-inventory/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\": \"Drug 1\", \"category\": \"Category 1\", " +
                                "\"quantityOnHand\": 10, \"expiryDate\": \"2022-12-31\", \"unitPrice\": 10.0}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Drug 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Category 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantityOnHand").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.expiryDate").value("2022-12-31"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value(10.0));
    }

    @Test
    void should_update_drug_information_when_update_drug_given_drug_inventory_dto() {
        Integer productId = 1;
        DrugInventoryDto drugDto = new DrugInventoryDto();
        drugDto.setProductName("Updated Product");
        drugDto.setCategory("Updated Category");
        drugDto.setQuantityOnHand(10);
        drugDto.setExpiryDate("2022-12-31");
        drugDto.setUnitPrice(9.99);

        when(drugInventoryService.updateDrug(any(DrugInventoryDto.class))).thenReturn(drugDto);

        DrugInventoryDto result = drugInventoryController.updateDrug(productId, drugDto);

        verify(drugInventoryService).updateDrug(drugDto);
        assertEquals(drugDto, result);
    }

    @Test
    public void should_delete_drug_when_delete_drug_given_product_id() throws Exception {
        Integer productId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/drug-inventory/delete/{productId}", productId))
                .andExpect(status().isOk());

        verify(drugInventoryService).deleteDrug(productId);
    }

    @Test
    public void should_return_drug_data_when_get_drug_by_id_given_product_id() throws Exception {
        DrugInventoryDto drugDto = new DrugInventoryDto();
        drugDto.setProductId(1);
        drugDto.setProductName("Test Drug");
        drugDto.setCategory("Test Category");
        drugDto.setQuantityOnHand(10);
        drugDto.setExpiryDate("2024-01-31");
        drugDto.setUnitPrice(100.0);

        given(drugInventoryService.getDrugById(drugDto.getProductId())).willReturn(drugDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/drug-inventory/{productId}", drugDto.getProductId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(drugDto.getProductId()));
    }

    @Test
    public void should_return_list_of_drugs_when_get_all_drugs() throws Exception {
        DrugInventoryDto drugDto1 = new DrugInventoryDto();
        drugDto1.setProductId(1);
        drugDto1.setProductName("Test Drug 1");
        drugDto1.setCategory("Test Category 1");
        drugDto1.setQuantityOnHand(10);
        drugDto1.setExpiryDate("2024-01-31");
        drugDto1.setUnitPrice(100.0);

        DrugInventoryDto drugDto2 = new DrugInventoryDto();
        drugDto2.setProductId(2);
        drugDto2.setProductName("Test Drug 2");
        drugDto2.setCategory("Test Category 2");
        drugDto2.setQuantityOnHand(20);
        drugDto2.setExpiryDate("2024-01-31");
        drugDto2.setUnitPrice(200.0);

        List<DrugInventoryDto> drugDtoList = Arrays.asList(drugDto1, drugDto2);

        given(drugInventoryService.getAllDrugs()).willReturn(drugDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/drug-inventory"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(drugDto1.getProductId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId").value(drugDto2.getProductId()));
    }

}