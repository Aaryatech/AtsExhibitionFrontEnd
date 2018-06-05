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
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<!--topLeft-nav-->
<div class="sidebarOuter"></div>
<!--topLeft-nav-->

<!--wrapper-start-->
<div class="wrapper">

	<!--topHeader-->
	<c:url var="taskListByEmpId" value="/taskListByEmpId" />
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
					<h2 class="pageTitle">Pending Task List</h2>


				</div>
				<%-- <div class="col1title" align="right"> 
						<a href="${pageContext.request.contextPath}/purchaseBill"><input type="button" value="Purchase Bill" class="btn btn-info">
										</a>
										</div> --%>
				<form id="validation-form">

					<div class="colOuter">
						<!-- copy div kalpesh -->
							<div class="col-md-2" align="left">Select Employee</div>
						<div class="col-md-1">
							<select class="selectpicker" data-live-search="true"
								title="Please Select" name="empId" id="empId">
								<option value="0">All</option>
								<c:forEach items="${empList}" var="empList">
									<option value="${empList.empId}">${empList.empName}</option>

								</c:forEach>
							</select>
						</div>


						<button type="button" class="btn  buttonsaveorder"
							onclick="serchOtherBill()">Search</button>

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
								</table>
							</div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
									<thead>
										<tr class="bgpink">
											<th class="col-md-1">Sr No</th>
											<th class="col-md-1">Date</th>
											<th class="col-md-1">Employee Name</th>
											<th class="col-md-1">Task Description</th>
											<th class="col-md-1">Remark</th>
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

		var fromDate = $("#datepicker").val();
		var toDate = $("#datepicker2").val();

		var headeIdText = $("#headeIdText").val();
		alert(headeIdText);

		var isValid = true;
		if (headeIdText == "" || headeIdText == null) {
			isValid = false;
		} else if (fromDate == "" || fromDate == null) {

			isValid = false;
			alert("Please select From Date");
		} else if (toDate == "" || toDate == null) {

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

	function serchOtherBill() {

		var empId = document.getElementById("empId").value;
		$.getJSON('${taskListByEmpId}',

		{

			empId : empId,
			ajax : 'true'

		}, function(data) {

			$('#table_grid td').remove();

			if (data == "") {
				alert("No Record Found");
			} else {
				//document.getElementById("excel").disabled = false;
			}

			$.each(data, function(key, itemList) {

				var tr = $('<tr></tr>');

				tr.append($('<td></td>').html(key + 1));
				tr.append($('<td></td>').html(itemList.date));
				tr.append($('<td ></td>').html(itemList.empName));
				tr.append($('<td style="text-align:left;"></td>').html(
						itemList.taskDesc));
				tr.append($('<td style="text-align:left;"></td>').html(
						itemList.remark));

				$('#table_grid tbody').append(tr);

			})

		});

	}
	function exportToExcel() {

		window.open("${pageContext.request.contextPath}/exportToExcel");
		document.getElementById("excel").disabled = true;
	}

	function genPdf() {

		var fromDate = document.getElementById("datepicker").value;
		var toDate = document.getElementById("datepicker2").value;
		var empId = document.getElementById("empId").value;

		window.open('${pageContext.request.contextPath}/trackingHistoryPdf/'
				+ fromDate + '/' + toDate + '/' + empId);

	}
</script>




</body>
</html>