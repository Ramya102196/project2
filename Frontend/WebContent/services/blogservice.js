/**
 *Blog Service 
 */
app.factory('BlogService',function($http)
		{
	var blogService={}
	var BASE_URL="http://localhost:8085/Middleware"
		blogService.saveBlog=function(blog)
		{
		return $http.post(BASE_URL + "/saveblog",blog)
		}
	//select * from blogpost where approved=1 
	blogService.getBlogsApproved=function()
	{
	return $http.get(BASE_URL + "/getblogs/"+1)
	}
	//select * from blogpost where approved=0
	blogService.getBlogsWaitingForApproval=function()
	{
	return $http.get(BASE_URL + "/getblogs/"+0)
	}
	
	blogService.getBlogPost=function(id){
		return $http.get(BASE_URL + "/getblog/" +id)
	}
	blogService.updateBlogPost=function(blogPost,rejectionReason){
		return $http.put(BASE_URL + "/updateapprovalstatus?rejectionReason="+rejectionReason,blogPost)
	}
	return blogService;
		})