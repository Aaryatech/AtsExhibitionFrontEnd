
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
#carousel {
	width: 100%;
	height: 150px;
	background-color: #ff0000;
	overflow: visible;
	white-space: nowrap;
}

#carousel .slide {
	display: inline-block;
}

nav {
	float: left;
	max-width: 200px;
	margin: 0;
	border-right: 1px solid gray;
	padding: 1em;
}

article {
	margin-left: 300px;
	padding: 1em;
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

					<div class="col-md -3">

						<div class="col1title" align="left">
							<h3>About Event</h3>
						</div>

					</div>

					<div class="colOuter">
						<div class="col-md-4">
							<div class="col1title" align="left">
								<b>Event :</b> ${event.eventName}
							</div>
						</div>
					</div>
					<div class="colOuter">
						<div class="col-md-12">
							<div class="col1title" align="left">
								<b>About Event : </b> ${event.aboutEvent}

							</div>
						</div>
					</div>
					<div class="colOuter">
						<div class="col-md-4">
							<div class="col1title" align="left">
								<b>Contact Person : </b>${event.contactPersonName1}
							</div>
						</div>

						<div class="col-md-4">
							<div class="col1title" align="left">
								<b>Contact Person Mobile : </b>${event.person1Mob}

							</div>
						</div>

						<div class="col-md-4">
							<div class="col1title" align="left">
								<b>Contact Person Email : </b>${event.person1EmailId}
							</div>
						</div>

					</div>

					<div class="colOuter">
						<div class="col-md-12">
							<div class="col1title" align="left">
								<b> Committee Members : </b>
							</div>
						</div>
					</div>
					<div class="carousel">
						<c:forEach items="${memberList}" var="member">
							<div class="slide">
								<img src="${MemberImgUrl}${member.image}"
									style="border-radius: 50%; width: 125px; height: 125px;">
								<p>${member.memberName}-${member.designation}</p>
							</div>
						</c:forEach>
					</div>

					<div class="colOuter">
						<div class="col-md-12">
							<div class="col1title" align="left">
								<b>Event Sponsors : </b>
							</div>
						</div>
						<div class="background"
							style="width: auto; height: 170; white-space: nowrap; overflow-y: scroll; border: 0; padding: 10px;">
							<c:forEach items="${sponserList}" var="sponsor">
								<img src="src=" ${sponsorImgUrl}
									${sponsor.photo}"
									style="border-radius: 50%; width: 150px; height: 100px;">
								<p>${sponsor.name}${sponsor.designation}</p>

							</c:forEach>
						</div>
					</div>
					<!-- 
<table width="100%" border="0"  cellpadding="0" cellspacing="0" style="border-left:1px solid #313131;border-right:1px solid #313131;border-top:1px solid #313131;">
  <tr>
    <td colspan="2" width="30%" style=" padding:10px;color:#FFF; border-bottom:1px solid #313131;font-size:15px;">

</td>

    <td colspan="9" width="60%" style="border-left:1px solid #313131; border-bottom:1px solid #313131; padding:10px;color:#FFF; font-size:15px;">
   	<p style="color:#000; font-size:11px; text-align:center;margin:0px;"></p> 
    </td>
    
  </tr>
  </table> -->

					<div class="colOuter">
						<div class="col-md-12">
							<div class="col1title" align="left">
								<b>Schedule : </b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								style="border-left: 1px solid #313131; border-right: 1px solid #313131; border-top: 1px solid #313131;">


								<%-- <div class="w3-panel w3-pale-red  w3-border-green w3-border">
									<nav>
									
											<p>Date:${schedule.date}</p>

											<p>Activity:${schedule.eventName}</p>
											<p>Day:${schedule.dayName}</p>
	</nav>
											<article>Detail

											<c:forEach items="${schedule.scheduleDetailList}"
												var="detail">

												<div>Topic:${detail.topic}</div>

												<div>Timing:${detail.fromTime}-${detail.toTime}</div>
												<div>Venue:${detail.venue}</div>

												<div>Speaker:${detail.speaker}</div>

												<div>:${detail.remark}</div>


											</c:forEach></article>

								

								
							</div>	 --%>

								<c:forEach items="${scheduleList}" var="schedule"
									varStatus="count">
									<tr style="background-color: lightgrey;">
										<td colspan="11" width="100%"
											style="border-bottom: 1px solid #313131; padding: 10px; color: black; font-size: 15px;"> 
											<div class="row">
											
											<div class="col-md-3">
											Schedule:${count.index+1}
											</div>
											
											<div class="col-md-3">
											Date:${schedule.date}
											</div>
												<div class="col-md-3">
											Activity:${schedule.eventName}
											</div>
												<div class="col-md-3">
											Day:${schedule.dayName}
											</div>
											
											</div>
											</td>
									</tr>
									<tr style="background-color: ff33f3;">
										<%-- <td colspan="2" width="30%"
											style="padding: 10px; color: black; border-bottom: 1px solid #313131; font-size: 15px;"><p>Date:${schedule.date}</p>

											<p>Activity:${schedule.eventName}</p>
											<p>Day:${schedule.dayName}</p></td> --%>
										<td colspan="9" width="60%"
											style="border-left: 1px solid #313131; border-bottom: 1px solid #313131; padding: 10px; color: #FFF; font-size: 15px;">

											<c:forEach items="${schedule.scheduleDetailList}"
												var="detail">
											
											<!-- 	<p><b><i>Details</i></b></p> -->
												
											<div class="row">
											
											<div class="col-md-3">
											<b>Topic:</b>&nbsp;${detail.topic}
											</div>
											
											<div class="col-md-3">
											<b>Timing:</b>&nbsp;${detail.fromTime}-${detail.toTime}
											</div>
												<div class="col-md-3">
											<b>Venue:</b>${detail.venue}
											</div>
												<div class="col-md-3">
											<b>Speaker:</b>${detail.speaker}
											</div>
											
											</div>
											
											<div class="row">
											<div class="col-md-3" align="center">
											${detail.remark}
											</div>
											</div>
												<hr/>
										
													
													

													<%-- Timing:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${detail.fromTime}-${detail.toTime}</br>
													:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${detail.venue}</br>

													:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${detail.speaker}</br>
 --%>
												
											</c:forEach>
										</td>
									</tr>
								</c:forEach>



							</table>
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
