<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.pageTitle {
	margin-top: 0px;
}

.overflow {
	width: 16em;
	outline: 0px solid #000;
	margin: 0 0 1em 0;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}

.text {
	text-overflow: "---";
}
</style>

<%-- <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>Monginis</title>
<link
	href="${pageContext.request.contextPath}/resources/css/monginis.css"
	rel="stylesheet" type="text/css" />
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/feviconicon.png"
	type="image/x-icon" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>

<!--rightNav-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/menuzord.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery("#menuzord").menuzord({
			align : "left"
		});
	});
	$(".toggle").on("click", function() {
		$(".marquee").toggleClass("microsoft");
	});
</script>
<!--rightNav-->

</head>
<body> comment by sachin --%>
<style type="text/css">
.fit-img {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: auto;
	max-width: 60%;
	max-height: 60%
}

.fit-img-bottom {
	top: auto;
}

.fit-img-tight {
	max-width: 60%;
	max-height: 60%
}

/* my style  */
div.gallery {
	margin: 5px;
	border: 1px solid #ccc;
	float: left;
	width: 250px;
	margin: 15px 0 15px 10px;
	/*         padding: 15px 15px;
 */
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	text-align: center;
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0
		rgba(0, 0, 0, 0.12);
	background: white;
}

div.gallery:hover {
	border: 1px solid #777;
}

a {
	color: #4c9cdf;
	text-decoration: none;
}

a {
	cursor: pointer;
}

user agent stylesheet
a:-webkit-any-link {
	color: -webkit-link;
	cursor: pointer;
	text-decoration: underline;
}

div.desc {
	padding: 15px;
	text-align: center;
}
</style>
<style>
.btn1 {
	border: 2px solid black;
	background-color: white;
	color: pink;
	padding: 14px 28px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 12px;
}

/* Blue */
.info1 {
	border-color: green;
	color: dodgerblue
}

