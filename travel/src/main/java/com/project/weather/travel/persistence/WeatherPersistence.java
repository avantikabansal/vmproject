package com.project.weather.travel.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "WEATHER")
public class WeatherPersistence {

	@Id
	@Column(name = "WEATHERID")
	private int weatherID;

	@Column(name = "LOCATIONID")
	private int locationId;

	@Column(name = "DATE")
	private Date date;

	@Column(name = "TEMPERATURE")
	private String temperature;
	
	@ManyToOne
	@JoinColumn(name="LOCATIONID", nullable=false, insertable=false, updatable=false)
	private LocationPersistence location;

	public int getWeatherID() {
		return weatherID;
	}

	public void setWeatherID(int weatherID) {
		this.weatherID = weatherID;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTemperature() {
		return temperature;
	}

	public LocationPersistence getLocation() {
		return location;
	}

	public void setLocation(LocationPersistence location) {
		this.location = location;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	

}
