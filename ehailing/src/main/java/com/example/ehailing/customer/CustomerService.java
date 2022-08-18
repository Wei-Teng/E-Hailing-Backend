package com.example.ehailing.customer;

import com.example.ehailing.driver.Driver;
import com.example.ehailing.driver.DriverRepository;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository, DriverRepository driverRepository) {

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void saveNewCustomerRequest(Customer customer) {
        // Server have converts malaysia time received from front end into utc time, so code below convert it back
        customer.setExpectedArrivalDateTime(customer.getExpectedArrivalDateTime().plusHours(8));
        customerRepository.save(customer);
    }

    public Customer findTargetCustomer(String name, double pickUpLatitude,
                                       double pickUpLongitude, double destinationLatitude,
                                       double destinationLongitude) {
        return customerRepository.findTargetCustomer(name, pickUpLatitude, pickUpLongitude,
                destinationLatitude, destinationLongitude);
    }

    public List<ConnectTo> getChooseList(Customer customer) {
        clearConnectToRelationshipOfTargetCustomer(customer.getId());
        createNewConnectToRelationshipOfTargetCustomer(customer);
        return customerRepository.findById(customer.getId()).get().getConnectToList();
    }

    public void clearConnectToRelationshipOfTargetCustomer(Long id) {
        customerRepository.clearConnectToRelationshipOfTargetCustomer(id);
    }

    public void createNewConnectToRelationshipOfTargetCustomer(Customer customer) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        for (Driver driver : driverRepository.findAll()) {
            Duration timeGiven = Duration.between(LocalDateTime.now(), customer.getExpectedArrivalDateTime());
            if (driver.isAvailable() && driver.getCarCapacity() >= customer.getPassengerNum() && timeGiven.getSeconds() >= 0) {
                // speed is 100 km/min
                double pickedUpTimeTaken = Double.parseDouble(df.format(distanceFromDriverToCustomer(driver, customer) / 100));
                double reachDestinationTimeTaken = Double.parseDouble(df.format(distanceFromCustomerToDestination(customer) / 100));
                if (pickedUpTimeTaken + reachDestinationTimeTaken <= timeGiven.getSeconds() / 60.0)
                    customerRepository.connectToAvailableDriver(customer.getId(), driver.getId(), pickedUpTimeTaken, reachDestinationTimeTaken);
            }
        }
    }

    public void removeUnusualConnectionOfTargetCustomer(Long customerId, Long driverIdThatNeedRemainConnection) {
        customerRepository.removeUnusualConnectionOfTargetCustomer(customerId, driverIdThatNeedRemainConnection);
    }

    public void updateCustomerStatus(Long id, String status) {
        customerRepository.updateCustomerStatus(id, status);
    }

    private double distanceFromDriverToCustomer(Driver driver, Customer customer) {
        return Math.sqrt(Math.pow(driver.getLatitude()-customer.getPickUpLatitude(), 2)+Math.pow(driver.getLongitude()-customer.getPickUpLongitude(), 2));
    }

    private double distanceFromCustomerToDestination(Customer customer) {
        return Math.sqrt(Math.pow(customer.getPickUpLatitude()-customer.getDestinationLatitude(), 2)+Math.pow(customer.getPickUpLongitude()-customer.getDestinationLongitude(), 2));
    }
}
