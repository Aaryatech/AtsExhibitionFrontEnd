package com.ats.exhibitionfrontend;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EventListener;
import java.util.List;
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
import com.ats.exhibitionfrontend.model.eventhistory.Events;
import com.ats.exhibitionfrontend.model.eventhistory.EventsWithSubStatus;
import com.ats.exhibitionfrontend.model.feedback.EventExhMapping;

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

		model.addAttribute("serverTime", formattedDate);

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
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("userMob", name);
				map.add("password", password);
				LoginResponseExh loginResponse = rest.postForObject(Constants.url + "/loginExhibitor", map,
						LoginResponseExh.class);
				System.out.println("loginResponse" + loginResponse);

				if (loginResponse.isError() == false) {
					mav = new ModelAndView("home");
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", loginResponse);
					
					if(loginResponse.getIsSubscribed()==1) {
						
						System.err.println("Exhibitor is Subscribed USer"+loginResponse.getIsSubscribed());
					}
					
					else {
						
						System.err.println("Exhibitor is Not  Subscribed");
					}
					
					
					// HttpSession session = request.getSession();
					LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
					System.out.println("exhiId " + login.getExhibitor().getExhId());
					mav.addObject("login", login.getExhibitor());

					map = new LinkedMultiValueMap<String, Object>();

					map.add("exhId", login.getExhibitor().getExhId());
					try {
						EventsWithSubStatus[] eventListResp = rest.postForObject(
								Constants.url + "getAllEventsWithExhId", map, EventsWithSubStatus[].class);

						List<EventsWithSubStatus> eventList;

						eventList = new ArrayList<EventsWithSubStatus>(Arrays.asList(eventListResp));
					DateFormat df=new SimpleDateFormat("dd-mm-yyyy");
						
				/*	for(EventsWithSubStatus events:eventList) {
						
						events.setEventFromDate(df.format(events.getEventFromDate()));
						
						events.setEventToDate(df.format(events.getEventToDate()));
						
					}*/

					
					System.err.println("Event List at home " +eventList.toString());
						mav.addObject("eventList", eventList);
						
						mav.addObject("eventImgUrl", Constants.EVENT_IMG_URL);
						
					} catch (Exception e) {
						
						System.err.println("getAllEventsWithExhId/ HomeController Exception " + e.getMessage());
						e.printStackTrace();

					}

				} else {

					mav = new ModelAndView("login");
					System.out.println("Invalid login credentials");

				}

				
			}
		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
			e.printStackTrace();

		}

		return mav;

	}
	
	
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse res) throws IOException {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	
		ModelAndView mav = new ModelAndView("home");

		map = new LinkedMultiValueMap<String, Object>();
		HttpSession session = request.getSession();

		LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
		map.add("exhId", login.getExhibitor().getExhId());
		try {
			
			RestTemplate rest = new RestTemplate();

			EventsWithSubStatus[] eventListResp = rest.postForObject(
					Constants.url + "getAllEventsWithExhId", map, EventsWithSubStatus[].class);

			List<EventsWithSubStatus> eventList;

			eventList = new ArrayList<EventsWithSubStatus>(Arrays.asList(eventListResp));
			
			mav.addObject("eventList",eventList);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}
//	getEventDetail
	
	

}
