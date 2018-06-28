
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalTime"%>
<%@ page import="java.time.ZoneId"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<style type="text/css">
.topSlide2 {
	padding: 20px 60px 20px 20px;
}

.sidebarOuter {
	z-index: 0;
	left: 0px;
	top: 0px;
	bottom: 0px;
	width: 130px;
	background: #41879e
		url(${pageContext.request.contextPath}/resources/images/left1.jpeg)
}
</style>
<div class="sidebarhome">
	<div class="content mCustomScrollbar">
		<c:choose>
			<c:when test="${sessionScope.UserDetail.isSubscribed==0}">
				<ul>

					<li><a href="${pageContext.request.contextPath}/addEmployee"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Add Employee</span>
							</div></a></li>

					<li><a href="${pageContext.request.contextPath}/addProduct"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Add Product</span>
							</div></a></li>

					<li><a href="${pageContext.request.contextPath}/addMaterial"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Add Material</span>
							</div></a></li>

					<li><a
						href="${pageContext.request.contextPath}/showEditProfile"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Edit Profile</span>
								<%-- <c:choose>
						<c:when test="${sessionScope.UserDetail.isSubscribed == true}">
						
						

						</c:when>
						</c:choose> --%>
							</div></a></li>

					<li><a
						href="${pageContext.request.contextPath}/showAddFbQuestion"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Feedback Question</span>
							</div></a></li>




					<li><a href="${pageContext.request.contextPath}/showFbQueTxn"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Feedback Question Txn</span>
							</div></a></li>



					<li><a
						href="${pageContext.request.contextPath}/showEventHistory"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Event History</span>
							</div></a></li>

					<li><a
						href="${pageContext.request.contextPath}/addSpinQuestion"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Add Spin Question</span>
							</div></a></li>

					<li><a
						href="${pageContext.request.contextPath}/showEnquiryBetweenDates"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Enquiry Bet Dates</span>
							</div></a></li>



					<li><a
						href="${pageContext.request.contextPath}/showQuestionsBetweenDates"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Spin Question Analysis</span>
							</div></a></li>

					<li><a
						href="${pageContext.request.contextPath}/showEventVisName"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Event Visitors</span>
							</div></a></li>



					<li><a
						href="${pageContext.request.contextPath}/showgetEventLikeCounts"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Event Like Comparison</span>
							</div></a></li>

					<li><a
						href="${pageContext.request.contextPath}/exhibitorEmplolyeeDashboard"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Employee Dashboard</span>
							</div></a></li>



					<li><a
						href="${pageContext.request.contextPath}/showEmployeeExhGraph"><div
								class="img">
								<i class="fa fa-file-o icon"></i>
							</div>
							<div class="title">
								<span>Employee Comparision Report</span>
							</div></a></li>


				</ul>
			</c:when>

			<c:otherwise>

				<li><a href="#" onClick="openNav()"><div class="img">
							<img
								src="${pageContext.request.contextPath}/resources/images/form.png"
								alt="img">
						</div>
						<div class="title">
							Manage <span>Masters</span>

						</div></a></li>



				<li><a href="#" onClick="openNav1()"><div class="img">
							<img
								src="${pageContext.request.contextPath}/resources/images/events.png"
								alt="img">
						</div>
						<div class="title">
							<span>Events</span>
						</div></a></li>

				<li><a href="#" onClick="openNav2()"><div class="img">
							<img
								src="${pageContext.request.contextPath}/resources/images/enquiry.png"
								alt="img">
						</div>
						<div class="title">
							Enquiry <span>Info</span>
						</div></a></li>
				<%-- <li><a href="#" onClick="openNav3()"><div class="img">
							<img
								src="${pageContext.request.contextPath}/resources/images/feedback.png"
								alt="img">
						</div>
						<div class="title">
							Feedback Question <span>Info</span>
						</div></a></li> --%>

				<li><a href="#" onClick="openNav4()"><div class="img">
							<img
								src="${pageContext.request.contextPath}/resources/images/user.png"
								alt="img">
						</div>
						<div class="title">
							Employee <span>Info</span>
						</div></a></li>

				<li><a
					href="${pageContext.request.contextPath}/showEditProfile"><div
							class="img">
							<img
								src="${pageContext.request.contextPath}/resources/images/profile.png"
								alt="img">
						</div>
						<div class="title">
							Edit <span>Profile</span>
						</div></a></li>

			</c:otherwise>
		</c:choose>

	</div>
