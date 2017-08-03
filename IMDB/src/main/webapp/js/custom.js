var txt = "";
function myFunction() {
     var x = document.getElementById("movieNames");

     if ('files' in x) {

       for (var i = 0; i < x.files.length; i++) {
         var file = x.files[i];
         if ('name' in file) {
           txt += file.name + "<br>";
         }

       }
     }
     console.log("hey"+txt);
     //document.getElementById("names").innerHTML = txt;
   }
var myApp=angular.module("myModule",[]);
myApp.controller("myController",function($scope,$http){
$scope.submitForm = function () {	
	console.log("testing ");
	$http.post( '/imdb/showrating.do', txt)
	.then(function(response){
		$scope.users=response.data;
		console.log($scope.users);
	}).catch(function(response) {
		  console.error('Error occurred:', response.status, response.data);
	}).finally(function() {
		 console.log("Task Finished.");
	});
}
});
  
