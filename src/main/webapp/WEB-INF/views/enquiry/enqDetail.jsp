

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>

	<!--datepicker-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#fromdatepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
		$(function() {
			$("#todatepicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
	</script>
	<!--datepicker-->

	<!--topLeft-nav-->
	<div class="sidebarOuter"></div>
	<!--topLeft-nav-->

	<!--wrapper-start-->
	<div class="wrapper">

		<!--topHeader-->
		<c:url var="findAddOnRate" value="/getAddOnRate" />
		<c:url var="findItemsByCatId" value="/getFlavourBySpfId" />
		<c:url var="findAllMenus" value="/getAllTypes" />
		<jsp:include page="/WEB-INF/views/include/logo.jsp"></jsp:include>


		<!--topHeader-->

		<!--rightContainer-->
		<div class="fullGrid center">
			<!--fullGrid-->
			<div class="wrapperIn2">

				<!--leftNav-->

				<jsp:include page="/WEB-INF/views/include/left.jsp">
					<jsp:param name="myMenu" value="${menuList}" />
				</jsp:include>



				<div class="sidebarright">

					<form name="frm_search" id="frm_search" method="post"
						action="${pageContext.request.contextPath}">
						<input type="hidden" name="tQueId" id="tQueId"
							value="search_result">


						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Visitor Info</h3>
							</div>

						</div>


						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Visitor Name*:</div>
							</div>
							<div class="col-md-3">
								<input id="visitorName" class="form-control"
									placeholder="Visitor Name"
									value="${enquiryHeaderWithName.visitorName}"
									style="text-align: left;" name="visitorName" type="text"
									required readonly />

							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Mobile Number*:</div>
							</div>
							<div class="col-md-3">
								<input id="visitorMobile" class="form-control"
									style="text-align: left;" placeholder="Mobile Number"
									value="${enquiryHeaderWithName.visitorMobile}"
									name="visitorMobile" type="text" required readonly />

							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Employee Name</div>
							</div>
							<div class="col-md-3">
								<input id="empName" class="form-control" placeholder="Emp Name"
									name="empName" style="text-align: left;"
									value="${enquiryHeaderWithName.empName}" type="text" required
									readonly />

							</div>

							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Event Name:</div>
							</div>
							<div class="col-md-3">
								<input id="eventName" class="form-control"
									placeholder="Event Name" name="eventName"
									style="text-align: left;"
									value="${enquiryHeaderWithName.eventName}" type="text" required
									readonly />

							</div>
						</div>



						<br> <br> <br>
						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Enquiry Details</h3>
							</div>

						</div>
						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Date*:</div>
							</div>
							<div class="col-md-3">
								<input id="date" class="form-control" placeholder="Date"
									value="${enquiryHeaderWithName.visitorName}"
									style="text-align: left;" name="date" type="text" required
									readonly />

							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Venue*:</div>
							</div>
							<div class="col-md-3">
								<input id="venue" class="form-control" style="text-align: left;"
									placeholder="venue"
									value="${enquiryHeaderWithName.visitorMobile}" name="venue"
									type="text" required readonly />

							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Time</div>
							</div>
							<div class="col-md-3">
								<input id="time" class="form-control" placeholder="Time"
									name="time" style="text-align: left;"
									value="${enquiryHeaderWithName.empName}" type="text" required
									readonly />

							</div>

							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Status:</div>
							</div>
							<div class="col-md-3">
								<input id="status" class="form-control" placeholder="Status"
									name="status" style="text-align: left;"
									value="${enquiryHeaderWithName.eventName}" type="text" required
									readonly />

							</div>
							<br> <br>
							<div class="colOuter">
								<div class="col-md-2">
									<div class="col1title" align="left">Remark*:</div>
								</div>
								<div class="col-md-3">
									<textarea style="width: 275px;" name="question"
									
										placeholder="Question" required="required">${spinQueHeaderList.question}</textarea>
								</div>
							</div>
						</div>


						<%-- <div class="w3-container">

								<ul class="w3-ul w3-card-4">
									<c:forEach items="${enqList}" var="enqList" varStatus="count">
										<li class="w3-bar">

											<div class="w3-bar-item"
												style='text-align: left; width: 1000px;'>
												<span class="w3-large"><b>Question:</b>
													${enqList.date}</span><br> <span class="w3-large"><b>Ans:</b>${enqList.dateTime}</span>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div> --%>

					</form>


				</div>
				<!--tabNavigation-->

			</div>
			<!--rightSidebar-->

		</div>
		<!--fullGrid-->
	</div>
	<!--rightContainer-->

	</div>
	<!--wrapper-end-->
	<!--easyTabs-->
	<!--easyTabs-->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<!--easyTabs-->




</body>
</html>
