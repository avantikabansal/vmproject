package com.project.weather.travel.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.weather.travel.exception.DataAlreadyExistsException;
import com.project.weather.travel.model.Weather;
//import com.project.weather.travel.persistence.LocationPersistence;
import com.project.weather.travel.persistence.WeatherPersistence;
//import com.project.weather.travel.repository.LocationRepository;
import com.project.weather.travel.repository.WeatherRepository;
import com.project.weather.travel.util.ConvertorService;
import com.project.weather.travel.util.WeatherUtility;

@Service
public class WeatherService {

	//@Autowired
	//private LocationRepository locationRepository;

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private ConvertorService convertorService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private WeatherUtility weatherUtility;

	Logger log = LoggerFactory.getLogger(WeatherService.class);

	/**
	 * 
	 * @param weather - weather model object containing data for weather and location
	 * @return - a weather model with location details (auto generated location Id if the location is not 
	 * already present in DB or existing location Id if location is already present in DB)
	 */
	public Weather createWeather(Weather weather) {
		log.debug("creating weather");
		int weatherID = weather.getId();
		if (!weatherRepository.findById(weatherID).isPresent()) {
			log.debug("weather id does not exist");
			
			weather.getLocation().setLatitude
			(convertorService.round(weather.getLocation().getLatitude(), 4));
			weather.getLocation().setLongitude
			(convertorService.round(weather.getLocation().getLongitude(), 4));
			
			float[] roundedArray = convertorService.
					roundArrayToOneDecimal(weather.getTemperature());
			weather.setTemperature(roundedArray);
			
			int locationID = locationService.fetchLocationID(weather);
			weather.getLocation().setLocationId(locationID);
			
			// setting data in persistence layer
			WeatherPersistence weatherPONew = new WeatherPersistence();
			weatherPONew.setDate(weather.getDate());
			weatherPONew.setLocationId(locationID);
			weatherPONew.setWeatherID(weatherID);
			weatherPONew.setTemperature(convertorService.convertFloatArrToStr(roundedArray));
			weatherRepository.save(weatherPONew);
			
			return weather;
		} else {
			log.debug("weather id exists");
			throw new DataAlreadyExistsException("Weather Data with the given ID already exists");
		}
	}

	/**
	 * 
	 * @return - List of all the weather data model or returns null if no weather record
	 * exists in table
	 */
	public List<Weather> getAllWeatherData() {
		List<WeatherPersistence> weatherList = (List<WeatherPersistence>) weatherRepository
				.findAllByOrderByWeatherIDAsc();
		int weatherSize = weatherList.size();
		if (weatherSize > 0) {
			return weatherUtility.fetchWeatherModelList(weatherList, weatherSize);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param date - Input to find weather data for a specific date
	 * @return - List of weather data model for the input date or returns null if no weather record
	 * exists for the given date
	 */
	public List<Weather> getWeatherByDate(Date date) {
		List<WeatherPersistence> weatherList = weatherRepository.findByDate(date);
		int listSize = weatherList.size();
		if (listSize > 0) {
			return weatherUtility.fetchWeatherModelList(weatherList, listSize);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param id - Input to find weather data for a specific ID
	 * @return - A single Weather Model object for the given ID or returns null if no weather record
	 * exists for the given ID
	 */
	public Weather getWeatherById(int id) {
		if (weatherRepository.findById(id).isPresent()) {
			Weather weather = weatherUtility.fetchWeatherModel(weatherRepository.findById(id).get());
			return weather;
		} else {
			return null;
		}
	}

	/**
	 * Deletes all weather record from the table
	 */
	public void deleteAllWeather() {
		weatherRepository.deleteAll();
	}

	/*
	public List<LocationPersistence> getAllLocationData() {
		List<LocationPersistence> locationList = (List<LocationPersistence>) locationRepository.findAll();
		return locationList;
	}*/

}
