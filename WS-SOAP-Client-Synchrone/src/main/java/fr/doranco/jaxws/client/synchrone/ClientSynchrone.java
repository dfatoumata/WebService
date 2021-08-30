package fr.doranco.jaxws.client.synchrone;

import java.util.List;

import fr.doranco.jaxws.webservice.Etudiant;
import fr.doranco.jaxws.webservice.EtudiantService;
import fr.doranco.jaxws.webservice.EtudiantService_Service;
import fr.doranco.jaxws.webservice.Exception_Exception;

public class ClientSynchrone {

	public static void main(String[] args) {

		EtudiantService_Service service = new EtudiantService_Service();
		EtudiantService port = service.getEtudiantPort();
		Etudiant  etudiantToAdd = new Etudiant();
		etudiantToAdd.setNom("fififi");
		etudiantToAdd.setPrenom("fafafaf");
		etudiantToAdd.setSpecialite("Robotic");
		etudiantToAdd.setAge(35);
		
//		Etudiant etudiantAdded = null;
//		Etudiant etudiantGet = null;
		List<Etudiant> listeEtudiant = null;
		try {
			System.out.println(port.addEtudiant(etudiantToAdd));
			System.out.println("======================================================================================");
			System.out.println(port.getEtudiantById(1));
			System.out.println("==================================================================================");
			listeEtudiant = port.getEtudiants();
			listeEtudiant.forEach(e-> System.out.println(e));
		} catch (Exception_Exception e) {
			// TODO: handle exception
		}
	}

}
