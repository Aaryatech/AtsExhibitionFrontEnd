



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
								<h3>Add Feedback Question</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-1">
								<div class="col1title" align="left">Question:<b style="color: red">*</b></div>
							</div>
							<div class="col-md-3">
								<textarea rows="5" cols="40" name="question"
									placeholder="Question" required oninvalid="this.setCustomValidity('Enter Feedback Question Here')"
    oninput="this.setCustomValidity('')">${fbQue.question}</textarea>
							</div>
														<div class="col-md-1"></div>
							
						<!-- </div>
						<div class="colOuter"> -->
							<div class="col-md-1">
								<div class="col1title" align="left">Question Description:<b style="color: red">*</b>
								</div>
							</div>
							<div class="col-md-1">
								<textarea style="width: 520px;" cols="40" rows="5" name="que_desc"
									placeholder=" Question Description" required oninvalid="this.setCustomValidity('Enter Feedback Question Description Here')"
    oninput="this.setCustomValidity('')">${fbQue.queDesc}</textarea>

							</div>
							</div>
							<br>

							<div class="colOuter">
								<div class="col-md-1">
									<div class="col1title">Out of Rating:<b style="color: red">*</b>
									</div>
								</div>
								<div class="col-md-3">

									<select name="outOfRating" style="width: 150px;" required oninvalid="this.setCustomValidity('Select  Question Rating  Here')"
    oninput="this.setCustomValidity('')">
										<c:choose>
											<c:when test="${fbQue.outOfRating==1}">
												<option selected value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
											</c:when>

											<c:when test="${fbQue.outOfRating==2}">
												<option value="1">1</option>
												<option selected value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>

											</c:when>

											<c:when test="${fbQue.outOfRating==3}">
												<option value="1">1</option>
												<option value="2">2</option>
												<option selected value="3">3</option>
												<option value="4">4</option>

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
												<option  value="4">4</option>
												<option selected value="5">5</option>
											</c:when>

											<c:otherwise>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option  value="5">5</option>
											</c:otherwise>
											
										</c:choose>
									</select>
								</div>
								
<div class="col-md-2"></div>

								<input type="hidden" name="fbId" value="${fbQue.fbId}" />
								<div class="col-md-1">
								<div class="col1title" align="right">
								<input name="submit" class="buttonsaveorder btn btn-md" value="Submit"
									type="submit" >
									</div>
							
							</div>
							<div id="table-scroll" class="table-scroll">
								<div id="faux-table" class="faux-table" aria="hidden" ></div>
								<div class="table-wrap">
									<table id="table_grid" class="main-table" border="1px;">
										<thead>
											<tr class="bgpink">
												<th class="col-sm-1">Sr No</th>
												<th class="col-md-3">Question</th>
												<th class="col-md-3">Description</th>
												<th class="col-md-2" align="center">Rating Out Of</th>
												<th class="col-md-1">Action</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${fbQueList}" var="fbQueList"
												varStatus="count">
												<tr>
													<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
													<td class="col-md-3"><c:out
															value="${fbQueList.question}" /></td>
													<td class="col-md-3"><c:out
															value="${fbQueList.queDesc}" /></td>
													<td class="col-md-2" align="center"><c:out
															value="${fbQueList.outOfRating}" /></td>
													<td class="col-md-1"><div>
															<a
																href="${pageContext.request.contextPath}/editFbQue/${fbQueList.fbId}" title="Edit Question" class="btn btn-basic"><i style="color: green;" class='fa fa-edit'></i></a> <a
																href="${pageContext.request.contextPath}/deleteFbQue/${fbQueList.fbId}" title="Delete Question" class="btn btn-basic"
																onClick="return confirm('Are you sure want to delete this record');">
																<i class='fa fa-trash' style="color: red; "></i>
															</a>
														</div></td>
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
