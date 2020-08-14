package com.project.weather.travel.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "date", "location", "temperature" })
public class Weather {

	@JsonProperty("id")
	private int Id;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("date")
	private Date date;

	@JsonProperty("temperature")
	private float[] temperature;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float[] getTemperature() {
		return temperature;
	}

	public void setTemperature(float[] temperature) {
		this.temperature = temperature;
	}

}
