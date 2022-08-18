package com.example.ehailing.driver;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/driver")
@CrossOrigin
public record DriverController(DriverService driverService) {

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @PostMapping
    public void saveNewDriver(@RequestBody Driver driver) {
        driverService.saveNewDriver(driver);
    }

    @DeleteMapping(path = "{driverId}")
    public void deleteDriverById(@PathVariable("driverId") Long id) {
        driverService.deleteDriverById(id);
    }
}