</div>
<!--leftNav-->

<!--navInner-->

<!--1nav-->

<!--1nav-->



<!--2nav-->
<div id="mySidenav" class="sidenav">
	<div class="topSlide">
		<div class="topSlide2">
			<a href="javascript:void(0)" class="closebtn" onClick="closeNav()"><img
				src="${pageContext.request.contextPath}/resources//images/closebtn.png"
				alt="ATS"></a>
			<div class="listarea">


				<ul>
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Master</div>
							</div>

							<a href="${pageContext.request.contextPath}/addProduct"><i
								class="fa fa-plus-circle icon"></i> Add Product </a>
							<div class="clearfix"></div>
							<a href="${pageContext.request.contextPath}/addMaterial"><i
								class="fa fa-search-plus icon"></i> Add Material </a>
							<div class="clearfix"></div>

							<a href="${pageContext.request.contextPath}/addEmployee"><i
								class="fa fa-search-plus icon"></i> Add Employee </a>

						</div>
					</li>

					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Question Master</div>
							</div>


							<a href="${pageContext.request.contextPath}/showAddFbQuestion"><i
								class="fa fa-plus-circle icon"></i>Feedback Question </a> <a
								href="${pageContext.request.contextPath}/addSpinQuestion"><i
								class="fa fa-search-plus icon"></i>Spin Question </a>

						</div>

					</li>



				</ul>
			</div>

		</div>
	</div>
</div>




<!--2nav-->

<!--4nav-->

<!--4nav-->
<div id="mySidenav1" class="sidenav">
	<div class="topSlide">
		<div class="topSlide2">
			<a href="javascript:void(0)" class="closebtn" onClick="closeNav1()"><img
				src="${pageContext.request.contextPath}/resources/images/closebtn.png"
				alt="monginis"></a>
			<div class="listarea">

				<ul>
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Event Info</div>
							</div>

							<a href="${pageContext.request.contextPath}/showEventHistory"><i
								class="fa fa-plus-circle icon"></i> Event History </a>
							<div class="clearfix"></div>
							<a href="${pageContext.request.contextPath}/showEventVisName"><i
								class="fa fa-search-plus icon"></i>Visitor List </a>
							<div class="clearfix"></div>

	<a href="${pageContext.request.contextPath}/showProdVisName"><i
								class="fa fa-search-plus icon"></i>Procuct Visitor</a>
							<div class="clearfix"></div>
							<a
								href="${pageContext.request.contextPath}/showgetEventLikeCounts"><i
								class="fa fa-search-plus icon"></i> Event Likes </a>
								
								

						</div>
					</li>



				<!-- 	</ul>
					<ul> -->
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Question</div>
							</div>

							<a href="${pageContext.request.contextPath}/showFbQueTxn"><i
								class="fa fa-plus-circle icon"></i>Feedback Question Transaction</a>
							<div class="clearfix"></div>
							<a
								href="${pageContext.request.contextPath}/showQuestionsBetweenDates"><i
								class="fa fa-search-plus icon"></i>Spin Question Analysis </a>
							<div class="clearfix"></div>
							

						</div>
					</li>



				</ul>
			</div>

		</div>
	</div>
</div>
<!--4nav-->

