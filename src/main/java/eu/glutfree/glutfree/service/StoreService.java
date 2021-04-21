package eu.glutfree.glutfree.service;

import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.service.ReceiptAddServiceModel;
import eu.glutfree.glutfree.model.service.StoreAddServiceModel;

import java.util.List;

public interface StoreService {

    void addStore (StoreAddServiceModel storeAddServiceModel);

    void seedStores();


    StoreEntity findStoreByName(String name);

    List<String> findAllStores();
}

