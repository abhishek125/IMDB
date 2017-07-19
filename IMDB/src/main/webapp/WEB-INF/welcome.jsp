<!-- The superclass "javax.servlet.http.HttpServlet" was not found
 on the Java Build Path. To fix the problem, right click on project
  -> Properties -> Java Build Path -> Add Library...-> Server Runtime -> 
  Apache Tomcat -> Finish. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onchange="myFunction()">
	<%-- <!-- just get the data from controller and present to the user -->
<%= request.getAttribute("welcome ")	 %>
 --%>
	<br />
	<script type="text/javascript">
		function myFunction() {
			var x = document.getElementById("movieNames");
			var txt = "";
			if ('files' in x) {

				for (var i = 0; i < x.files.length; i++) {
					var file = x.files[i];
					if ('name' in file) {
						txt += file.name + "<br>";
					}

				}
			}

			document.getElementById("names").innerHTML = txt;
		}
	</script>
	<form action="showrating.do" method="post">
		<input type="file" id="movieNames" onchange="myFunction()" multiple />
		<textarea rows="10" cols="10" id="textNames" name="textNames"></textarea>
		<textarea rows="10" cols="10" id="names" name="names"
			style="display: none;"></textarea>
		<input type="submit" value="submit" />
	</form>

</body>
</html>