package com.obdsimulation.obdautodata.utilities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDatalist {

    String brand;
    List<String> models;

    public CarDatalist(){}

    @JsonCreator
    public CarDatalist(@JsonProperty("brand") String brand, @JsonProperty("models") List<String> models) {
        this.brand = brand;
        this.models = models;

    }

	public String getBrand()
	{
		return this.brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public List<String> getModels()
	{
		return this.models;
	}

	public void setModels(List<String> models)
	{
		this.models = models;
	}

}