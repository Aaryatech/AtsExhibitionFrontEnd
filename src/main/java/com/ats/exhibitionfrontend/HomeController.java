package com.ats.exhibitionfrontend;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
 

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		ModelAndView mav = new ModelAndView("login");

		res.setContentType("text/html"); 
		try {
			System.out.println("Login Process " + name);
			System.out.println("password " + password);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} else {

				  
				 
				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,Object>();
				map.add("userMob", name);
				map.add("password", password);
				LoginResponseExh loginResponse = rest.postForObject(Constants.url + "/loginExhibitor",map,
						LoginResponseExh.class);
				System.out.println("loginResponse" + loginResponse);
				 
				 if (loginResponse.isError()==false) 
				{ 
					mav = new ModelAndView("home");
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", loginResponse); 
					//HttpSession session = request.getSession(); 
					LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
					System.out.println("exhiId " + login.getExhibitor().getExhId());
					mav.addObject("login", login.getExhibitor());
					 
				} else {

					
					mav = new ModelAndView("login"); 
					System.out.println("Invalid login credentials");

				} 
				
				 /*if (name.equals("tester") && password.equals("1234")) 
				{ 
					mav = new ModelAndView("home");
					 
					 
				} else {

					
					mav = new ModelAndView("login"); 
					System.out.println("Invalid login credentials");

				} */

			}
		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
		}

		return mav;

	}
	
}
