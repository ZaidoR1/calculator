package com.example.calc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class CalcEntry {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalcEntry.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String operation;

	@JsonProperty(access = Access.READ_ONLY)
	private double result;

	@JsonProperty(access = Access.READ_ONLY)
	private boolean valide;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	public CalcEntry() {
		super();
	}

	public CalcEntry(String operation) {
		super();
		setOperation(operation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		if (operation == null) {
			operation = "";
		}
		this.operation = operation.trim().replaceAll(",", ".");
		try {
			setResult(CalcWorker.calculate(operation));
			setValide(true);
		} catch (Exception e) {
			LOGGER.error("Failed to create result for operation '{}'", operation, e);
			setResult(0);
			setValide(false);
		}
	}

	public double getResult() {
		return result;
	}

	private void setResult(double result) {
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isValide() {
		return valide;
	}

	private void setValide(boolean valide) {
		this.valide = valide;
	}

	@Override
	public String toString() {
		return "CalcEntry [id=" + id + ", operation=" + operation + ", result=" + result + ", valide=" + valide
				+ ", date=" + date + "]";
	}
}
