package com.ats.exhibitionfrontend.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.exhibitionfrontend.common.Constants;
import com.ats.exhibitionfrontend.common.DateConvertor;
import com.ats.exhibitionfrontend.model.EnquiryHeaderWithName;
import com.ats.exhibitionfrontend.model.EventExhMappingWithExhName;
import com.ats.exhibitionfrontend.model.ExhEmpWithExhName;
import com.ats.exhibitionfrontend.model.ExhibitorDashboard;
import com.ats.exhibitionfrontend.model.ExportToExcel;
import com.ats.exhibitionfrontend.model.FeedbackTxn;
import com.ats.exhibitionfrontend.model.GetTrackHeader;
import com.ats.exhibitionfrontend.model.LoginResponseExh;
import com.ats.exhibitionfrontend.model.VisitorByExhiId;
import com.ats.exhibitionfrontend.model.VisitorWithOrgEventName;
import com.ats.exhibitionfrontend.model.eventhistory.GetEventVisitorName; 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 

@Controller
@Scope("session")
public class DashboardController {

	List<EnquiryHeaderWithName> pending = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> processing = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> completed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> closed = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> allEnquiryProcessing = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> allEnquiryPending = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> enquiryNotBetweenClosedList = new ArrayList<EnquiryHeaderWithName>();
	List<EnquiryHeaderWithName> enquiryNotBetweenCompletedList = new ArrayList<EnquiryHeaderWithName>();
	List<EventExhMappingWithExhName> eventList = new ArrayList<EventExhMappingWithExhName>();
	List<VisitorByExhiId> visitorList = new ArrayList<VisitorByExhiId>();
	List<EnquiryHeaderWithName> enquiryList = new ArrayList<EnquiryHeaderWithName>();
	List<FeedbackTxn> feedbackList = new ArrayList<FeedbackTxn>();

