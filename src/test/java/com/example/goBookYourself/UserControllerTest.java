package com.example.goBookYourself;

import com.example.goBookYourself.security.TokenUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
public class UserControllerTest {

    private static final String URL_PREFIX = "/api/users";

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllAvioCompaniesTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].username").value(hasItem("user1")))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Nenad")))
                .andExpect(jsonPath("$.[*].surname").value(hasItem("Marković")));
    }



}
