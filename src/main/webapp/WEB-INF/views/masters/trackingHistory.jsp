<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
table, th, td {
    border: 1px solid #9da88d;
}
</style>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

	<!--topHeader-->
	<c:url var="trackingHistoryBetweenDate" value="/trackingHistoryBetweenDate" /> 
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


			<!--leftNav-->



			<!--rightSidebar-->
			<div class="sidebarright">
				<div class="order-left">
					<h2 class="pageTitle">Tracking History</h2>
					

				</div>
				<%-- <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/purchaseBill"><input type="button" value="Purchase Bill" class="btn btn-info">
										</a>
										</div> --%>
				<form id="validation-form">

					<div class="colOuter">
						<!-- copy div kalpesh -->

						<div class="col-md-1" align="left">From:</div>
						<div class="col-md-2">
							<input id="datepicker" class="texboxitemcode texboxcal"
								value="${fromDate}" name="from_Date" type="text">
								
								 
						</div>
						  
						<div class="col-md-1" align="left">TO:</div>
						<div class="col-md-2">
							<input id="datepicker2" class="texboxitemcode texboxcal"
								value="${toDate}" name="to_Date" type="text">
								 
						</div>
						
						<div class="col-md-1" align="left">Select</div>
						<div class="col-md-1">
						<select class="selectpicker" data-live-search="true"  title="Please Select" 
															name="empId" id="empId"  > 
														 <option value="0">All</option>
																<c:forEach items="${empList}" var="empList">
																	<option value="${empList.empId}">${empList.empName}</option>
																
																</c:forEach>
															 </select>
															 </div>
						
						 
						 
					 

					</div>
					
					<div class="colOuter">
						   <button type="button" class="btn  buttonsaveorder"
								onclick="serchOtherBill()">Search</button>
								
						<button type="button" id="excel" class="btn  buttonsaveorder"
							onclick="exportToExcel()" disabled>Export To Excel</button>
						  
					 
						<button type="button" class="btn  buttonsaveorder" value="PDF" id="PDFButton"
							onclick="genPdf()"  >PDF</button>
					 
					</div>
						



					<!--tabNavigation-->
					<div class="cd-tabs">
						<!--tabMenu-->

						<!--tabMenu-->


						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden">
							<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											</tr>
									</thead>
									</table></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
										<th class="col-md-1">Sr No</th>
											<th class="col-md-1">Employee Name</th>
											<th class="col-md-1">Date</th>   
											<th class="col-md-1">Total KM</th>  
											<th class="col-md-1">Action</th>
										</tr>
									</thead>
									<tbody>
									 
									</tbody>

								</table>
							</div>
						</div>



					</div>
					<!--tabNavigation-->


				</form>
			</div>

			<!--rightSidebar-->

		</div>
		<!--fullGrid-->
	</div>
	<!--rightContainer-->

</div>
<!--wrapper-end-->
 
  
<script type="text/javascript">
		function validate() {
		
			var fromDate =$("#datepicker").val();
			var toDate =$("#datepicker2").val();
			
			var headeIdText=$("#headeIdText").val();
			alert(headeIdText);
			
			var isValid = true;
			if(headeIdText =="" || headeIdText == null){
				 isValid = false;
			}
			else if (fromDate == "" || fromDate == null) {

				isValid = false;
				alert("Please select From Date");
			}
		 else if (toDate == "" || toDate == null) {

				isValid = false;
				alert("Please select To Date");
			}
		
			return isValid;

		}
	</script>


<script>
	/*
//  jquery equivalent
jQuery(document).ready(function() {
   jQuery(".main-table").clone(true).appendTo('#table-scroll .faux-table').addClass('clone');
   jQuery(".main-table.clone").clone(true).appendTo('#table-scroll .faux-table').addClass('clone2'); 
 });
*/
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

function serchOtherBill()
{
	var fromDate=document.getElementById("datepicker").value;
	var toDate=document.getElementById("datepicker2").value;
	var empId=document.getElementById("empId").value;
	$
	.getJSON(
			'${trackingHistoryBetweenDate}',

			{
				 
				fromDate : fromDate,
				toDate : toDate,
				empId : empId,
				ajax : 'true'

			},
			function(data) {
				
				  	 
						 
				$('#table_grid td').remove(); 
				
				if(data=="")
					{
					alert("No Record Found");
					}
				else{
					document.getElementById("excel").disabled=false;
				}
				
				var totalkm=0;
				  
			  $.each(
							data,
							function(key, itemList) {
							

								var tr = $('<tr></tr>');
								 
								tr.append($('<td></td>').html(key+1));
								tr.append($('<td></td>').html(itemList.empName));
							  	tr.append($('<td ></td>').html(itemList.date));
							  	tr.append($('<td style="text-align:right;"></td>').html(itemList.totalKm));
							  	totalkm = totalkm + itemList.totalKm ;
							  	tr.append($('<td></td>').html('<a href="${pageContext.request.contextPath}/trackingHeaderWithDetail/'+itemList.trackId+'" class="action_btn" ><abbr title="Details"><i class="fa fa-list"></i></abbr></a> '));
						  		
							    $('#table_grid tbody').append(tr);
 

							})
							
							var tr = $('<tr></tr>');
				 
				tr.append($('<td colspan="3"></td>').html("Total"));
				 
			  	tr.append($('<td style="text-align:right;"></td>').html(totalkm));
			  	tr.append($('<td></td>').html(""));
			    $('#table_grid tbody').append(tr);
					 
				
			});
	
	
}
function exportToExcel() {

	window.open("${pageContext.request.contextPath}/exportToExcel"); 
	document.getElementById("excel").disabled=true;
}

function genPdf() {

	var fromDate=document.getElementById("datepicker").value;
	var toDate=document.getElementById("datepicker2").value;
	var empId=document.getElementById("empId").value;
	
	window.open('${pageContext.request.contextPath}/trackingHistoryPdf/'+ fromDate + '/' + toDate + '/' + empId);

}
	</script>




</body>
</html>