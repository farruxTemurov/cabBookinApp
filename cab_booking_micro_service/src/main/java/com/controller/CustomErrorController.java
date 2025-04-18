package com.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(Model model) {
		model.addAttribute("message", "An error occurred! This page doesn't exist or you don't have access.");
		return "errorPage"; // This corresponds to the errorPage.html template
	}
}
