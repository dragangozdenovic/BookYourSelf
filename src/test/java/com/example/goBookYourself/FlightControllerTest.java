package com.example.goBookYourself;

import com.example.goBookYourself.DTO.FlightDTO;
import com.example.goBookYourself.DTO.LocationDTO;
import com.example.goBookYourself.model.AvioCompany;
import com.example.goBookYourself.model.Flight;
import com.example.goBookYourself.model.Location;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.service.AvioCompanyService;
import com.example.goBookYourself.service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashSet;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
public class FlightControllerTest {

    private static final String URL_PREFIX = "/api/flights";

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private String token;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private FlightService service;

    private Flight flight;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.token = tokenUtils.generateToken("user3");
        flight = service.findById(1);
    }

    @Test
    public void getAllFlightsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].ticketPrice").value(hasItem((double) 150)));
    }

    @Test
    public void getOurFlightsTest() throws Exception {
        assertNotNull(token);
        mockMvc.perform(get(URL_PREFIX + "/allOur").header("Authorization", "Bearer " + this.token)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].ticketPrice").value(hasItem((double) 150)));

        mockMvc.perform(get(URL_PREFIX + "/allOur")).andExpect(status().isUnauthorized());
    }
}
