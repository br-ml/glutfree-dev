package eu.glutfree.glutfree.web;


import eu.glutfree.glutfree.model.entities.FeedbackEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import eu.glutfree.glutfree.repository.FeedbackRepository;
import eu.glutfree.glutfree.repository.UserRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class FeedbackControllerTest {

    private static final String FEEDBACK_CONTROLLER_PREFIX = "/feedback";


    private long testFeedbackId;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CloudinaryService mockCloudinaryService;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;


    @BeforeEach
    public void setup() {
        this.init();
    }

    public void init() {

        userRepository.deleteAll();
        feedbackRepository.deleteAll();

        UserEntity userPesho = new UserEntity();
        userPesho.setUsername("pesho");
        userPesho.setPassword("pesho1");
        UserEntity theUser = userRepository.save(userPesho);



        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setUrlToPic("testUrl");
        feedbackEntity.setUser(theUser);
        feedbackEntity.setName("banitca");
        feedbackEntity.setFeedbackText("mndobrabanitca");
        feedbackEntity.setScore(2);
        feedbackEntity.setTypeOfPlace(TypeOfPlaceEnums.МАГАЗИН);
        feedbackEntity.setWebSiteUrl("listazabanicata");

        FeedbackEntity  entity = feedbackRepository.save(feedbackEntity);
        testFeedbackId = entity.getId();



    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FEEDBACK_CONTROLLER_PREFIX + "/details/{id}",   testFeedbackId
        )).andExpect(status().isOk()).andExpect(view().name("details-feedback")).
                andExpect(model().attributeExists("feedback"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_feedbacks_add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FEEDBACK_CONTROLLER_PREFIX + "/add"
        )).andExpect(status().isOk()).andExpect(view().name("add-feedback"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_all_feedbacks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FEEDBACK_CONTROLLER_PREFIX + "/"
        )).andExpect(status().isOk()).andExpect(view().name("view-feedbacks"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void addFeedback_should_add_feedback_success_post() throws Exception {

        MockMultipartFile mockImgFile
                = new MockMultipartFile(
                "image",
                "hello.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart(FEEDBACK_CONTROLLER_PREFIX + "/add")
                .file(mockImgFile)
                .param("name", "banitca").
                        param("typeOfMeal", TypeOfPlaceEnums.МАГАЗИН.name()).
                        param("webSiteUrl", "fKAsvJrFes").
                        param("feedbackText", "Description test").
                        param("score", "2").
                        param("typeOfPlace", String.valueOf(TypeOfPlaceEnums.МАГАЗИН)).
                        with(csrf())).
                andExpect(status().is3xxRedirection());


        Assertions.assertEquals(2, feedbackRepository.count());
    }



}
