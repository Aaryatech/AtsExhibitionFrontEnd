

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

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
						action="${pageContext.request.contextPath}"
						enctype="multipart/form-data">
						<input type="hidden" name="mod_ser" id="mod_ser"
							value="search_result">


						<div class="col-md -3">

							<div class="col1title" align="left">
								<c:choose>
									<c:when test="${status==1}">
										<h3>Pending Enquiry List</h3>
									</c:when>
									<c:when test="${status==2}">
										<h3>Processing Enquiry List</h3>
									</c:when>
									<c:when test="${status==4}">
										<h3>Closed Enquiry List</h3>
									</c:when>
									<c:when test="${status==5}">
										<h3>Completed Enquiry List</h3>
									</c:when>


								</c:choose>

							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-2" align="left"></div>


							<div class="col-md-2">
								<b>From Date </b>: ${fromDate}
							</div>

							<div class="col-md-2">
								<b>To Date </b> : ${toDate}
							</div>

							<div class="col-md-2">
								Employee Name :
								<c:forEach items="${empList}" var="empList">
									<c:choose>
										<c:when test="${empList.empId == empId}">

											<c:out value="${empList.empName}" />
										</c:when>
									</c:choose>
								</c:forEach>
							</div>


						</div>
						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-1">Visitor Name</th>
											<th class="col-md-1">Event Name</th>
											<th class="col-md-1">No of Days</th>

											<th class="col-md-1">Mobile No</th>
											<th class="col-md-1">Email</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${list}" var="list" varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-2"><c:out value="${list.visitorName}" /></td>
												<td class="col-md-1"><c:out value="${list.eventName}" /></td>
												<td class="col-md-1"><c:out value="${list.noOfEnqDays}" /></td>

												<c:forEach items="${visNameList}" var="visNameList">

													<c:choose>
														<c:when test="${visNameList.visitorId==list.visitorId}">
															<td class="col-md-2"><c:out
																	value="${visNameList.visitorMobile}" /></td>
															<td class="col-md-2"><c:out
																	value="${visNameList.visitorEmail}" /></td>
														</c:when>
													</c:choose>
												</c:forEach>

											</tr>
										</c:forEach>
								</table>

							</div>
						</div>


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
