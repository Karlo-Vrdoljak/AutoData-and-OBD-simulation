package com.obdsimulation.obdautodata.obd;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class ObdReader implements ObdReaderBase {


    private ObdReaderError[] errors;

	public ObdReaderError[] getErrors()
	{
		return this.errors;
	}

	public void setErrors(ObdReaderError[] errors)
	{
		this.errors = errors; 
	}

    @JsonCreator
	public ObdReader(@JsonProperty("errors") ObdReaderError[] errors) {
		this.errors = errors;
	}
    
    @Override
    public ObdReaderError[] readAllData() {
        return this.errors;
    }

    
}