package tp6;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import tp3.Client;
import tp3.Voiture;

public class InterfacePrincipale extends JFrame{
private ControlerVoitures CV;
private InterfaceLocation interfaceLocation;
private JTabbedPane tablePane;
public InterfacePrincipale()
{
	tablePane=new JTabbedPane();
	CV=new ControlerVoitures();
	interfaceLocation=new InterfaceLocation();
	CV.interfaceVoitures.agence.loueVoiture(new Client("a","a","a","a"), new Voiture("AUDI","RS3",2020,20000,"a1"));
	CV.interfaceVoitures.agence.affichVoituresLouees();
	tablePane.add("Gestion des Voitures",CV.interfaceVoitures);
	tablePane.add("Gestion des Locations",interfaceLocation);
	CV.colorerVoitureLouees();

	this.add(tablePane);
	this.setSize(900, 700);
	this.setVisible(true);
	this.setResizable(false);
}


	
public static void main(String[] args) {
	InterfacePrincipale inte =new InterfacePrincipale();
}
	
	
	
}
