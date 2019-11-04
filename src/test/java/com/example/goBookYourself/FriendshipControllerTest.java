package com.example.goBookYourself;

import com.example.goBookYourself.security.TokenUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FriendshipControllerTest {

    private static final String URL_PREFIX = "/api/friendships";

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
        this.token = tokenUtils.generateToken("user1");
    }

    @Test
    public void getAllFriendshipsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getAllFriendsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/friends").header("Authorization", "Bearer " + this.token)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addAndAcceptFriendTest() throws Exception {
        mockMvc.perform(post(URL_PREFIX + "/add").header("Authorization", "Bearer " + this.token).contentType(contentType)
                .content("user3"))
                .andExpect(status().isCreated());

        mockMvc.perform(get(URL_PREFIX + "/friendRequests").header("Authorization", "Bearer " + tokenUtils.generateToken("user3"))).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)));

        mockMvc.perform(put(URL_PREFIX + "/accept").header("Authorization", "Bearer " + tokenUtils.generateToken("user3")).contentType(contentType)
                .content("user1"))
                .andExpect(status().isOk());
    }

}
