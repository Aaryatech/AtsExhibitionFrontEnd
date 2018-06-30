

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>
	--%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="isVisitorExist" value="/isVisitorExist"></c:url>
	<!-- 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
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
						action="${pageContext.request.contextPath}/insertEnquiry"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">


								<h4>Add Enquiry</h4>
							</div>

						</div>

						<div class="colOuter">

							<div class="col-md-2">
								<div class="col1title" align="left">Select Event*:</div>
							</div>

							<div class="col-md-3">
								<select class="selectpicker" data-live-search="true"
									onchange="getEmpByEventId();" title="Please Select"
									name="eventId" id="eventId" required>

									<c:forEach items="${eventHistory}" var="eventHistory">
										<option value="${eventHistory.eventId}">${eventHistory.eventName}</option>

									</c:forEach>
								</select>
							</div>


							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Visitor Mobile No*:</div>
							</div>
							<div class="col-md-3">
								<input id="visitorMobile" class="form-control"
									placeholder="Mobile No" name="visitorMobile"
									style="text-align: left;" pattern="^\d{10}$" type="text"
									maxlength="10" onchange="checkMobNo();" required> <input
									type="hidden" name="visitorId" id="visitorId">

							</div>
							<div align="right" id="tick" class="col-md-0.5"
								style="display: none;">
								<i class="fa fa-check-square"
									style="font-size: 36px; color: green"></i>
							</div>
							<div align="right" id="addvis" class="col-md-1"
								style="display: none;">
								<a
									href="${pageContext.request.contextPath}/addVisitorByExhibitor"><input
									name="button" class="buttonsaveorder" value="Add Visitor"
									type="button" align="right"></a>
							</div>


						</div>
						<div id="hidden_div" style="display: none;">
							<div class="colOuter">
								<div class="col-md-2">
									<div class="col1title" align="left">Visitor Name*:</div>
								</div>
								<div class="col-md-3">
									<input id="visName" class="form-control"
										placeholder="Visitor Name" style="text-align: left;"
										name="visName" type="text" readonly />

								</div>
								<div class="col-md-1"></div>

								<div class="col-md-2">
									<div class="col1title" align="left">E-Mail*:</div>
								</div>
								<div class="col-md-3">
									<input id="visEmail" class="form-control" placeholder="Email"
										name="visEmail" type="email" style="text-align: left;"
										readonly />

								</div>

							</div>
						</div>

						<%-- <div class="colOuter">


							<div class="col-md-2">
								<div class="col1title" align="left">Mobile No*:</div>
							</div>
							<div class="col-md-3">
								<input id="mob" class="form-control" placeholder="Mobile No"
									name="mob" style="text-align: left;" oninput="checkMobNo();"
									pattern="^\d{10}$" type="text" readonly />

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Company Name*:</div>
							</div>
							<div class="col-md-3">
								<input id="root" class="form-control" placeholder="Company Name"
									name="compName" style="text-align: left;"
									value="${customer.custRoot}" type="text" readonly />

							</div>


						</div> --%>

						<div class="colOuter">

							<div class="col-md-2">
								<div class="col1title" align="left">Select Employee*:</div>
							</div>
							<div class="col-md-3">
								<select class="selectpicker" data-live-search="true"
									title="Please Select" name="empId" id="empId" required>

									<c:forEach items="${empList}" var="empList">
										<option value="${empList.empId}">${empList.empName}</option>

									</c:forEach>
								</select>
							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Next Meet Date*:</div>
							</div>

							<div class="col-md-3">
								<input id="fromdatepicker" class="texboxitemcode texboxcal"
									name="meetDate" type="text" required>

							</div>
						</div>



						<div class="colOuter">
							<div align="center">
								<input name="submit" class="buttonsaveorder" value="Submit"
									type="submit" align="center" id="submitButton">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
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
		function getEmpByEventId() {

			var eventId = document.getElementById("eventId").value;
			$('#loader').show();

			$.getJSON('${getEmpByEventId}',

			{

				eventId : eventId,
				ajax : 'true'

			}, function(data) {

				var html;
				var len = data.length;
				for (var i = 0; i < len; i++) {

					html += '<option value="' + data[i].visitorId + '">'
							+ data[i].visitorName + '</option>';

				}
				html += '</option>';
				$('#visitorId').html(html);
				$('.selectpicker').selectpicker('refresh');

			});

		}

		function edit(suppId) {

			$('#loader').show();

			$
					.getJSON(
							'${editFrSupplier}',

							{

								suppId : suppId,
								ajax : 'true'

							},
							function(data) {

								document.getElementById("suppId").value = data.suppId;
								document.getElementById("suppName").value = data.suppName;
								document.getElementById("suppAdd").value = data.suppAddr;
								document.getElementById("city").value = data.suppCity;
								document.getElementById("mob").value = data.mobileNo;
								document.getElementById("email").value = data.email;
								document.getElementById("gstnNo").value = data.gstnNo;
								document.getElementById("panNo").value = data.panNo;
								document.getElementById("liceNo").value = data.suppFdaLic;
								document.getElementById("creditDays").value = data.suppCreditDays;
								document.getElementById("isSameState").value = data.isSameState;
								document.getElementById("cancel").disabled = false;
							});

		}

		function cancel1() {

			//alert("cancel");
			document.getElementById("suppId").value = "";
			document.getElementById("suppName").value = "";
			document.getElementById("suppAdd").value = "";
			document.getElementById("city").value = "";
			document.getElementById("mob").value = "";
			document.getElementById("email").value = "";
			document.getElementById("gstnNo").value = "";
			document.getElementById("panNo").value = "";
			document.getElementById("liceNo").value = "";
			document.getElementById("creditDays").value = "";
			document.getElementById("isSameState").value = "";
			document.getElementById("cancel").disabled = false;

		}
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

		function checkMobNo() {
			document.getElementById("visitorMobile").style = "color:black"

			var visitorMobile = document.getElementById("visitorMobile").value;

			$
					.getJSON(
							'${isVisitorExist}',
							{
								visitorMobile : visitorMobile,

								ajax : 'true'

							},
							function(data) {

								if (data.visitorId == 0) {
									alert("Visitor with this Mobile Number Not Registered. Please Add Visitor First.");
									document.getElementById("visitorMobile").style.backgroundColor = "red";
									document.getElementById("visitorMobile").style.color = "white";
									$('#hidden_div').hide();
									$('#tick').hide();
									$('#addvis').show();

								} else {
									$('#hidden_div').show();

									document.getElementById("visitorMobile").style.bordercolor = "green";
									document.getElementById("tick").style.color = "green";
									document.getElementById("visitorId").value = data.visitorId;
									document.getElementById("visName").value = data.visitorName;
									document.getElementById("visEmail").value = data.visitorEmail;

									$('#tick').show();

									//alert("In")
								}
							});
			document.getElementById("mob").style = "color:black"

		}
	</script>
</body>
</html>
