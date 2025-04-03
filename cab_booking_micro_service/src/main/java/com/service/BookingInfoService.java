package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingInfoService {
	@Autowired
	RestTemplate restTemplate;

	public float getFare(String source, String destination) {
		try {
			String url = "http://FARE-SERVICE/fare/get?source=" + source + "&destination=" + destination;
			return restTemplate.getForObject(url, Float.class); // Calls the fare service API
		} catch (Exception e) {
			System.err.println("Error while fetching fare: " + e.getMessage());
			return -1; // Return -1 if there's an error
		}
	}
}
