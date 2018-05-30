

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
									value="${spinWithVisitorInfo.visitorName}"
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
									value="${spinWithVisitorInfo.visitorMobile}"
									name="visitorMobile" type="text" required readonly />

							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Email Id:</div>
							</div>
							<div class="col-md-3">
								<input id="visitorEmail" class="form-control"
									placeholder="Email Id" name="visitorEmail"
									style="text-align: left;"
									value="${spinWithVisitorInfo.visitorEmail}" type="text"
									required readonly />

							</div>
						</div>



						<br> <br> <br>
						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Question History</h3>
							</div>

						</div>

						<div class="w3-container">

							<ul class="w3-ul w3-card-4">
								<c:forEach items="${spinQueDetailWithQue}"
									var="spinQueDetailWithQue" varStatus="count">
									<li class="w3-bar">

										<div class="w3-bar-item" style='text-align:left; width:1000px;'>
											<span class="w3-large"><b>Question:</b>
												${spinQueDetailWithQue.question}</span><br>
												 <span
												class="w3-large"><b>Ans:</b>${spinQueDetailWithQue.actualAns}</span>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<%-- 		<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-1">Question</th>
											<th class="col-md-1">Answer</th>

											<!-- <th class="col-md-2" style="text-align: center;">Action</th> -->
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${spinQueDetailWithQue}"
											var="spinQueDetailWithQue" varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-2"><c:out
														value="${spinQueDetailWithQue.question}" /></td>
												<td class="col-md-1"><c:out
														value="${spinQueDetailWithQue.actualAns}" /></td>
												<td>
														<a
															href="${pageContext.request.contextPath}/abtEvent/${evHist.eventId}">Abt
															Event</a> &nbsp;&nbsp; <a
															href="${pageContext.request.contextPath}/getVisitorNames/${evHist.eventId}/${evHist.eventName}">Liked
															Visitor </a>
													</div></td>
											</tr>
										</c:forEach>
								</table>

							</div>
						</div>

 --%>
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
