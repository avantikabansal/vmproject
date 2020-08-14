package com.project.weather.travel.model;

//import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "lat", "lon", "city", "state" })
public class Location {

	private int locationId;

	@JsonProperty("city")
	private String city;

	@JsonProperty("state")
	private String state;

	@JsonProperty("lon")
	private double longitude;

	@JsonProperty("lat")
	private double latitude;
	
	private List<Weather> weatherData;

	@JsonIgnore
	public List<Weather> getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(List<Weather> weatherData) {
		this.weatherData = weatherData;
	}

	@JsonIgnore
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
