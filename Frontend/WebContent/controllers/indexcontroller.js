/**
 * IndexController
 *
 */

app.controller('IndexController',function($rootScope,$location,IndexService){
	function getNotification(){
		IndexService.getNotificationNotViewed().then(function(response){
			$rootScope.notificationNotViewed=response.data //select * from notification where username=? and viewed=0
			$rootScope.notificationNotViewedLength=$rootScope.notificationNotViewed.length
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
		IndexService.getNotificationViewed().then(function(response){
			$rootScope.notificationViewed=response.data //select * from notification where username=? and viewed=1
			},function(response){
				if(response.status==401)
					$location.path('/login')
			})
		}
		
	if($rootScope.currentUser!=undefined)
	getNotification()
	$rootScope.updateLength=function(){
	$rootScope.notificationNotViewedLength=0
	}
	$rootScope.updateNotification=function(notificationId)
	{
		IndexService.updateNotification(notificationId).then(function(response){
			getNotification()
			
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
})