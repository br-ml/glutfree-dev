package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.exceptions.StoreNotFoundException;
import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.service.StoreAddServiceModel;
import eu.glutfree.glutfree.repository.StoreRepository;
import eu.glutfree.glutfree.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final ModelMapper modelMapper;
    private final StoreRepository storeRepository;

    public StoreServiceImpl(ModelMapper modelMapper, StoreRepository storeRepository) {
        this.modelMapper = modelMapper;
        this.storeRepository = storeRepository;
    }


    @Override
    public void addStore(StoreAddServiceModel storeAddServiceModel) {
        StoreEntity store = modelMapper.map(storeAddServiceModel, StoreEntity.class);
        storeRepository.save(store);
    }

    @Override
    public void seedStores() {
        if( storeRepository.count() == 0 ) {
            StoreEntity lidl = new StoreEntity();
            lidl.setName("Лидл");
            lidl.setLogoUrl("/img/Stores/Lidl-Logo.png");
            lidl.setStoreWebSiteUrl("https://lidl.bg");

            storeRepository.save(lidl);
        }
    }

    @Override
    public StoreEntity findStoreByName(String name) {
        return storeRepository.findByName(name).orElseThrow(() -> new StoreNotFoundException("This Store does not exist in the database!"));
    }

    @Override
    public List<String> findAllStores() {
        return storeRepository.findAllStores();
    }
}
