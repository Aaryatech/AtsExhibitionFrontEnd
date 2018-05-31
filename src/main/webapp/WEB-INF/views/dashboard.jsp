

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

 

</head>
<body>
 --%>

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>

<!--datepicker-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
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
					<div class="colOuter">
					</div>
						
				<div class="colOuter">
						<!-- copy div kalpesh -->

				<div class="col-md-2" align="left"></div>
				
				
						<div class="col-md-2" >
						 <h3><a href="${pageContext.request.contextPath}/showEventHistory">
						   ${eventList.size()} <br> Total Event
						  </a></h3>  
						 <p><a href="${pageContext.request.contextPath}/showEventHistory">  </a></p>
								</div>
								
								
						<div class="col-md-2">
							<h3><a   href="${pageContext.request.contextPath}/showVisitorLikesList">
   							 ${visitorList.size()} <br> Total Visitor 
  							</a></h3> 
								 
						</div>
						
						<div class="col-md-2">
							 <h3><a   href="${pageContext.request.contextPath}/showFbQueTxn">
							    ${feedbackList.size()} <br> Total Feedback
							  </a></h3> 
								 
						</div>
						
						  
					</div>	     
					
					  <div class="colOuter">
						<!-- copy div kalpesh -->

				<div class="col-md-2" align="left"></div>
				
				
						<div class="col-md-2" >
						 <h3> <a   href="${pageContext.request.contextPath}/showEnquiryBetweenDates">
   							${enquiryList.size()} <br> Total Enquiry
  								</a></h3> 
								</div>
								
								<div class="col-md-2" >
						 <h3> <a   href="${pageContext.request.contextPath}/enquiryList/1">
   							${pending.size()} <br> Total Pending Enquiry
  								</a></h3> 
								</div>
								
								<div class="col-md-2" >
						 <h3> <a   href="${pageContext.request.contextPath}/enquiryList/2">
   							${processing.size()} <br> Total Processing Enquiry
  								</a></h3> 
								</div>
							 
						  
					</div>	 
					
					<div class="colOuter">
					 

				<div class="col-md-2" align="left"></div>
				
				
						<div class="col-md-2" >
						 <h3> <a   href="${pageContext.request.contextPath}/enquiryList/4">
   							${closed.size()} <br> Total Closed Enquiry
  								</a></h3> 
								</div>
								
								<div class="col-md-2" >
						 <h3> <a   href="${pageContext.request.contextPath}/enquiryList/5">
   							${completed.size()} <br> Total Completed Enquiry
  								</a></h3> 
								</div>
								
								 
								<div   class="col-md-4"  align="center" id="chart" style="display: none"><br><br><br>
								 <div >
	  
			<div  id="Piechart" style="width:40%%; height:300; float: right;" ></div> 
			</div>
			 
			 
				 
				</div>
			</div>
			
			<hr>
							 
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
							        
						
						 
								    
									   dataTable.addRows([

									             ['Pending', data.totalPending]

									           ]);
									   dataTable.addRows([

								             ['Processing', data.TotalProcessing]

								           ]);
									   dataTable.addRows([

								             ['Closed', data.totalClosed]

								           ]);
									   dataTable.addRows([

								             ['Completed', data.totalCompleted]

								           ]);
									   

									   
							 var options = {'title':'Enquiry',
				                       'width':400,
				                       'height':250};
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
