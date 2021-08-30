package fr.doranco.jaxws.dao;

import java.util.List;

import fr.doranco.entity.Etudiant;

public interface IEtudiantDAO {
	
	List<Etudiant> getEtudiants() throws Exception;
	Etudiant getEtudiantById(Integer id) throws Exception;
	Etudiant addEtudiant(Etudiant etudiant) throws Exception; 

}
