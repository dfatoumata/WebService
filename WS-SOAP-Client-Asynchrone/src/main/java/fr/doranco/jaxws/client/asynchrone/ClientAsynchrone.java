package fr.doranco.jaxws.client.asynchrone;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import fr.doranco.jaxws.webservice.AddEtudiantResponse;
import fr.doranco.jaxws.webservice.Etudiant;
import fr.doranco.jaxws.webservice.EtudiantService;
import fr.doranco.jaxws.webservice.EtudiantService_Service;
import fr.doranco.jaxws.webservice.GetEtudiantByIdResponse;
import fr.doranco.jaxws.webservice.GetEtudiantsResponse;

public class ClientAsynchrone {

	public static void main(String[] args) {

		EtudiantService_Service service = new EtudiantService_Service();
		EtudiantService port = service.getEtudiantPort();
		Etudiant  etudiantToAdd = new Etudiant();
		etudiantToAdd.setNom("fiSyncAsynctest3");
		etudiantToAdd.setPrenom("fafafaftest3");
		etudiantToAdd.setSpecialite("Systemes embarqués");
		etudiantToAdd.setAge(43);
		

		AsyncHandler<AddEtudiantResponse> asyncHandler = new AsyncHandler<AddEtudiantResponse>() {
			
			@Override
			public void handleResponse(Response<AddEtudiantResponse> res) {
				
				System.out.println("-> In handleResponse");
				if (res.isCancelled()&& res.isDone()) {
					
					try {
						Etudiant etudiantAdded = res.get().getReturn();
						System.out.println("-> Etudiant ajouté: " +etudiantAdded);						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
	
			}
		};
		
		System.out.println("-> Appel de la methode distante 'addEtudiant' avec callback");
		port.addEtudiantAsync(etudiantToAdd, asyncHandler);
		System.out.println("-> Appel avec callback effectué ");
		
		
		try {
			System.out.println("-> Appel de la methode distante 'addEtudiant' avec polling");		
			Response<AddEtudiantResponse> response = port.addEtudiantAsync(etudiantToAdd);
			Thread.sleep(2000);
			AddEtudiantResponse etudiantResponse = response.get();
			System.out.println("=>Etudiant ajouté : " +etudiantResponse.getReturn());
			System.out.println("===============================================================================");				
			Response<GetEtudiantByIdResponse> responseGetById  = port.getEtudiantByIdAsync(10);
			Thread.sleep(2000);
			GetEtudiantByIdResponse getByIdEtudiantResponse = responseGetById.get();
			System.out.println("=> Etuidiant 10 : " +getByIdEtudiantResponse.getReturn());
			System.out.println("===============================================================================");				
			Response<GetEtudiantsResponse> responseGet  = port.getEtudiantsAsync();
			Thread.sleep(2000);
			GetEtudiantsResponse getEtudiantResponse = responseGet.get();
			System.out.println("=> List Etuidiant : " +getEtudiantResponse.getReturn());			
		} catch (Exception e) {

			System.out.println("erreur" +e.getMessage());
		}
	}

}
