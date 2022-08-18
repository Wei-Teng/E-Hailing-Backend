package com.example.ehailing.customer;

import com.example.ehailing.driver.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
@CrossOrigin
public record CustomerController(CustomerService customerService, DriverService driverService) {

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public void saveNewCustomerRequest(@RequestBody Customer customer) {
        customerService.saveNewCustomerRequest(customer);
    }

    @GetMapping(path = "connection/{name}/{pickUpLatitude}/{pickUpLongitude}/{destinationLatitude}/{destinationLongitude}")
    public List<ConnectTo> getChooseList(@PathVariable("name") String name,
                                         @PathVariable("pickUpLatitude") double pickUpLatitude,
                                         @PathVariable("pickUpLongitude") double pickUpLongitude,
                                         @PathVariable("destinationLatitude") double destinationLatitude,
                                         @PathVariable("destinationLongitude") double destinationLongitude) {
        Customer customer = customerService.findTargetCustomer(name, pickUpLatitude, pickUpLongitude,
                destinationLatitude, destinationLongitude);
        return customerService.getChooseList(customer);
    }

    @GetMapping(path = "{name}/{pickUpLatitude}/{pickUpLongitude}/{destinationLatitude}/{destinationLongitude}/{driverId}")
    public Long setUpCustomerWithChosenDriver(@PathVariable("name") String name,
                                                   @PathVariable("pickUpLatitude") double pickUpLatitude,
                                                   @PathVariable("pickUpLongitude") double pickUpLongitude,
                                                   @PathVariable("destinationLatitude") double destinationLatitude,
                                                   @PathVariable("destinationLongitude") double destinationLongitude,
                                                   @PathVariable("driverId") Long driverId) {
        Customer customer = customerService.findTargetCustomer(name, pickUpLatitude, pickUpLongitude,
                destinationLatitude, destinationLongitude);
        customerService.removeUnusualConnectionOfTargetCustomer(customer.getId(), driverId);
        driverService.removeUnusualConnectionOfTargetDriver(customer.getId(), driverId);
        customerService.updateCustomerStatus(customer.getId(), "Waiting");
        driverService.updateDriverAvailability(driverId, false);
        return customer.getId();
    }

    @GetMapping(path = "{id}")
    public void updateCustomerStatusWhenPickedUp(@PathVariable("id") Long id) {
        customerService.updateCustomerStatus(id, "Picked Up");
    }

    @GetMapping(path = "{customerId}/{driverId}")
    public void updateCustomerStatusAndDriverAvailabilityWhenReachDestination(@PathVariable("customerId") Long customerId,
                                                                              @PathVariable("driverId") Long driverId) {
        customerService.updateCustomerStatus(customerId, "Reached");
        customerService.clearConnectToRelationshipOfTargetCustomer(customerId);
        driverService.updateDriverAvailability(driverId, true);
    }
}
