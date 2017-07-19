<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	page import="org.abhi.spring.model.Movie"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>name</td>
			<td>year</td>
			<td>rating</td>
			<td>votes</td>
			<td>metascore</td>
			<td>Plot</td>
		</tr>
		<%
			Movie[] movies = (Movie[]) request.getAttribute("movies");
			for (int i = 0; i < movies.length; i++) {
		%>
		<tr>
			<td><%=movies[i].getName()%></td>
			<td><%=movies[i].getYear()%></td>
			<td><%=movies[i].getRating()%></td>
			<td><%=movies[i].getVotes()%></td>
			<td><%=movies[i].getMetascore()%></td>
			<td><%=movies[i].getPlot()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>