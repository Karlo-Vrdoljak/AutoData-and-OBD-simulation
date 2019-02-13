package com.obdsimulation.obdautodata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obdsimulation.obdautodata.obd.ObdReaderError;

public interface ObdReaderErrorRepository extends JpaRepository<ObdReaderError, Long> {

    ObdReaderError findByErrorcode(int errorCode);
    
    

}