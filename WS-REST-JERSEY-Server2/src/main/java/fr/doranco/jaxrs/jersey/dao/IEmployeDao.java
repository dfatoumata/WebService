package fr.doranco.jaxrs.jersey.dao;

import java.util.List;

import fr.doranco.jaxrs.jersey.entity.Employe;


public interface IEmployeDao {
	
	List<Employe> getEmployes() throws Exception;
	Employe getEmployeById(Integer id) throws Exception;
	Employe addEmploye(Employe employe) throws Exception; 
	void updateEmploye(Employe employe) throws Exception;
	void removeEmploye(Integer id) throws Exception;
}
