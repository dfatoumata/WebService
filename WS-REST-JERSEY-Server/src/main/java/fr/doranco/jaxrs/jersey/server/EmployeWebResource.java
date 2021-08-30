package fr.doranco.jaxrs.jersey.server;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.doranco.jaxrs.jersey.dao.EmployeDao;
import fr.doranco.jaxrs.jersey.entity.Employe;
import fr.doranco.jaxrs.utils.Strings;


@Path("employes")
@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
public class EmployeWebResource implements IEmployeWebResource{

	public EmployeWebResource() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@GET
	public String getInfo() {
		return "vous avez appelé le web services REST 'employes' pour récuperer des infos." ;
	}
	
	
	@Override
	@GET
	@Path("employe-{id}-XML")
	@Produces(MediaType.APPLICATION_XML + CHARSET)
	public String getEmployeXML(@PathParam("id") @DefaultValue("1") Integer id) {
		EmployeDao employeDao = new EmployeDao();
		Employe employe=null;
		try {
			employe = employeDao.getEmployeById(id);
		} catch (Exception e) {
			System.out.println(e);

		}
		return Strings.getEmployeAuFormatXmlString(employe);
	}

	@Override
	@GET
	@Path("employe-{id}-JSON")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)	
	public String getEmployeJSON(@PathParam("id") Integer id) {
		EmployeDao employeDao = new EmployeDao();
		Employe employe=null;
		try {
			employe = employeDao.getEmployeById(id);
		} catch (Exception e) {
			System.out.println(e);
		}
		return Strings.getEmployeAuFormatJsonString(employe);
	}

	@Override
	@GET
	@Path("employe-{id}")
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public Response getEmployeJSONtoResponse(@PathParam("id") Integer id) {
		EmployeDao employeDao = new EmployeDao();
		Employe employe=null;
		try {
			employe = employeDao.getEmployeById(id);
		} catch (Exception e) {
			System.out.println(e);
		}	
		return Response.ok().entity(employe).build();
	}

	@Override
	@GET
	@Path("liste")
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	@Consumes({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public List<Employe> getEmployes() {
		EmployeDao employeDao = new EmployeDao();
		
		try {
			return employeDao.getEmployes();
		} catch (Exception e) {
			System.out.println(e);
			return new ArrayList<Employe>();
		}
	}

	@Override
	@POST
	@Path("add")
	@Produces({ MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Consumes({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public Response addEmploye(Employe employe) throws URISyntaxException {
		EmployeDao employeDao = new EmployeDao();
		if (employe == null || employe.getNom() == null || employe.getNom().isEmpty() 
				|| employe.getPrenom() == null || employe.getPrenom().isEmpty() 
				|| employe.getPosteOccupe() == null || employe.getPosteOccupe().isEmpty() ) {

			throw new IllegalArgumentException("Les paramètres ne doivent pas être nuls ou vides !");
		}
		
		try {
			Employe emp = employeDao.addEmploye(employe);
			return Response.status(Status.CREATED).entity(emp).build();
		} catch (Exception e) {
			System.out.println(e);
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Erreur technique !\n" + e.getMessage())
					.build();
		}
	}

	@Override
	@PUT
	@Path("update")
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	@Consumes({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public Response updateEmploye(Employe employe) throws URISyntaxException {
		EmployeDao employeDao = new EmployeDao();
		if (employe == null || employe.getNom() == null || employe.getNom().isEmpty() 
				|| employe.getPrenom() == null || employe.getPrenom().isEmpty() 
				|| employe.getPosteOccupe() == null || employe.getPosteOccupe().isEmpty() ) {

			throw new IllegalArgumentException("Les paramètres ne doivent pas être nuls ou vides !");
		}
		try {
			employeDao.updateEmploye(employe);
			
			return Response.status(Status.ACCEPTED).entity("employé" +employe.toString()+"mise à jour avec succès").build();
		} catch (Exception e) {
			System.out.println(e);
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Erreur technique !\n" + e.getMessage())
					.build(); 
		}		

	}

	@Override
	@DELETE
	@Path("remove-{id}")
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	@Consumes({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public Response removeEmploye(@PathParam("id") Integer id) throws URISyntaxException {
		EmployeDao employeDao = new EmployeDao();
		if (id ==null || id <= 0) {
			throw new IllegalArgumentException("L'id doit être positif !");
		}
		try {
			employeDao.removeEmploye(id);
			return Response.status(Status.ACCEPTED).entity("employé avec l'id " +id +" supprimé avec succès").build();
		} catch (Exception e) {
			System.out.println(e);
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Erreur technique !\n" + e.getMessage())
					.build();
		}
	}



}
