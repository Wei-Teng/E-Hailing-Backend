package com.example.ehailing.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record DriverService(DriverRepository driverRepository) {

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public void saveNewDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }

    public void updateDriverAvailability(Long id, boolean available) {
        driverRepository.updateDriverAvailability(id, available);
    }

    public void removeUnusualConnectionOfTargetDriver(Long customerIdThatNeedRemainConnection, Long driverId) {
        driverRepository.removeUnusualConnectionOfTargetDriver(customerIdThatNeedRemainConnection, driverId);
    }
}
