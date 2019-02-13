package com.obdsimulation.obdautodata.obd;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "errors")
public class ObdReaderError {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "error_id")
	private Long id;


	@Column(name = "error_code")
	private int errorcode;


	@Column(name = "fault")
	private String fault;


	@Column(name = "cause")
	private String cause;


	@Column(name = "repair_time")
	private double repairTime;

	public ObdReaderError(){}

	@JsonCreator
	public ObdReaderError(@JsonProperty("errorCode") int errorcode, @JsonProperty("fault") String fault,
			@JsonProperty("cause") String cause, @JsonProperty("repairTime") double repairTime) {
		this.errorcode = errorcode;
		this.fault = fault;
		this.cause = cause;
		this.repairTime = repairTime;
	}

	public double getRepairTime() {
		return this.repairTime;
	}

	public ObdReaderError setRepairTime(double repairTime) {
		this.repairTime = repairTime;
		return this;
	}

	public String getCause() {
		return this.cause;
	}

	public ObdReaderError setCause(String cause) {
		this.cause = cause;
		return this;
	}

	public String getFault() {
		return this.fault;
	}

	public ObdReaderError setFault(String fault) {
		this.fault = fault;
		return this;
	}

	public Long getId() {
		return this.id;
	}

	public ObdReaderError setId(Long id) {
		this.id = id;
		return this;
	}

	public int getErrorcode() {
		return this.errorcode;
	}

	public ObdReaderError setErrorcode(int errorcode) {
		this.errorcode = errorcode;
		return this;
	}

}