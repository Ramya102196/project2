/**
 * IndexService
 */
app.factory('IndexService',function($http){
	var indexService={}
	var BASE_URL="http://localhost:8085/Middleware"
indexService.getNotificationNotViewed=function()
{
		//select * from notification where username=? and viewed=0
return $http.get(BASE_URL + "/getnotification/"+0)		
}
	indexService.getNotificationViewed=function()
	{
			//select * from notification where username=? and viewed=1
	return $http.get(BASE_URL + "/getnotification/"+1)		
	}
	indexService.updateNotification=function(notificationId){
		return $http.put(BASE_URL + "/updatenotification/"+notificationId)	
	}
return indexService;
})

