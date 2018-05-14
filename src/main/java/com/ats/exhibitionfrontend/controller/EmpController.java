package com.ats.exhibitionfrontend.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpController {
	
	
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/addEmployee");
		try
		{ 
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertEmployee", method = RequestMethod.POST)
	public String insertEmployee(HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("masters/addEmployee");
		try
		{ 
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return "redirect:/addEmployee";
	}

}