<!--3nav-->
<div id="mySidenav2" class="sidenav">
	<div class="topSlide">
		<div class="topSlide2">
			<a href="javascript:void(0)" class="closebtn" onClick="closeNav2()"><img
				src="${pageContext.request.contextPath}/resources//images/closebtn.png"
				alt="ATS"></a>
			<div class="listarea">

				<ul>
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Enquiry Info</div>
							</div>

							<a
								href="${pageContext.request.contextPath}/showEnquiryBetweenDates"><i
								class="fa fa-plus-circle icon"></i>Enquiry Between Date</a> 
								
								
							<a
								href="${pageContext.request.contextPath}/showEnquiryByEvent"><i
								class="fa fa-plus-circle icon"></i>Enquiry By Event</a> 
								
								<a
								href="${pageContext.request.contextPath}/addEnquiryByExhibitor"><i
								class="fa fa-plus-circle icon"></i> Add Enquiry</a> <a
								href="${pageContext.request.contextPath}/addVisitorByExhibitor"><i
								class="fa fa-plus-circle icon"></i> Add Visitor</a>
							<%-- <div class="clearfix"></div>
							<a href="${pageContext.request.contextPath}/showEventVisName"><i
								class="fa fa-search-plus icon"></i>Visitor List </a>
							<div class="clearfix"></div>

							<a href="${pageContext.request.contextPath}/showgetEventLikeCounts"><i
								class="fa fa-search-plus icon"></i> Event Likes </a> --%>

						</div>
					</li>



				</ul>
			</div>

		</div>
	</div>
</div>
<!--3nav-->

<!--4nav-->
<%-- <div id="mySidenav3" class="sidenav">
	<div class="topSlide">
		<div class="topSlide2">
			<a href="javascript:void(0)" class="closebtn" onClick="closeNav3()"><img
				src="${pageContext.request.contextPath}/resources//images/closebtn.png"
				alt="ATS"></a>
			<div class="listarea">

				<ul>
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Question</div>
							</div>

							<a href="${pageContext.request.contextPath}/showFbQueTxn"><i
								class="fa fa-plus-circle icon"></i>Feedback Question Transaction</a>
							<div class="clearfix"></div>
							<a
								href="${pageContext.request.contextPath}/showQuestionsBetweenDates"><i
								class="fa fa-search-plus icon"></i>Spin Question Analysis </a>
							<div class="clearfix"></div>

							<a href="${pageContext.request.contextPath}/showgetEventLikeCounts"><i
								class="fa fa-search-plus icon"></i> Event Likes </a>



						</div>
					</li>



				</ul>
			</div>

		</div>
	</div>
</div> --%>
<!--4nav-->


<!--5nav-->
<div id="mySidenav4" class="sidenav">
	<div class="topSlide">
		<div class="topSlide2">
			<a href="javascript:void(0)" class="closebtn" onClick="closeNav4()"><img
				src="${pageContext.request.contextPath}/resources//images/closebtn.png"
				alt="monginis"></a>
			<div class="listarea">

				<ul>
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Employee Info</div>
							</div>

							<a href="${pageContext.request.contextPath}/addEmployee"><i
								class="fa fa-plus-circle icon"></i>Add Employee </a>
							<div class="clearfix"></div>
