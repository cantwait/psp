<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@include file="/commons/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title><decorator:title default="PDVSA - PSP" /></title>
<link href="css/estilos_pdvsa.css" rel="stylesheet" type="text/css"></link>
<decorator:head />
</head>

<body>
<center>
<table style="height: 100%;" width="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tbody>
		<tr>
			<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tbody>
								<tr>
									<td valign="top" width="190" height="56"><a
										href="http://www.pdvsa.com/"><img
										src="images/imagenes_02.gif" border="0" /></a></td>
								</tr>
								<tr>
									<td height="12"><img src="images/spacer.gif" alt=""
										height="10" width="10"></td>
								</tr>

								<tr>
									<td style="background-color: red;" height="2"><img
										src="images/spacer.gif" alt="" height="2" width="10"></td>
								</tr>

							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table style="height: 100%;" width="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tbody>
					<tr>
						<td valign="top"></td>
					</tr>
					<tr>
						<td style="height: 100%;" valign="top"><decorator:body /></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td style="height: 36px;"><!-- Bottom b -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td style="background-color: red;" height="2"><img
							src="images/spacer.gif" alt="" height="2" width="10"></td>
					</tr>
					<tr>
						<td style="background-color: rgb(200, 0, 0);" height="34px"
							align="center"><span style="color: white">PDVSA, Comercio y Suministro</span> </a> </span></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>
