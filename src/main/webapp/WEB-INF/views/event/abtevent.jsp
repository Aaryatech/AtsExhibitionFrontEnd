
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
						action="${pageContext.request.contextPath}/saveFeedbackQue"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>About Event</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-4">
								<div class="col1title" align="left">
									Event Name :<b> ${event.eventName}</b>
								</div>
							</div>
						<div class="col-md-4">
								<div class="col1title" align="left">
									About Event:
								
										<b> ${event.aboutEvent}</b>
									
								</div>
							</div>
								<div class="col-md-3">
								<div class="col1title" align="left">
									Contact Person :<b> ${event.contactPersonName1}</b>
								</div>
							</div>
						</div>

						<div class="colOuter"> 
							<%-- <div class="col-md-1">
								<div class="col1title" align="left">
									About Event:
									<p>
										<b> ${event.aboutEvent}</b>
									</p>
								</div>
							</div> --%>
							<!-- <div class="col-md-3"></div> -->
						<!-- </div>


						<div class="colOuter"> -->
						<%-- 	<div class="col-md-1">
								<div class="col1title" align="left">
									Contact Person :<b> ${event.contactPersonName1}</b>
								</div>
							</div> --%>
							<!-- <div class="col-md-3"></div>
						</div>

						<div class="colOuter"> -->
							<div class="col-md-4">
								<div class="col1title" align="left">
									Contact Person Mobile:
								
										<b>${event.person1Mob}</b>
									
								</div>
							</div>
						<!-- 	<div class="col-md-3"></div>
						</div>

						<div class="colOuter"> -->
							<div class="col-md-5">
								<div class="col1title" align="left">
									Contact Person Email:
								
										<b>${event.person1EmailId}</b>
									
								</div>
							</div>
							<!-- <div class="col-md-3"></div>
						</div>-->
						</div>
<%-- 						<div class="colOuter"> 
							
								<div class="col1title" align="left">
									Committee members<br>
									<table border="1" style=" width: 50%">
									<thead>
									<th>Member Name</th> 
									<th> Designation</th>
									</thead>
									<tbody>
									<c:forEach items="${memberList}" var="member">
									
									<tr>
									<td>${member.memberName}</td>
									<td>${member.designation}</td>
									</tr>
										Name <b>${member.memberName}</b>
										
										Designation <b>${member.designation}</b>
									
									</c:forEach>
									
									
									</tbody>
									</table>
									
								</div>
							
							<div class="col-md-3"></div>
						</div>
 --%>	<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-md-4">Member Name</th>
											<th class="col-md-2">Designation</th>
											
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${memberList}" var="member">

											<tr>
												<td>${member.memberName}</td>
									<td>${member.designation}</td>
											</tr>

										</c:forEach>
								</table>
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
	<script>
		(function() {
			var fauxTable = document.getElementById("faux-table");
			var mainTable = document.getElementById("table_grid");
			var clonedElement = table_grid.cloneNode(true);
			var clonedElement2 = table_grid.cloneNode(true);
			clonedElement.id = "";
			clonedElement2.id = "";
			fauxTable.appendChild(clonedElement);
			fauxTable.appendChild(clonedElement2);
		})();
	</script>

</body>
</html>
