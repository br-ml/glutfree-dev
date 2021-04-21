package eu.glutfree.glutfree.web;


import eu.glutfree.glutfree.model.entities.ReceiptEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import eu.glutfree.glutfree.model.entities.enums.UserRoleEnum;
import eu.glutfree.glutfree.repository.ReceiptRepository;
import eu.glutfree.glutfree.repository.UserRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.AfterEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ReceiptControllerTest {

    private static final String RECEIPT_CONTROLLER_PREFIX = "/receipt";


    private long testReceiptId;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CloudinaryService mockCloudinaryService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReceiptRepository receiptRepository;


    @BeforeEach
    public void setup() {
        this.init();
    }

    public void init() {

        userRepository.deleteAll();
        receiptRepository.deleteAll();

        UserEntity userPesho = new UserEntity();
        userPesho.setUsername("pesho");
        userPesho.setPassword("pesho1");
        UserEntity theUser = userRepository.save(userPesho);



        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setUrlToPic("testUrl");
        receiptEntity.setUser(theUser);
        receiptEntity.setName("banitca");
        receiptEntity.setDescription("mndobrabanitca");
        receiptEntity.setDuration(2);
        receiptEntity.setTypeOfMeal(TypeOfMealsEnums.ЗАКУСКА);
        receiptEntity.setProductsList("listazabanicata");

       ReceiptEntity  entity = receiptRepository.save(receiptEntity);
        testReceiptId = entity.getId();



    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                RECEIPT_CONTROLLER_PREFIX + "/details/{id}",   testReceiptId
        )).andExpect(status().isOk()).andExpect(view().name("details-receipt")).
                andExpect(model().attributeExists("receipt"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_receipts_add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                RECEIPT_CONTROLLER_PREFIX + "/add"
        )).andExpect(status().isOk()).andExpect(view().name("add-receipts"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_all_receipts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                RECEIPT_CONTROLLER_PREFIX + "/"
        )).andExpect(status().isOk()).andExpect(view().name("view-receipts"));
    }



    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void addReceipt() throws Exception {

        MockMultipartFile mockImgFile
                = new MockMultipartFile(
                "image",
                "hello.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart(RECEIPT_CONTROLLER_PREFIX + "/add")
                        .file(mockImgFile)
                        .param("name", "banitca").
                        param("typeOfMeal", TypeOfMealsEnums.ЗАКУСКА.name()).
                        param("productsList", "_fKAsvJrFes").
                        param("description", "Description test").
                        param("duration", "12").
                        with(csrf())).
                andExpect(status().is3xxRedirection());



        Assertions.assertEquals(2, receiptRepository.count());
    }


}
