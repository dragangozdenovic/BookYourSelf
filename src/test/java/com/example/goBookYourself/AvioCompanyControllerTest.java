package com.example.goBookYourself;

import com.example.goBookYourself.DTO.AvioCompanyDTO;
import com.example.goBookYourself.DTO.LocationDTO;
import com.example.goBookYourself.model.AvioPrices;
import com.example.goBookYourself.security.TokenUtils;
import com.sun.xml.internal.bind.v2.runtime.Location;
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
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
public class AvioCompanyControllerTest {

    private static final String URL_PREFIX = "/api/aviocompanies";

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private String token;

    @Autowired
    private TokenUtils tokenUtils;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.token = tokenUtils.generateToken("user3");
    }

    @Test
    public void getAllAvioCompaniesTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Swiss")))
                .andExpect(jsonPath("$.[*].image").value(hasItem("swiss.png")));
    }

    @Test
    public void getAllOurTicketsTest() throws Exception {
        assertNotNull(token);
        mockMvc.perform(get(URL_PREFIX + "/allOurTickets").header("Authorization", "Bearer " + this.token)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(13)))
                .andExpect(jsonPath("$.[*].fastReservationDiscount").value(hasItem(0)))
                .andExpect(jsonPath("$.[*].reserved").value(hasItem(false)))
                .andExpect(jsonPath("$.[*].seatNumber").value(hasItem(2)));

        mockMvc.perform(get(URL_PREFIX + "/allOurTickets")).andExpect(status().isUnauthorized());
    }

    @Test
    public void getAllAvioCompaniesNotMineTest() throws Exception {
        assertNotNull(token);
        mockMvc.perform(get(URL_PREFIX + "/allNotMine").header("Authorization", "Bearer " + this.token)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].airport").value(hasItem("b")))
                .andExpect(jsonPath("$.[*].city").value(hasItem("b")))
                .andExpect(jsonPath("$.[*].country").value(hasItem("b")));

        mockMvc.perform(get(URL_PREFIX + "/allNotMine")).andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeLocationTest() throws Exception {
        assertNotNull(token);
        mockMvc.perform(put(URL_PREFIX + "/remove").header("Authorization", "Bearer " + this.token).contentType(contentType)
                .content(TestUtil.json(new LocationDTO(1, "", "", "", (long) 1))))
                .andExpect(status().isOk());

        mockMvc.perform(put(URL_PREFIX + "/remove").contentType(contentType)
                .content(TestUtil.json(new LocationDTO(1, "", "", "", (long) 1))))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addLocationTest() throws Exception {
        assertNotNull(token);
        mockMvc.perform(put(URL_PREFIX + "/addToAvio").header("Authorization", "Bearer " + this.token).contentType(contentType)
                .content(TestUtil.json(new LocationDTO(3, "", "", "", (long) 1))))
                .andExpect(status().isOk());

        mockMvc.perform(put(URL_PREFIX + "/addToAvio").contentType(contentType)
                .content(TestUtil.json(new LocationDTO(3, "", "", "", (long) 1))))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void changeAvioInfoTest() throws Exception {
        assertNotNull(token);
        mockMvc.perform(put(URL_PREFIX + "/changeAvio").header("Authorization", "Bearer " + this.token).contentType(contentType)
                .content(TestUtil.json(new AvioCompanyDTO(3, "Swiss", "lalal", "lalala", (Set)new HashSet<Location>(), "lalala", (Set)new HashSet<AvioPrices>()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("name").value("Swiss"))
                .andExpect(jsonPath("address").value("lalal"))
                .andExpect(jsonPath("promoDescription").value("lalala"));

        mockMvc.perform(put(URL_PREFIX + "/changeAvio").contentType(contentType)
                .content(TestUtil.json(new AvioCompanyDTO(3, "Swiss", "lalal", "lalala", (Set)new HashSet<Location>(), "lalala", (Set)new HashSet<AvioPrices>()))))
                .andExpect(status().isUnauthorized());
    }
}
