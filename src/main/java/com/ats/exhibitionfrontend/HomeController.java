package com.ats.exhibitionfrontend;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.Exhibitor;
import com.ats.exhibitionfrontend.model.ExhibitorWithOrgName;
import com.ats.exhibitionfrontend.model.GenerateOtp;
import com.ats.exhibitionfrontend.model.LoginResponse;
import com.ats.exhibitionfrontend.model.LoginResponseExh;

import com.ats.exhibitionfrontend.model.eventhistory.EventsWithSubStatus;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	RestTemplate rest = new RestTemplate();

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

		return "index";
	}

	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		ModelAndView mav = new ModelAndView("index");

		res.setContentType("text/html");
		try {
			System.out.println("Login Process " + name);
			System.out.println("password " + password);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("index");
			} else {

				RestTemplate rest = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("userMob", name);
				map.add("password", password);
				LoginResponseExh loginResponse = rest.postForObject(Constants.url + "/loginExhibitor", map,
						LoginResponseExh.class);
try {
				loginResponse.getExhSubHeader()
						.setFromDate(DateConvertor.convertToDMY(loginResponse.getExhSubHeader().getFromDate()));

				loginResponse.getExhSubHeader()
						.setToDate(DateConvertor.convertToDMY(loginResponse.getExhSubHeader().getToDate()));
}catch (Exception e) {
	// TODO: handle exception
}
				System.out.println("loginResponse" + loginResponse);

				if (loginResponse.isError() == false) {
					mav = new ModelAndView("home");
					HttpSession session = request.getSession();
					session.setAttribute("UserDetail", loginResponse);
					session.setAttribute("LOGOURL", Constants.LOGO_URL);
					if (loginResponse.getIsSubscribed() == 1) {

						System.err.println("Exhibitor is Subscribed USer" + loginResponse.getIsSubscribed());
					}

					else {

						System.err.println("Exhibitor is Not  Subscribed");
					}

					// HttpSession session = request.getSession();
					LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");
					System.out.println("exhiId " + login.getExhibitor().getExhId());
					mav.addObject("index", login.getExhibitor());

					map = new LinkedMultiValueMap<String, Object>();

					map.add("exhId", login.getExhibitor().getExhId());
					try {
						EventsWithSubStatus[] eventListResp = rest.postForObject(
								Constants.url + "getAllEventsWithExhId", map, EventsWithSubStatus[].class);

						List<EventsWithSubStatus> eventList;

						eventList = new ArrayList<EventsWithSubStatus>(Arrays.asList(eventListResp));
						DateFormat df = new SimpleDateFormat("dd-mm-yyyy");

						/*
						 * for(EventsWithSubStatus events:eventList) {
						 * 
						 * events.setEventFromDate(df.format(events.getEventFromDate()));
						 * 
						 * events.setEventToDate(df.format(events.getEventToDate()));
						 * 
						 * }
						 */

						System.err.println("Event List at home " + eventList.toString());
						mav.addObject("eventList", eventList);

						mav.addObject("eventImgUrl", Constants.EVENT_IMG_URL);

					} catch (Exception e) {

						System.err.println("getAllEventsWithExhId/ HomeController Exception " + e.getMessage());
						e.printStackTrace();

					}

				} else {

					mav = new ModelAndView("index");
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

			EventsWithSubStatus[] eventListResp = rest.postForObject(Constants.url + "getAllEventsWithExhId", map,
					EventsWithSubStatus[].class);

			List<EventsWithSubStatus> eventList;

			eventList = new ArrayList<EventsWithSubStatus>(Arrays.asList(eventListResp));

			System.err.println("Dashboard Data event Info " + eventList.toString());

			mav.addObject("eventList", eventList);
			mav.addObject("eventImgUrl", Constants.EVENT_IMG_URL);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}
	// getEventDetail

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/sessionTimeOut", method = RequestMethod.GET)
	public ModelAndView displayLoginAgain(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("index");

		logger.info("/sessionTimeOut request mapping.");

		model.addObject("loginResponseMessage", "Session timeout ! Please login again . . .");

		return model;

	}

	@RequestMapping(value = "/createNewPassword", method = RequestMethod.GET)
	public ModelAndView createNewPassword(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("otpGeneration");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/generateOtp", method = RequestMethod.GET)
	@ResponseBody
	public GenerateOtp generateOtp(HttpServletRequest request, HttpServletResponse res) throws IOException {
		GenerateOtp generateOtp = new GenerateOtp();
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);

		String userMobile = request.getParameter("userMobile");
		System.out.println("userMobile:" + userMobile);
		System.out.println("otp:" + n);

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userMob", userMobile);
			map.add("userOtp", n);

			LoginResponse loginReponse = rest.postForObject(Constants.url + "/login", map, LoginResponse.class);
			System.out.println("loginResponse" + loginReponse);

			System.out.println("UserMobile" + loginReponse.getExhibitor().getUserMob());
			generateOtp.setOtp(n);
			generateOtp.setExhId(loginReponse.getExhibitor().getExhId());

		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
			e.printStackTrace();
		}

		return generateOtp;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String userPassword = request.getParameter("userPassword");
		String exhId = request.getParameter("exhId");

		res.setContentType("text/html");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", exhId);

			RestTemplate rest = new RestTemplate();
			Exhibitor exhibitorRes = new Exhibitor();
			Exhibitor exhibitor = new Exhibitor();
			ExhibitorWithOrgName exhibitorList = new ExhibitorWithOrgName();

			exhibitorList = rest.postForObject(Constants.url + "/getExhibitorByExhId", map, ExhibitorWithOrgName.class);

			exhibitorRes.setPassword(userPassword);
			exhibitorRes.setExhId(Integer.parseInt(exhId));

			exhibitorRes.setAboutCompany(exhibitorList.getAboutCompany());
			exhibitorRes.setAddress(exhibitorList.getAddress());
			exhibitorRes.setCompanyType(exhibitorList.getCompanyType());
			exhibitorRes.setCompanyTypeId(exhibitorList.getCompanyTypeId());

			exhibitorRes.setCompLat(exhibitorList.getCompLat());
			exhibitorRes.setCompLong(exhibitorList.getCompLong());
			exhibitorRes.setContactPersonName1(exhibitorList.getContactPersonName1());
			exhibitorRes.setContactPersonName2(exhibitorList.getContactPersonName2());
			exhibitorRes.setExhCompany(exhibitorList.getExhCompany());
			exhibitorRes.setExhName(exhibitorList.getExhName());
			exhibitorRes.setIsUsed(exhibitorList.getIsUsed());
			exhibitorRes.setLocationId(exhibitorList.getLocationId());
			exhibitorRes.setLogo(exhibitorList.getLogo());
			exhibitorRes.setOrgId(exhibitorList.getOrgId());
			exhibitorRes.setPersonEmail1(exhibitorList.getPersonEmail1());
			exhibitorRes.setPersonEmail2(exhibitorList.getPersonEmail2());
			exhibitorRes.setPersonMob1(exhibitorList.getPersonMob1());
			exhibitorRes.setPersonMob2(exhibitorList.getPersonMob2());
			exhibitorRes.setContactPersonName1(exhibitorList.getContactPersonName1());
			exhibitorRes.setContactPersonName2(exhibitorList.getContactPersonName2());
			exhibitorRes.setUserMob(exhibitorList.getUserMob());

			System.out.println("exhibitorRes" + exhibitorRes);
			exhibitor = rest.postForObject(Constants.url + "/saveExhibitor", exhibitorRes, Exhibitor.class);

			System.out.println("userPwd" + exhibitor.toString());

		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
			e.printStackTrace();

		}
		return "index";

	}

}
