package com.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bean.BookingInfo;
import com.repository.BookingInfoRepository;
import com.service.BookingInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

class BookingInfoControllerTest {

	private BookingInfoController controller;
	private BookingInfoService bookingInfoService;
	private BookingInfoRepository bookingInfoRepository;
	private Model model;

	@BeforeEach
	void setUp() {
		bookingInfoService = mock(BookingInfoService.class);
		bookingInfoRepository = mock(BookingInfoRepository.class);
		model = mock(Model.class);

		controller = new BookingInfoController();
		ReflectionTestUtils.setField(controller, "bookingInfoService", bookingInfoService);
		ReflectionTestUtils.setField(controller, "bookingInfoRepository", bookingInfoRepository);
	}

	@Test
	void testBookingLandingPage() {
		BookingInfo booking = new BookingInfo();
		String viewName = controller.bookingLandingPage(model, booking);

		assertEquals("bookCab", viewName);
		verify(model).addAttribute(eq("booking"), any(BookingInfo.class));
	}

	@Test
	void testBookCabSuccess() {
		BookingInfo booking = new BookingInfo();
		booking.setSource("A");
		booking.setDestination("B");

		when(bookingInfoService.getFare("A", "B")).thenReturn(150f);

		String viewName = controller.bookCab(model, booking);

		assertEquals("bookCab", viewName);
		verify(bookingInfoRepository).save(booking);
		verify(model).addAttribute("msg", "Booking Successful! Fare: $150.0");
		verify(model).addAttribute(eq("booking"), any(BookingInfo.class));
	}

	@Test
	void testBookCabFailure() {
		BookingInfo booking = new BookingInfo();
		booking.setSource("X");
		booking.setDestination("Y");

		when(bookingInfoService.getFare("X", "Y")).thenReturn(-1f);

		String viewName = controller.bookCab(model, booking);

		assertEquals("bookCab", viewName);
		verify(model).addAttribute("msg", "Booking failed: Fare not available!");
		verify(model).addAttribute(eq("booking"), any(BookingInfo.class));
		verify(bookingInfoRepository, never()).save(any());
	}
}