	String fromDate = new String();
	String toDate = new String();

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/exhibitorDashboard", method = RequestMethod.GET)
	public ModelAndView addEmployee(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("dashboard");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getTotalList", method = RequestMethod.POST)
	public ModelAndView getTotalList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("dashboard");

		try {
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			EventExhMappingWithExhName[] res = rest.postForObject(Constants.url + "/eventListByExhIdBetweenDate", map,
					EventExhMappingWithExhName[].class);
			eventList = new ArrayList<EventExhMappingWithExhName>(Arrays.asList(res));

			VisitorByExhiId[] visitorByExhiId = rest.postForObject(Constants.url + "/visitorListByExhibitorId", map,
					VisitorByExhiId[].class);
			visitorList = new ArrayList<VisitorByExhiId>(Arrays.asList(visitorByExhiId));

			EnquiryHeaderWithName[] enquiryHeaderWithName = rest.postForObject(
					Constants.url + "/enquiryBetweenDateByExhibitorId", map, EnquiryHeaderWithName[].class);
			enquiryList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(enquiryHeaderWithName));

			FeedbackTxn[] feedbackTxn = rest.postForObject(Constants.url + "/feedbackbetweenDate", map,
					FeedbackTxn[].class);
			feedbackList = new ArrayList<FeedbackTxn>(Arrays.asList(feedbackTxn));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("status", 4);
			EnquiryHeaderWithName[] notBetweenClosed = rest.postForObject(
					Constants.url + "/enquiryNotBetweenDateWithStatus", map, EnquiryHeaderWithName[].class);
			enquiryNotBetweenClosedList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(notBetweenClosed));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("status", 5);
			EnquiryHeaderWithName[] notBetweenCompleted = rest.postForObject(
					Constants.url + "/enquiryNotBetweenDateWithStatus", map, EnquiryHeaderWithName[].class);
			enquiryNotBetweenCompletedList = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(notBetweenCompleted));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("status", 1);
			EnquiryHeaderWithName[] allPending = rest.postForObject(Constants.url + "/equiryListWithStatus", map,
					EnquiryHeaderWithName[].class);
			allEnquiryPending = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(allPending));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("status", 2);
			EnquiryHeaderWithName[] allProcessing = rest.postForObject(Constants.url + "/equiryListWithStatus", map,
					EnquiryHeaderWithName[].class);
			allEnquiryProcessing = new ArrayList<EnquiryHeaderWithName>(Arrays.asList(allProcessing));

			pending = new ArrayList<EnquiryHeaderWithName>();
			processing = new ArrayList<EnquiryHeaderWithName>();
			completed = new ArrayList<EnquiryHeaderWithName>();
			closed = new ArrayList<EnquiryHeaderWithName>();

			for (int i = 0; i < enquiryList.size(); i++) {
				if (enquiryList.get(i).getStatus() == 1) {
					pending.add(enquiryList.get(i));
				} else if (enquiryList.get(i).getStatus() == 2) {
					processing.add(enquiryList.get(i));
				} else if (enquiryList.get(i).getStatus() == 5) {
					completed.add(enquiryList.get(i));
				} else if (enquiryList.get(i).getStatus() == 4) {
					closed.add(enquiryList.get(i));
				}
			}

			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

			model.addObject("pending", pending);
			model.addObject("processing", processing);
			model.addObject("completed", completed);
			model.addObject("closed", closed);
			model.addObject("enquiryNotBetweenClosedList", enquiryNotBetweenClosedList);
			model.addObject("enquiryNotBetweenCompletedList", enquiryNotBetweenCompletedList);
			model.addObject("allEnquiryProcessing", allEnquiryProcessing);
			model.addObject("allEnquiryPending", allEnquiryPending);

			model.addObject("eventList", eventList);
			model.addObject("visitorList", visitorList);
			model.addObject("enquiryList", enquiryList);
			model.addObject("feedbackList", feedbackList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getChart", method = RequestMethod.GET)
	@ResponseBody
	public ExhibitorDashboard getChart(HttpServletRequest request, HttpServletResponse response) {

		ExhibitorDashboard exhibitorDashboard = new ExhibitorDashboard();
		try {

			exhibitorDashboard.setTotalPending(pending.size());
			exhibitorDashboard.setTotalProcessing(processing.size());
			exhibitorDashboard.setTotalClosed(closed.size());
			exhibitorDashboard.setTotalCompleted(completed.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return exhibitorDashboard;
	}

	@RequestMapping(value = "/enquiryList/{status}", method = RequestMethod.GET)
	public ModelAndView enquiryList(@PathVariable int status, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/enquiryList");
		try {
			if (status == 1)
				model.addObject("list", pending);
			else if (status == 2)
				model.addObject("list", processing);
			else if (status == 4)
				model.addObject("list", closed);
			else if (status == 5)
				model.addObject("list", completed);
			else if (status == 6)
				model.addObject("list", enquiryNotBetweenClosedList);
			else if (status == 7)
				model.addObject("list", enquiryNotBetweenCompletedList);
			else if (status == 8)
				model.addObject("list", allEnquiryPending);
			else if (status == 9)
				model.addObject("list", allEnquiryProcessing);

			VisitorWithOrgEventName[] visNames = rest.getForObject(Constants.url + "/getAllVisitorsByIsUsed",
					VisitorWithOrgEventName[].class);

			List<VisitorWithOrgEventName> visNameList = new ArrayList<VisitorWithOrgEventName>(Arrays.asList(visNames));
			model.addObject("visNameList", visNameList);
			model.addObject("status", status);
			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/showVisitorLikesList", method = RequestMethod.GET)
	public ModelAndView showVisitorLikesList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/visitorLikes");
		try {

			model.addObject("fromDate", fromDate);
			model.addObject("toDate", toDate);

			model.addObject("visitorList", visitorList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/visitorLikesList", method = RequestMethod.GET)
	@ResponseBody
	public List<VisitorByExhiId> visitorLikesList(HttpServletRequest request, HttpServletResponse response) {

		List<VisitorByExhiId> exhibitorDashboard = new ArrayList<VisitorByExhiId>();
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			VisitorByExhiId[] visitorByExhiId = rest.postForObject(Constants.url + "/visitorListByExhibitorId", map,
					VisitorByExhiId[].class);
			exhibitorDashboard = new ArrayList<VisitorByExhiId>(Arrays.asList(visitorByExhiId));

			System.out.println(exhibitorDashboard);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return exhibitorDashboard;
	}
	
	
	@RequestMapping(value = "/trackingHistory", method = RequestMethod.GET)
	public ModelAndView trackingHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("masters/trackingHistory");
		try {

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail"); 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhId", login.getExhibitor().getExhId()); 
			ExhEmpWithExhName[] res = rest.postForObject(Constants.url + "/getAllEmployeeIsUsed", map,
					ExhEmpWithExhName[].class); 
			List<ExhEmpWithExhName> empList = new ArrayList<ExhEmpWithExhName>(Arrays.asList(res));
			model.addObject("empList", empList);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/trackingHistoryBetweenDate", method = RequestMethod.GET)
	@ResponseBody
	public List<GetTrackHeader> trackingHistoryBetweenDate(HttpServletRequest request, HttpServletResponse response) {

		List<GetTrackHeader> trackingHistoryBetweenDate = new ArrayList<GetTrackHeader>();
		try {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			int empId = Integer.parseInt(request.getParameter("empId"));

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhiId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("empId", empId);

			GetTrackHeader[] getTrackHeader = rest.postForObject(Constants.url + "/trakingHistoryBetweenDate", map,
					GetTrackHeader[].class);
			trackingHistoryBetweenDate = new ArrayList<GetTrackHeader>(Arrays.asList(getTrackHeader));

			List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

			ExportToExcel expoExcel = new ExportToExcel();
			List<String> rowData = new ArrayList<String>();

			rowData.add("Sr. No");
			rowData.add("Employee Name");
			rowData.add("Date");
			rowData.add("Total Km"); 

			
			expoExcel.setRowData(rowData);
			exportToExcelList.add(expoExcel); 
			for (int i = 0; i < trackingHistoryBetweenDate.size(); i++) {
				expoExcel = new ExportToExcel();
				rowData = new ArrayList<String>();
				    
				rowData.add("" + (i+1));
				rowData.add(trackingHistoryBetweenDate.get(i).getEmpName());
				rowData.add(trackingHistoryBetweenDate.get(i).getDate()); 
				rowData.add("" +trackingHistoryBetweenDate.get(i).getTotalKm()); 
				
				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);

			}
 
			session.setAttribute("exportExcelList", exportToExcelList);
			session.setAttribute("excelName", "EmpTrackingList");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return trackingHistoryBetweenDate;
	}
	
	
	 @RequestMapping(value = "trackingHistoryPdf/{fromDate}/{toDate}/{empId}", method = RequestMethod.GET)
		public void sortedExhibitorListPdf(@PathVariable String fromDate,@PathVariable String toDate,@PathVariable String empId,HttpServletRequest request, HttpServletResponse response)  throws FileNotFoundException 
		 {
			 
		 	List<GetTrackHeader> trackingHistoryBetweenDate = new ArrayList<GetTrackHeader>();
			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showBillwisePurchasePdf");

			HttpSession session = request.getSession();
			LoginResponseExh login = (LoginResponseExh) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("exhiId", login.getExhibitor().getExhId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			map.add("empId", empId);

			GetTrackHeader[] getTrackHeader = rest.postForObject(Constants.url + "/trakingHistoryBetweenDate", map,
					GetTrackHeader[].class);
			trackingHistoryBetweenDate = new ArrayList<GetTrackHeader>(Arrays.asList(getTrackHeader));

			// moneyOutList = prodPlanDetailList;
			Document document = new Document(PageSize.A4);
			// ByteArrayOutputStream out = new ByteArrayOutputStream();

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(4);
			try {
				System.out.println("Inside PDF Table try");
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 1.4f, 3.7f, 2.8f, 2.8f});
				Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
				Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
				headFont1.setColor(BaseColor.WHITE);
				Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.PINK);

				hcell.setPadding(3);
				hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Employee Name", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Date", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total KM", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);

				table.addCell(hcell);

				  
				int index = 0;
				for (GetTrackHeader trackHeader : trackingHistoryBetweenDate) {
					index++;
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(trackHeader.getEmpName(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase(trackHeader.getDate(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(""+trackHeader.getTotalKm(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);
 
	 

				}
				document.open();
				Paragraph name = new Paragraph("Trancking History\n", f);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph(" "));
				/*Paragraph company = new Paragraph("Bill wise Purchase Report\n", f);
				company.setAlignment(Element.ALIGN_CENTER);
				document.add(company);
				document.add(new Paragraph(" "));*/

				/*DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				String reportDate = DF.format(new Date());
				Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
				p1.setAlignment(Element.ALIGN_CENTER);
				document.add(p1);*/
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				// Atul Sir code to open a Pdf File
				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: BOm Prod  View Prod" + ex.getMessage());

				ex.printStackTrace();

			}
			 
		}
	 
	 
	 @RequestMapping(value = "/trackingHeaderWithDetail/{trackId}", method = RequestMethod.GET)
		public ModelAndView trackingHeaderWithDetail(@PathVariable int trackId, HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("masters/trackingDetail");
			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("trackId",trackId); 
				GetTrackHeader getTrackHeader = rest.postForObject(Constants.url + "/getTrackHeaderAndDetailById", map,
						GetTrackHeader.class);
				 
				model.addObject("getTrackHeader", getTrackHeader);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

			return model;
		}

}
