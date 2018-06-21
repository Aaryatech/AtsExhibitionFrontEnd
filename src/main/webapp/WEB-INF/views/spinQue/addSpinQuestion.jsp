<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>
 
<!-- <link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
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
						action="${pageContext.request.contextPath}/saveSpinQuestion"
						enctype="multipart/form-data">

						<div class="col-md-3">

							<div class="col1title" align="left">
								<h3>Add Spin Question</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-1">
								<div class="col1title" align="left">
									Question<b style="color: red;">*</b>:
								</div>
							</div>
							<div class="col-md-3">
								<textarea cols="40" rows="5" name="question" maxlength="100"
									placeholder="Question" required oninvalid="this.setCustomValidity('Enter Spin Question Here')"
    oninput="this.setCustomValidity('')">${spinQueHeaderList.question}</textarea>
							</div>
								<div class="col-md-1"></div>
							<!-- </div>
						<div class="colOuter"> -->
							<div class="col-md-1">
								<div class="col1title" align="left">
									Description<b style="color: red;">*</b>:
								</div>
							</div>
							<div class="col-md-1">
								<textarea cols="40" rows="5" name="queDesc" maxlength="100"
									style="width: 520px;" placeholder="Description"
									required oninvalid="this.setCustomValidity('Enter Spin Question Description Here')"
    oninput="this.setCustomValidity('')">${spinQueHeaderList.queDesc}</textarea>

							</div>
						</div>


						<div class="colOuter">
							<div class="col-md-1">
								<div class="col1title" align="left">
									Date<b style="color: red;">*</b>:
								</div>
							</div>
							<div class="col-md-3">
								<input type="text" name="date" value="${spinQueHeaderList.date}"
									placeholder="Date" id="fromdatepicker" class="form-control"
									required oninvalid="this.setCustomValidity('Select Feedback Question Date Here')"
    oninput="this.setCustomValidity('')" />
							</div>

							<!-- </div> -->
<div class="col-md-2"></div>
							<input type="hidden" name="queId"
								value="${spinQueHeaderList.queId}" /> 
								<div class="col-md-4">
								
								<input name="submit"
								class="buttonsaveorder btn btn-success" value="Submit" type="submit">
								
								<input name="submit"
								class="buttonsaveorder btn btn-primary" value="Cancel" type="button" onclick="clearText()">
								
								</div>
								
							<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
							<!-- 	</div>
						</div>
 -->
						</div>
						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table" border="1">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-1">Exh Name</th>
											<th class="col-md-2">Question</th>

											<th class="col-md-2">Description</th>
											<th class="col-md-1">Date</th>
											<th class="col-md-1">Action</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${queList}" var="queList" varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>

												<td class="col-md-1"><c:out value="${queList.exhName}" /></td>
												<td class="col-md-2"><c:out value="${queList.question}" /></td>

												<td class="col-md-2"><c:out value="${queList.queDesc}" /></td>
												<td class="col-md-1" align="right"><c:out
														value="${queList.date}" /></td>
												<td class="col-md-0.5"><div>
														<a
															href="${pageContext.request.contextPath}/editSpinQue/${queList.queId}" title="Edit Spin Question" class="btn btn-light">
															<i class='fa fa-edit' style="color:maroon"></i> </a> <a
															href="${pageContext.request.contextPath}/deleteSpinQue/${queList.queId}" title="Delete Spin Question" class="btn btn-dark"
															onClick="return confirm('Are you sure want to delete this record');">
															<i class='fa fa-trash' style=" color: red; "></i>
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
	
	<script>
	function clearText() {
	
		window.open('${pageContext.request.contextPath}/addSpinQuestion','_self');
		//window.close();

	}
	</script>
	

</body>
</html>
