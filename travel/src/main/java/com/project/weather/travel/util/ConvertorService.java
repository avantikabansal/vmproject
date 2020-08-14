package com.project.weather.travel.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.weather.travel.service.WeatherService;

@Component
public class ConvertorService {
	Logger log = LoggerFactory.getLogger(WeatherService.class);

	/**
	 * Converting an array of float to comma separated string
	 * @param tempArray - Float Array of temperature which has 
	 * values rounded up to one decimal place
	 * @return - Comma separated string of temperature values
	 */
	public String convertFloatArrToStr(float[] tempArray) {
		StringBuffer sbr = new StringBuffer();
		for (float t : tempArray) {
			sbr.append(t + ",");
		}
		String tempString = sbr.substring(0, (sbr.length() - 1)).toString();
		return tempString;
	}

	/**
	 * Converting a comma separated string to float array
	 * @param tempString - Comma separated String of temperatures
	 * @return - Float array of temperature values
	 */
	public float[] convertStringToFloatArray(String tempString) {
		String[] strArray = tempString.split(",");
		float[] tempArray = new float[24];
		for (int j = 0; j < strArray.length; j++) {
			//log.debug("Value of temperature from string array {}", strArray[j]);
			tempArray[j] = Float.parseFloat(strArray[j]);
			//log.debug("Value of temperature from string array {}", tempArray[j]);
		}
		return tempArray;
	}

	/**
	 * 
	 * @param date date coming from JSON input
	 * @return - String format of date as yyyy-MM-dd
	 */
	public String convertDateToString(Date date) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(date);
	}
	
	/**
	 * Rounds up float value to 'place' number of decimal places
	 * @param value - float input from JSON
	 * @param place - number of places up to which the float should
	 * be rounded up
	 * @return - double output rounded up to 'place' number of
	 *  decimal places (half_up)
	 */
	public double round(double value, int place) {
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(place, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public double stripTrailingZeros(double value) {
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    return bd.stripTrailingZeros().doubleValue();
	}
	
	/**
	 * Converting float array of temperature values to another float
	 * array whose values are rounded up to one decimal place
	 * @param tempArray - Float array of temperature as given in 
	 * input by the user
	 * @return Float array of temperature values rounded up to
	 * one decimal place
	 */
	public float[] roundArrayToOneDecimal(float[] tempArray) {
		float[] roundedArray = new float[24];
		for (int j = 0; j < tempArray.length; j++) {
			roundedArray[j] = (float) round(tempArray[j], 1);
		}
		return roundedArray;
	}
}
