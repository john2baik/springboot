package com.example.springbootdockdoor.config;

import com.example.springbootdockdoor.model.Door;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoorConfig {
    @Bean Door door(){
        return new Door();
    }
}
