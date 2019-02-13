package com.obdsimulation.obdautodata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.obdsimulation.obdautodata.obd.Model;
import com.obdsimulation.obdautodata.obd.ObdReader;
import com.obdsimulation.obdautodata.obd.ObdReaderError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.obdsimulation.obdautodata.repository.ModelRepository;
import com.obdsimulation.obdautodata.repository.ObdReaderErrorRepository;
import com.obdsimulation.obdautodata.utilities.CarDatalistWrapper;

@Service
public class ObdModelService {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    ObdReaderErrorRepository errorRepository;
    private static final int MAX_CODE = 88;
    private static final int MIN_CODE = 13;

    private CarDatalistWrapper carDatalistWrapper;

    public CarDatalistWrapper getCardatalistwrapper() {
        return this.carDatalistWrapper;
    }

    public void setCardatalistwrapper(CarDatalistWrapper carDatalistWrapper) {
        this.carDatalistWrapper = carDatalistWrapper;
    }

    private Model simulateErrorCodeScan(Model vehicle) {
        int errorCode;
        Random rand = new Random();
        int numberOfErrors = rand.nextInt((int) errorRepository.count()) / 2;
        ObdReaderError error = new ObdReaderError();
        List<ObdReaderError> errors = new ArrayList<ObdReaderError>();
        for (int i = 0; i < numberOfErrors; i += 1) {
            errorCode = rand.nextInt((MAX_CODE - MIN_CODE)) + MIN_CODE;
            if (errorRepository.findByErrorcode(errorCode) != null) {
                error = errorRepository.findByErrorcode(errorCode);
                if (errors.contains(error) == false) {
                    errors.add(error);
                }
            }
        }
        if(errors.contains(errorRepository.findByErrorcode(12)) == false){
            errors.add(errorRepository.findByErrorcode(12));
        }
        vehicle.setErrors(errors);
        return vehicle;
    }

    public List<Model> getAllVehicles() {
        return modelRepository.findAll();
    }

    public List<Model> getAllVehiclesSortedByOwner() {
        return modelRepository.findAllByOrderByOwnerAsc();
    }

    public Optional<Model> getModelByID(Long id){
        return modelRepository.findById(id);
    }

    public List<Model> getVehiclesByOwner(String owner) {
        return modelRepository.findByOwner(owner);
    }

    public List<Model> getVehiclesByErrors(List<ObdReaderError> errors) {
        return modelRepository.findByErrors(errors);
    }

    public Model saveVehicle(Model vehicle, String owner) {
        vehicle.setOwner(owner).setMaxRpm("6000");
        vehicle = simulateErrorCodeScan(vehicle);
        return modelRepository.save(vehicle);
    }

    public void deleteVehicle(Model vehicle) {
        modelRepository.delete(vehicle);
    }

    @PostConstruct
    private void setupDefaultVehicle() throws IOException {

        this.carDatalistWrapper = new ObjectMapper().readerFor(CarDatalistWrapper.class).readValue(new File(
                "C:\\Users\\1karl\\Documents\\GitHub\\AutoData\\AutoData-and-OBD-simulation\\obd-autodata\\src\\main\\resources\\car_list.json"));

        if (errorRepository.count() == 0) {
            ObdReader errors = new ObjectMapper().readerFor(ObdReader.class).readValue(new File(
                    "C:\\Users\\1karl\\Documents\\GitHub\\AutoData\\AutoData-and-OBD-simulation\\obd-autodata\\src\\main\\resources\\error_code_config.json"));
            for (ObdReaderError error : errors.readAllData()) {
                errorRepository.save(error);
            }
        }
        if (modelRepository.count() == 0) {

            modelRepository.save(new Model().setOwner("ramiz").setManufacturername("Opel").setCompressionRatio("9.2")
                    .setCylinders("Inline 4").setEngineCode("C16NZ").setEngineCapacity("1.6").setEngineOilGrade("15w40")
                    .setEngineType("Gasoline").setGearbox("5 Speed Manual").setMaxRpm("6000").setModelname("Astra F")
                    .setPower("75 HP").setVin("W0L000058T2562926").setYear("1994")
                    .setErrors(Arrays.asList(errorRepository.findByErrorcode(13), errorRepository.findByErrorcode(21),
                            errorRepository.findByErrorcode(32), errorRepository.findByErrorcode(39)
                    //
                    )))

            ;
        }
    }

}

/*
 * int seconds = (totalSeconds % 60); int minutes = (totalSeconds % 3600) / 60;
 * int hours = (totalSeconds % 86400) / 3600; int days = (totalSeconds % (86400
 * * 30)) / 86400;
 */