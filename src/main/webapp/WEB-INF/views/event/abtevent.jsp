
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- <style>
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
</style> -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style>
/* .search-table-outter {border:2px solid lightblue;}
 */.search-table{table-layout: fixed; margin:40px auto 0px auto; }
.search-table, td, th{border-collapse:collapse; }
th{padding:10px 7px; font-size:15px; color:lightblue; background:#66C2E0;}
td{padding:5px 8px; height:35px;}

.search-table-outter { overflow-x: scroll; }
th, td { min-width: 200px; }
/* table {
    border-collapse: collapse;
    border-spacing: 20px;
    width: 100%;
    border: 1px solid #ddd;
    height: 150px;
    overflow: visible;
    white-space:nowrap;

}
th, td {
    text-align: center;
    padding: 13px;
    padding-left:12px;
    }
  tr{ overflow-x: scroll;}
    


tr:nth-child(even){background-color: #f2f2f2} */
</style>
</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

	<!-- <link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
 -->

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

						<div class="col1title" align="left"style="color:#9191b3;">
							<h3>About Event</h3>
						</div>

					</div>
					<div class="colOuter" style=" background:white; border-box;   box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);">
						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Event :</b> ${event.eventName}
							</div>
						</div>
					</div>
					<div class="colOuter" style="  background:white; box-sizing: border-box;   box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);">
						<div class="col-md-12">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>About Event : </b> ${event.aboutEvent}

							</div>
						</div>
					</div> 
					<div class="colOuter" style="  background:white; box-sizing: border-box;   box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);">
						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Contact Person : </b>${event.contactPersonName1}
							</div>
						</div>

						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Contact Person Mobile : </b>${event.person1Mob}

							</div>
						</div>

						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Contact Person Email : </b>${event.person1EmailId}
							</div>
						</div>

					</div>

					<div class="col-md-12"  style="background:#f9f1e5; box-sizing: border-box;  color:white; box-shadow: 0 2px 2px 0 rgba(0,0,0,0.16), 0 2px 2px 0 rgba(0,0,0,0.12);">
						
							<div class="col1title" align="left" style="color:#502c2c;">
								<b> Committee Members : </b>
							</div>
						
					</div>
					
					<div class="search-table-outter wrapper">
	<table class="search-table inner">
					<tr>
						<c:forEach items="${memberList}" var="member">
						<td>
						
								<img src="${MemberImgUrl}${member.image}" class="w3-circle"
									style="padding-left:15px; width: 170px; height: 125px;">
								<p style="text-align: center; background-color: white; color: black;">${member.memberName}-${member.designation}</p>
						
							</td>
						</c:forEach>
						
					</tr>
                   </table></div>
					<div class="col-md-12" style="background:#f9f1e5; box-sizing: border-box;  color:white; box-shadow: 0 2px 2px 0 rgba(0,0,0,0.16), 0 2px 2px 0 rgba(0,0,0,0.12);">
							<div class="col1title" align="left" style="color:#502c2c;" >
								<b>Event Sponsors : </b>
						</div>
						</div>
					<!-- 	<div class="background"
							style="width: auto; height: 170; white-space: nowrap; overflow-y: scroll; border: 0; padding: 10px;">
						 -->
						 	<div class="search-table-outter wrapper">
	<table class="search-table inner">
					<tr>	<c:forEach items="${sponserList}" var="sponsor">
								<td><img src="${SponsorImgUrl}${sponsor.photo}" class="w3-circle"
									style="padding-left:15px; width: 170px; height: 125px;">
								<p style="text-align: center; background-color: white; color: black;">${sponsor.name}&nbsp;${sponsor.designation}</p>
                              </td>
							</c:forEach>
					</tr>
                   </table></div>
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

					<!-- <div class="colOuter"> -->
						<div class="col-md-12"style="background:#f9f1e5; box-sizing: border-box;  color:white; box-shadow: 0 2px 2px 0 rgba(0,0,0,0.16), 0 2px 2px 0 rgba(0,0,0,0.12);">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Schedule : </b>
							</div>
							<table width="100%" height="30%" border="0" cellpadding="0" cellspacing="0"
								style="border-left: 1px solid #87ceeb; border-right:1px solid #87ceeb; border-top: 1px solid #87ceeb">


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
									<tr style="background-color: #badcea;">
										<td colspan="11" width="100%"
											style="border-bottom: 1px solid #badceb; padding: 10px; color: black; font-size: 15px;">Schedule
											${count.index+1}</td>
									</tr>
									<tr style="background-color: #f3f3dd;">
										<td colspan="2" width="10%" 
											style="padding: 10px; color: black; border-bottom: 1px solid #9fd5ec; font-size: 15px; height:20px;">
                                          <table>
												<tr><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">Date:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">${schedule.date}</td></tr>

											<tr><td style="padding-left: 10px;height:20px;border:0px solid lightblue; ">Activity:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">${schedule.eventName}</td></tr>
											<tr><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">Day:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">${schedule.dayName}</td></tr>
											</table>
										<td colspan="9" width="60%"
											style="border-left: 1px solid #badceb; height:20px; border-bottom: 1px solid #badceb; padding: 10px; color: #903a21; font-size: 15px;">

											<c:forEach items="${schedule.scheduleDetailList}"
												var="detail">
												<p style="border:1px solid #badceb; padding-left: 10px;padding-top: 10px;height: 30px;">Details</p>
												<hr>
												<table>
												<tr>
													<td style="padding-left: 10px; height:20px; border:0px solid lightblue;">Topic:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">${detail.topic}</td></tr>

													<tr><td style="padding-left: 10px; height:20px; border:0px solid lightblue;">	Timing: </td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">${detail.fromTime}-${detail.toTime}</td></tr>
														<tr><td style="padding-left: 10px; height:20px; border:0px solid lightblue;">Venue:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;"> ${detail.venue}</td></tr>

														<tr><td style="padding-left: 10px; height:20px; border:0px solid lightblue;">Speaker:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;"> ${detail.speaker}</td></tr>

												<tr><td style="padding-left: 10px; height:20px; border:0px solid lightblue;">Remark:</td><td style="padding-left: 10px; height:20px;border:0px solid lightblue;">${detail.remark}</td></tr>
												
												</table>
											</c:forEach>
										</td>
									</tr>
								</c:forEach>



							</table>
						</div>

					<!-- </div> -->

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
