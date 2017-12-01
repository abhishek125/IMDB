    var textNames;
    function myFunction() {
    	 textNames = "";
         var x = document.getElementById("movieNames");
         var target=document.getElementById("files");
         if(x.files.length>0){
         target.innerHTML=x.files.length+" files selected";
         }
         else
         target.innerHTML="browse movies";
         target.innerHTML+="<input type=\"file\" id=\"movieNames\" onchange=\"myFunction()\" multiple/>";
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
    $scope.sortColumn="name";
    $scope.reverseSort=false;
    $scope.submitForm = function () {	
    	document.getElementById("loading").style.display = 'block';
    	imdbKey=document.getElementById("api").value;
    	console.log(imdbKey);
    	var data ={'textNames':JSON.stringify(textNames),'imdbKey':imdbKey};
    	$http.post( '/imdb/showrating.do', data)
    	.then(function(response){
    		document.getElementById("loading").style.display = 'none';
    		$scope.movies=response.data;
    		console.log($scope.movies);
    	});
    }
    });