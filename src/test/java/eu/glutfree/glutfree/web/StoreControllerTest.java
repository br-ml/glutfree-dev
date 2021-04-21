package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class StoreControllerTest {

    private static final String STORE_CONTROLLER_PREFIX = "/store";


    private long testStoreId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    public void setup() {
        this.init();
    }

    public void init() {


        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setLogoUrl("testUrl");
        storeEntity.setStoreWebSiteUrl("websaita");
        storeEntity.setName("lidlaa");


        storeEntity = storeRepository.save(storeEntity);
        testStoreId = storeEntity.getId();



    }



    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnViewStatusAndModel_of_store_add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                STORE_CONTROLLER_PREFIX + "/add"
        )).andExpect(status().isOk()).andExpect(view().name("add-store"));
    }


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addStore_post_success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(STORE_CONTROLLER_PREFIX + "/add")
                .param("name", "banitca").
                        param("storeWebSiteUrl", "_fKAsvJrFes").
                        param("logoUrl", "UTRest").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(3, storeRepository.count());
    }






}
