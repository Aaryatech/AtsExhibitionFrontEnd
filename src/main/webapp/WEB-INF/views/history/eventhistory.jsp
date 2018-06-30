

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>

	<style>
.btn-circle {
	border-radius: 100%;
	width: 50px;
	height: 50px;
	padding: 11px;
}

.btn {
	margin-right: 5px;
}

.btn-info {
	color: #fff;
	background-color: #2962ff;
	border-color: #2962ff;
}

.btn1 {
	display: inline-block;
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	border: 1px solid transparent;
	line-height: 1.5;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	color: #2962ff;
	font-size: 20px;
	text-decoration: none;
	background-color: transparent;
	-webkit-text-decoration-skip: objects;
}

* {
	outline: 0;
}

*, ::after, ::before {
	box-sizing: border-box;
}
</style>


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


						<div class="row" align="left">
							<div class="col-md-10">
								<h3>Event History</h3>
							</div>
							<c:set var="count" value="${fn:length(eventHistory) }"></c:set>
							<div class="col-md-2" style="padding-top: 0px;" align="right">
								<button class="btn-circle btn1 btn-info" data-toggle="tooltip"
									title="Count" disabled>${count}</button>

							</div>
						</div>
						<br> <br>
						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-1">Event Name</th>
											<th class="col-md-1">Venue</th>
											<th class="col-md-1">From Date</th>
											<th class="col-md-1">To Date</th>
											<th class="col-md-1">Likes</th>
											<th class="col-md-1">Action</th>
										</tr>

									</thead>
									<tbody>

										<c:forEach items="${eventHistory}" var="evHist"
											varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-2"><c:out value="${evHist.eventName}" /></td>
												<td class="col-md-1"><c:out
														value="${evHist.eventLocation}" /></td>
												<td class="col-md-1"><c:out
														value="${evHist.eventFromDate}" /></td>
												<td class="col-md-1"><c:out
														value="${evHist.eventToDate}" /></td>
												<td class="col-md-1"><c:out value="${evHist.likeCount}" /></td>
												<td class="col-md-1" align="right"><div>
														<!-abtEvent/evendId first then gED/1/0-  -->
														<a
															href="${pageContext.request.contextPath}/getEventDetail/${evHist.eventId}/0"><abbr
															title="About Event"><i class="fa fa-info"></i></abbr></a>
														&nbsp;&nbsp; <a
															href="${pageContext.request.contextPath}/getVisitorNames/${evHist.eventId}/${evHist.eventName}"><abbr
															title="Liked Visitors"><i
																class="fa fa-thumbs-o-up"></i></abbr> </a>
													</div></td>
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
