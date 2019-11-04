package com.example.goBookYourself;


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
import org.springframework.web.util.NestedServletException;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TicketControllerTest {

    private static final String URL_PREFIX = "/api/tickets";

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
    public void getAllTicketsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(15)));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    @Rollback(true)
    public void removeTicketTest() throws Exception {
        mockMvc.perform(put(URL_PREFIX + "/remove").header("Authorization", "Bearer " + this.token).contentType(contentType)
                .content(TestUtil.json(1)))
                .andExpect(status().isOk());
    }
}
