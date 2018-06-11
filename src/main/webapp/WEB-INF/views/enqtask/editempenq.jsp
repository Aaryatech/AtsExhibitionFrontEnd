
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

		<script>

		$(function() {
			
			var b=${enqSize};
			//alert(b);
			for(var a=0;a<b;a++){
			$("#meetDate"+a).datepicker({
				dateFormat : 'dd-mm-yy'
			});
			}});
	
		
		
		
		
		
		/* $(function() {
			$("#meetDatePicker").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
		
		 */
		
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
						action="${pageContext.request.contextPath}/getEnqAndTaskByEmpForEdit"
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
								<select name="emp" class="form-control-choosen"
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
							</div>
						</div>
					</form>
				</div>

				<div class="wrap">


					
						<div>
							<strong><p
										style="text-align: center; color: red; font-size: 25px;"> Enquiry List</p></strong>
						</div>
						<div class="fleft">
						<c:set var="status" value="null"></c:set>
							<hr style="border: 1px ;" />
						<c:forEach items="${enqList}" var="enquiry" varStatus="count">
	<p style="text-align: left; background: #dbc5d1;">
										<b>&nbsp;&nbsp;Date:</b> ${enquiry.nextMeetDate}&nbsp;&nbsp;
									</p>

							<c:choose>
								<c:when test="${enquiry.status==1}">
									<c:set var="status" value="Pending"></c:set>
								</c:when>
								<c:when test="${enquiry.status==2}">
									<c:set var="status" value="Processing"></c:set>
								</c:when>

								<c:when test="${enquiry.status==3}">
									<c:set var="status" value="Working"></c:set>
								</c:when>

								<c:when test="${enquiry.status==4}">
									<c:set var="status" value="Closed"></c:set>
								</c:when>

								<c:otherwise>
									<c:set var="status" value="Completed"></c:set>
								</c:otherwise>

							</c:choose>
						
							<div class="row">
							
								<div class="col-md-4" align="right">
									<p>
										<b>Employee:</b> <select name="emp" id="empId${enquiry.enqId}"
											class="form-control-choosen"  style="width: 120px;">
											<c:forEach items="${empList}" var="emp">
												<c:choose>
													<c:when test="${enquiry.empId==emp.empId}">
														<option selected value="${enquiry.empId}">${enquiry.empName}</option>

													</c:when>
													<c:otherwise>
														<option value="${emp.empId}">${emp.empName}</option>

													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
								</div>
								<div class="col-md-3" ><b>Status:</b>
										 <select name="enqStatus"
										id="enqStatus${enquiry.enqId}" class="form-control-choosen"
										style="width: 120px;">
										<c:choose>

											<c:when test="${enquiry.status==1}">
												<option selected value="1">Pending</option>
												<option value="2">Processing</option>
												<option value="3">Working</option>
												<option value="4">Closed</option>
												<option value="5">Completed</option>
											</c:when>


											<c:when test="${enquiry.status==2}">
												<option value="1">Pending</option>
												<option selected value="2">Processing</option>
												<option value="3">Working</option>
												<option value="4">Closed</option>
												<option value="5">Completed</option>
											</c:when>

											<c:when test="${enquiry.status==3}">
												<option value="1">Pending</option>
												<option value="2">Processing</option>
												<option selected value="3">Working</option>
												<option value="4">Closed</option>
												<option value="5">Completed</option>
											</c:when>


											<c:when test="${enquiry.status==4}">
												<option value="1">Pending</option>
												<option value="2">Processing</option>
												<option value="3">Working</option>
												<option selected value="4">Closed</option>
												<option value="5">Completed</option>
											</c:when>

											<c:otherwise>
												<option value="1">Pending</option>
												<option value="2">Processing</option>
												<option value="3">Working</option>
												<option value="4">Closed</option>
												<option selected value="5">Completed</option>
											</c:otherwise>
										</c:choose>

									</select>
								</div>
								<c:set var="index" value="${count.index}"></c:set>
								<div class="col-md-2">

									<b>Event:</b> ${enquiry.eventName}
									
								</div>
								
								<div class="col-md-3">
									<b> Meeting Date:</b> <input class="datepicker"
										placeholder="Meeting Date" id='meetDate${index}'
										value="${enquiry.nextMeetDate}" style="width: 120px;" 
										name="nextMeetDate" type="text" required /> 
										</div>
							</div>

							<div class="row" >
								<div class="col-md-4" align="right">
									
										<b>Visitor:</b> ${enquiry.visitorName}
								</div>
								<div class="col-md-3"><b>Mob No:</b> ${enquiry.visitorMobile}</div>
							
							<div class="col-md-2">
									<b>Email Id:</b> ${enquiry.visitorEmail}</div>
									
								<!-- </div>
								<div class="row"> -->
								<%-- <div class="col-md-6">
									<b> Meeting Date:</b> <input class="datepicker"
										placeholder="Meeting Date" id='meetDate${index}'
										value="${enquiry.nextMeetDate}" style="width: 250px;"
										name="nextMeetDate" type="text" required /> 
										</div> --%>
										
								
							<div class="col-md-3" align="right">
							<input type="button" value="Edit" style="width: 70px; height:30px;" class="buttonsaveorder btn-xs"
								onclick="editEnquiryAction(${enquiry.enqId},${index})">
								</div>
								</div>
							<hr style="border: 1px ;" />
						</c:forEach>
						</div>
					</div>

				<div>
							<strong><p
										style="text-align: center; color: red; font-size: 25px;"> Task List</p></strong>
						</div>
					<div class="fright">
					
								
								<c:set var="status" value="null"></c:set>
								<c:forEach items="${taskList}" var="tasks">
									<!-- 								<hr style="border-color: #dbc5d1;"/>
 -->
									<p style="text-align: left; background: #dbc5d1;">
										<b>&nbsp;&nbsp;Date:</b> ${tasks.date}&nbsp;&nbsp;
									</p>

										<c:choose>
											<c:when test="${tasks.status==1}">
												<c:set var="status" value="Pending"></c:set>
											</c:when>

											<c:otherwise>

												<c:set var="status" value="Completed"></c:set>
											</c:otherwise>

										</c:choose>

										<div class="row" align="right">
											<div class="col-md-3">

												<b>Employee:</b> ${tasks.empName}
											</div>

											<div class="col-md-3" >
												<b>Task Desc:</b> ${tasks.taskDesc}
											</div>
.


										<%-- 	<div class="col-md-3" >
												<b>Date:</b> ${tasks.date}
											</div> --%>

											<div class="col-md-2"  style="color: red;">
												<b>Status </b> ${status}
											</div>

										</div>
										
										<c:choose>
											<c:when test="${! loop.last}">
												<hr />
											</c:when>
											
										</c:choose>

									</c:forEach>

							</div>
				</div>



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

	<script type="text/javascript">
	
	function editEnquiryAction(enqId,indexee) {
		//alert("khoijldjfl;k");
		 var form = document.getElementById("frm_search")
		 //alert("form "+form);
		 
		 //empId
		 var empId = document.getElementById("empId"+enqId).value;
		 
		 var enqStatus = document.getElementById("enqStatus"+enqId).value;

		 var meetDate= document.getElementById("meetDate"+indexee).value;
		 alert("Index " +indexee);
		 alert("Meet Date  " +meetDate);
		 
		// alert("Employee Id " +empId);
		 
		    //form.action ="/editEmpEnquiry";
		    window.open('${pageContext.request.contextPath}/editEmpEnquiry/'+empId+'/'+enqId+'/'+meetDate+'/'+enqStatus);
		    //form.submit();
		
	}
	
	</script>

</body>
</html>