.info1:hover {
	background: #2196F3;
	color: white;
}
</style>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

	<!--topHeader-->
	<jsp:include page="/WEB-INF/views/include/logo.jsp">

		<jsp:param name="fr" value="${frDetails}" />
	</jsp:include>




	<!--topHeader-->

	<!--rightContainer-->
	<div class="fullGrid center">
		<!--fullGrid-->
		<div class="wrapperIn2">

			<jsp:include page="/WEB-INF/views/include/left.jsp">
				<jsp:param name="myMenu" value="${menuList}" />

			</jsp:include>
			<%--     <input type="hidden" id="achievedTarget" value="${sessionScope.achievedTarget}">
       <input type="hidden" id="target" value="${sessionScope.fraTarget}"> --%>
			<c:choose>
				<c:when test="${loginInfo.accessRight==1}">
					<a href="${pageContext.request.contextPath}/viewMonthwiseSellBill">
						<div id="chart_div"
							style="width: 220px; height: 70px; float: right; margin-right: 60px; margin-top: 10px;"></div>
					</a>

				</c:when>

			</c:choose>

			<!--rightSidebar-->
			<c:choose>

				<c:when test="${empty sessionScope.UserDetail.exhSubHeader}">
					<c:set var="header1" value=""></c:set>
				</c:when>

				<c:otherwise>
					<c:set value="${sessionScope.UserDetail.exhSubHeader}"
						var="header1" scope="request"></c:set>
				</c:otherwise>

			</c:choose>


			<div class="sidebarright">
				<div class="col-md-10">
					<h2 class="pageTitle">
						Hi <span>${sessionScope.UserDetail.exhibitor.exhName}</span>,
						Welcome Back <i>
						<c:choose><c:when test="${not empty header1}"> [  ${sessionScope.UserDetail.exhSubHeader.fromDate}&nbsp;&nbsp; To &nbsp;&nbsp;-${sessionScope.UserDetail.exhSubHeader.toDate} ]</c:when>
						<c:otherwise>-To Subscribe Contact to : 1234567890</c:otherwise>
						</c:choose>
						</i>
					</h2>
				</div>
				<div class="col-md-2">
					<p style="text-align: right;" class="pageTitle">
						<b><a
							href="${pageContext.request.contextPath}/exhibitorDashboard">Dashboard</a></b>
					</p>
				</div>

				<!--slider-->
				<!--slider thum size : width:850px height:350px-->
				<%-- 	<div class="latestNews">
					
						<h3 class="latestNewsTitle">Event List</h3>
							<div class="microsoft marquee">
						<c:forEach items="${schedulerLists}" var="schedulerLists"  varStatus="count">
						
					            <c:set var="colors" value=""/>
					            <c:choose>
					            <c:when test="${count.index%2==0}">
					            <c:set var="colors" value="white"/>
					           </c:when>
					           <c:otherwise>
					            <c:set var="colors" value="lightblue"/>
					          </c:otherwise>
					          </c:choose>
								<span style="color:${colors}"> ${schedulerLists.schMessage} </span>
							
						
						</c:forEach>
						</div>
					</div> --%>
				<!-- 		<div id="owl-example" class="owl-carousel"> -->
				<form name="homepage" id="homepage" method="get">
					<c:forEach items="${eventList}" var="eventList">

						<div class="gallery">
							<a target="_blank"
								href="${pageContext.request.contextPath}/getEventDetail/${eventList.eventId}/${eventList.orgId}">
								<img src="${eventImgUrl}${eventList.eventLogo}" width="250"
								height="170"
								onerror="this.src='resources/images/No_Image_Available.jpg';">
							</a> <br></br>
							<div style="padding-left: 5px;">
								<p align="left" class="overflow">
									&nbsp;&nbsp; <b> Event Name: </b><abbr
										title="${eventList.eventName}"
										style="border: none; text-decoration: none; cursor: inherit !important;">${eventList.eventName}</abbr>
								</p>
								<p align="left">
									&nbsp;&nbsp; <b> Place: </b>${eventList.eventLocation}</p>
								<p align="left">
									&nbsp;&nbsp; <b>Stall Size: </b>${eventList.stallSize}
								</p>
								<c:choose>

									<c:when
										test="${eventList.priceForExh==eventList.discountedPrice}">
										<p align="left">
											&nbsp;&nbsp; <b> Price: ${eventList.priceForExh} </b>
											Discount Price: <b> ${eventList.discountedPrice} </b>
										</p>
									</c:when>

									<c:otherwise>
										<p align="left">
											&nbsp;&nbsp;<b> Price: </b><strike>${eventList.priceForExh}</strike>
											<mark>
												<b style="background-color: orange;"> Discount Price: </b>
											</mark>
											${eventList.discountedPrice}
										</p>
									</c:otherwise>

								</c:choose>
								<%--     <p align="left"> &nbsp;&nbsp;   &nbsp;&nbsp; Price:<b> ${eventList.priceForExh} </b> Discount Price:<b> ${eventList.discountedPrice} </b></p>
 --%>


								<p align="left">
									&nbsp;&nbsp; <b>From: </b>${eventList.eventFromDate}<b>
										&nbsp;To: </b>${eventList.eventToDate}</p>
							</div>
							<hr></hr>
							<div class="desc">
								<c:choose>
									<c:when test="${eventList.appliedStatus==1}">
										<button class="btn1 info2"
											style="padding: 6px 34px; background-color: lightblue; color: white; border: 1px solid #28adb7;"
											disabled>Pending</button>
									</c:when>

									<c:when test="${eventList.subStatus==0}">
										<button class="btn1 info1"
											onclick="callSubscribe(${eventList.orgId},${eventList.eventId})"
											style="padding: 6px 34px; border: 1px solid #28adb7;">SUBSCRIBE</button>
									</c:when>


									<c:otherwise>
										<button class="btn1 info2"
											style="padding: 6px 34px; background-color: lightblue; color: white; border: 1px solid #28adb7;"
											disabled>
											SUBSCRIBED <i class="fa fa-thumbs-o-up"></i>
										</button>
									</c:otherwise>

								</c:choose>
							</div>
						</div>
					</c:forEach>

				</form>


				<%-- <div class="item">
							<div class="screen4plan">
								<div class="homesliderImg">
									<img
										src="${pageContext.request.contextPath}/resources/images/slide1.jpg"
										alt="img">
								</div>
								<h3 class="homesliderTitle">
								
								Contrary to popular belief,
									Lorem Ipsum is not simply in some form, by injected humour, or
									randomised words which don't look even slightly believable.</h3>
							</div>
						</div>

						<div class="item">
							<div class="screen4plan">
								<div class="homesliderImg">
									<img
										src="${pageContext.request.contextPath}/resources/images/slide2.jpg"
										alt="img">
								</div>
								<h3 class="homesliderTitle">Contrary to popular belief,
									Lorem Ipsum is not simply in some form, by injected humour, or
									randomised words which don't look even slightly believable.</h3>
							</div>
						</div>

						<div class="item">
							<div class="screen4plan">
								<div class="homesliderImg">
									<img
										src="${pageContext.request.contextPath}/resources/images/slide1.jpg"
										alt="img">
								</div>
								<h3 class="homesliderTitle">Contrary to popular belief,
									Lorem Ipsum is not simply in some form, by injected humour, or
									randomised words which don't look even slightly believable.</h3>
							</div>
						</div>

						<div class="item">
							<div class="screen4plan">
								<div class="homesliderImg">
									<img
										src="${pageContext.request.contextPath}/resources/images/slide1.jpg"
										alt="img">
								</div>
								<h3 class="homesliderTitle">Contrary to popular belief,
									Lorem Ipsum is not simply in some form, by injected humour, or
									randomised words which don't look even slightly believable.</h3>
							</div>
						</div>

						<div class="item">
							<div class="screen4plan">
								<div class="homesliderImg">
									<img
										src="${pageContext.request.contextPath}/resources/images/slide1.jpg"
										alt="img">
								</div>
								<h3 class="homesliderTitle">Contrary to popular belief,
									Lorem Ipsum is not simply in some form, by injected humour, or
									randomised words which don't look even slightly believable.</h3>
							</div>
						</div>
 --%>
			</div>
			<!--slider-->

			<!--latestNews-->


			<%-- 	<div class="latestNews">
					
						<h3 class="latestNewsTitle">Latest News</h3>
							<div class="microsoft marquee">
						<c:forEach items="${schedulerLists}" var="schedulerLists">
						
					

								<span> ${schedulerLists.schMessage} </span>
							
						
						</c:forEach>
						</div>
					</div> --%>


			<!--latestNews-->

		</div>
		<!--rightSidebar-->

	</div>
	<!--fullGrid-->
