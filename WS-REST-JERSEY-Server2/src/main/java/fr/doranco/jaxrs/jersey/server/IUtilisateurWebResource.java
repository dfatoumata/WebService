package fr.doranco.jaxrs.jersey.server;

import java.util.List;

import javax.ws.rs.core.Response;

import fr.doranco.jaxrs.jersey.entity.Utilisateur;

public interface IUtilisateurWebResource {

	final static String CHARSET = ";charset=UTF-8";

	String getInfos();

	Response getUtilisateur(Integer id);

	Response addUtilisateur(Utilisateur utilisateur);
	
	Response addUtilisateurs(List<Utilisateur> utilisateurs) ;

	Response updateUtilisateur(Utilisateur utilisateur);

	Response removeUtilisateur(Integer id);

	Response updateUtilisateurPrenom(Integer id, String prenom);

}
