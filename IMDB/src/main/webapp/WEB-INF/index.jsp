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
    <title>Top navbar example for Bootstrap</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
    <!-- Custom styles for this template -->
    <script>
    var textNames = "";
    function myFunction() {
         var x = document.getElementById("movieNames");

         if ('files' in x) {

           for (var i = 0; i < x.files.length; i++) {
             var file = x.files[i];
             if ('name' in file) {
               textNames += file.name + "<br>";
             }

           }
         }
         //document.getElementById("names").innerHTML = textNames;
       }
    var myApp=angular.module("myModule",[]);
    myApp.controller("myController",function($scope,$http){
    $scope.submitForm = function () {	
    	console.log("asda");
    	$http.post( '/imdb/showrating.do', textNames)
    	.then(function(response){
    		$scope.users=response.data;
    		//console.log($scope.users);
    	});
    }
    });
      
    </script>
    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
  

</head>
<body >
  <div class="container">
    <!-- Static navbar -->
      <nav class="navbar navbar-default ">
          <div class="container-fluid">
              <div class="navbar-header col-md-1">
                  <a class="navbar-brand" href="#">IMDB</a>
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                <div class="col-md-3">
                  <input type="text" class="form-control btn-bs-file" placeholder="search"/>
                </div>
              <div class="col-md-2">
                <label class="btn-bs-file btn btn-primary">
                  Browse movies
                  <input type="file" id="movieNames" onchange="myFunction()" multiple/>
                </label>
                <%-- <form action="showrating.do" method="post" style="display: none;" id="movieForm">
                  <textarea id="names" name="names"></textarea>
                </form> --%>
              </div>
              <div class="col-md-1">
                <input type="button" id="movieNames" value="get rating" ng-click="submitForm()" class=" btn btn-primary btn-bs-file"/>
              </div>
              <div class="col-md-2 ">
                <input type="text" id="api" value="" class="btn-bs-file form-control" placeholder="optional omdb api key"/>
              </div>
              <div class="form-group col-md-3 btn-bs-file">
                <select class="form-control" id="sel1">
                  <option>sort by</option>
                  <option>by rating</option>
                  <option>by name</option>
                  <option>by votes</option>
                  <option>by year</option>
                </select>
              </div>
            </div>
          <!--/.nav-collapse -->
        </div>
      <!--/.container-fluid -->
      </nav>
      <table class="table table-striped">
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
    		<tbody  ng-controller="myController">
    		<tr ng-repeat="movie in movies">
    		<td>
    						<a href="http://www.imdb.com/title/{{movie.id}}/?ref_=fn_tt_tt_1">{{movie.id}}</a>
    					</td>
    					<td>{{movie.name}}</td>
    					<td>{{movie.year}}</td>
    					<td>{{movie.rating}}</td>
    					<td>{{movie.votes}}</td>
    					<td>{{movie.plot}}</td>
    					<td>
    		</tr>


    		</tbody>
    	</table>
    </div>
<!-- /container -->
  </body>
</html>
