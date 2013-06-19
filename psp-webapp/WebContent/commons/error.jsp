<%@ page language="java" isErrorPage="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
    <title>Error</title>
</head>

<body>
 <% if (exception != null) { %>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
 <% } else if ((Exception)request.getAttribute("javax.servlet.error.exception") != null) { %>
    <pre><% ((Exception)request.getAttribute("javax.servlet.error.exception"))
                           .printStackTrace(new java.io.PrintWriter(out)); %></pre>
 <% } %>
</body>
</html>
