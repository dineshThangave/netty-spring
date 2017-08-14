package com.example.httpserver.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Controller;

import com.example.httpserver.controller.request.Employee;
import com.example.httpserver.protobuf.ProtoBufferHandler;

@Controller
@Path("/employee")
public class EmployeeController {
    
	ProtoBufferHandler protoBufferHandler;

    @POST
    @Path("/add")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Employee save(@FormParam("firstname") String firstname,@FormParam("lastName") String lastName,
                        @FormParam("id") int id,@FormParam("salary") double salary) {
    	protoBufferHandler=new ProtoBufferHandler();
    	protoBufferHandler.insertValues(firstname, lastName, id,salary);
		return new Employee(firstname, lastName, id,salary);
	}


}
