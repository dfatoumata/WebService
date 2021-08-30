package fr.doranco.jaxrs.jersey.server;


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
import javax.ws.rs.core.Response.Status;

import fr.doranco.jaxrs.jersey.entity.Utilisateur;


@Path("users")
@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
public class UtilisateurWebResource implements IUtilisateurWebResource {

	@Override
	@GET
	public String getInfos() {
		return "Web Services for 'Utilisateur'";
	}

	@Override
	@GET
	@Path("get/{identifiant}")
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response getUtilisateur(@PathParam("identifiant") Integer id) {
		if (id <= 0) {
			return Response.status(Status.BAD_REQUEST)
					.entity("L'id de l'utilisateur à récupérer doit être > 0 !")
					.build();
		}
		// bouchon
		Utilisateur user = new Utilisateur("Toto", "titi");  // utilisateurDao.getUtilisateur(id);
		user.setId(id);
		//return Response.status(Status.OK).entity(user).build();
		return Response.ok(user).build();
	}

	@Override
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response addUtilisateur(Utilisateur utilisateur) {
		
		if (utilisateur == null) {
			return Response.status(Status.NO_CONTENT)
						.entity("L'utilisateur à ajouter ne doit pas être nul !")
						.build();
		}
		if (utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty()
				|| utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty()) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Tous les attributs del'utilisateur à ajouter sont obligatoires !")
					.build();
		}
		// bouchon
		utilisateur.setId(1);	// utilisateurDao.add(utilisateur);
		return Response.status(Status.CREATED).entity(utilisateur).build();
	}

	@Override
	@POST
	@Path("adds")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response addUtilisateurs(List<Utilisateur> utilisateurs) {
		
		if (utilisateurs == null || utilisateurs.isEmpty()) {
			return Response.status(Status.NO_CONTENT)
						.entity("L'utilisateur à ajouter ne doit pas être nul !")
						.build();
		}
		for (Utilisateur utilisateur : utilisateurs) {
			if (utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty()
					|| utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty()) {
				
				return Response.status(Status.BAD_REQUEST)
						.entity("Tous les attributs del'utilisateur à ajouter sont obligatoires !")
						.build();
			}
			// bouchon
		}

		int i =1;
		for (Utilisateur utilisateur : utilisateurs) {
			utilisateur.setId(i++);	// utilisateurDao.add(utilisateur);
			
		}
		return Response.status(Status.CREATED).entity(utilisateurs).build();

	}
	
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response updateUtilisateur(Utilisateur utilisateur) {
		if (utilisateur == null) {
			return Response.status(Status.NO_CONTENT)
						.entity("L'utilisateur à mettre à jour ne doit pas être nul !")
						.build();
		}
		if (utilisateur.getId() == null || utilisateur.getId()<=0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("L'identitfiant de l'utilisateur à mettre à jour doit être superieur à 0 !")
					.build();
		}
		
		if (utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty()
				|| utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty()) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Tous les attributs del'utilisateur à mettre à jour sont obligatoires !")
					.build();
		}
		// bouchon
		utilisateur.setNom(utilisateur.getNom()+ "-modifié");
		utilisateur.setPrenom(utilisateur.getPrenom()+ "-modifié");
		// utilisateurDao.update(utilisateur);
		return Response.status(Status.CREATED).entity("utilisatuer modifé avec succès voir l'utilisateur ==>"+utilisateur).build();
	}

	@Override
	@PUT
	@Path("updatePrenom/{id}/{prenom}")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response updateUtilisateurPrenom(@PathParam("id")Integer id, @PathParam("prenom") String prenom ) {
		if (id == null || id <=0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("L'identitfiant de l'utilisateur à mettre à jour doit être superieur à 0 !")
					.build();
		}
			
		if (prenom == null || prenom.trim().isEmpty()) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("Le prenom de l'utilisateur à mettre à jour est obligatoire !")
					.build();
		}
		// bouchon
		
//		utilisateur.setNom(utilisateur.getNom()+ "-modifié");
//		utilisateur.setPrenom(utilisateur.getPrenom()+ "-modifié");
		// utilisateurDao.update(utilisateur);
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(id);
		utilisateur.setPrenom(prenom);
		return Response.status(Status.CREATED).entity(utilisateur).build();
	}


	@Override
	@DELETE
	@Path("remove/{id}")
	@Consumes({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	@Produces({MediaType.APPLICATION_JSON + CHARSET, MediaType.APPLICATION_XML + CHARSET})
	public Response removeUtilisateur(@PathParam("id") Integer id) {
		if (id == null || id <= 0) {
			
			return Response.status(Status.BAD_REQUEST)
					.entity("L'identitfiant de l'utilisateur à supprimer doit être superieur à 0 !")
					.build();
		}
		
		return Response.status(Status.ACCEPTED).entity("utilisatuer supprimé avec l'id " + id + " avec succès voir l'utilisateur").build();

	}

}
