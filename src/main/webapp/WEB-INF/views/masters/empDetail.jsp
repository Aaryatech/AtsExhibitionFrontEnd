

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>
	

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="isMobileNoExist" value="/isMobileNoExist"></c:url>

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
						action="${pageContext.request.contextPath}/editEmployee"
						enctype="multipart/form-data">

						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Edit Employee Detail</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Employee Name<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
								<input id="custName" class="form-control"
									placeholder="Employee Name" value="${empDetail.empName}"
									style="text-align: left;" name="empName" type="text"> <input
									id="empId" class="form-control" name="empId"
									value="${empDetail.empId}" type="hidden">

							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Employee Designation<b style="color: red">:*</b>
								</div>
							</div>
							<div class="col-md-3">
								<input id="empDesg" class="form-control"
									style="text-align: left;" placeholder="Employee Designation"
									value="${empDetail.empDesignation}" name="empDesg" type="text">

							</div>

						</div>

						<div class="colOuter">


							<div class="col-md-2">
								<div class="col1title" align="left">Employee Mobile No<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
								<input id="mob" class="form-control" placeholder="Mobile No"
									name="mob" style="text-align: left;" onchange="checkMobNo()"
									value="${empDetail.empMobile}" pattern="^\d{10}$" type="text">

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Employee E-Mail<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
								<input id="email" class="form-control" placeholder="Email"
									name="email" type="email" style="text-align: left;"
									value="${empDetail.empEmail}">

							</div>

						</div>

						<div class="colOuter">


							<div class="col-md-2">
								<div class="col1title" align="left">Employee Password<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
								<input id="empPwd" class="form-control"
									placeholder="Employee Password" name="empPwd"
									value="${empDetail.password}" style="text-align: left;"
									type="password" required>

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Employee Token<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
								<input id="token" class="form-control"
									placeholder="Employee Token" name="token" type="text"
									style="text-align: left;" value="${empDetail.token}" required>

							</div>

						</div>



						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Employee Education<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
								<input id="root" class="form-control"
									placeholder="Employee Education" name="education"
									style="text-align: left;" value="${empDetail.empEducation}"
									type="text">
							</div>
						</div>

						<div class="profile">
							<div class="col-md-2">
								<div class="col1title" align="left">Employee Photo<b style="color: red">:*</b></div>
							</div>
							<div class="col-md-3">
							<!-- 	<div class="profileinput">
									<div class="editimg">
 -->
										<input type="file" name="image" value="image" id="image"  onchange="readURL3(this);" value="${empDetail.empPhoto}"
									>
							</div>
							
								<div class="col-md-4"> Emp Photo
							
							    <img  id="blah3" src="${empImgUrl}${empDetail.empPhoto}" alt="No Image Availbable" width="100" height="100" />
							
							</div>
								<!-- 	</div>
								</div> -->
							</div>
						</div>

						<div class="colOuter">
							<div align="center">
								<input name="submit" class="buttonsaveorder" value= "Edit Employee"
									type="submit" align="center" id="submitButton">
										<input name="submit"
								class="buttonsaveorder" value="Cancel" type="button" onclick="clearText()">
								
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
							</div>

						</div>




					</form>


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
	<!--easyTabs-->


	<script>
		function edit(suppId) {

			$('#loader').show();

			$
					.getJSON(
							'${editFrSupplier}',

							{

								suppId : suppId,
								ajax : 'true'

							},
							function(data) {

								document.getElementById("suppId").value = data.suppId;
								document.getElementById("suppName").value = data.suppName;
								document.getElementById("suppAdd").value = data.suppAddr;
								document.getElementById("city").value = data.suppCity;
								document.getElementById("mob").value = data.mobileNo;
								document.getElementById("email").value = data.email;
								document.getElementById("gstnNo").value = data.gstnNo;
								document.getElementById("panNo").value = data.panNo;
								document.getElementById("liceNo").value = data.suppFdaLic;
								document.getElementById("creditDays").value = data.suppCreditDays;
								document.getElementById("isSameState").value = data.isSameState;
								document.getElementById("cancel").disabled = false;
							});

		}

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
<script >
		function readURL3(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();

	            reader.onload = function (e) {
	                $('#blah3')
	                    .attr('src', e.target.result)
	                    .width(100)
	                    .height(100);
	            };

	            reader.readAsDataURL(input.files[0]);
	        }
	    }
	    </script>
	    
	     <script >
		function checkMobNo() {
			document.getElementById("mob").style="color:black"

			var prevMobNo=${empDetail.empMobile};
			
			document.getElementById("submitButton").disabled=true;
			var mobNo=document.getElementById("mob").value;
			$
			.getJSON(
					'${isMobileNoExist}',
					{
						mobileNo : mobNo,
						callService : 1,
						ajax : 'true'

					},
					function(data) {

						var isSame=false;
						if(prevMobNo==mobNo){
						isSame=true;
						
						document.getElementById("submitButton").disabled=false;
						document.getElementById("mob").style="color:black"
						}
						if(isSame==false){
							
						document.getElementById("submitButton").disabled=false;
						document.getElementById("mob").style="color:black"
						
						 if(data==0){
							alert("Employee with this Mobile Number Already Registered. Please Enter Any Other Mobile No.");
							
							document.getElementById("mob").style="color:red"
							document.getElementById("mob").focus();
							document.getElementById("submitButton").disabled=true;

						}/* else{
							//document.getElementById("submitButton").disabled=false;
							//document.getElementById("mob").style="color:black"
							//alert("In else ");

							//alert("In Else")
						} */
						}
					});
			document.getElementById("mob").style="color:black"

	    }
	    </script>
	    <script>
	function clearText() {
		window.open('${pageContext.request.contextPath}/addEmployee','_self');
		//window.close();
	}
	</script>
	    
</body>
</html>
