package com.punko.MockTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.punko.ApartmentService;
import com.punko.config.SpringBootApplicationConfig;
import com.punko.controller.ApartmentController;
import com.punko.entity.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ApartmentController.class)
@ContextConfiguration(classes = SpringBootApplicationConfig.class)
public class ApartmentTestMock {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmentService apartmentService;

    @Autowired
    protected ObjectMapper objectMapper;

    private static Apartment apartment1;
    private static Apartment apartment2;
    private static Apartment apartment3;

    @BeforeAll
    static void init() {
        apartment1 = createApartment(1, 101, "CHEAP");
        apartment2 = createApartment(2, 102, "MEDIUM");
        apartment3 = createApartment(3, 103, "LUXURIOUS");
    }

    @Test
    public void shouldReturnAllApartments() throws Exception {
        when(apartmentService.getAllApartment())
                .thenReturn(Arrays.asList(apartment1, apartment2, apartment3));
        mockMvc.perform(get("/apartments"))
                .andDo(print())
                .andExpect(status().isOk());

        List<Apartment> apartmentList = apartmentService.getAllApartment();
        Assertions.assertNotNull(apartmentList);
        Assertions.assertTrue(apartmentList.size() > 0);
    }

    @Test
    public void shouldReturnApartmentById() throws Exception {
        int id = 1;
        when(apartmentService.getById(id)).thenReturn(apartment1);

        mockMvc.perform(get("/apartments/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        Apartment apartment = apartmentService.getById(id);
        Assertions.assertNotNull(apartment);
        Assertions.assertEquals(apartment, apartment1);
    }

    @Test
    public void shouldCreateNewApartment() throws Exception {
        Apartment apartment = new Apartment(101, "CHEAP");
        String json = objectMapper.writeValueAsString(apartment);

        mockMvc.perform(post("/apartments")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void shouldUpdateApartment() throws Exception {
        String json = objectMapper.writeValueAsString(apartment1);
        mockMvc.perform(put("/apartments")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    public void shouldDeleteApartment() throws Exception {
        int id = 1;
        when(apartmentService.getById(id)).thenReturn(apartment1);
        when(apartmentService.delete(apartment1.getApartmentId())).thenReturn("SUCCESS");

        mockMvc.perform(delete("/apartments/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnCountOfApartment() throws Exception {
        when(apartmentService.count()).thenReturn(Long.valueOf(2));

        mockMvc.perform(get("/apartments/count"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private static Apartment createApartment(int id, int number, String apartmentClass) {
        Apartment apartment = new Apartment();
        apartment.setApartmentId(id);
        apartment.setApartmentNumber(number);
        apartment.setApartmentClass(apartmentClass);
        return apartment;
    }

}
