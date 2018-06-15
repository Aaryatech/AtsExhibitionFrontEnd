package com.ats.exhibitionfrontend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.feedback.EventExhMapping;
import com.ats.exhibitionfrontend.model.feedback.FeedbackQue;
import com.ats.exhibitionfrontend.model.feedback.GetFbQueTxn;
import com.ats.exhibitionfrontend.model.feedback.GetFeedbackTxnDetails;

@Controller
public class FeedbackController {

	// showAddFbQuestionshowAddFbQuestion

	@RequestMapping(value = "/showAddFbQuestion", method = RequestMethod.GET)
	public ModelAndView showAddFbQuestion(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("feedback/addfbquestion");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("exhbId", exhbId);

			FeedbackQue[] fbQueRes = rest.postForObject(Constants.url + "/getFeedbackQueByExbId", map,
					FeedbackQue[].class);
			List<FeedbackQue> fbQueList = new ArrayList<FeedbackQue>(Arrays.asList(fbQueRes));
			model.addObject("fbQueList", fbQueList);

			// model.addObject("fbQue", fbQueResponse);

		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());

			e.printStackTrace();
		}

		return model;
	}

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/saveFeedbackQue", method = RequestMethod.POST)
	public String saveFeedbackQue(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			String que = request.getParameter("question");
			String queDesc = request.getParameter("que_desc");
			String outOfRating = request.getParameter("outOfRating");

			System.err.println("Questioj  " + que + "Description  " + queDesc);

			FeedbackQue fbQue = new FeedbackQue();

			fbQue.setExhbId(exhbId);
			fbQue.setQuestion(que);
			fbQue.setQueDesc(queDesc);
			fbQue.setOutOfRating(Integer.parseInt(outOfRating));
			fbQue.setIsActive(1);
			fbQue.setIsUsed(1);

			String fbId = request.getParameter("fbId");

			if (fbId != null || fbId != "") {
				try {

					fbQue.setFbId(Integer.parseInt(fbId));

				} catch (Exception e) {

					System.err.println("Catch fbId not used ");
				}
			}

			FeedbackQue fbQueResponse = rest.postForObject(Constants.url + "saveFeedbackQue", fbQue, FeedbackQue.class);

			System.err.println("Insert feedback Question Response  " + fbQueResponse.toString());

		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showAddFbQuestion";
	}

	FeedbackQue fbQueResponse;

	@RequestMapping(value = "/editFbQue/{fbId}", method = RequestMethod.GET)
	public ModelAndView editFbQue(HttpServletRequest request, HttpServletResponse response, @PathVariable int fbId) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("feedback/addfbquestion");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fbId", fbId);

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();

			map1.add("exhbId", exhbId);

			fbQueResponse = rest.postForObject(Constants.url + "/getFbQueByFbId", map, FeedbackQue.class);

			model.addObject("fbQue", fbQueResponse);

			FeedbackQue[] fbQueRes = rest.postForObject(Constants.url + "/getFeedbackQueByExbId", map1,
					FeedbackQue[].class);
			List<FeedbackQue> fbQueList = new ArrayList<FeedbackQue>(Arrays.asList(fbQueRes));
			model.addObject("fbQueList", fbQueList);
		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteFbQue/{fbId}", method = RequestMethod.GET)
	public String deleteFbQue(HttpServletRequest request, HttpServletResponse response, @PathVariable int fbId) {

		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fbId", fbId);

			fbQueResponse = rest.postForObject(Constants.url + "/getFbQueByFbId", map, FeedbackQue.class);

			fbQueResponse.setIsUsed(0);
			FeedbackQue deleteFbQueResponse = rest.postForObject(Constants.url + "saveFeedbackQue", fbQueResponse,
					FeedbackQue.class);

			System.err.println("Delete feedback Question Response  " + deleteFbQueResponse.toString());

		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showAddFbQuestion";
	}

	// getFbQueTxnByExhbId

	@RequestMapping(value = "/showFbQueTxn", method = RequestMethod.GET)
	public ModelAndView showFbQueTxn(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("");
		try {
			model = new ModelAndView("feedback/showFbQueTxn");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("exhbId", exhbId);

			// EventExhMapping

			EventExhMapping[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
					EventExhMapping[].class);

			List<EventExhMapping> eventList = new ArrayList<EventExhMapping>(Arrays.asList(eventListResp));

			model.addObject("eventList", eventList);

		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// getFbTxnByEvent

	@RequestMapping(value = "/getFbTxnByEvent", method = RequestMethod.POST)
	public ModelAndView getFbTxnByEvent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("");
		try {
			model = new ModelAndView("feedback/showFbQueTxn");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			int eventId = Integer.parseInt(request.getParameter("sel_event"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			// map.add("eventId", eventId);
			List<GetFbQueTxn> fbTxnList;
			if (eventId == -1) {
				map.add("exhbId", exhbId);
				GetFbQueTxn[] fbQueTxn = rest.postForObject(Constants.url + "getFbQueTxnByExhbId", map,
						GetFbQueTxn[].class);

				fbTxnList = new ArrayList<GetFbQueTxn>(Arrays.asList(fbQueTxn));

			}

			else {
				System.err.println("inside else if event Id not eq -1");

				map.add("eventId", eventId);
				GetFbQueTxn[] fbQueTxn = rest.postForObject(Constants.url + "getFbQueTxnByEventId", map,
						GetFbQueTxn[].class);

				fbTxnList = new ArrayList<GetFbQueTxn>(Arrays.asList(fbQueTxn));

			}
			System.err.println("output  " + fbTxnList.toString());
			model.addObject("fbTxnList", fbTxnList);

			map = new LinkedMultiValueMap<String, Object>();

			map.add("exhbId", exhbId);

			// EventExhMapping

			EventExhMapping[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
					EventExhMapping[].class);

			List<EventExhMapping> eventList = new ArrayList<EventExhMapping>(Arrays.asList(eventListResp));

			model.addObject("eventList", eventList);

			model.addObject("eventId", eventId);

		} catch (Exception e) {

			System.err.println("Exception in show Add Feddback Questions " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/showFbTxnDetail/{fbId}", method = RequestMethod.GET)
	public ModelAndView showFbTxnDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int fbId) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("feedback/fbTxnDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("fbId", fbId);

			GetFeedbackTxnDetails[] fbTxnList = rest.postForObject(Constants.url + "/getFeedbackTxnDetails", map,
					GetFeedbackTxnDetails[].class);
			List<GetFeedbackTxnDetails> fbTxnQueDetail = new ArrayList<GetFeedbackTxnDetails>(Arrays.asList(fbTxnList));

			System.err.println("Output fbTxn Details " + fbTxnQueDetail.toString());

			model.addObject("fbTxnDetail", fbTxnQueDetail);

			// model.addObject("fbQue", fbQueResponse);

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			int exhbId = login.getExhibitor().getExhId();

			map = new LinkedMultiValueMap<String, Object>();

			map.add("exhbId", exhbId);

			// EventExhMapping

			EventExhMapping[] eventListResp = rest.postForObject(Constants.url + "getEventsByExhbId", map,
					EventExhMapping[].class);

			List<EventExhMapping> eventList = new ArrayList<EventExhMapping>(Arrays.asList(eventListResp));

			model.addObject("eventList", eventList);

			model.addObject("question", fbTxnQueDetail.get(0).getQuestion());
			model.addObject("queDesc", fbTxnQueDetail.get(0).getQueDesc());

		} catch (Exception e) {

			System.err.println("Exception in show showFbTxnDetail " + e.getMessage());

			e.printStackTrace();
		}

		return model;
	}

}
