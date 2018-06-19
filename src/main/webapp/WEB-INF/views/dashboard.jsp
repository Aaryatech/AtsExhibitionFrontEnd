

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>


<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 -->    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>

<!--datepicker-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<style>

	.bgc-white, .bgcH-white:hover {
    background-color: skyblue!important;
    color: black;
}
.bgc-white2{
  background-color: #ffc107!important;
    color: black;
}
.bgc-white3{
  background-color: #e2d360!important;
    color: black;
}
.bd {
    border: 1px solid rgba(0,0,0,.0625)!important;
}

.p-20 {
    padding: 20px!important;
}

.d-f, .layers {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
}

.layers {
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-flow: column nowrap;
    flex-flow: column nowrap;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
}

*, :after, :before {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
user agent stylesheet
div {
    display: block;
}
mB-10 {
    margin-bottom: 10px!important;
}
.w-100 {
    width: 100%!important;
}
*, :after, :before {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
.lh-1 {
    line-height: 1!important;
    font-size: 16px;
}

h1, h2, h3, h4, h5, h6 {
    letter-spacing: .5px;
}
.c-green-500, .cH-green-500:hover {
    color: #4caf50!important;
}
.bgc-green-50, .bgcH-green-50:hover {
    background-color: #e8f5e9!important;
}
.bdrs-10em {
    border-radius: 10em!important;
}
.lh-0 {
    line-height: 0!important;
}
.fw-600 {
    font-weight: 600!important;
}
.pX-15 {
    padding-left: 15px!important;
    padding-right: 15px!important;
}
.pY-15 {
    padding-top: 15px!important;
    padding-bottom: 15px!important;
}
.va-m {
    vertical-align: middle!important;
}
.d-ib {
    display: inline-block!important;
}

.hr{
  background-color: red;
   border: 10px;
}
</style>
<script>

		$(function() {
			showChart();
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
	<c:url var="getTotalList" value="/getTotalList" />
	<c:url var="getChart" value="/getChart" />
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
				 
				<form name="frm_search" id="frm_search" method="post" action="${pageContext.request.contextPath}/getTotalList"
				enctype="multipart/form-data"> 
					 
						<div class="col-md -3">
							
								<div class="col1title" align="left"><h3>Dashboard</h3></div>
								 
						</div>
						
						<div class="colOuter">
						<!-- copy div kalpesh -->

						<div class="col-md-2" align="left">From:</div>
						<div class="col-md-2">
							<input id="fromdatepicker" class="texboxitemcode texboxcal"
								value="${fromDate}" name="fromDate" type="text">
								
								 
						</div>
						<div class="col-md-1" align="left"></div>
						 
						<div class="col-md-1" align="left">TO:</div>
						<div class="col-md-2">
							<input id="todatepicker" class="texboxitemcode texboxcal"
								value="${toDate}" name="toDate" type="text">
								 
						</div>
						
						<div class="col-md-1" align="left"></div>
						<div class="col-md-1">
							<input type="submit" class="btn  buttonsaveorder"/> 
							<!-- <input type="button" class="btn  buttonsaveorder" value="Graph" onclick="showChart()"/>  -->
							  
						</div>
					 

					</div><br><br>
					<div class="colOuter"><hr></hr>
					</div>
						
				<div class="colOuter">
						<!-- copy div kalpesh -->

				
				
						<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/showEventHistory">	<div class="layers bd bgc-white2 p-20" style="background: url(${pageContext.request.contextPath}/resources/images/a1.jpg)"><div class="layer w-100 mB-10"><h6 class="lh-1">Total Events</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${eventList.size()}</span></div></div></div></div>
							</a>	</div>
								
							<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/showVisitorLikesList">	<div class="layers bd bgc-white p-20" style="background: url(${pageContext.request.contextPath}/resources/images/a3.png); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1">Total Visitor</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${visitorList.size()} </span></div></div></div></div>
							</a>	</div>		
					
							<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/showFbQueTxn">	<div class="layers bd bgc-white3 p-20"style="background: url(${pageContext.request.contextPath}/resources/images/a7.png); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1">Total Feedback</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${feedbackList.size()} </span></div></div></div></div>
							</a>	</div>	
							
								<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/showEnquiryBetweenDates">	<div class="layers bd bgc-white p-20"style="background: url(${pageContext.request.contextPath}/resources/images/a4.jpeg); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1">Total Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${enquiryList.size()} </span></div></div></div></div>
							</a>	</div>		
						  
					</div>	     
					
					  <div class="colOuter">
						<!-- copy div kalpesh -->

					
						<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/1">	<div class="layers bd bgc-white2 p-20"style="background: url(${pageContext.request.contextPath}/resources/images/a5.jpeg); background-position: center; "><div class="layer w-100 mB-10" ><h6 class="lh-1">Total Pending Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${pending.size()} </span></div></div></div></div>
							</a>	</div>	
								
								<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/2">	<div class="layers bd bgc-white3 p-20"style="background: url(${pageContext.request.contextPath}/resources/images/a1.jpg); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1">Total Processing Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${processing.size()} </span></div></div></div></div>
							</a>	</div>		
							
							 
					<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/4">	<div class="layers bd bgc-white3 p-20" style="background: url(${pageContext.request.contextPath}/resources/images/a8.jpeg); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1"> Total Closed Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${closed.size()}</span></div></div></div></div>
							</a>	</div>
								 
						<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/5">	<div class="layers bd bgc-white p-20"><div class="layer w-100 mB-10"><h6 class="lh-1">  Total Completed Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${completed.size()}</span></div></div></div></div>
							</a>	</div>
						  
					</div>	 
						 <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
					 
							<div class="colOuter" style=" background:white; border-box;   box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);">
						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Enquiry Graph :</b> 
							</div>
						</div>
					
		 
								  <div   class="col-md-4"  align="center" id="chart" style="display: none"><br><br><br>
								 <div >
	  
			<div  id="Piechart" style="width:40%; height:50%; float: center;" ></div> 
			</div>
			  
				</div> 
			</div> 
			
			 
			  <hr color="red" width="100%" />
<div class="colOuter" style=" background:white; border-box;   box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);">
						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>Completed And Closed Enquiry Between Date :</b> 
							</div>
						</div>
					</div>
			
				<div class="colOuter"> 
					<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/6">	<div class="layers bd bgc-white2 p-20" style="background: url(${pageContext.request.contextPath}/resources/images/a1.jpg)"><div class="layer w-100 mB-10"><h6 class="lh-1">Total Closed Between Date</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${enquiryNotBetweenClosedList.size()}</span></div></div></div></div>
							</a>	</div>
						 
							<div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/7">	<div class="layers bd bgc-white2 p-20"style="background: url(${pageContext.request.contextPath}/resources/images/a5.jpeg); background-position: center; "><div class="layer w-100 mB-10" ><h6 class="lh-1">Total Completed Between Date</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${enquiryNotBetweenCompletedList.size()} </span></div></div></div></div>
							</a>	</div>	
								
								
							 
						  
					</div>	
					  <hr color="red" width="100%" />
<div class="colOuter" style=" background:white; border-box;   box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);">
						<div class="col-md-4">
							<div class="col1title" align="left" style="color:#502c2c;">
								<b>All Pending And Processing Enquiry :</b> 
							</div>
						</div>
					</div>
				
					<div class="colOuter"> 
				 	 <div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/8">	<div class="layers bd bgc-white p-20" style="background: url(${pageContext.request.contextPath}/resources/images/a3.png); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1">Total Pending Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${allEnquiryPending.size()} </span></div></div></div></div>
							</a>	</div>	
						 <div class="col-md-3">
							<a href="${pageContext.request.contextPath}/enquiryList/9">	<div class="layers bd bgc-white p-20" style="background: url(${pageContext.request.contextPath}/resources/images/a6.jpg); background-position: center; "><div class="layer w-100 mB-10"><h6 class="lh-1">Total Processing Enquiry</h6></div><div class="layer w-100"><div class="peers ai-sb fxw-nw"><div class="peer peer-greed"><span id="sparklinedash"><canvas width="45" height="20" style="display: inline-block; width: 45px; height: 20px; vertical-align: top;"></canvas></span></div><div class="peer"><span class="d-ib lh-0 va-m fw-600 bdrs-10em pX-15 pY-15 bgc-green-50 c-green-500">0${allEnquiryProcessing.size()}  </span></div></div></div></div>
							</a>	</div>		
								
						  
					</div>			 
					</form>	  
					</div>	 
					
				 
       
				

				 
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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!--easyTabs-->


<script>
 

function cancel1() {

    //alert("cancel");
	document.getElementById("suppId").value="";
	document.getElementById("suppName").value="";  
	document.getElementById("suppAdd").value="";
	document.getElementById("city").value="";
	document.getElementById("mob").value="";
	document.getElementById("email").value="";
	document.getElementById("gstnNo").value="";
	document.getElementById("panNo").value="";
	document.getElementById("liceNo").value="";
	document.getElementById("creditDays").value="";
	document.getElementById("isSameState").value=""; 
	document.getElementById("cancel").disabled=false;

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


	</script>
	
	<script type="text/javascript">
	 
function showChart(){
	$("#PieChart_div").empty();
	$("#chart_div").empty();
	$("#Piechart").empty();
	
		document.getElementById('chart').style.display = "block";
		   //document.getElementById("table").style="display:none";
		   
		   var fromDate = document.getElementById("fromdatepicker").value;
			var toDate = document.getElementById("todatepicker").value;
			   
			  
			 $.getJSON('${getChart}',{
				
								fromDate : fromDate,
								toDate : toDate,
								ajax : 'true',

							},
							function(data) {
								 
							 if (data == "") {
									alert("No records found !!");

								}
							 var i=0;
							
							 google.charts.load('current', {'packages':['corechart']});
							  //google.charts.load('current', {'packages':['corechart', 'bar']});
							  //google.charts.setOnLoadCallback(drawStuff);
							 google.charts.setOnLoadCallback(drawPie1Chart);
							
							 function drawPie1Chart() {
								 var dataTable = new google.visualization.DataTable();
								 dataTable.addColumn('string', 'Topping');
								 dataTable.addColumn('number', 'Slices');
							        
						
						/*  alert("Pending"+ data.totalPending);
						 alert("Processing"+ data.totalPending);
						 alert("Pending"+ data.totalPending);
						 alert("Pending"+ data.totalPending); */
								    
									   dataTable.addRows([

									             ['Pending', data.totalPending]

									           ]);
									   dataTable.addRows([

								             ['Processing', data.totalProcessing]

								           ]);
									   dataTable.addRows([

								             ['Closed', data.totalClosed]

								           ]);
									   dataTable.addRows([

								             ['Completed', data.totalCompleted]

								           ]);
									   

									   
							 var options = {'title':'Enquiry',
				                       'width':400,
				                       'height':250,is3D:true};
							 var chart = new google.visualization.PieChart(document.getElementById('Piechart'));
							 //document.getElementById("Piechart").style.border = "thin dotted red";
							      function selectHandler() {
						          var selectedItem = chart.getSelection()[0];
						          if (selectedItem) {
						            var topping = dataTable.getValue(selectedItem.row, 0);
						            //alert('The user selected ' + selectedItem.row,0);
						            i=selectedItem.row,0;
						            google.charts.setOnLoadCallback(drawBarChart);
						          }
						        }

						        google.visualization.events.addListener(chart, 'select', selectHandler);    
						        chart.draw(dataTable, options);
						         
						      }
							 
							 
										
							  	}); 
		 
			} 
</script>

</body>
</html>
