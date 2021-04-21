package eu.glutfree.glutfree;

import eu.glutfree.glutfree.service.StoreService;
import eu.glutfree.glutfree.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GlutfreeDBApplicationInit implements CommandLineRunner {

    private final UserService userService;
    private final StoreService storeService;

    public GlutfreeDBApplicationInit(UserService userService, StoreService storeService) {
        this.userService = userService;
        this.storeService = storeService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        storeService.seedStores();

    }
}