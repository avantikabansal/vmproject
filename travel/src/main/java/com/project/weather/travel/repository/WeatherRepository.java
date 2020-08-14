package com.project.weather.travel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.weather.travel.persistence.WeatherPersistence;

public interface WeatherRepository extends CrudRepository<WeatherPersistence, Integer> {

	List<WeatherPersistence> findAllByOrderByWeatherIDAsc();

	List<WeatherPersistence> findByDate(Date date);
}
