
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

</head>
<body>

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
						action="${pageContext.request.contextPath}/uploadVisFileProcess"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">
								<strong><a style="color: green;"
									href="${pageContext.request.contextPath}/exhibitorDashboard">Dashboard</a></strong>

								<h4>Add Visitor File</h4>
								<a style="color: green;"
									href="${pageContext.request.contextPath}/downloadFile">Download Excel File Format</a>

									
							</div>

						</div>

						<div class="colOuter">

							<div class="col-md-2">
								<div class="col1title" align="left">Select Event*:</div>
							</div>

							<div class="col-md-2">
								<select class="selectpicker" data-live-search="true"
									title="Please Select" name="eventId" id="eventId" required>

									<c:forEach items="${eventHistory}" var="eventHistory">
										<option value="${eventHistory.eventId}">${eventHistory.eventName}</option>

									</c:forEach>
								</select>
							</div>


							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Select Location*:</div>
							</div>
							<div class="col-md-2">
								<select class="selectpicker" data-live-search="true"
									title="Please Select" name="locationId" id="locationId">
									<option value="">Select</option>
									<c:forEach items="${locationList}" var="locationList">
										<option value="${locationList.locationId}">${locationList.locationName}</option>

									</c:forEach>
								</select>
							</div>

							<%-- 	<div align="right" id="addvis" class="col-md-1"
								style="display: none;">
								<a
									href="${pageContext.request.contextPath}/addVisitorByExhibitor"><input
									name="button" class="buttonsaveorder" value="Add Visitor"
									type="button" align="right"></a>
							</div> --%>
						</div>

						<div class="colOuter">

							<div class="col-md-2">
								<div class="col1title" align="left">Company Type</div>
							</div>

							<div class="col-md-2">
								<select class="selectpicker" data-live-search="true"
									title="Please Select" name="companyTypeId" id="companyTypeId"
									required>
									<option value="">Select</option>
									<c:forEach items="${companyTypeList}" var="companyTypeList">
										<option value="${companyTypeList.companyTypeId}">${companyTypeList.companyTypeName}</option>

									</c:forEach>
								</select>
							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Select Excel File</div>
							</div>

							<div class="col-md-2">
								<input type="file" name="file" id="file" />
							</div>
						</div>

						<div class="colOuter">
							<div align="center">
								<input name="submit" class="buttonsaveorder" value="Submit"
									type="submit" align="center" id="submitButton">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
							</div>

						</div>

						<c:set var="adCount" value="${fn:length(addedVis) }"></c:set>
						<c:set var="dupMobCount" value="${fn:length(dupMobNoVisList) }"></c:set>
						<c:if test="${adCount!=0 or dupMobCount!=0}">
							<div class="colOuter">
								<div align="center">
									<p> ${adCount} Visitors Added Successfully. Total ${dupMobCount}
										Existing Mobile No Found Not Added</p>
									<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
								</div>

							</div>
						</c:if>


						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-1">Name</th>
											<th class="col-md-1">Mobile No</th>
											<th class="col-md-1">Email</th>
											<th class="col-md-1">Represent</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${addedVis}" var="list" varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-2"><c:out value="${list.visitorName}" /></td>
												<td class="col-md-1"><c:out
														value="${list.visitorMobile}" /></td>

												<td class="col-md-1"><c:out
														value="${list.visitorEmail}" /></td>
												<td class="col-md-2"><c:out
														value="${list.visitorRepresent}" /></td>

											</tr>
											<c:if test="${count.last}">
												<c:set var="sr" value="${count}">

												</c:set>
											</c:if>
										</c:forEach>

										<c:forEach items="${dupMobNoVisList}" var="dupVis"
											varStatus="loop">
											<tr>
												<td class="col-sm-1"><c:out value="${loop.index+1}" /></td>

												<td class="col-md-2" style="color: red;"><c:out
														value="${dupVis.visitorName}" /></td>
												<td class="col-md-1" style="color: red;"><c:out
														value="${dupVis.visitorMobile}" /></td>
												<td class="col-md-1"><c:out
														value="${dupVis.visitorEmail}" /></td>
												<td class="col-md-2"><c:out
														value="${dupVis.visitorRepresent}" /></td>

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
									$('#addvis').show();

								} else {

									document.getElementById("visitorMobile").style = "color:black"
									document.getElementById("visitorId").value = data.visitorId;

									alert("In Else")
								}
							});
			document.getElementById("mob").style = "color:black"

		}
	</script>
</body>
</html>
