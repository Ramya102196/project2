/**
 *blog service 
 */
app.factory('BlogService',function($http)
		{
	var blogService={}
	var BASE_URL="http://localhost:8085/Middleware"
		blogService.saveBlog=function(blog)
		{
		return $http.post(BASE_URL + "/saveblog",blog)
		}
	blogService.saveBlog=function(blog)
	{
	return $http.post(BASE_URL + "/getblogs/",+1)
	}
	blogService.saveBlog=function(blog)
	{
	return $http.post(BASE_URL + "/getblogs/",+0)
	}
	return blogService;
		})