</div>
<!--rightContainer-->
</div>
<!--wrapper-end-->

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	var achievedTarget=document.getElementById("achievedTarget").value;
	var target=document.getElementById("target").value;
	var monthTarget=target;
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);

function drawMultSeries() {
	 
	achievedTarget=Math.ceil(achievedTarget);
	 
	if(achievedTarget>=target)
		{
		 
		target=0;
		}
	else{
		 
		 
		target=target-achievedTarget;
		
	}
	
      var data = google.visualization.arrayToDataTable([
        ['Target In Lakhs', 'Assigned Target'],
        ['Monthly Target:'+monthTarget,target],
        ['Achieved Target:'+achievedTarget,achievedTarget],
      ]);

      var options = {
    	        title: 'Franchise Target',
    	        chartArea: {width: '1220',height: '1220'},
    	        hAxis: {
    	          title: 'Target Details',
    	          minValue: 0
    	        },
    	        vAxis: {
    	          title: 'Target In Lakhs'
    	        }
    	     
    	      };


            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));

   
      chart.draw(data, options);
    }
</script>

<script type="text/javascript">

function callSubscribe(orgId,eventId) {
	
	 var form = document.getElementById("homepage");
	/*  alert(form);
	 alert(orgId)
	 alert(eventId);
	  */
	    form.action ='${pageContext.request.contextPath}/addEventSubsctiption/'+orgId+'/'+eventId;
	    form.submit();
}


</script>

</body>
</html>
