package com.example.goBookYourself;

import com.example.goBookYourself.DTO.HotelDTO;
import com.example.goBookYourself.DTO.HotelServicePriceDTO;
import com.example.goBookYourself.DTO.RoomDTO;
import com.example.goBookYourself.model.HotelServicePrice;
import com.example.goBookYourself.model.Room;
import com.example.goBookYourself.security.TokenUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
public class HotelControllerTest {

    private static final String URL_PREFIX = "/api/hotel";

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private String token;
    private String token1;
    @Autowired
    private TokenUtils tokenUtils;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.token = tokenUtils.generateToken("user2");
        this.token1 = tokenUtils.generateToken("user4");

    }

    @Test
    public void getHotelAllTest() throws Exception
    {
        this.mockMvc.perform(get(URL_PREFIX+"/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$.[*].name").value(hasItem("grand_hotel")))
                .andExpect(jsonPath("$.[*].address").value(hasItem("Kopaonik bb")));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void changeHotelTest() throws Exception
    {
        assertNotNull(token);
        this.mockMvc.perform(put(URL_PREFIX+"/changeHotel").header("Authorization","Bearer " + this.token).contentType(contentType)
                .content(TestUtil.json(new HotelDTO(2, "grand_hotel", "Cvetna", "WOW", null, null, (Set)new HashSet<Room>(), (Set)new HashSet<HotelServicePrice>()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("name").value("grand_hotel"))
                .andExpect(jsonPath("address").value("Cvetna"))
                .andExpect(jsonPath("promoDescription").value("WOW"));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addHotelTest() throws Exception
    {
        assertNotNull(token1);
        this.mockMvc.perform(post(URL_PREFIX+"/add").header("Authorization","Bearer " + this.token1).contentType(contentType)
                .content(TestUtil.json(new HotelDTO(3, "proba1", "proba1", "oooooooProba", "100 000", "porba.jpg", (Set)new HashSet<Room>(), (Set)new HashSet<HotelServicePrice>()))))
                .andExpect(status().isOk());
    }

}
