package com.project.weather.travel.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.weather.travel.model.Location;
import com.project.weather.travel.model.Weather;
import com.project.weather.travel.persistence.WeatherPersistence;
import com.project.weather.travel.service.LocationService;

@Component
public class WeatherUtility {

	@Autowired
	private ConvertorService convertorService;

	@Autowired
	private LocationService locationService;
	
	
	/**
	 * Iterates over a list of Persistence objects and fills up the model object. 
	 * Also, fetches location object and sets it in weather model object 
	 * @param weatherList -  List of WeatherPersistence objects
	 * @param weatherSize - Size of weatherList
	 * @return - List of Weather model objects
	 */
	public List<Weather> fetchWeatherModelList(List<WeatherPersistence> weatherList, int weatherSize) {
		List<Weather> weatherModelList = new ArrayList<Weather>();
		for (int i = 0; i < weatherSize; i++) {
			WeatherPersistence weatherObj = weatherList.get(i);
			Location fetchedLocObj = locationService.fetchLocation(weatherObj.getLocationId());
			Weather weatherModelObj = new Weather();
			weatherModelObj.setDate(weatherObj.getDate());
			weatherModelObj.setId(weatherObj.getWeatherID());
			weatherModelObj.setLocation(fetchedLocObj);
			// convert string to array
			weatherModelObj.setTemperature(convertorService.convertStringToFloatArray(weatherObj.getTemperature()));
			weatherModelList.add(weatherModelObj);
		}
		return weatherModelList;
	}

	/**
	 * Maps the data of weather persistent object to weather model object
	 * @param weatherObj - Weather Persistence object
	 * @return - Weather Model object
	 */
	public Weather fetchWeatherModel(WeatherPersistence weatherObj) {
		Weather weatherModelObj = new Weather();
		Location fetchedLocObj = locationService.fetchLocation(weatherObj.getLocationId());
		weatherModelObj.setDate(weatherObj.getDate());
		weatherModelObj.setId(weatherObj.getWeatherID());
		weatherModelObj.setLocation(fetchedLocObj);
		// convert string to array
		weatherModelObj.setTemperature(convertorService.convertStringToFloatArray(weatherObj.getTemperature()));
		return weatherModelObj;
	}

}
