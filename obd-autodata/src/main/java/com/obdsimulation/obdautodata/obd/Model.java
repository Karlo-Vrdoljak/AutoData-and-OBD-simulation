package com.obdsimulation.obdautodata.obd;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "model_id")
    private Long id;

    @NotEmpty
    @Column(name = "manufacturer_name")
    private String manufacturername;

    @Column(name = "owner")
    private String owner;

    @NotEmpty
    @Column(name = "model_name")
    private String modelname;

    @Column(name = "vin")
    private String vin;

    @NotEmpty
    @Column(name = "year")
    private String year;

    @Column(name = "engine_code") // c16nz
    private String engineCode;

    @NotEmpty
    @Column(name = "engine_type") // benzinac /dizel /lpg
    private String engineType;

    @NotEmpty
    @Column(name = "power") // 75hp/5200 rpm
    private String power;

    @Column(name = "compression_ratio") // 9.2
    private String compressionRatio;

    @Column(name = "cylinders") // inline 4, v 8 v6 inline 6 etc
    private String cylinders;

    @Column(name = "engine_oil_grade") // 10w40 etc
    private String engineOilGrade;

    @Column(name = "engine_capacity") // 1.6, 2.5, 3.0
    private String engineCapacity;

    @NotEmpty
    @Column(name = "gearbox") // 5 speed manual etc
    private String gearbox;

    @Column(name = "max_rpm") // max rpm
    private String maxRpm;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<ObdReaderError> errors;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelname() {
        return this.modelname;
    }

    public Model setModelname(String modelname) {
        this.modelname = modelname;
        return this;
    }

    public String getVin() {
        return this.vin;
    }

    public Model setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getYear() {
        return this.year;
    }

    public Model setYear(String year) {
        this.year = year;
        return this;
    }

    public String getEngineCode() {
        return this.engineCode;
    }

    public Model setEngineCode(String engineCode) {
        this.engineCode = engineCode;
        return this;
    }

    public String getEngineType() {
        return this.engineType;
    }

    public Model setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public String getPower() {
        return this.power;
    }

    public Model setPower(String power) {
        this.power = power;
        return this;
    }

    public String getCompressionRatio() {
        return this.compressionRatio;
    }

    public Model setCompressionRatio(String compressionRatio) {
        this.compressionRatio = compressionRatio;
        return this;
    }

    public String getCylinders() {
        return this.cylinders;
    }

    public Model setCylinders(String cylinders) {
        this.cylinders = cylinders;
        return this;
    }

    public String getEngineOilGrade() {
        return this.engineOilGrade;
    }

    public Model setEngineOilGrade(String engineOilGrade) {
        this.engineOilGrade = engineOilGrade;
        return this;
    }

    public String getEngineCapacity() {
        return this.engineCapacity;
    }

    public Model setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
        return this;
    }

    public String getGearbox() {
        return this.gearbox;
    }

    public Model setGearbox(String gearbox) {
        this.gearbox = gearbox;
        return this;
    }

    public String getMaxRpm() {
        return this.maxRpm;
    }

    public Model setMaxRpm(String maxRpm) {
        this.maxRpm = maxRpm;
        return this;
    }

    public List<ObdReaderError> getErrors() {
        return this.errors;
    }

    public Model setErrors(List<ObdReaderError> errors) {
        this.errors = errors;
        return this;
    }

    public String getManufacturername() {
        return this.manufacturername;
    }

    public Model setManufacturername(String manufacturername) {
        this.manufacturername = manufacturername;
        return this;
    }

    public String getOwner() {
        return this.owner;
    }

    public Model setOwner(String owner) {
        this.owner = owner;
        return this;
    }

}
