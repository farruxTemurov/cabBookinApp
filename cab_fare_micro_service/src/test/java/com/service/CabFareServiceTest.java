package com.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.CabFare;

class CabFareServiceTest {
	@Autowired
	CabFareService cabFareService;

	@Test
	void testStoreCabFareInfo() {
		CabFare cf = new CabFare();
		cf.setSource("A");
		cf.setDestination("B");
		cf.setFare(10);
		String result = cabFareService.storeCabFareInfo(cf);
		assertEquals("Car Fare Information stored", result);
	}

	@Test
	@Disabled
	void testFindFare() {
		fail("Not yet implemented");
	}

}
