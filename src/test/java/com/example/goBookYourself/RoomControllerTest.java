package com.example.goBookYourself;

import com.example.goBookYourself.DTO.RoomDTO;
import com.example.goBookYourself.security.TokenUtils;
import org.hibernate.mapping.Set;
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
import java.time.LocalDate;
import java.util.Date;

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
public class RoomControllerTest {

    private static final String URL_PREFIX = "/api/room";

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
    public void getRoomsTest() throws Exception
    {
        this.mockMvc.perform(get(URL_PREFIX+"/all")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").value(hasSize(4)))
                .andExpect(jsonPath("$.[*].name").value(hasItem("jednokrevetna")))
                .andExpect(jsonPath("$.[*].number_of_room").value(hasItem("202")))
                .andExpect(jsonPath("$.[*].number_bed").value(hasItem(1)));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteRoomTest() throws Exception
    {
        assertNotNull(token);
        this.mockMvc.perform(put(URL_PREFIX+"/deleteRoom").header("Authorization","Bearer "+this.token).contentType(contentType)
                            .content(TestUtil.json(new RoomDTO(1, null, null, false, null, null, 1, 0, 1, 1, 0,0L))))
                            .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void editRoomTest() throws Exception
    {
        assertNotNull(token);
        this.mockMvc.perform(put(URL_PREFIX+"/editRoom").header("Authorization","Bearer "+this.token).contentType(contentType)
                             .content(TestUtil.json(new RoomDTO(1, "jednoooooooo", "145", false, null, null, 1, 0, 6, 4, 0,0L))))
                             .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addRoomTest() throws Exception
    {
        assertNotNull(token);
        this.mockMvc.perform(post(URL_PREFIX+"/addRoom").header("Authorization","Bearer "+this.token).contentType(contentType)
                            .content(TestUtil.json(new RoomDTO(4, "petokrevetna", "505", false,null,null, 2, 100, 5, 5, 0,0L))))
                            .andExpect(status().isOk());
    }


}
