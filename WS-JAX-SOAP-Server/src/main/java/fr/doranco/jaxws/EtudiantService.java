package fr.doranco.jaxws;

import java.util.List;

import javax.jws.WebService;

import fr.doranco.entity.Etudiant;
import fr.doranco.jaxws.dao.EtudiantDAO;

@WebService(endpointInterface = "fr.doranco.jaxws.EtudiantService", serviceName = "EtudiantService", portName = "EtudiantPort")
public class EtudiantService implements IEtudiantService {
	EtudiantDAO etudiantDao = new EtudiantDAO();
	
	@Override
	public Etudiant addEtudiant(Etudiant etudiant) throws Exception {
		if (etudiant == null || etudiant.getNom() == null || etudiant.getNom().isEmpty() 
				|| etudiant.getPrenom() == null || etudiant.getPrenom().isEmpty() 
				|| etudiant.getSpecialite() == null || etudiant.getSpecialite().isEmpty()) {

			throw new IllegalArgumentException("Les paramètres ne doivent pas être nuls ou vides !");
		}
		if ( etudiant.getAge() != null && etudiant.getAge() <= 0) {
			throw new Exception("L'age doit être positif !");
		}
		
		return etudiantDao.addEtudiant(etudiant);
	}

	@Override
	public List<Etudiant> getEtudiants() throws Exception {
		
		return etudiantDao.getEtudiants();
	}

	@Override
	public Etudiant getEtudiantById(Integer id) throws Exception {
		
		return etudiantDao.getEtudiantById(id);
	}

}
