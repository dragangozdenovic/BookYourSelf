package com.example.goBookYourself;

import com.example.goBookYourself.DTO.HotelServicePriceDTO;
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

import java.net.URL;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
public class HotelServicePriceControllerTest {

    private static final String URL_PREFIX = "/api/price";

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
        this.token = tokenUtils.generateToken("user2");
    }
    @Test
    public void getHotelAllTest() throws Exception
    {
        this.mockMvc.perform(get(URL_PREFIX+"/all")).andExpect(status().isOk())
                            .andExpect(content().contentType(contentType))
                            .andExpect(jsonPath("$", hasSize(3)))
                            .andExpect(jsonPath("$.[*].name").value(hasItem("Swimming pool")))
                            .andExpect(jsonPath("$.[*].price").value(hasItem(100.0)));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addServiceTest() throws Exception
    {
        assertNotNull(token);
        this.mockMvc.perform(post(URL_PREFIX+"/addService").header("Authorization","Bearer "+this.token).contentType(contentType)
                            .content(TestUtil.json(new HotelServicePriceDTO(2, "Dinner", 12.5, 0,0L))))
                            .andExpect(status().isOk());
    }
}
