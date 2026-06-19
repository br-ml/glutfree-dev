package eu.glutfree.glutfree.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudConfig {

        private final Environment env;

        public CloudConfig(Environment env) {
            this.env = env;
        }

        @Bean
        public Cloudinary cloudinary(){return  new Cloudinary(env.getProperty("CLOUDINARY_URL"));

//
//                    new Cloudinary(ObjectUtils.asMap(
//                    "cloud_name", env.getProperty("CLOUD-NAME"),
//                    "api_key", env.getProperty("API-KEY"),
//                    "api_secret", env.getProperty("API-SECRET")));
        }

    }

