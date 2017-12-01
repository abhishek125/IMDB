<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="myModule" lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>multithreaded api for movie rating</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
    <!-- Custom styles for this template -->
    <script>

      
    </script>
    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
  

</head>
<body  ng-controller="myController" >
  <div class="container">
    <!-- Static navbar -->
      <nav class="navbar navbar-default ">
          <div class="container-fluid">
              <div class="navbar-header col-md-1">
                  <a class="navbar-brand" href="#">IMDB</a>
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                <div class="col-md-3">
                  <input type="text" class="form-control btn-bs-file" placeholder="search" ng-model="searchText.name"/>
                </div>
              <div class="col-md-2">
                <label class="btn-bs-file btn btn-primary" id="files">
                  Browse movies
                  <input type="file" id="movieNames" onchange="myFunction()" multiple/>
                </label>
                <%-- <form action="showrating.do" method="post" style="display: none;" id="movieForm">
                  <textarea id="names" name="names"></textarea>
                </form> --%>
              </div>
              <div class="col-md-1">
              <button id="movieNames" value="get rating" ng-click="submitForm()" class=" btn btn-primary btn-bs-file">get rating</button>
              </div>
              <div class="col-md-2 ">
                <input type="text" id="api" value="" class="btn-bs-file form-control" placeholder="optional omdb api key"/>
              </div>
              <div class="form-group col-md-3 btn-bs-file">
                <select class="form-control" id="sortBy" ng-model="sortColumn">
                  <option value="name">by name</option>
                  <option value="rating">by rating</option>
                  <option value="votes">by votes</option>
                  <option value="year">by year</option>
                </select>
              </div>
            </div>
          <!--/.nav-collapse -->
        </div>
      <!--/.container-fluid -->
      </nav>
      <img src="${pageContext.request.contextPath}/resources/images/loading.gif" id="loading" class="loading" />
      <table class="table table-striped table-bordered">
    		<thead>
    			<tr>
            		<th>imdb id</th>
    				<th>Name</th>
    				<th>year</th>
    				<th>rating</th>
    				<th>votes</th>
    				<th>plot</th>
    			</tr>
    		</thead>
    		<tbody >
    		<tr ng-repeat="movie in movies | orderBy:sortColumn:reverseSort | filter:searchText">
    		<td><a href="http://www.imdb.com/title/{{movie.imdbId}}/?ref_=fn_tt_tt_1">{{movie.imdbId}}</a></td>
    		<td>{{movie.name}}</td>
			<td>{{movie.year}}</td>
			<td>{{movie.rating}}</td>
			<td>{{movie.votes}}</td>
			<td>{{movie.plot}}</td>
    		</tr>


    		</tbody>
    	</table>
    </div>
<!-- /container -->
  </body>
</html>
