
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.fleft {
	float: left;
	width: 100%;
	/* background: #ef70b4; */
	border: 1px solid #dbc5d1;
	height: auto;
}

.fright {
	float: right;
	/* 	background: orange;
 */
	height: auto;
	border: 1px solid #dbc5d1;
	width: 100%;
}
</style>
</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

	<!-- <link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
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
						action="${pageContext.request.contextPath}/getEnqAndTaskByEmp"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Enquiry And Task Between Date</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-1">
								<div class="col1title" align="left">From Date*:</div>
							</div>
							<div class="col-md-2">
								<input id="fromdatepicker" class="form-control"
									placeholder="From Date" value="${fromDate}"
									style="text-align: left;" name="fromDate" type="text" required>

							</div>

							<div class="col-sm-1"></div>

							<div class="col-md-1">
								<div class="col1title" align="left">To Date*:</div>
							</div>
							<div class="col-md-2">
								<input id="todatepicker" class="form-control"
									style="text-align: left;" placeholder="To Date"
									value="${toDate}" name="toDate" type="text" required>

							</div>

							<div class="col-md-1">
								<div class="col1title" align="left">Employee</div>
							</div>
							<div class="col-md-1">
								<select name="emp" class="form-control choosen"
									style="width: 250px;">
									<option value="-1">ALL</option>
									<c:forEach items="${empList}" var="emp">
										<c:choose>
											<c:when test="${emp.empId== empId}">
												<option selected value="${emp.empId}">${emp.empName}</option>

											</c:when>
											<c:otherwise>
												<option value="${emp.empId}">${emp.empName}</option>

											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

						</div>

						<div class="colOuter">
							<div align="center">
								<input name="submit" class="buttonsaveorder" value="Search"
									type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
							</div>
						</div>

						<div class="wrap">
							<div>
								<p style="text-align: left; color: red; font-size: 25px;">Enquiry
									List</p>
							</div>

							<div class="fleft">

								<c:forEach items="${enqDatewiseList}" var="enqBean">
									<!-- 								<hr style="border-color: #dbc5d1;"/>
 -->


									<p style="text-align: left; background: #dbc5d1;">
										<b>&nbsp;&nbsp;Meeting Date:</b> ${enqBean.date}&nbsp;&nbsp;
									</p>


									<c:forEach items="${enqBean.empEnq}" var="enq" varStatus="loop">
										<c:set var="status" value="null"></c:set>

										<c:choose>
											<c:when test="${enq.status==1}">
												<c:set var="status" value="Pending"></c:set>
											</c:when>
											<c:when test="${enq.status==2}">
												<c:set var="status" value="Processing"></c:set>
											</c:when>

											<c:when test="${enq.status==3}">
												<c:set var="status" value="Working"></c:set>
											</c:when>

											<c:when test="${enq.status==4}">
												<c:set var="status" value="Closed"></c:set>
											</c:when>

											<c:otherwise>
												<c:set var="status" value="Completed"></c:set>

											</c:otherwise>

										</c:choose>

										<div class="row">
											<div class="col-md-3" align="left">

												<b>&nbsp;&nbsp;Employee:</b> ${enq.empName}
											</div>

											<div class="col-md-3" align="left">
												<b>&nbsp;&nbsp;Event:</b> ${enq.eventName}
											</div>


											<div class="col-md-3" align="left">
												<b>&nbsp;&nbsp;Meeting Time:</b> ${enq.approximateTime}
											</div>

											<div class="col-md-3" align="left" style="color: red;">
												<b>&nbsp;&nbsp;Status </b> ${status}
											</div>

										</div>
										<div class="row">
											<div class="col-md-3" align="left">
												<b>&nbsp;&nbsp;Visitor:</b> ${enq.visitorName}
											</div>
											<div class="col-md-3" align="left">
												<b>Mob No:</b> ${enq.visitorMobile}
											</div>
											<div class="col-md-3" align="left">
												<b>&nbsp;&nbsp;Email Id:</b> ${enq.visitorEmail}
											</div>
										</div>
										<c:choose>
											<c:when test="${! loop.last}">
												<hr />
											</c:when>

										</c:choose>
									</c:forEach>
								</c:forEach>
							</div>

							<div>

								<p style="text-align: left; color: red; font-size: 25px;">Task
									List</p>
							</div>
							<div class="fright">

								<c:forEach items="${taskDatewiseList}" var="taskBean">
									<!-- 								<hr style="border-color: #dbc5d1;"/>
 -->
									<p style="text-align: left; background: #dbc5d1;">
										<b>&nbsp;&nbsp;Date:</b> ${taskBean.date}&nbsp;&nbsp;
									</p>
									<c:forEach items="${taskBean.empTask}" var="tasks"
										varStatus="loop">
										<c:set var="status" value="null"></c:set>

										<c:choose>
											<c:when test="${tasks.status==1}">
												<c:set var="status" value="Pending"></c:set>
											</c:when>

											<c:otherwise>

												<c:set var="status" value="Completed"></c:set>
											</c:otherwise>

										</c:choose>

										<div class="row">
											<div class="col-md-3" align="left">

												<b>&nbsp;&nbsp;Employee:</b> ${tasks.empName}
											</div>

											<div class="col-md-3" align="left">
												<b>&nbsp;&nbsp;Task Desc:</b> ${tasks.taskDesc}
											</div>

											<div class="col-md-6" align="left" style="color: red;">
												<b>&nbsp;&nbsp;Status </b> ${status}
											</div>

										</div>

										<c:choose>
											<c:when test="${! loop.last}">
												<hr />
											</c:when>

										</c:choose>


									</c:forEach>
								</c:forEach>

							</div>
						</div>

					</form>
				</div>

				<!--tabNavigation-->
				<!--<div class="order-btn"><a href="#" class="saveOrder">SAVE ORDER</a></div>-->



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



	<!--  <script src="http://code.jquery.com/jquery-1.12.1.min.js"></script>
 -->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.rowspanizer.min.js"></script>
	<script>
		$(window).load(function() {
			$("#example").rowspanizer({
				vertical_align : 'middle'
			});
			$("#example1").rowspanizer({
				vertical_align : 'middle'
			});

		});
	</script>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
		_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>


</body>
</html>
