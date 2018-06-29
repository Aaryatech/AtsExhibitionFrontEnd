

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>
	--%>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>
<!-- 
	<link rel="stylesheet"
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


	<c:url var="getInfoForFraph" value="/getInfoForFraph" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<style>
hr {
	display: block;
	margin-top: 0.5em;
	margin-bottom: 0.5em;
	margin-left: auto;
	margin-right: auto;
	border-style: inset;
	border-width: 10px;
}
</style>

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

					<form name="frm_search" id="frm_search" method="post"
						action="${pageContext.request.contextPath}/infoExhEmpEnqCount"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Employee Comparision Report</h3>
							</div>

						</div>

						<div class="colOuter">
							<!-- copy div kalpesh -->

							<div class="col-md-1" align="left">From Date:</div>
							<div class="col-md-2">
								<input id="fromdatepicker" class="texboxitemcode texboxcal"
									value="${fromDate}" name="fromDate" type="text">


							</div>
							<div class="col-md-1" align="left"></div>

							<div class="col-md-1" align="left">To Date:</div>
							<div class="col-md-2">
								<input id="todatepicker" class="texboxitemcode texboxcal"
									value="${toDate}" name="toDate" type="text">

							</div>

							<div class="col-md-1" align="left"></div>

							<input type="submit" class="btn  buttonsaveorder" value="Search"/> <input
								type="button" class="btn  buttonsaveorder" value="Graph"
								onclick="showChart()" />
						</div>

						<br> <br>
						<div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-sm-1">Sr No</th>
											<th class="col-md-1">Employee Name</th>
											<th class="col-md-1">No Of Enquiries</th>
											<th class="col-md-1">Completed</th>
											<th class="col-md-1">Pending</th>
											<th class="col-md-1">Processing</th>
											<th class="col-md-1">Closed</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${enqInfo}" var="enqInfo" varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-1"><c:out value="${enqInfo.empName}" /></td>

												<td class="col-md-1"><c:out value="${enqInfo.noOfEnq}" /></td>
												<td class="col-md-1"><c:out
														value="${enqInfo.completed}" /></td>
												<td class="col-md-1"><c:out value="${enqInfo.pending}" /></td>
												<td class="col-md-1"><c:out
														value="${enqInfo.processing}" /></td>
												<td class="col-md-1"><c:out value="${enqInfo.closed}" /></td>
										</c:forEach>
								</table>

							</div>
						</div>
						<div id="chart">
							<br> <br> <br>
							<div id="chart_div" style="width: 100%; height: 500px;"></div>
						</div>

					</form>
				</div>
			</div>
		</div>
		<!--rightSidebar-->

	</div>
	<!--fullGrid-->
	</div>
	<!--rightContainer-->

	</div>
	<!--wrapper-end-->

	<!--easyTabs-->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<!--easyTabs-->

	<!--wrapper-end-->
	<!--easyTabs-->
	<!--easyTabs-->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<!--easyTabs-->


	<script>
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
	</script>

	<script type="text/javascript">
		function searchSellBill() {
			document.getElementById('table').style.display = "block";
			document.getElementById('chart').style = "display:none";
			// document.getElementById('showchart').style.display = "block";
			$('#table_grid td').remove();

			var isValid = validate();

			if (isValid) {
				//	document.getElementById('btn_pdf').style.display = "block";
				var fromDate = document.getElementById("fromdatepicker").value;
				var toDate = document.getElementById("todatepicker").value;

				$
						.getJSON(
								'${getInfoForFraph}',
								{

									fromDate : fromDate,
									toDate : toDate,
									ajax : 'true',

								},
								function(data) {

									if (data == "") {
										alert("No records found !!");
										document.getElementById("expExcel").disabled = true;
									}

									var taxTotal = 0;
									var igstTotal = 0;
									var cgstTotal = 0;
									var sgstTotal = 0;
									var cessTotal = 0;

									$
											.each(
													data,
													function(key, exhEmpGraph) {

														document
																.getElementById("expExcel").disabled = false;
														document
																.getElementById('range').style.display = 'block';

														var tr = $('<tr></tr>');

														tr
																.append($(
																		'<td></td>')
																		.html(
																				key + 1));

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				sellTaxData.billDate));

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				exhEmpGraph.tax_per));

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				exhEmpGraph.tax_amount));
														taxTotal = taxTotal
																+ sellTaxData.tax_amount;

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				exhEmpGraph.igst));

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				exhEmpGraph.cgst));

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				exhEmpGraph.sgst));

														tr
																.append($(
																		'<td class="col-md-1"></td>')
																		.html(
																				exhEmpGraph.sess));

														$('#table_grid tbody')
																.append(tr);

													})

									var tr = "<tr>";
									var total = "<td colspan='3'>&nbsp;&nbsp;&nbsp;<b> Total</b></td>";

									var totalTax = "<td>&nbsp;&nbsp;&nbsp;<b>"
											+ taxTotal.toFixed(2);
									+"</b></td>";

									var igst = "<td><b>&nbsp;&nbsp;&nbsp;"
											+ igstTotal.toFixed(2);
									+"</b></td>";
									var cgst = "<td><b>&nbsp;&nbsp;&nbsp;"
											+ cgstTotal.toFixed(2);
									+"</b></td>";
									var sgst = "<td><b>&nbsp;&nbsp;&nbsp;"
											+ sgstTotal.toFixed(2);
									+"</b></td>";
									var cess = "<td><b>&nbsp;&nbsp;&nbsp;"
											+ cessTotal.toFixed(2);

									var trclosed = "</tr>";

									$('#table_grid tbody').append(tr);
									$('#table_grid tbody').append(total);

									$('#table_grid tbody').append(totalTax)

									$('#table_grid tbody').append(igst);
									$('#table_grid tbody').append(cgst);
									$('#table_grid tbody').append(sgst);
									$('#table_grid tbody').append(cess);
									$('#table_grid tbody').append(trclosed);

								});

			}
		}
	</script>
	<script type="text/javascript">
		function validate() {

			var fromDate = $("#fromdatepicker").val();
			var toDate = $("#todatepicker").val();

			var isValid = true;

			if (fromDate == "" || fromDate == null) {

				isValid = false;
				alert("Please select From Date");
			} else if (toDate == "" || toDate == null) {

				isValid = false;
				alert("Please select To Date");
			}
			return isValid;

		}
	</script>
	<script type="text/javascript">
		function showChart() {
			
			$("#chart_div").empty();

			document.getElementById('chart').style.display = "block";
			document.getElementById("table_grid").style = "display:none";

			var fromDate = document.getElementById("fromdatepicker").value;
			var toDate = document.getElementById("todatepicker").value;

			//document.getElementById('btn_pdf').style.display = "block";
			$
					.getJSON(
							'${getInfoForFraph}',
							{

								fromDate : fromDate,
								toDate : toDate,
								ajax : 'true',

							},
							function(data) {
								
								if (data == "") {
									alert("No records found !!");

								}
								var i = 0;

								google.charts.load('current', {
									'packages' : [ 'corechart', 'bar' ]
								});
								google.charts.setOnLoadCallback(drawStuff);

								function drawStuff() {

									var chartDiv = document
											.getElementById('chart_div');
									document.getElementById("chart_div").style.border = "thin dotted red";
									var dataTable = new google.visualization.DataTable();

									dataTable.addColumn('string',
											'Employee Name'); // Implicit domain column.
									dataTable.addColumn('number',
											'No. Of Enquiries'); // Implicit data column.

									dataTable.addColumn('number', 'Completed');

									dataTable.addColumn('number', 'Processing');

									dataTable.addColumn('number', 'Pending');

									dataTable.addColumn('number', 'Closed');
									$.each(data, function(key, item) {

										dataTable.addRows([

										[ item.empName, item.noOfEnq,
												item.completed,
												item.processing, item.pending,
												item.closed ]

										]);
									})

									var materialOptions = {
										width : 700,
										chart : {
											title : 'Employee Exhibitor Report',
											subtitle : 'Employee '
										},
										axes : {
											y : {
												distance : {
													label : ''
												}, // Left y-axis.
												brightness : {
													side : 'right',
													label : 'Employee Data'
												}
											// Right y-axis.
											}
										}
									};

									function drawMaterialChart() {
										var materialChart = new google.charts.Bar(
												chartDiv);
										materialChart
												.draw(
														dataTable,
														google.charts.Bar
																.convertOptions(materialOptions));
										// button.innerText = 'Change to Classic';
										// button.onclick = drawClassicChart;
									}

									drawMaterialChart();
								}
								;

							});

		}
	</script>

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

		function exportToExcel() {

			window.open("${pageContext.request.contextPath}/exportToExcel");
			document.getElementById("expExcel").disabled = true;
		}
	</script>
	<script type="text/javascript">
		</body>
		</html>
	