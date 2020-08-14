package com.project.weather.travel.persistence;

import java.util.ArrayList;
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


@Entity
@Table(name = "LOCATION")
public class LocationPersistence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCATIONID")
	private int locationId;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "LONGITUDE")
	private double longitude;

	@Column(name = "LATITUDE")
	private double latitude;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="location", cascade=CascadeType.ALL)
	private List<WeatherPersistence> weatherData = new ArrayList<WeatherPersistence>();
	
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
	
	public List<WeatherPersistence> getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(List<WeatherPersistence> weatherData) {
		this.weatherData = weatherData;
	}


}
