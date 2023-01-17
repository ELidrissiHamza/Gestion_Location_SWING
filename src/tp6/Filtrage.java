package tp6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp3.Agence;
import tp3.CritereAnnee;
import tp3.CritereMarque;
import tp3.CriterePrix;
import tp3.InterCritere;
import tp3.Voiture;

public class Filtrage extends JPanel implements ActionListener{
	private static final long serialVersionUID = -6125233068539041345L;
	private JPanel panelSearsh;
	private JLabel[] labels;
	private JTextField[] inputs; 
	private JButton bouton;
	private Agence agence;
	private AffichageVoiture showUps;// l'affichage des voitures
	public Filtrage(Agence agence) {
		this.agence=agence;
		panelSearsh=new JPanel(new GridLayout(4,2,0,0));
		panelSearsh.setBackground(NosCouleur.COLOR1);
	
		inputs=new JTextField[3];
		for(int i=0;i<3;i++) 
			inputs[i]=new JTextField(20);	
		

		
		labels=new JLabel[4];
		labels[0]=new JLabel("Filtrage ");
		labels[1]=new JLabel("Annee");
		labels[2]=new JLabel("Marque");
		labels[3]=new JLabel("Prix");
		bouton=new JButton("Chercher");
		bouton.setBackground(NosCouleur.COLOR2);
		
		bouton.setSize(20, 2);
		bouton.setForeground(Color.BLACK);
		bouton.addActionListener(this);
		
		panelSearsh.add(labels[1]);panelSearsh.add(labels[2]);
		panelSearsh.add(inputs[0]);panelSearsh.add(inputs[1]);
		panelSearsh.add(labels[3]);panelSearsh.add(labels[0]);
		panelSearsh.add(inputs[2]);panelSearsh.add(bouton);
		this.setLayout(new BorderLayout());
		this.add(panelSearsh,BorderLayout.NORTH);
		this.setVisible(true);
		this.setBackground(NosCouleur.COLOR1);
		 showUps=new AffichageVoiture(agence.voitures);
		 
		 this.add(showUps,BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		InterCritere crit=new InterCritere();
		// anne marque prix
		String Annee=inputs[0].getText();
		if (!Annee.equals("")) {	crit.addCritere(new CritereAnnee(Voiture.convertInt(Annee)));}
		
		String marque=inputs[1].getText();
		if (!marque.equals("")) {	crit.addCritere(new CritereMarque(marque));}
		
		String prix=inputs[2].getText();
		if (!prix.equals("")) {	crit.addCritere(new CriterePrix(Voiture.convertInt(prix)));}
		
		DefaultTableModel modele=(DefaultTableModel) showUps.getTable().getModel();

		int rows=modele.getRowCount();
		for(int i=0;i<rows;i++)modele.removeRow(0);
			
		
		Iterator<Voiture> iter=agence.selectionne(crit);
		try{
			if(!iter.hasNext()) throw new Exception();
			while(iter.hasNext())
			{
				Voiture v=iter.next();
				modele.addRow(new Object[]
						{v.getMatricule(),
						v.getMarque(),v.getModele(),
						v.getAnneeProd(),v.getPrix()}) ;
			}
		}catch(Exception exp) {
		
			JOptionPane.showMessageDialog(this, "Il n'exist pas de voiture avec ses criteres!", "Voiture introuvable", JOptionPane.ERROR_MESSAGE);
		}
			
		viderInputs();
		
	}
	
	private void viderInputs() {
		for(int i=0;i<3;i++) inputs[i].setText("");
		inputs[0].requestFocusInWindow();//cursor
	}
	
	/*
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
		new Filtrage(agence);
	}
	
	
	
	*/

}
