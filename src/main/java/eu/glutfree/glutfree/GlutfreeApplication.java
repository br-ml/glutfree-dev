package eu.glutfree.glutfree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GlutfreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlutfreeApplication.class, args);
    }

}
