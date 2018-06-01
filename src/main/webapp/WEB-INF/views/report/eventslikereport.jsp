
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
</head>

<body onload="callMe()">

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
	<c:url var="getEventLikeCounts" value="/getEventLikeCounts"></c:url>

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
						action="${pageContext.request.contextPath}/getVisNameByEveId"
						enctype="multipart/form-data">
						<input type="hidden" name="mod_ser" id="mod_ser"
							value="search_result">
							<div><b>Number of likes by Event</b></div>
<div id="chart">
					<br>
					<br> <br>
					<hr>
					<div id="PieChart_div" style="width: 100%; height: 100%;"></div>

					<div id="chart_div" style="width: 100%; height: 100%;"></div>

				</div>
						</form>
						
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
	<script type="text/javascript">
/* $(document).ready(function() {
 */
 function callMe(){
/* 	$("#PieChart_div").empty();
	$("#chart_div").empty();
		document.getElementById('chart').style.display = "block";
		  */
		  $ .getJSON(
					'${getEventLikeCounts}',

					{
					
						ajax : 'true'

					},
					function(data) {

					
				 if (data == "") {
						alert("No records found !!");
						//$('#loader').hide();

					}
				 var i=0;
				// $('#loader').hide();
				 google.charts.load('current', {'packages':['corechart', 'bar']});
				 google.charts.setOnLoadCallback(drawStuff);

				 function drawStuff() {
					 
					// alert("Inside DrawStuff");

				   var chartDiv = document.getElementById('chart_div');
				   document.getElementById("chart_div").style.border = "thin dotted red";
				   
				   
				   var PiechartDiv = document.getElementById('PieChart_div');
				   document.getElementById("PieChart_div").style.border = "thin dotted red";
				   
				   
			       var dataTable = new google.visualization.DataTable();
			       dataTable.addColumn('string', 'Event'); // Implicit domain column.
			       dataTable.addColumn('number', 'Likes'); // Implicit data column.
			       
			       var piedataTable = new google.visualization.DataTable();
			       piedataTable.addColumn('string', 'Event'); // Implicit domain column.
			       piedataTable.addColumn('number', 'Likes');
			       piedataTable.addColumn('number', 'eventId');
			       
			       
			       $.each(data,function(key, report) {

			    	   
			    	 // alert("In Data")
			    	   var eventName=report.eventName;
			    	  var likeCount=report.likeCount;
			    	  
					   dataTable.addRows([
						 
						   [eventName,likeCount],
						   
					           ]);
					   
					   
					   piedataTable.addRows([
						 
						   [eventName,likeCount,report.eventId],
						   
					          
					           ]);
					     }) // end of  $.each(data,function(key, report) {-- function
						    
var materialOptions = {
			    	
width: 500,
chart: {
title: 'Event Likes Report',
subtitle: 'Like ',

},
series: {
0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
},
axes: {
y: {
 distance: {label: 'Like Value'}, // Left y-axis.
 brightness: {side: 'right', label: 'LikeValue'} // Right y-axis.
}
}
};
			       function drawMaterialChart() {
			           var materialChart = new google.charts.Bar(chartDiv);
			           
			           materialChart.draw(dataTable, google.charts.Bar.convertOptions(materialOptions));
			           
			         }
			       
			        var chart = new google.visualization.ColumnChart(
			                document.getElementById('chart_div'));
			        
			        var Piechart = new google.visualization.PieChart(
			                document.getElementById('PieChart_div'));
			       chart.draw(dataTable,
			          {title: 'Event Likes Report'});
			       
			       
			       Piechart.draw(piedataTable,
					          {title: 'Event Likes Report',is3D:true	});

			       google.visualization.events.addListener(Piechart, 'select', selectHandler);

			       function selectHandler(e) {
			         //alert('A table row was selected');
			         var row = Piechart.getSelection()[0].row;
			         var eventId=piedataTable.getValue(row,2);
			         var eventName=piedataTable.getValue(row,0);
    	
			         window.open("${pageContext.request.contextPath}/getVisitorNames/"+eventId+"/"+eventName+"");;
			         
			       }
			       
			      
				 };
				 
							
				 });


					}
 
					
		  
</script>
</body>
</html>
