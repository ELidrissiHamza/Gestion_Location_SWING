package tp3;

import java.util.Objects;
import java.util.Scanner;

/*
 Un client ne peut louer qu'un véhicule à la fois. 
 On supposera que les clients sont identifiés par un nom,
  un prénom, un CIN et une civilité (M., Mlle, Mme)
 */
public class Client implements Comparable<Client> {
	private String CIN;
	private String nom;
	private String prenom;
	private String civilite;
	
	public Client(String cIN, String nom, String prenom, String civilite) {
		CIN = cIN;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
	}
	
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(CIN, civilite, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(CIN, other.CIN) && Objects.equals(civilite, other.civilite)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}
	

	@Override
	public String toString() {
		
		return "CIN: "+CIN+" Nom et prenom: "+civilite+" "+nom+" "+prenom;
	}

	@Override
	public int compareTo(Client o) {
		int cmp=this.nom.compareTo(o.nom);
		if(cmp==0) this.prenom.compareTo(o.prenom);
		return cmp;
	}

	public static Client Saisir_Client() {
		Scanner clavier = new Scanner(System.in);
		String prenom, nom, cin, civilite;
		System.out.println("Donner Le nom du client ");
		nom = clavier.nextLine();
		System.out.println("Donner Le prenom du client ");
		prenom = clavier.nextLine();
		System.out.println("Donner le cin du client");
		cin = clavier.nextLine();
		System.out.println("Donner la civilite du client");
		civilite = clavier.nextLine();
		return new Client(cin, prenom,nom, civilite);
	}

}
