//package com.example.ehailing.customer;
//
//import com.example.ehailing.driver.Driver;
//import com.example.ehailing.driver.DriverRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Configuration
//public class CustomerConfig {
//
//    @Bean
//    public CommandLineRunner configCustomer(CustomerRepository customerRepository, DriverRepository driverRepository) {
//        return args -> {
//            Customer customer = new Customer("John", LocalDateTime.now(), 4,
//                    120, 120, 200, 200, "Pending");
//            customerRepository.save(customer);
//            Optional<Customer> customer1 = customerRepository.findById(Long.valueOf(10));
//            if (customer1 != null) {
//                for (ConnectTo c : customer1.get().getConnectToList())
//                    System.out.println(c);
//            }
//        };
//    }
//}
