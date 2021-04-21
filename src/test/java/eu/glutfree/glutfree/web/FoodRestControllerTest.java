package eu.glutfree.glutfree.web;


import eu.glutfree.glutfree.model.entities.FoodEntity;
import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.repository.FoodRepository;
import eu.glutfree.glutfree.repository.LogRepository;
import eu.glutfree.glutfree.repository.StoreRepository;
import eu.glutfree.glutfree.repository.UserRepository;
import eu.glutfree.glutfree.service.LogService;
import eu.glutfree.glutfree.service.StoreService;
import org.junit.jupiter.api.AfterEach;
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
public class FoodRestControllerTest {



    private Long testFoodId;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private LogRepository logRepository;



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


    @AfterEach
    public void tearDown() {
        foodRepository.deleteAll();
        logRepository.deleteAll();
        storeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testFetch_all_foods() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/food/api")).
                andExpect(status().isOk()).
                andExpect(jsonPath("[0].name").value("banitca")).
                andExpect(jsonPath("[0].brand").value("brandaa")).
                andExpect(jsonPath("[0].urlToPic").value("testUrl"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testFetchAlbums_all_tested_foods() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/food/api-tested")).
                andExpect(status().isOk()).
                andExpect(jsonPath("[0].name").value("banitca")).
                andExpect(jsonPath("[0].brand").value("brandaa")).
                andExpect(jsonPath("[0].urlToPic").value("testUrl"));
    }






}
