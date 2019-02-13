package com.obdsimulation.obdautodata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.obdsimulation.obdautodata.obd.Model;
import com.obdsimulation.obdautodata.obd.ObdReaderError;

public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findByModelname(String modelname);
    List<Model> findByOwner(String owner);
    List<Model> findByErrors(List<ObdReaderError> errors);
    List<Model> findAllByOrderByOwnerAsc();
    
}