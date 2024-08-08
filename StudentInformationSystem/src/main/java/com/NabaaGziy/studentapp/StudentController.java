package com.NabaaGziy.studentapp;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/students")
public class StudentController {
	@GET
	@Path("/totalApiCalls")
	public Response getTotalApiCalls(@Context ServletContext ctx) {
		Integer counter =  (Integer) ctx.getAttribute("apiCallCounter")

		/*
		 * retrieve counter from application context. Hint: You can inject the
		 * ServletContext in your REST method, similar to how you inject
		 * HttpServletRequest in doGet in the previos labs.
		 */;
		if (counter == null) {
			counter = 0; // Handle the case where the counter attribute is not set
		}
		return Response.ok(counter.toString()).build();
	}

	private StudentDAO studentDAO = new StudentDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudent() {
		return studentDAO.readAll();
	}

	/*
	 * GET: Retrieve data • POST: Create data • PUT: Update data • DELETE: Delete
	 * data
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createStudent(Student student) {
		studentDAO.create(student);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("id") int id) {
		return studentDAO.read(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateStudent(@PathParam("id") int id, Student student) {
		studentDAO.update(id, student);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("id") int id) {
		studentDAO.delete(id);
	}

}
