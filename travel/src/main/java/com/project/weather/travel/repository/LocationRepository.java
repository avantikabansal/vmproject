package com.project.weather.travel.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.weather.travel.persistence.LocationPersistence;

public interface LocationRepository extends CrudRepository<LocationPersistence, Integer> {

	LocationPersistence findByCityAndState(String city, String state);

}
