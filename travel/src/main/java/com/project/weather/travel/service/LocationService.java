package com.project.weather.travel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.weather.travel.model.Location;
import com.project.weather.travel.model.Weather;
import com.project.weather.travel.persistence.LocationPersistence;
import com.project.weather.travel.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	Logger log = LoggerFactory.getLogger(LocationService.class);

	/**
	 * Checks if location exists for the given Id. If yes, then fills Location model
	 * object with corresponding data from DB and returns the object
	 * 
	 * @param locationId - Id to fetch a particular location
	 * @return - Location model object filled with data fetched from DB
	 */
	public Location fetchLocation(int locationId) {
		if (locationRepository.findById(locationId).isPresent()) {
			LocationPersistence locObj = locationRepository.findById(locationId).get();
			Location locModelObj = new Location();
			locModelObj.setCity(locObj.getCity());
			locModelObj.setState(locObj.getState());
			locModelObj.setLatitude(locObj.getLatitude());
			locModelObj.setLongitude(locObj.getLongitude());
			locModelObj.setLocationId(locObj.getLocationId());
			return locModelObj;
		} else {
			return null;
		}

	}

	/**
	 * For the given JSON value of city and state, this function checks if Location
	 * already exists. If yes, then it returns the ID of the existing location else
	 * it creates a new Location row in DB and then returns the new auto-generated
	 * Id
	 * 
	 * @param weather - Weather model object from JSON
	 * @return - Id of the location
	 */
	public int fetchLocationID(Weather weather) {
		log.debug("fetching location ID");
		int locationID;
		LocationPersistence locationPO = locationRepository.findByCityAndState(weather.getLocation().getCity(),
				weather.getLocation().getState());
		if (locationPO != null) {
			locationID = locationPO.getLocationId();
			log.debug("location already exits");
		} else {
			log.debug("new location - inserting in DB");
			LocationPersistence locationPONew = new LocationPersistence();
			locationPONew.setCity(weather.getLocation().getCity());
			locationPONew.setState(weather.getLocation().getState());
			locationPONew.setLatitude(weather.getLocation().getLatitude());
			locationPONew.setLongitude(weather.getLocation().getLongitude());
			LocationPersistence result = locationRepository.save(locationPONew);
			locationID = result.getLocationId();
		}
		return locationID;
	}
}
