/**
 * JobService
 */

app.factory('JobService',function($http){
       var jobService={}
       
       jobService.addJob=function(job)
       {
    	   return $http.post("http://localhost:8085/Middleware/savejob",job);
       }
       jobService.getAllJobs=function()
       {
    	   return $http.get("http://localhost:8085/Middleware/alljobs");
       }
       jobService.getJob=function(jobId)
       {
    	   return $http.get("http://localhost:8085/Middleware/getjob/"+jobId)
       }
        return jobService;
})
