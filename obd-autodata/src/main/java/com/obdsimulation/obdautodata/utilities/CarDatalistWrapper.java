package com.obdsimulation.obdautodata.utilities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.obdsimulation.obdautodata.utilities.CarDatalist;

import java.util.List;

public class CarDatalistWrapper {

    private List<CarDatalist> carDatalist;

	@JsonCreator
	public CarDatalistWrapper(@JsonProperty("carDatalist") List<CarDatalist> carDatalist){
		this.carDatalist = carDatalist;
	}

	public List<CarDatalist> getCardatalist()
	{
		return this.carDatalist;
	}

	public void setCardatalist(List<CarDatalist> carDatalist)
	{
		this.carDatalist = carDatalist;
	}

    
}