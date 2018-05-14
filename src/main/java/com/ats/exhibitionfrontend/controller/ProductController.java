package com.ats.exhibitionfrontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("product/addProduct");
		try
		{ 
		 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}

}
