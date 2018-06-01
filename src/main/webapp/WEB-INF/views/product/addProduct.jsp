

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



</head>
<body>

	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
	<c:url var="editFrSupplier" value="/editFrSupplier"></c:url>

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
						action="${pageContext.request.contextPath}/saveProduct" enctype="multipart/form-data">
						<input type="hidden" name="productId" id="productId"
							value="${product.prodId}">
                       <input type="hidden" name="isEdit" id="isEdit"
							value="${isEdit}">
							  <input type="hidden" name="image11" id="image11"
							value="${product.prodImage1}">
							  <input type="hidden" name="image22" id="image22"
							value="${product.prodImage2}">
							  <input type="hidden" name="image33" id="image33"
							value="${product.prodImage3}">
						<div class="col-md -3">

							<div class="col1title" align="left">
								<h3>Add Product</h3>
							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Product Name*:</div>
							</div>
							<div class="col-md-3">
								<input id="productName" class="form-control"
									placeholder="Product Name" value="${product.prodName}"
									style="text-align: left;" name="productName" type="text" required>
							
							</div>
							<div class="col-md-1"></div>

							<div class="col-md-2">
								<div class="col1title" align="left">Product Description*:
								</div>
							</div>
							<div class="col-md-3">
								<input id="productDescription" class="form-control"
									style="text-align: left;" placeholder="Product Description "
									value="${product.prodDesc}" name="productDescription" type="text"
									required>

							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Product Specification:</div>
							</div>
							<div class="col-md-3">
								<input id="productSpecif" class="form-control"
									placeholder="Product Specification" name="productSpecif"
									style="text-align: left;" value="${product.prodSpecification}"
									type="text" required>

							</div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Product Experty*:</div>
							</div>
							<div class="col-md-3">
								<input id="proExperty" class="form-control"
									placeholder="Product Experty " name="proExperty" type="text"
									style="text-align: left;" value="${product.prodExperty}"
									required>

							</div>

						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Product Image 1:</div>
							</div>
							<div class="col-md-3">
								 <input type="file" name="img1" id="img1" value="${url}${product.prodImage1}"/>
						<c:if test="${isEdit==1}">		 <a href="${url}${product.prodImage1}"
											data-lightbox="image-1"tabindex="-1"><c:out value='View Image1' /></a>
							</c:if></div>
							<div class="col-md-1"></div>
							<div class="col-md-2">
								<div class="col1title" align="left">Product Image 2:</div>
							</div>
							<div class="col-md-3">
								
	                         <input type="file" name="img2" id="img2" value="${url}${product.prodImage2}"/>
								<c:if test="${isEdit==1}">
								 <a href="${url}${product.prodImage2}"
											data-lightbox="image-1"tabindex="-1"><c:out value='View Image2' /></a></c:if>
							</div>
						</div>

						<div class="colOuter">
							<div class="col-md-2">
								<div class="col1title" align="left">Product Image 3:</div>
							</div>
							<div class="col-md-3">
								 <input type="file" name="image3" id="image3"  value="${url}${product.prodImage3}"/>
	                    <c:if test="${isEdit==1}">
	                      <a href="${url}${product.prodImage3}"
											data-lightbox="image-1"tabindex="-1"><c:out value='View Image3' /></a></c:if>
							
							</div>	
							
							
							</div>

						<div class="colOuter">
							<div align="center">
								<input name="submit" class="buttonsaveorder" value="Submit"
									type="submit" align="center">
								<!-- <input type="button" class="buttonsaveorder" value="Cancel" id="cancel" onclick="cancel1()" disabled> -->
							</div>

						</div>

						 <div id="table-scroll" class="table-scroll">
							<div id="faux-table" class="faux-table" aria="hidden"></div>
							<div class="table-wrap">
								<table id="table_grid" class="main-table">
                                
							<thead>
								<tr class="bgpink">
								
									<th class="col-sm-1">Sr No</th>
									<th class="col-md-1">Product</th> 
									<th class="col-md-1">Description</th>
									<th class="col-md-1">Specification</th>
									<th class="col-md-1">Image1</th>
									<th class="col-md-1">Action</th>
								</tr>
							</thead>
									
									<tbody>

										<c:forEach items="${productList}" var="productList"
											varStatus="count">
											<tr>
												<td class="col-sm-1"><c:out value="${count.index+1}" /></td>
												<td class="col-md-2"><c:out
														value="${productList.prodName}" /></td>
												<td class="col-md-1"><c:out
														value="${productList.prodDesc}" /></td>
												<td class="col-md-1"><c:out
														value="${productList.prodSpecification}" /></td>
															<td class="col-md-1"><img src="${url}${productList.prodImage1}" alt="No Image Available" /></td>
												<td class="col-md-1"><div>
														<a
															href="${pageContext.request.contextPath}/editProduct/${productList.prodId}"><abbr
															title='Edit'><i class='fa fa-edit'></i> </abbr></a> <a
															href="${pageContext.request.contextPath}/deleteProduct/${productList.prodId}"
															onClick="return confirm('Are you sure want to delete this record');">
															<abbr title='Delete'><i class='fa fa-trash'></i></abbr>
														</a>

													</div></td>
											</tr>
										</c:forEach>
								</table>

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

</body>
</html>
