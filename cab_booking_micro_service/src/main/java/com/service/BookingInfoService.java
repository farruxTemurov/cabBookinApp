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
			String url = "http://FARE-SERVICE/cabfare/findFare/" + source + "/" + destination;
			System.out.println("Calling fare URL: " + url);
			Float fare = restTemplate.getForObject(url, Float.class);
			System.out.println("Received fare: " + fare);
			return fare != null ? fare : -1;
		} catch (Exception e) {
			System.err.println("Error while fetching fare: " + e.getMessage());
			return -1;
		}
	}
}
