package eu.glutfree.glutfree.web;


import eu.glutfree.glutfree.model.entities.FeedbackEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import eu.glutfree.glutfree.repository.FeedbackRepository;
import eu.glutfree.glutfree.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class HomeControllerTest {

    private static final String HOME_CONTROLLER_PREFIX = "/";


    @Autowired
    private MockMvc mockMvc;


    @Test
    void testShouldReturnViewStatusAndModel_for_Index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                HOME_CONTROLLER_PREFIX
        )).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnViewStatusAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/home"
        )).andExpect(status().isOk()).andExpect(view().name("index"));
    }



}
