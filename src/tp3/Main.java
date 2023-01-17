package tp3;

import java.util.*;


public class Main {
	public static void main(String[] args) {
		Agence agence =new Agence();
		agence.ajouterVoiture(new Voiture("BMW", "A3", 2018, 67,"123D23"));
		agence.ajouterVoiture(new Voiture("BMW", "A7", 2018, 110,"123S23"));
		agence.ajouterVoiture(new Voiture("BMW", "A5", 2018, 256,"123J23"));
		agence.ajouterVoiture(new Voiture("Audi", "Q7", 2016, 86,"123H21"));
		agence.ajouterVoiture(new Voiture("Mazerati", "M23A", 2017, 100,"123F63"));
		agence.ajouterVoiture(new Voiture("Mercides", "190D", 1996, 45,"153Q23"));
		
		agence.ajouterVoiture(new Voiture("Renaut", "21", 2009, 56,"666A21"));
		agence.ajouterVoiture(new Voiture("Renaut", "2C34", 2009, 146,"443V93"));
		agence.ajouterVoiture(new Voiture("Renaut", "2008", 2021, 170,"765Q09"));
		
		agence.loueVoiture(	new Client("A321", "vall", "brahim", "Mr"),new Voiture("BMW", "A3", 2018, 67,"123D23") );
		
		agence.loueVoiture(new Client("X873", "Hamza", "El", "Mr"),new Voiture("Renaut", "2008", 2021, 170,"765Q09") );
		
		//agence.loueVoiture(	new Client("X873", "Hamza", "El", "Mr"),new Voiture("Renaut", "2008", 2021, 170,"765Q09") );
		
	//	System.out.println(agence.getClient("A321"));
		
		/*
		 Iterator<Voiture> it=agence.lesVoituresLouees();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}*/
		
		//agence.afficherLocation();
		agence.menu();
		
	}	
	
}
