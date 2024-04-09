package com.example.ecommerce.cloudinary.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        Map<String, java.io.Serializable> config = new HashMap<String, java.io.Serializable>();
        config.put("cloud_name", "dzmisciui");
        config.put("api_key", "834632886576358");
        config.put("api_secret", "XpYq-issxmB1LxQCdS_PsIgdY4I");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
