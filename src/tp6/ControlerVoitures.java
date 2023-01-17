package tp6;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tp3.Agence;
import tp3.CritereAnnee;
import tp3.CritereMarque;
import tp3.CriterePrix;
import tp3.InterCritere;
import tp3.Voiture;

public class ControlerVoitures implements ActionListener,MouseListener,KeyListener{
public Interface_Voiture interfaceVoitures;

public ControlerVoitures(Agence agence) {
	
	interfaceVoitures=new Interface_Voiture(agence);
	interfaceVoitures.recupererVoitures();
	interfaceVoitures.getBoutons()[0].addActionListener(this);
	interfaceVoitures.getBoutons()[1].addActionListener(this);
	interfaceVoitures.getBoutons()[2].addActionListener(this);
	interfaceVoitures.getBoutons()[3].addActionListener(this);
	interfaceVoitures.getBoutons()[3].addMouseListener(this);
	
	interfaceVoitures.getInputs()[3].addKeyListener(this);
	interfaceVoitures.getInputs()[4].addKeyListener(this);
	interfaceVoitures.getInputs()[5].addKeyListener(this);
	interfaceVoitures.getInputs()[7].addKeyListener(this);
	
	interfaceVoitures.getTable().addMouseListener(this);
	
	

	


}
@Override
public void actionPerformed(ActionEvent e) {

	
		DefaultTableModel modele=(DefaultTableModel) interfaceVoitures.getTable().getModel();
		JButton b=(JButton)e.getSource();
		if(b.getText().equals("Ajouter")) {
			
		boolean ajout=true;
		for(int i=0;i<5;i++)
		{
			if(interfaceVoitures.getInputs()[i].getText().equals(""))
			{
				JOptionPane.showMessageDialog(interfaceVoitures, "Remplisser tous les champs!", "Champ vide", JOptionPane.ERROR_MESSAGE);
				ajout=false;
				break;
			}
			
		}
		
		if(ajout)
		{
			Voiture v=new Voiture(interfaceVoitures.getInputs()[1].getText(),interfaceVoitures.getInputs()[2].getText(),Integer.parseInt(interfaceVoitures.getInputs()[3].getText()),
					Integer.parseInt(interfaceVoitures.getInputs()[4].getText()),interfaceVoitures.getInputs()[0].getText()	);
			if(interfaceVoitures.agence.containsV(v.getMatricule()))
			{
				JOptionPane.showMessageDialog(interfaceVoitures, "Cette voiture existe!", "Voiture existe", JOptionPane.ERROR_MESSAGE);
				interfaceVoitures.getInputs()[0].requestFocus();//cursor

			}
			else {
				interfaceVoitures.agence.ajouterVoiture(v);
			modele.addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;
			interfaceVoitures.agence.afficherVoiture();
			interfaceVoitures.viderInputs(0,5);

			 
			}
		}
	}
		else	 if(b.getText().equals("Supprimer"))
		{

			int ligne=interfaceVoitures.getTable().getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) interfaceVoitures.getTable().getValueAt(ligne,0);
				System.out.println(o);
				interfaceVoitures.agence.supprimerVoiture(o);
				modele.removeRow(ligne);
				interfaceVoitures.viderInputs(0,5);
				
			}
			else JOptionPane.showMessageDialog(interfaceVoitures, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);

		}
		else if(b.getText().equals("Modifier"))
		{
			boolean verif=true;
			 
			 for(int i=0;i<5;i++)
			 {
				 if((interfaceVoitures.getInputs()[i].getText().equals(""))) verif=false;
			 }
			 if(verif) {
			int ligne=interfaceVoitures.getTable().getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) interfaceVoitures.getTable().getValueAt(ligne,0);
				System.out.println(o);
				for(int i=0;i<5;i++)
				modele.setValueAt(interfaceVoitures.getInputs()[i].getText(), ligne, i);
				interfaceVoitures.agence.getVoiture(o).setMatricule(interfaceVoitures.getInputs()[0].getText());
				interfaceVoitures.agence.getVoiture(interfaceVoitures.getInputs()[0].getText()).setMarque(interfaceVoitures.getInputs()[1].getText());
				interfaceVoitures.agence.getVoiture(interfaceVoitures.getInputs()[0].getText()).setModele(interfaceVoitures.getInputs()[2].getText());
				interfaceVoitures.agence.getVoiture(interfaceVoitures.getInputs()[0].getText()).setAnneeProd(Integer.parseInt(interfaceVoitures.getInputs()[3].getText()));
				interfaceVoitures.agence.getVoiture(interfaceVoitures.getInputs()[0].getText()).setPrix(Integer.parseInt(interfaceVoitures.getInputs()[4].getText()));
				
				
				interfaceVoitures.viderInputs(0,5);
			}
			else JOptionPane.showMessageDialog(interfaceVoitures, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);
			 }
				else JOptionPane.showMessageDialog(interfaceVoitures, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);

		}
		else if(b.getText().equals("Chercher"))
		{
			boolean verif=true;
			 
			 for(int i=5;i<8;i++)
			 {
				 if(!(interfaceVoitures.getInputs()[i].getText().equals(""))) verif=false;
			 }
			 if(!verif)
			 {
				 InterCritere criteres=new InterCritere();
				 
				 //critere Annee
				 if(!(interfaceVoitures.getInputs()[5].getText().equals("")))
				 {
					 
					 CritereAnnee c=new CritereAnnee(Integer.parseInt(interfaceVoitures.getInputs()[5].getText()));
					 criteres.addCritere(c);
					 interfaceVoitures.getInputs()[5].setText("");
				 }
				 //critere marque
				 if(!(interfaceVoitures.getInputs()[6].getText().equals("")))
				 {
					 
					 CritereMarque c=new CritereMarque(interfaceVoitures.getInputs()[6].getText());
					 criteres.addCritere(c);
					 interfaceVoitures.getInputs()[6].setText("");
				 }
				 //critere Prix
				 if(!(interfaceVoitures.getInputs()[7].getText().equals("")))
				 {
					 
					 CriterePrix c=new CriterePrix(Integer.parseInt(interfaceVoitures.getInputs()[7].getText()));
					 criteres.addCritere(c);
					 interfaceVoitures.getInputs()[7].setText("");
				 }
				 Iterator<Voiture> iter=interfaceVoitures.agence.selectionne(criteres);
				 interfaceVoitures.SupprimerTable();
				 Voiture v;
				 while(iter.hasNext())
				 {
					v=iter.next();
					 modele.addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;
					
				 }
				// viderInputs(5,8);
			 }
			 else {
				 colorerVoitureLouees();
			 }
		}
		
	

	
}
@Override
public void keyTyped(KeyEvent e) {
	char c=e.getKeyChar();
	if(!Character.isDigit(c)) e.consume();	
}
@Override
public void keyPressed(KeyEvent e) {}
@Override
public void keyReleased(KeyEvent e) {}
@Override
public void mouseClicked(MouseEvent e) {
	if (e.getClickCount() == 2)
	{
		interfaceVoitures.SupprimerTable();
		interfaceVoitures.remplirTableau();
		colorerVoitureLouees();
	}

@SuppressWarnings("unused")
DefaultTableModel modele=(DefaultTableModel) interfaceVoitures.getTable().getModel();
int ligne=interfaceVoitures.getTable().getSelectedRow();
if(ligne!=-1)
{
	String o= (String) interfaceVoitures.getTable().getValueAt(ligne,0);
	interfaceVoitures.getInputs()[0].setText(interfaceVoitures.agence.getVoiture(o).getMatricule());
	interfaceVoitures.getInputs()[1].setText(interfaceVoitures.agence.getVoiture(o).getMarque());
	interfaceVoitures.getInputs()[2].setText(interfaceVoitures.agence.getVoiture(o).getModele());
	interfaceVoitures.getInputs()[3].setText(Integer.toString(interfaceVoitures.agence.getVoiture(o).getAnneeProd()));
	interfaceVoitures.getInputs()[4].setText(Integer.toString(interfaceVoitures.agence.getVoiture(o).getPrix()));
}	
}
@Override
public void mousePressed(MouseEvent e) {}
@Override
public void mouseReleased(MouseEvent e) {}
@Override
public void mouseEntered(MouseEvent e) {}
@Override
public void mouseExited(MouseEvent e) {}

//public static void main(String[] args) {
//	@SuppressWarnings("unused")
//	ControlerVoitures cv=new ControlerVoitures();
//}

public void colorerVoitureLouees()

{
	Voiture v;
	int[] tab = new int[interfaceVoitures.getTable().getRowCount()];
//initialiser la table
	for (int i = 0; i < tab.length; i++)
		tab[i] = -1;
	int j = 0;
	DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
	interfaceVoitures.getTable().getDefaultRenderer(Object.class);
	for (int i = 0; i < interfaceVoitures.getTable().getRowCount(); i++)
	{
		v = new Voiture(
				(String) interfaceVoitures.getTable().getValueAt(i, 1),
			(String) interfaceVoitures.getTable().getValueAt(i, 2),
				(int) interfaceVoitures.getTable().getValueAt(i, 3),
				(int) interfaceVoitures.getTable().getValueAt(i, 4),
				(String) interfaceVoitures.getTable().getValueAt(i, 0)
		);
		if (interfaceVoitures.agence.estLoue(v))
		{
			tab[j++] = i;
			System.out.println("----------");
		}
	}
	interfaceVoitures.ColorerTable(interfaceVoitures.getTable(), tab);
}
}


