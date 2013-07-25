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
	<table style="height: 100%;" width="100%" border="0">
		<tr style="height: 10%">
			<td valign="top">
				<table border="0" width="100%">
					<tbody>
						<tr>
							<td><a href="http://www.pdvsa.com/"> <img
									src="images/imagenes_02.gif" />
							</a></td>
						</tr>
						<tr>
							<td><img src="images/spacer.gif" alt="" height="2" /></td>
						</tr>

						<tr>
							<td style="background-color: red;" height="5"><img
								src="images/spacer.gif" alt="" height="2" /></td>
						</tr>

					</tbody>
				</table>
			</td>
		</tr>
		<tr style="height: 80%">
			<td><decorator:body /></td>
		</tr>
		<tr style="height: 5%">
			<td style="background-color: rgb(200, 0, 0);" 
							align="center"><span style="color: white">
				PDVSA,
								Comercio y Suministro</span> </a> </span>
			</td>
		</tr>
	</table>
</body>
</html>
