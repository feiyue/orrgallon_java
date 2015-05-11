<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getServletContext().getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>/static/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="<%=basePath%>/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=basePath%>/static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/bootstrap/js/bootstrap.min.js"></script>
<title>explainplan</title>
<script type="text/javascript">
	function submitForm(actionType){
		$("#myform").attr("action", "<%=basePath%>/"+actionType);
		$("#myform").submit();
	}
</script>
</head>
<jsp:include page="/pages/template/header.jsp"></jsp:include>
<body style="padding-top: 60px;">
	<div class="container-fluid">
		<form action="" id="myform" method="post">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">查看执行计划类型</h3>
						</div>
						<div class="panel-body">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-6">
										<p>
											<button type="button" class="btn btn-default btn-sm"  onclick="submitForm('explainplan')">ExplainPlan</button>
											<button type="button" class="btn btn-default btn-sm" onclick="submitForm('displaycursor')">DisplayCursor</button>
											<button type="button" class="btn btn-default btn-sm" onclick="submitForm('DisplayCursor')">10046Trace</button>
										</p>
									</div>
									<div class="col-md-6">
										<!-- Single button -->
										<div class="btn-group"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">输入SQL</h3>
						</div>
						<div class="panel-body">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-12">
										<textarea class="form-control" rows="10" id="inputsql" name="inputsql"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>