/**
 * FriendController
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function getAllSuggestedUsers()
	{
		FriendService.getAllSuggestedUsers().then(function(response){
			$scope.suggestedusers=response.data //A-(BUC) query
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
	}
	function getAllPendingRequests(){
		FriendService.getAllPendingRequests().then(function(response){
			$scope.pendingrequests=response.data //select * from friend where toid=? and status='P' [list of pending]
		},function(response){
			if(response.status==401)
				$locatioon.patn('/login')
		})
	}
	
	function getListOfFriends(){
		FriendService.getListOfFriends().then(function(response){
			$scope.friends=response.data
			},function(response){
				if(response.status==401)
					$locatioon.patn('/login')

			
		})
	}
	$scope.addFriendRequest=function(toId){
		FriendService.addFriendRequest(toId).then(function(response){
			alert('Friend request successfully sended')
		getAllSuggestedUsers()
		},function(response){
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	//pendingrequest-friend obj with status 'P'
	//updatedstatus-char [A/D] from pendingrequest.html
	$scope.updatePendingRequest=function(pendingrequest,updatedstatus)
	{
		pendingrequest.status=updatedstatus
		//pendingrequest-friend object with status as 'A'/'D'
		FriendService.updatePendingRequest(pendingrequest).then(function(response){
			getAllPendingRequests()	
		},function(response){
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	getAllSuggestedUsers()
	getAllPendingRequests()
	getListOfFriends()
})