package eu.glutfree.glutfree.web;


import eu.glutfree.glutfree.model.entities.FoodEntity;
import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.repository.FoodRepository;
import eu.glutfree.glutfree.repository.StoreRepository;
import eu.glutfree.glutfree.repository.UserRepository;
import eu.glutfree.glutfree.service.LogService;
import eu.glutfree.glutfree.service.StoreService;
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
public class FoodControllerTest {

    private static final String FOOD_CONTROLLER_PREFIX = "/food";


    private long testFoodId;


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private LogService logService;

    @BeforeEach
    public void setup() {
        this.init();
    }

    public void init() {

        userRepository.deleteAll();
        foodRepository.deleteAll();
        storeRepository.deleteAll();

        UserEntity userPesho = new UserEntity();
        userPesho.setUsername("pesho");
        userPesho.setPassword("pesho1");
        UserEntity theUser = userRepository.save(userPesho);

        StoreEntity store = new StoreEntity();
        store.setStoreWebSiteUrl("superURL");
        store.setLogoUrl("superURLLogo");
        store.setName("lidlaaaa");
        StoreEntity thestore = storeRepository.save(store);

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setUrlToPic("testUrl");
        foodEntity.setName("banitca");
        foodEntity.setBrand("brandaa");
        foodEntity.setStore(thestore);
        foodEntity.setNimaTested(true);
        foodEntity.setWithoutLactose(true);
        foodEntity.setMarkedAsGF(true);

        FoodEntity  entity = foodRepository.save(foodEntity);
        testFoodId = entity.getId();



    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_on_Details() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FOOD_CONTROLLER_PREFIX + "/details/{id}",   testFoodId
        )).andExpect(status().isOk()).andExpect(view().name("details-food")).
                andExpect(model().attributeExists("food"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_receipts_add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FOOD_CONTROLLER_PREFIX + "/add"
        )).andExpect(status().isOk()).andExpect(view().name("add-food"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_all_foods() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FOOD_CONTROLLER_PREFIX + "/"
        )).andExpect(status().isOk()).andExpect(view().name("view-foods"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
    void testShouldReturnViewStatusAndModel_of_all_tested_foods() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                FOOD_CONTROLLER_PREFIX + "/tested"
        )).andExpect(status().isOk()).andExpect(view().name("view-testedFoods"));
    }



    //problem with MultipathFile
//    @Test
//    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"}) // autheticateUser
//    void addReceipt() throws Exception {
//
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post(RECEIPT_CONTROLLER_PREFIX + "/add")
//                .param("name", "banitca").
//                        param("typeOfMeal", TypeOfMealsEnums.ЗАКУСКА.name()).
////                        param("image").
//                        param("productsList", "_fKAsvJrFes").
//                        param("description", "Description test").
//                        param("duration", "12").
//                        with(csrf())).
//                andExpect(status().is3xxRedirection());
//
//        Assertions.assertEquals(3, receiptRepository.count());
//    }


}
