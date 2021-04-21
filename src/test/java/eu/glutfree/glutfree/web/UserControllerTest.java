package eu.glutfree.glutfree.web;



import eu.glutfree.glutfree.model.entities.UserEntity;
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

import java.io.IOException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws IOException {
        this.init();
    }


    @Test
    @WithMockUser(username = "pesho", roles = {"USER"})
    void testLoginPageReturnValidStatusOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")).
                andExpect(status().isOk()).
                andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "pesho", roles = {"USER", "ADMIN"})
    void testRegisterPageReturnValidStatusOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }


    @Test
    void testLoginPageReturnRedirectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error").
                param("username", "pesho").
                param("password", "pesho1")
                .with(csrf())).
                andExpect(status().is3xxRedirection());
    }

    @Test
    void testRegisterPageReturnInvalidRedirectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").
                param("username", "pesho111").
                param("password", "pesho1111").
                param("email", "peshopesho.com").
                param("confirmPassword", "pesho1")
                .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/users/register"));
    }

    @Test
    void testRegisterPageReturnInvalidRedirectStatusForAlreadyCreatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").
                param("username", "pesho").
                param("email", "pesho@pesho.com").
                param("password", "pesho1").
                param("confirmPassword", "pesho1")
                .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/users/register"));
    }

    @Test
    void testRegisterPageReturnValidRedirectStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").
                param("username", "pesho1").
                param("email", "pesho@pesho.com").
                param("password", "pesho1").
                param("confirmPassword", "pesho1")
                .with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/home"));
    }


    private void init() {
        UserEntity userEntity = new UserEntity();

        if (userRepository.findByUsername("pesho").isPresent()) {
            userEntity = userRepository.findByUsername("pesho").get();
        } else {
            userEntity.setUsername("pesho");
            userEntity.setEmail("pesho@pesho.com");
            userEntity.setPassword("pesho1");
            userEntity = userRepository.save(userEntity);
        }
    }

}
