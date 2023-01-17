package tp3;

import java.util.Scanner;

public class Voiture {
	private String matricule;
	private String marque;
	private String modele;
	private int anneeProd;
	private int prix;
	public Voiture(String marque, String modele, int anneeProd, int prix,String matricule) {
		this.marque = marque;
		this.modele = modele;
		this.anneeProd = anneeProd;
		this.prix = prix;
		this.setMatricule(matricule);
	}
	
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getAnneeProd() {
		return anneeProd;
	}
	public void setAnneeProd(int anneeProd) {
		this.anneeProd = anneeProd;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==this) return true;
		if(!(obj instanceof Voiture)) return false;
		
		return ((this.marque!=null)&&(this.marque.equals(((Voiture) obj).getMarque()))&&
				(this.matricule!=null)&&(this.matricule.equals(((Voiture) obj).getMatricule()))&&
				(this.anneeProd==((Voiture)obj).getAnneeProd())&&
				(this.prix==((Voiture)obj).getPrix())&&
				(this.modele!=null)&&(this.modele.equals(((Voiture)obj).getModele())));
	}
	@Override
	public String toString() {
		return "Matricule : "+matricule+" Marque : "+marque+" Modele : "+modele+" Annee de Prod : "+anneeProd +" Prix : "+prix;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	 public static Integer convertInt(String chaine)
	    {
		    try {
		    	return Integer.parseInt(chaine);
		    }
			    catch(Exception e) {
			    //e.printStackTrace();
			    System.out.println("Probleme de convertion de : "+chaine);
			    return 0;
		    }
	    }
	public static Voiture Saisir_Voiture()
	{
		Scanner clavier = new Scanner(System.in);
		String marque, nom,matricule;
		int prix, annee;
		System.out.println("Donner Le matricule du voiture ");
		matricule = clavier.nextLine();
		System.out.println("Donner La marque du voiture ");
		marque = clavier.nextLine();
		System.out.println("Donner Le nom du modele du voiture ");
		nom = clavier.nextLine();
		System.out.println("Donner le prix du voiture");
		prix =convertInt(clavier.nextLine());
		System.out.println("Donner l'annee de production du voiture");
		annee = convertInt(clavier.nextLine());
		return new Voiture(marque, nom, annee, prix,matricule);
	}
	
	public  void modifierVoiture()
	{
		char key;
		do {
			System.out.println("1-changer le matricule");
			System.out.println("2-changer la marque");
			System.out.println("3-changer le modele ");
			System.out.println("4-changer l'annee de production");
			System.out.println("5-changer le prix");
			System.out.println("0-retourner au menu principal");
			Scanner scan=new Scanner(System.in);
			key=scan.next().charAt(0);
			switch (key) {
			case '1'->{
				System.out.println("entrer le nouveau matricule: " );
				setMatricule(scan.nextLine());
			}
			case '2'->{
				System.out.println("entrer la nouvelle marque: " );
				setMarque(scan.nextLine());
			}
			case '3'->{
				System.out.println("entrer le nouveau modele: " );
				setModele(scan.nextLine());
			}
			case '4'->{
				System.out.println("entrer la nouvelle annee de production: " );
				setAnneeProd(convertInt(scan.nextLine()));
			}
			case '5'->{
				System.out.println("entrer le nouveau prix: " );
				setPrix(convertInt(scan.nextLine()));
			}

			case '0'->{
				System.out.println("retour au menu principale");
			}
			default->{
				System.out.println("choix incorrect!!!!");
			}
			}
			
		}while(key!='0');
	}
	
}