<a href="${pageContext.request.contextPath}/assignTaskToEmp"><i
								class="fa fa-plus-circle icon"></i>Assign Task </a>
								
								
							<a href="${pageContext.request.contextPath}/showEmpEnqEdit"><i
								class="fa fa-search-plus icon"></i>Edit Enquiry</a>

							

						
							
						</div>
					</li>




					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Employee Info</div>
							</div>
							<a
								href="${pageContext.request.contextPath}/exhibitorEmplolyeeDashboard"><i
								class="fa fa-search-plus icon"></i>Employee Wise Dashboard </a>
							<div class="clearfix"></div>
							
								<a href="${pageContext.request.contextPath}/showEmployeeExhGraph"><i
								class="fa fa-search-plus icon"></i> Employee Comparision Report</a>
								 <a
								href="${pageContext.request.contextPath}/trackingHistory"><i
								class="fa fa-search-plus icon"></i> Total KM  By  Travelling  Employee</a>
							<div class="clearfix"></div>

							<div class="clearfix"></div>

							<a href="${pageContext.request.contextPath}/showEmpEnqTask"><i
								class="fa fa-search-plus icon"></i>Enquiry And Task</a>

							<div class="clearfix"></div>


							<div class="clearfix"></div>

						</div>
					</li>
					
					
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Pending Enquiry</div>
							</div>
							
							

							<div class="clearfix"></div>

							<a href="${pageContext.request.contextPath}/showTaskList"><i
								class="fa fa-search-plus icon"></i>Pending Task List</a>

							<div class="clearfix"></div>
								<a href="#"><i
								class="fa fa-search-plus icon"></i>Pending Enquiry</a>
							

						</div>
					</li>



				</ul>
			</div>

		</div>
	</div>
</div>
<!--5nav-->

<!--6nav-->
<div id="mySidenav5" class="sidenav">
	<div class="topSlide">
		<div class="topSlide2">
			<a href="javascript:void(0)" class="closebtn" onClick="closeNav5()"><img
				src="${pageContext.request.contextPath}/resources//images/closebtn.png"
				alt="monginis"></a>
			<div class="listarea">

				<ul>
					<li class="small-box">

						<div class="ibox">
							<div class="ibox-head">
								<div class="ibox-title">Edit Profile</div>
							</div>

							<a href="${pageContext.request.contextPath}/showEditProfile"><i
								class="fa fa-plus-circle icon"></i>Profile </a>
							<div class="clearfix"></div>
							<%-- 	<a href="${pageContext.request.contextPath}/addSpinQuestion"><i
								class="fa fa-search-plus icon"></i>Spin Question </a> --%>
							<%-- <div class="clearfix"></div>

							<a href="${pageContext.request.contextPath}/showgetEventLikeCounts"><i
								class="fa fa-search-plus icon"></i> Event Likes </a>
 --%>


						</div>
					</li>



				</ul>
			</div>

		</div>
	</div>
</div>
<!--6nav-->



<!--navInner-->
<script>
	function openNav() {
		document.getElementById("mySidenav").style.width = "100%";
	}

	function closeNav() {
		document.getElementById("mySidenav").style.width = "0";
	}
	function openNav1() {
		document.getElementById("mySidenav1").style.width = "100%";
	}

	function openNav2() {
		document.getElementById("mySidenav2").style.width = "100%";
	}

	function closeNav1() {
		document.getElementById("mySidenav1").style.width = "0";
	}

	function closeNav2() {
		document.getElementById("mySidenav2").style.width = "0";
	}
	function openNav3() {
		document.getElementById("mySidenav3").style.width = "100%";
	}

	function closeNav3() {
		document.getElementById("mySidenav3").style.width = "0";
	}
	function openNav4() {
		document.getElementById("mySidenav4").style.width = "100%";
	}

	function closeNav4() {
		document.getElementById("mySidenav4").style.width = "0";
	}

	function openNav5() {
		document.getElementById("mySidenav5").style.width = "100%";
	}

	function closeNav5() {
		document.getElementById("mySidenav5").style.width = "0";
	}
</script>

<script
	src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<script>
	$(document).ready(function() {
		$('.imgpath').click(function() {
			$(this).hide();
			$('.videoWrapper').show();
			$(".sproutvideo-player")[0].src += "?autoplay=1";
		});
		var owl = $("#owl-example");
		owl.owlCarousel({
			items : 2,
			loop : false,
			autoplay : false,
		});

	});
</script>

<script type="text/javascript">
	function showWindow(fromTime, toTime) {
		confirm("Timeout:\n You can place order from " + fromTime + " To "
				+ toTime);
	}
</script>


<!-- custom scrollbar plugin added by kalpesh -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>