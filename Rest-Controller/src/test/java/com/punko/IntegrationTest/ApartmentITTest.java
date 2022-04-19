package com.punko.IntegrationTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.punko.config.SpringBootApplicationConfig;
import com.punko.entity.Apartment;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Spring didn't see main SpringBootApplicationConfig.class Maybe Because of that in one more package: config
@SpringBootTest(classes = SpringBootApplicationConfig.class)
@AutoConfigureMockMvc
@Transactional
class ApartmentITTest {

//     useful approach is to not start the server at all but
//     to test only the layer below that, where Spring handles
//     the incoming HTTP request and hands it off to your controller.
//     That way, almost of the full stack is used, and your code will
//     be called in exactly the same way as if it were processing a real
//     HTTP request but without the cost of starting the server. To do that, use Spring’s MockMvc
//     and ask for that to be injected for you by using the @AutoConfigureMockMvc
//     annotation on the test case.

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    MockApartmentService apartmentService = new MockApartmentService();

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentITTest.class);

    @Test
    void shouldReturnAllApartment() throws Exception {
        LOGGER.debug("should find all Apartments test");
        List<Apartment> apartmentList = apartmentService.getAllApartments();
        assertNotNull(apartmentList);
        assertTrue(apartmentList.size() > 0);
    }


    @Test
    void shouldFindByIdTest() throws Exception {
        LOGGER.debug("should find Apartment by id test");
        List<Apartment> apartmentList = apartmentService.getAllApartments();
        assertNotNull(apartmentList);
        assertTrue(apartmentList.size() > 0);

        Integer apartmentId = apartmentList.get(0).getApartmentId();
        Apartment apartment = apartmentService.findById(apartmentId);
        assertNotNull(apartment);
        assertEquals(apartment.getApartmentNumber(), apartmentList.get(0).getApartmentNumber());
    }

    @Test
    void shouldReturnNotFoundOnMissedApartment() throws Exception {
        LOGGER.debug("should return NotFound on missed Apartment test");
        int id = 999999;
        MockHttpServletResponse response = mockMvc.perform(get(
                "/apartments/" + id)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound())
                .andReturn().getResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateTest() throws Exception {
        LOGGER.debug("should create Apartment by id test");

        Integer countBeforeCreate = apartmentService.count();
        apartmentService.create(new Apartment(110, "MEDIUM"));
        Integer countAfterCreate = apartmentService.count();
        assertEquals(countBeforeCreate + 1, countAfterCreate);
    }

    @Test
    void createApartmentWithSameNumberTest() throws Exception {
        LOGGER.debug("create Apartment with the same number test");

        apartmentService.create(new Apartment(1, "MEDIUM"));

        String json = objectMapper.writeValueAsString(new Apartment(1, "MEDIUM"));
        MockHttpServletResponse response =
                mockMvc.perform(post("/apartments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                        .andReturn().getResponse();
        assertNotNull(response);
    }

    @Test
    void shouldUpdateApartmentTest() throws Exception {
        LOGGER.debug("should update apartment()");
        List<Apartment> apartmentList = apartmentService.getAllApartments();
        assertNotNull(apartmentList);
        assertTrue(apartmentList.size() > 0);

        Apartment apartment = apartmentList.get(0);
        apartment.setApartmentNumber(200);
        apartmentService.update(apartment);

        Apartment realApartment = apartmentService.findById(apartment.getApartmentId());
        assertEquals(200, realApartment.getApartmentNumber());
        assertEquals(apartment, realApartment);
    }

    @Test
    void shouldDeleteApartmentTest() throws Exception {
        LOGGER.debug("should delete Apartment()");
        List<Apartment> apartmentList = apartmentService.getAllApartments();
        assertTrue(apartmentList.size() > 0);

        Integer countBeforeDelete = apartmentService.count();
        apartmentService.delete(apartmentList.get(0).getApartmentId());
        Integer countAfterDelete = apartmentService.count();
        assertEquals(countBeforeDelete, countAfterDelete + 1);
    }

    @Test
    void shouldReturnNotFoundOnDeleteMissedApartment() throws Exception {
        LOGGER.debug("should return not found on delete missed Apartment()");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete(
                "/apartments" + "/999999")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError())
                .andReturn().getResponse();
        assertNotNull(response);
    }


    ///////////////////////    Mock tests body
    class MockApartmentService {

        public List<Apartment> getAllApartments() throws Exception {
            LOGGER.debug("findAll()");
            MockHttpServletResponse response = mockMvc.perform(get("/apartments"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            //парсим json into string
            return objectMapper.readValue(response.getContentAsString(),
                    new TypeReference<List<Apartment>>() {
                    });
        }


        public Apartment findById(Integer apartmentId) throws Exception {
            LOGGER.debug("findById({})", apartmentId);
            MockHttpServletResponse response = mockMvc
                    .perform(get("/apartments/" + apartmentId)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Apartment.class);
        }

        public void create(Apartment apartment) throws Exception {
            LOGGER.debug("create({})", apartment);
            String json = objectMapper.writeValueAsString(apartment);
            MockHttpServletResponse response =
                    mockMvc.perform(post("/apartments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                            .andReturn().getResponse();
            objectMapper.readValue(response.getContentAsString(), Void.class);
        }

        public void update(Apartment apartment) throws Exception {
            LOGGER.debug("update({})", apartment);
            MockHttpServletResponse response =
                    mockMvc.perform(put("/apartments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(apartment))
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                            .andReturn().getResponse();
            objectMapper.readValue(response.getContentAsString(), Void.class);
        }

        public void delete(Integer apartmentId) throws Exception {
            LOGGER.debug("delete(id:{})", apartmentId);
            MockHttpServletResponse response =
                    //need to clearly identify MockMvcRequestBuilders.delete because of wrong delete method
                    mockMvc.perform(MockMvcRequestBuilders.delete("/apartments/" + apartmentId)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                            .andReturn().getResponse();

//        as ApartmentController return String, If I will parse JSON I will get a mistake.
//        I shouldn't parse string into JSON
//       return objectMapper.readValue((String) response, String.class);
        }

        public Integer count() throws Exception {
            LOGGER.debug("count()");
            MockHttpServletResponse response = mockMvc.perform(get("/apartments/count")
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

    }

}
