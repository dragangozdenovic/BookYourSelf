package com.example.goBookYourself;

import com.example.goBookYourself.DTO.UserDTO;
import com.example.goBookYourself.controller.AuthenticationController;
import com.example.goBookYourself.model.Authority;
import com.example.goBookYourself.security.TokenUtils;
import com.example.goBookYourself.security.auth.JwtAuthenticationRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationControllerTest {

    private static final String URL_PREFIX = "/api/auth";

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private String token;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TokenUtils tokenUtils;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.token = tokenUtils.generateToken("user1");

    }

    @Test
    public void userProfileUnauthenticated() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/profile"))
                .andExpect(status().is(401));
    }

    @Test
    public void userProfileAuthenticated() throws Exception {
        assertNotNull(token);
        mockMvc.perform(get(URL_PREFIX + "/profile").header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("username").value("user1"))
                .andExpect(jsonPath("name").value("Nenad"))
                .andExpect(jsonPath("surname").value("Marković"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void loginTest() throws Exception{

        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(TestUtil.json(new JwtAuthenticationRequest("user1", "123"))))
                .andExpect(status().isOk());

        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(TestUtil.json(new JwtAuthenticationRequest("user1", "321"))))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void allNonFriendsServiceAuthenticated() throws Exception {
        assertNotNull(token);
        mockMvc.perform(get(URL_PREFIX + "/allNonFriends").header("Authorization", "Bearer " + this.token))
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(3)))
                .andExpect(jsonPath("$.[*].username").value(hasItem("user3")))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Marko")))
                .andExpect(jsonPath("$.[*].surname").value(hasItem("Djenovic")));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void changeInfoServiceAuthenticated() throws Exception {
        assertNotNull(token);
        mockMvc.perform(post(URL_PREFIX + "/changeInfo")
                .header("Authorization", "Bearer " + this.token)
                .contentType(contentType)
                .content(TestUtil.json(new UserDTO(null, null, "test@mail.com", null, "testName", "testSurname", "Pancevo", "064-1234567", null,null, new ArrayList<>(), false))))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("email").value("test@mail.com"))
                .andExpect(jsonPath("name").value("testName"))
                .andExpect(jsonPath("surname").value("testSurname"))
                .andExpect(jsonPath("city").value("Pancevo"))
                .andExpect(jsonPath("number").value("064-1234567"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void changePasswordServiceAuthenticated() throws Exception {
        AuthenticationController.PasswordChanger passwordChanger = new AuthenticationController.PasswordChanger();
        passwordChanger.newPassword = "321";
        passwordChanger.oldPassword = "123";

        assertNotNull(token);
        mockMvc.perform(post(URL_PREFIX + "/change-password")
                .header("Authorization", "Bearer " + this.token)
                .contentType(contentType)
                .content(TestUtil.json(passwordChanger)))
                .andExpect(status().is(202));
    }
}
