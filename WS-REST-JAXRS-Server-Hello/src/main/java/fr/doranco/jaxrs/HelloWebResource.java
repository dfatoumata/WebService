package fr.doranco.jaxrs;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloWebResource {
	
	public HelloWebResource() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	public String getHello() {
		return "Bonjour Doranco";
	}
	
	@GET
	@Path("{name}-{id}")
	public String getHello(@PathParam("id") Integer id, @DefaultValue("INCONNU") @PathParam("name") String nom) {
		return "Bonjour " +nom +", tu portes le numero " +id;
	}

	@GET
	@Path("{name}")
	public String getHello(@DefaultValue("INCONNU") @PathParam("name") String nom) {
		return "Bonjour " +nom +" de la part de Fatou" ;
	}
	
	@GET
	@Path("nothing")
	public String getHello(@DefaultValue("INCONNU") @PathParam("nom") String nom, @PathParam("id") @DefaultValue("1") Integer id) {
		return "Bonjour " +nom +", qui portes le numero " +id;
	}
	
	@GET
	@Path("withHeaders-{name}-{id}")
	public Response getHelloWithHeaders(@DefaultValue("INCONNU") @PathParam("name") String nom, @PathParam("id") Integer id) {
		
		return Response.ok()
				.header("id", id)
				.entity("Bonjour " +nom +", qui portes l'id (voir l'entete)")
				.build();
	}
}
