//package com.example.ehailing.driver;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DriverConfig {
//
//    @Bean
//    public CommandLineRunner configDriver(DriverRepository driverRepository) {
//        return args -> {
//            Driver driver = new Driver("John", "Honda Jazz", 4,
//                    150, 180, true);
//            driverRepository.save(driver);
//        };
//    }
//}
