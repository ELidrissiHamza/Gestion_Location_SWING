package tp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Agence {
	public List<Voiture> voitures;
	public Map<Client, Voiture> locations;

	public Agence() {
		voitures = new ArrayList<>();
		locations = new TreeMap<>();
		//locations=new HashMap<>();
	}

	public Iterator<Voiture> selectionne(Critere c) {
		ArrayList<Voiture> tmp = new ArrayList<>();
		for (Voiture voiture : voitures) {
			if (c.estSatisfaitPar(voiture)) {
				tmp.add(voiture);
			}
		}
		
		return tmp.iterator();
	}

	public void afficheSelection(Critere c) {
		Iterator<Voiture> elements = selectionne(c);
		while (elements.hasNext()) {
			System.out.println(elements.next());
		}

	}
	public void afficherVoiture() {
		for(Voiture v : voitures)
		{
			System.out.println(v);
		}
	}
	public void affichageGeneralVoiture()
	{
		char key,key2;
		InterCritere crit=new InterCritere();
		System.out.println("1-Afficher  tous les voitures");
		System.out.println("2-afficher les voitures sous 1 seul critere");
		System.out.println("3-afficher les voitures sous plusieurs criteres");
		Scanner scan = new Scanner(System.in);
		key = scan.next().charAt(0);
		switch (key) {
			case '1' -> {
				afficherVoiture();
			}
			case '2' -> {
				System.out.println("1- pour critere Prix ");
				System.out.println("2- pour critere Marque ");
				System.out.println("3- pour critere Annee ");
				key2 = scan.next().charAt(0);
				switch (key2) {
					case '1'-> {	
						System.out.println("Entrer le prix critere ");
						crit.addCritere(new CriterePrix(Voiture.convertInt(scan.next())));		
					}
					case '2'-> {
						System.out.println("Entrer la marque critere ");
						crit.addCritere(new CritereMarque(scan.next()));
					}			
					case '3'-> {
						System.out.println("Entrer l'annee  critere ");
						crit.addCritere(new CritereAnnee(Voiture.convertInt(scan.next())));
					}			
					default->{
						System.out.println("choix invalide: " + key2);
					}
				}
				afficheSelection(crit);
				crit.viderCritere();
			}
			case '3' -> {
				
				do {
					System.out.println("1- pour ajouter critere Prix ");
					System.out.println("2- pour ajouter critere Marque ");
					System.out.println("3- pour ajouter critere Annee ");
					System.out.println("0- pour afficher selon les critere choisis ");
	
					key2 = scan.next().charAt(0);
					switch (key2) {
						case '1'-> {	
							System.out.println("Entrer le prix critere ");
							crit.addCritere(new CriterePrix(Voiture.convertInt(scan.next())));		
						}
						case '2'-> {
							System.out.println("Entrer la marque critere ");
							crit.addCritere(new CritereMarque(scan.next()));
						}			
						case '3'-> {
							System.out.println("Entrer l'annee  critere ");
							crit.addCritere(new CritereAnnee(Voiture.convertInt(scan.next())));
						}	
						case '0'->{
						}
						default->{
							System.out.println("choix invalide: " + key2);
						}
					}
				}while(key2!='0');
				afficheSelection(crit);
				crit.viderCritere();
	
			}
			default -> 
			{
				System.out.println("choix invalide: ");
			}
		}
	}
	public boolean containsV(final String matricule) {
		return voitures.stream().anyMatch(o -> matricule.equals(o.getMatricule()));
	}

	public void ajouterVoiture(Voiture v) {
		try {
			if (containsV(v.getMatricule())) {
				throw new Exception();
			} else {
				voitures.add(v);
			}
		} catch (Exception e) {
			System.out.println("le matricule : (" + v.getMatricule() + ") correspond a une voiture deja ajoutee");
		}
	}

	public void supprimerVoiture(String v) {
		try {
			if (!voitures.removeIf(vt -> vt.getMatricule().equals(v)))
				throw new Exception();
			else
				System.out.println("suppression avec succes");
		} catch (Exception e) {
			System.out.println("echec de suppression matricule ne correspond a aucune voiture");
		}
	}

	public Voiture getVoiture(String mat) {
		for (Voiture v : voitures)
			if (v.getMatricule().equals(mat))
				return v;
		return null;
	}
	
	public Client getClient(String cin) {
		ArrayList<Client> clients = new ArrayList<>(locations.keySet());
		for (Client cli: clients)
			if(cli.getCIN().equals(cin)) return (Client) cli;
		return null;
	}


	public int loueVoiture(Client client, Voiture v) {

		try {
			if (voitures.contains(v)) {
				try {
					if (!estLoue(v)) {

						try {
							if (!estLoueur(client)) {
								locations.put(client, v);
							} else
								throw new Exception();
						} catch (Exception e) {
							System.out.println("client loueur ");
							return 1;
						}

					} else
						throw new Exception();
				} catch (Exception e) {
					System.out.println("la voiture est deja loue ");
					return 2;

				}

			} else
				throw new Exception();
		} catch (Exception ignored) {
			System.out.println("la voiture n'est pas dans l'agence ");
			return 3;
		}
		return 0;

	}

	public boolean estLoueur(Client client) {
		return locations.containsKey(client);
	}

	public boolean estLoue(Voiture v) {
		return locations.containsValue(v);
	}

	public void rendVoiture(Client client) {
		if (estLoueur(client))
			locations.remove(client);
	}

	public Iterator<Voiture> lesVoituresLouees() {
		List<Voiture> voituresLouees = new ArrayList<Voiture>(this.locations.values());
		return voituresLouees.iterator();
	}

	public Iterator<Client> lesClientsLoueur() {
		List<Client> clientsLoueur = new ArrayList<Client>(this.locations.keySet());
		return clientsLoueur.iterator();
	}

	public void affichClientsLoueur() {
		Iterator<Client> iter_clients = this.lesClientsLoueur();
		while (iter_clients.hasNext())
			System.out.println(iter_clients.next());
	}

	public void affichVoituresLouees() {
		Iterator<Voiture> iter_voitures = this.lesVoituresLouees();
		while (iter_voitures.hasNext())
			System.out.println(iter_voitures.next());
	}

	public void afficherLocation() {
		locations.forEach(
				(k, v) -> System.out.println("---------------------\nClient: \n\t" + k + "\nVoiture loue: \n\t" + v));
	}

	public void menu() {
		char key;
		do {
			System.out.println("1-ajouter une voiture");
			System.out.println("2-supprimer une voiture");
			System.out.println("3-modifier une voiture");
			System.out.println("4-afficher les voitures");

			System.out.println("5-ajouter une location");
			System.out.println("6-confirmer le rend d'une voiture (fin location)");
			System.out.println("7-afficher les voitures louees");
			System.out.println("8-afficher les clients");
			System.out.println("9-afficher les locations");

			System.out.println("0-quiter le programme");
			Scanner scan = new Scanner(System.in);
			key = scan.next().charAt(0);
			switch (key) {
			case '1' -> {
				ajouterVoiture(Voiture.Saisir_Voiture());
			}
			case '2' -> {
				System.out.println("entrer le matricule de la voiture a supprimer de l'agence ");
				supprimerVoiture(scan.nextLine());
			}
			case '3' -> {
				System.out.println("entrer le matricule de la voityure a modufie : ");
				Voiture v =getVoiture(scan.nextLine());
				if(v!=null) v.modifierVoiture();
				else System.out.println("matricule incorrect !!");
			}
			case '4' -> {
				affichageGeneralVoiture();
			}
			case '5' -> {
				
				Voiture v;
				do {
					System.out.println("entrer le matricule de la voiture a louee: ");
					v=getVoiture(scan.next());
					if(v==null) System.out.println("Matricule incorrect");
				}while(v==null);
				loueVoiture(Client.Saisir_Client(),v);
			}
			case '6' -> {
				
				Client c;
				do {
					System.out.println("entrer le CIN du client : ");
					c=getClient(scan.next());
					if(c==null) System.out.println("CIN incorrect");
				}while(c==null);
				rendVoiture(c);
			}
			case '7' -> {
				affichVoituresLouees();
			}
			case '8' -> {
				affichClientsLoueur();
			}
			case '9' -> {
					afficherLocation();
			}
			case '0' -> {
				System.out.println("Fin du programme ");
			}
			default -> {
				System.out.println("choix incorrect!!!!");
			}
			}

		} while (key!='0');
	}

	public Iterator<Voiture> getVoitures() {
		
		return voitures.iterator();
	}
}
