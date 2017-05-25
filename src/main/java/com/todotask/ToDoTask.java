package com.todotask;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todotask.dao.TaskDAO;
import com.todotask.model.Task;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("todotask")
public class ToDoTask {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//        return "Got it!";
//    }
    
    @GET
    @Produces("application/json")
    public List<Task> getTasks() {
        TaskDAO dao = new TaskDAO();
        List tasks = dao.getTasks();
        return tasks;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTask(@PathParam("id") int id){
    	if(id<0){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
    	
        TaskDAO dao = new TaskDAO();
        Task task = dao.getTask(id);
        return Response.status(200).entity(task).build();
    }
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addTask(Task task){
    	task.setSubject(task.getSubject());
    	task.setDetail(task.getDetail());
    	task.setStatus(task.getStatus());
    	TaskDAO dao = new TaskDAO();
    	dao.addTask(task);
//        System.out.println(Response.ok().build().toString());
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateTask(@PathParam("id") int id, Task task){
    	if(id<0){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
        TaskDAO dao = new TaskDAO();
        int count = dao.updateTask(id, task);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    

    
    
    @PUT
    @Path("/status/{id}")
    @Consumes("application/json")
    public Response setStatus(@PathParam("id") int id, Task task){
    	if(id<0){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
        TaskDAO dao = new TaskDAO();
        int count = dao.setStatus(id, task);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") int id){
    	if(id<0){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
        TaskDAO dao = new TaskDAO();
        int count = dao.deleteTask(id);
        System.out.println("cont : "+count );
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
}
