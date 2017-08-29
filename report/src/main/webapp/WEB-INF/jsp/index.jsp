<%-- use book.js --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="taglib/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Example</title>
<%-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/validationEngine.jquery.css">
</head>
<body>
	<div class="container">
		<%-- Make the ugly row in the thead disappear by removing the DataTables' CSS from the bundle
			menghilangkan warna hitam pada theme bootraps --%>
		<dandelion:asset cssExcludes="datatables" />
		<!--  -->
		<h1>Make A Report</h1>
		<div id="message"></div>
		<%@include file="modal/bookModal.jsp"%>
		<datatables:table id="bookTable" url="/getAllBook" serverSide="true"
			dom="0ftrip" sortable="false" processing="true" ext="responsive"
			theme="bootstrap3" reloadSelector="#reload" 
			cssClass="table-responsive table table-striped table-bordered table-condensed table-hover"
			cssStyle=" font-size : 12px; width:100% ; text-transform: capitalize;" rowIdBase="id" rowIdPrefix="book_">
			<datatables:column title="id" property="id" cssStyle="width:3%;" />
			<datatables:column title="name" property="name" filterable="true"
				filterType="select" />
			<datatables:column title="author" property="author" filterable="true"
				filterType="input" />
			<datatables:column title="Release Date" property="releaseDate"
				cssStyle="width:9%;" />
			<datatables:column title="price" property="price" searchable="false" default="Nothing to display!"
				cssStyle="width:9%;" />
			<datatables:column title="stock" property="stock"
				cssStyle="width:9%;" />
			<datatables:column title="" property="id"
				renderFunction="buttonAction" cssStyle="width:9%;" />
			<datatables:extraHtml uid="0" cssStyle="dataTables_filter">
				<button class="btn btn-primary" title="Add Data" data-toggle="modal"
					data-target="#myModal">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown" title="Print to Pdf">
						<span class="glyphicon glyphicon-print" aria-hidden="true"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="bookReport">PDF</a></li>
						<li><a href="bookReportPdf?nameReport=report&mode=pdf">PDF2</a></li>						
					</ul>
				</div>
				<div class="btn-group">
					<button class="btn btn-success dropdown-toggle" data-toggle="dropdown">Excel</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="bookReportExcel">Excel Jasper</a></li>
						<li><a href="bookReportExcelPoi">Excel Poi (XLSX)</a></li>
						<li><a href="#">Excel Poi (XLS)</a></li>
					</ul>
				</div>
				<button class="btn btn-primary" title="Refresh" id="reload">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
				</button>
			</datatables:extraHtml>
			<datatables:extraJs bundles="jquery-validation"
				placeholder="AFTER_ALL" />
		</datatables:table>
	</div>
</body>
</html>