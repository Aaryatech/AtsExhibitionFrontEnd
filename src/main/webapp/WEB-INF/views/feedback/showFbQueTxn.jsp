



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
				<br>


				<div class="sidebarright">
					<form name="frm_search" id="frm_search" method="post"
						action="${pageContext.request.contextPath}/getFbTxnByEvent">


						<div>
						<b>Select EVENT</b>
							<select name="sel_event" style="width: 220px;">
								<c:forEach items="${eventList}" var="events">
									<option value="-1">ALL</option>
									<c:choose>
										<c:when test="${eventId==events.eventId}">
											<option selected value="${events.eventId}">${events.eventName}</option>

										</c:when>
										<c:otherwise>
											<option value="${events.eventId}">${events.eventName}</option>
										</c:otherwise>

									</c:choose>
								</c:forEach>
							</select> <input type="submit" value="submit" class="btn-btn-primary">
						</div>

						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Feedback Question Transaction</h3>
							</div>

						</div>

						<%-- 						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Question*:</div>
							</div>
							<div class="col-md-3">
								<textarea style="size: 100px;" name="question"
									placeholder="Question" required="required">${fbQue.question}</textarea>
							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Question Description*:
								</div>
							</div>
							<div class="col-md-3">
								<textarea style="size: 100px;" name="que_desc"
									placeholder="Description" required="required">${fbQue.queDesc}</textarea>

							</div>


							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Out of Rating*:</div>
							</div>
							<div class="col-md-3">
								<section>
									<select name="outOfRating">
										<c:choose>
											<c:when test="${fbQue.outOfRating==1}">
												<option selected value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</c:when>

											<c:when test="${fbQue.outOfRating==2}">
												<option value="1">1</option>
												<option selected value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</c:when>

											<c:when test="${fbQue.outOfRating==3}">
												<option value="1">1</option>
												<option value="2">2</option>
												<option selected value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</c:when>

											<c:when test="${fbQue.outOfRating==4}">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option selected value="4">4</option>
												<option value="5">5</option>
											</c:when>


											<c:when test="${fbQue.outOfRating==5}">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option selected value="5">5</option>
											</c:when>

											<c:otherwise>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</c:otherwise>
										</c:choose>
									</select>
								</section>
							</div>
						</div>
						
						<input type="hidden" name="fbId" value="${fbQue.fbId}" />

						<div class="colOuter">
							<div align="center">
								<input name="submit" class="buttonsaveorder" value="Submit"
									type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
							</div>
						</div> --%>

						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-4">Question</th>
											<th class="col-md-2">Rating Out Of</th>
											<th class="col-md-2">Rating</th>
											<th class="col-md-2">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${fbTxnList}" var="fbTxnList"
											varStatus="count">

											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-4"><c:out
														value="${fbTxnList.question}" /></td>
												<td class="col-md-2" align="right"><c:out
														value="${fbTxnList.outOfRating}" /></td>
												<td class="col-md-2"><c:out value="${fbTxnList.rating}" /></td>
												<td class="col-md-2" align="right"><a
													href="${pageContext.request.contextPath}/showFbTxnDetail/${fbTxnList.fbId}"
													class="btn-btn-primary">VIEW DETAILS</a></td>
											</tr>

										</c:forEach>
								</table>
							</div>
						</div>
					</form>
				</div>
				<!--tabNavigation-->
				<!--<div class="order-btn"><a href="#" class="saveOrder">SAVE ORDER</a></div>-->
				<%-- <div class="order-btn textcenter">
						<a
							href="${pageContext.request.contextPath}/showBillDetailProcess/${billNo}"
							class="buttonsaveorder">VIEW DETAILS</a>
						<!--<input name="" class="buttonsaveorder" value="EXPORT TO EXCEL" type="button">-->
					</div> --%>


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
