/**
 * Created by Apple on 22/09/2016.
 */
/*document.write("<script language=javascript src='../../bower_components/angular/angular.js'></script>");*/
console.log("1111111111");
var app = angular.module('myApp', [])
app.controller('contr', ["$scope", function ($scope) {
    $scope.tName = "hell";
}]);
