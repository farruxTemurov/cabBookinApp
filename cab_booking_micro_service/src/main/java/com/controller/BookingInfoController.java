package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.BookingInfo;

@Controller // view must be thymeleaf
public class BookingInfoController {
	// http://localhost:8181

	@GetMapping(value = "/")
	public String bookingLandingPage(Model mm, BookingInfo bi) {
		mm.addAttribute("booking", bi);
		return "booking-cab"; // need page with name as booking-cab
	}

	@PostMapping(value = "/bookCab")
	public String bookCab(Model mm, BookingInfo bi) {

		System.out.println("Booking Details: " + bi); // Calls toString() to print booking details
		mm.addAttribute("msg", "Booking Successful!");
		mm.addAttribute("booking", bi);
		return "bookCab"; // need page with name as booking-cab
	}
}
