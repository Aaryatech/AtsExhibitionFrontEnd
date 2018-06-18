
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

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
						action="${pageContext.request.contextPath}/saveExbProfile"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Edit Profile</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Exhibitor Name *:</div>
							</div>
							<div class="col-md-3">
								<input id="exb_name" class="form-control"
									placeholder="Exhibitor Name" value="${exhib.exhName}"
									style="text-align: left;" name="exb_name" type="text" required>

							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Company Name*:</div>
							</div>
							<div class="col-md-3">
								<input id="comp_name" class="form-control"
									style="text-align: left;" placeholder="Company Name "
									value="${exhib.exhCompany}" name="comp_name" type="text"
									required>

							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">About Company:</div>
							</div>
							<div class="col-md-3">
								<%-- <input id="abt_company" class="form-control"
									placeholder="About Company" name="abt_company"
									style="text-align: left;" value="${exhib.aboutCompany} "
									type="text" required>
									 --%>
								<textarea style="text-align: left; width: 785px;"
									id="abt_company" name="abt_company" placeholder="About Company">${exhib.aboutCompany}</textarea>

							</div>
							
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Contact Person 1 *:</div>
							</div>
							<div class="col-md-3">
								<input id="con_per1" class="form-control"
									placeholder="Contact Person 1 " name="con_per1" type="text"
									style="text-align: left;" value="${exhib.contactPersonName1}"
									required>

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Contact Person 2
									*:</div>
							</div>
							<div class="col-md-3">
								<input id="con_per2" class="form-control"
									placeholder="Contact Person 2 " name="con_per2"
									type="text" style="text-align: left;"
									value="${exhib.contactPersonName2}" required>

							</div>

						</div>



						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Contact Person 1 Mob</div>
							</div>
							<div class="col-md-3">
								<input id="con_per_mob1" class="form-control"
									placeholder="Contact Person 1 Mobile" name="con_per_mob1"
									type="text" style="text-align: left;"  pattern="[0-9]{10}" title="Enter proper 10 digit Mobile Number"
									value="${exhib.personMob1}" required>
							</div>
							
							
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Contact Person 2 Mob </div>
							</div>
							<div class="col-md-3">
								<input id="con_per_mob2" class="form-control"
									placeholder="Contact Person 2 Mobile" name="con_per_mob2"
									type="text" style="text-align: left;"  pattern="[0-9]{10}" title="Enter proper 10 digit Mobile Number"
									value="${exhib.personMob2}" required>

							</div>
							
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Contact Person 1 Email
									*:</div>
							</div>
							<div class="col-md-3">


								<input id="con_per_em1" class="form-control"
									placeholder="Contact Person 1 Email " name="con_per_em1"
									type="text" style="text-align: left;" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" data-rule-email="true"
									value="${exhib.personEmail1}" required>

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Contact Person 2 Email
									*:</div>
							</div>
							<div class="col-md-3">
								<input id="con_per_em2" class="form-control"
									placeholder="Contact Person 2 Email " name="con_per_em2"
									type="text" style="text-align: left;" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Invalid email address"
									value="${exhib.personEmail2}" required>


							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Latitude</div>
							</div>
							<div class="col-md-3">
								<input id="latitude" class="form-control" placeholder="Latitude"
									name="latitude" type="text" style="text-align: left;"
									value="${exhib.compLat}" required>

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Longitude</div>
							</div>
							<div class="col-md-3">
								<input id="longitude" class="form-control"
									placeholder="Longitude" name="longitude" type="text"
									style="text-align: left;" value="${exhib.compLong}" required>
							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Address:</div>
							</div>
							<div class="col-md-3">
								<%-- <input id="address" class="form-control" placeholder="Address"
									name="address" type="text" style="text-align: left;"
									value="${exhib.address}" required> --%>

								<textarea style="text-align: left; width: 785px;"
									id="address" name="address" placeholder="Address">${exhib.address}</textarea>

							</div>
							</div>
							<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Company Type Name</div>
							</div>
							<div class="col-md-3">
								<select name="company_type_id" style="width: 255px;" class="form-control">
									<c:forEach items="${companyList}" var="company">
										<c:choose>
											<c:when
												test="${exhib.companyTypeName eq company.companyTypeName}">
												<option selected value="${company.companyTypeId}">${company.companyTypeName}</option>
											</c:when>
											<c:otherwise>
												<option value="${company.companyTypeId}">${company.companyTypeName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						<!-- </div>

						<div class="colOuter"> -->
						<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Mobile No *:</div>
							</div>
							<div class="col-md-3">
								<input id="mob_no" class="form-control" placeholder="Mobile No "
									name="mob_no" type="text" style="text-align: left;"  pattern="[0-9]{10}" title="Enter proper 10 digit Mobile Number"
									value="${exhib.userMob}" required>
							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Location</div>
							</div>
							<div class="col-md-1">

								<select name="loc_id" style="width: 275px;" class="form-control">
									<c:forEach items="${locationList}" var="loc">
										<c:choose>
											<c:when test="${exhib.locationName eq loc.locationName}">
												<option selected value="${loc.locationId}">${loc.locationName}</option>
											</c:when>
											<c:otherwise>
												<option value="${loc.locationId}">${loc.locationName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
					<!-- 	</div>
						<div class="colOuter"> -->
							<div align="center">
								<input name="submit" class="buttonsaveorder" value="Submit"
									type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
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

</body>
</html>
