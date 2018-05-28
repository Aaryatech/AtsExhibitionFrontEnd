package com.ats.exhibitionfrontend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.ErrorMessage;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.SpinQueMasterWithName;
import com.ats.exhibitionfrontend.model.SpinQuestionMaster;

@Controller
public class SpinQueController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/addSpinQuestion", method = RequestMethod.GET)
	public ModelAndView addSpinQuestion(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("spinQue/addSpinQuestion");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());

			SpinQueMasterWithName[] res = rest.postForObject(Constants.url + "/getAllSpinQueByExhId", map,
					SpinQueMasterWithName[].class);

			List<SpinQueMasterWithName> queList = new ArrayList<SpinQueMasterWithName>(Arrays.asList(res));
			model.addObject("queList", queList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/saveSpinQuestion", method = RequestMethod.POST)
	public String saveSpinQuestion(HttpServletRequest request, HttpServletResponse response) {

		try {
			String queId = request.getParameter("queId");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhId = login.getExhibitor().getExhId();

			String que = request.getParameter("question");
			String queDesc = request.getParameter("queDesc");
			String date = request.getParameter("date");

			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.err.println("Questioj  " + que + "Description  " + queDesc);

			SpinQuestionMaster spinQuestionHeader = new SpinQuestionMaster();
			Date ymdFromDate = null;

			ymdFromDate = originalFormat.parse(date);

			spinQuestionHeader.setExhId(exhId);
			spinQuestionHeader.setQuestion(que);
			spinQuestionHeader.setQueDesc(queDesc);
			spinQuestionHeader.setDate(targetFormat.format(ymdFromDate));
			spinQuestionHeader.setIsActive(1);
			spinQuestionHeader.setIsUsed(1);

			if (queId != null || queId != "") {
				try {

					spinQuestionHeader.setQueId(Integer.parseInt(queId));

				} catch (Exception e) {

					System.err.println("Catch QueId not used ");
				}
			}

			SpinQuestionMaster spinQuestionHeaderres = rest.postForObject(Constants.url + "saveSpinQueMaster",
					spinQuestionHeader, SpinQuestionMaster.class);

			System.err.println(" Response  " + spinQuestionHeaderres.toString());

		} catch (Exception e) {

			System.err.println("Exception Message " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/addSpinQuestion";
	}

	@RequestMapping(value = "/editSpinQue/{queId}", method = RequestMethod.GET)
	public ModelAndView editSpinQue(HttpServletRequest request, HttpServletResponse response, @PathVariable int queId) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("spinQue/addSpinQuestion");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("queId", queId);

			SpinQueMasterWithName spinQueHeaderWithName = rest.postForObject(Constants.url + "/getAllSpinQueByQueId",
					map, SpinQueMasterWithName.class);

			spinQueHeaderWithName.setDate(DateConvertor.convertToDMY(spinQueHeaderWithName.getDate()));

			model.addObject("spinQueHeaderList", spinQueHeaderWithName);

		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteSpinQue/{queId}", method = RequestMethod.GET)
	public String deleteSpinQue(HttpServletRequest request, HttpServletResponse response, @PathVariable int queId) {

		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("queId", queId);

			ErrorMessage delete = rest.postForObject(Constants.url + "/deleteSpinQueMaster", map, ErrorMessage.class);

			System.out.println("delete" + delete);
		} catch (Exception e) {

			System.err.println(" Questions " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/addSpinQuestion";
	}

}