package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {


    private  StoreEntity storeEntity1, storeEntity2;

    private StoreServiceImpl storeServiceImplToTest;

    @Mock
    StoreRepository mockedstoreRepository;

    @BeforeEach
    public void init() {

        storeEntity1 = new StoreEntity();
        storeEntity1.setStoreWebSiteUrl("testUrl");
        storeEntity1.setLogoUrl("SuperLogo");
        storeEntity1.setName("lidl");

        storeEntity2 = new StoreEntity();
        storeEntity2.setStoreWebSiteUrl("testUrl2");
        storeEntity2.setLogoUrl("SuperLogo2");
        storeEntity2.setName("lidl2");

        storeServiceImplToTest = new StoreServiceImpl(new ModelMapper(), mockedstoreRepository );


    }


    @Test
    public void testGetStoreByName_should_be_successfully_when_name_exists() {
        when(mockedstoreRepository.findByName("lidla")).thenReturn(Optional.of(storeEntity1));
        StoreEntity lidlStore = storeServiceImplToTest.findStoreByName("lidla");
        Assertions.assertEquals(storeEntity1.getName(),lidlStore.getName());
    }

    @Test
    public void testGetStoreByName_should_return_nullpoint_when_name_doesnt_exist() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            storeServiceImplToTest.findStoreByName("invalidStore");
        });

    }

    @Test
    public void findAllStores_should_return_all_stores() {
        when(mockedstoreRepository.findAllStores()).thenReturn(List.of(storeEntity1.getName(),storeEntity2.getName() ));

        List<String> allStoreNames = storeServiceImplToTest.findAllStores();

        Assertions.assertEquals(2,allStoreNames.size());

    }





}
