package com.project.weather.travel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.weather.travel.exception.DataAlreadyExistsException;
import com.project.weather.travel.exception.NoDataFoundException;
import com.project.weather.travel.model.Weather;
//import com.project.weather.travel.persistence.LocationPersistence;
import com.project.weather.travel.service.WeatherService;

@RestController
@RequestMapping("/v1/travel")
public class WeatherController {

	// Setter injection
	@Autowired
	private WeatherService weatherService;
	
	Logger log = LoggerFactory.getLogger(WeatherController.class);

	// Constructor injection
	/*
	 * @Autowired public WeatherController(WeatherService weatherService) {
	 * this.weatherService = weatherService; }
	 */

	/**
	 * 
	 * @param weather - JSON data mapped to weather model
	 * @param ucBuilder -  to build the URI for the weather data using ID
	 * @return ResponseEntity with URI and HTTP Status code
	 * 201 Created status - if weather record is inserted in table
	 * 400 Bad Request - if weather record with given ID already exists in table
	 */
	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public ResponseEntity<Weather> createWeather(@RequestBody Weather weather, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/travel/weather/{weatherId}").buildAndExpand(weather.getId()).toUri());
		try {
			log.debug("in controller");
			weatherService.createWeather(weather);
			return new ResponseEntity<Weather>(headers, HttpStatus.CREATED);
		} catch (DataAlreadyExistsException e) {
			log.debug("Data Already Exists Exception message: {}", e.getMessage());
			return new ResponseEntity<Weather>(headers, HttpStatus.BAD_REQUEST);
		}
	}
	

	/**
	 * 
	 * @return - List of weather data model or Throws Custom Exception 'NoDataFoundException' exception if 
	 * no weather records exists in the table
	 */
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public List<Weather> getAllWeatherData() {
		List<Weather> weatherList = weatherService.getAllWeatherData();
		if (weatherList != null) {
			return weatherList;
		} else {
			throw new NoDataFoundException("No weather records exists in the table");
		}
	}



	/**
	 * 
	 * @param date - Input to find weather data for a specific date
	 * @return - List of weather data model for the input date or Throws Custom Exception 'NoDataFoundException' exception if 
	 * no weather records exists for the given Date
	 * @throws Exception - Date Parse Exception
	 */
	@RequestMapping(value = "/weather", params = "date", method = RequestMethod.GET)
	public List<Weather> getWeatherByDate(@RequestParam String date) throws ParseException {
		List<Weather> weatherList = weatherService.getWeatherByDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		if (weatherList != null) {
			return weatherList;
		} else {
			throw new NoDataFoundException("No weather records exists for the given Date");
		}
	}

	/**
	 * 
	 * @param id - Input to find weather data for a specific ID
	 * @return - A single Weather Model object for the given ID or Throws Custom Exception 'NoDataFoundException' exception if 
	 * no weather records exists for the given ID
	 */
	@RequestMapping(value = "/weather/{id}", method = RequestMethod.GET)
	public Weather getWeatherById(@PathVariable int id) {
		Weather weatherObj = weatherService.getWeatherById(id);
		if (weatherObj != null) {
			return weatherObj;
		} else {
			throw new NoDataFoundException("No weather records exists for the given ID");
		}
	}

	/**
	 * Deletes all weather record from the table
	 */
	@RequestMapping(value = "/erase", method = RequestMethod.DELETE)
	public void deleteAllWeather() {
		weatherService.deleteAllWeather();
	}
	
	
	/* @return - A list of all Location Model
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public List<LocationPersistence> getAllLocationData() {
		List<LocationPersistence> locationList = weatherService.getAllLocationData();
		return locationList;
	}*/
}
