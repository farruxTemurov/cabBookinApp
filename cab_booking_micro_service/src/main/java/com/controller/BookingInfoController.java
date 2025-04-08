package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.BookingInfo;
import com.repository.BookingInfoRepository;
import com.service.BookingInfoService;

@Controller
public class BookingInfoController {
    @Autowired
    private BookingInfoService bookingInfoService;

    @Autowired
    private BookingInfoRepository bookingInfoRepository;

    @GetMapping(value = "/")
    public String bookingLandingPage(Model mm, BookingInfo bi) {
        mm.addAttribute("booking", new BookingInfo()); // Pass an empty BookingInfo object to reset the form
        return "bookCab";
    }

    @PostMapping("/bookCab")
    public String bookCab(Model model, @ModelAttribute BookingInfo bi) {
        System.out.println("Booking Details: " + bi);

        // Call fare microservice to get price
        float fare = bookingInfoService.getFare(bi.getSource(), bi.getDestination());

        if (fare == -1) {
            model.addAttribute("msg", "Booking failed: Fare not available!");
            model.addAttribute("booking", new BookingInfo()); // Reset the form on failure
            return "bookCab";
        }

        // Set the fare and save booking
        bi.setPrice(fare);
        bookingInfoRepository.save(bi);

        model.addAttribute("msg", "Booking Successful! Fare: $" + fare);
        model.addAttribute("booking", new BookingInfo()); // Reset the form after success
        return "bookCab";
    }
}
