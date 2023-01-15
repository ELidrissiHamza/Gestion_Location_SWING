package tp6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp3.Agence;
import tp3.CritereAnnee;
import tp3.CritereMarque;
import tp3.CriterePrix;
import tp3.InterCritere;
import tp3.Voiture;

public class Interface_Voiture extends JPanel implements ActionListener,MouseListener,KeyListener{

	private static final long serialVersionUID = 1L;
	public Agence agence;
	private JPanel panelAjout;
	private JPanel panelBtns;
	private JPanel panelSearsh;
	private JPanel panelTab;
	private JTextField[] inputs; 
	private JButton[] boutons;
	private JLabel[] labels;
	private JScrollPane scrollpane;
	private JTable table;
	String[] colums= {"Matricule","Marque","Modele","Annee","Prix"};
	public Interface_Voiture()
	{
		agence=new Agence();
		JFrame f=new JFrame("Gestion location");
		f.setResizable(false);
		f.setLocation(150, 100);
		 f.setSize(300,300);
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container content = f.getContentPane();
		 content.setLayout(new BorderLayout());
		panelAjout=new JPanel(new GridLayout(5,2,10,10));
		panelAjout.setBackground(Color.LIGHT_GRAY);
		inputs=new JTextField[8];
		for(int i=0;i<5;i++)
			inputs[i]=new JTextField(20);
		labels=new JLabel[10];
		labels[0]=new JLabel("Matricule");
		labels[1]=new JLabel("Marque");
		labels[2]=new JLabel("Modele");
		labels[3]=new JLabel("Annee");
		labels[4]=new JLabel("Prix");
		for(int i=0;i<5;)
		{
			panelAjout.add(labels[i]);
			panelAjout.add(inputs[i]);
			i++;
		}
			
		
		
		
		
		panelBtns=new JPanel(new  BorderLayout(10,30));
		
		
		boutons=new JButton[4];
		boutons[0]=new JButton("Ajouter");
		boutons[0].addActionListener(this);
		boutons[1]=new JButton("Supprimer");
		boutons[1].addActionListener(this);
		boutons[2]=new JButton("Modifier");
		boutons[2].addActionListener(this);
		panelBtns.add(boutons[0],BorderLayout.NORTH);
		boutons[0].setBackground(Color.DARK_GRAY);
		boutons[0].setForeground(Color.white);
		panelBtns.add(boutons[1],BorderLayout.CENTER);
		boutons[1].setBackground(Color.DARK_GRAY);
		boutons[1].setForeground(Color.white);
		panelBtns.add(boutons[2],BorderLayout.SOUTH);
		boutons[2].setBackground(Color.DARK_GRAY);
		boutons[2].setForeground(Color.white);
		panelSearsh=new JPanel(new GridLayout(5,2,5,5));
		panelSearsh.setBackground(Color.LIGHT_GRAY);

		labels[5]=new JLabel("   ");
		labels[6]=new JLabel("     			             Filtrage ");
		labels[7]=new JLabel("     Annee");
		labels[8]=new JLabel("     Marque");
		labels[9]=new JLabel("     Prix");
		inputs[5]=new JTextField(20);
		inputs[6]=new JTextField(20);
		inputs[7]=new JTextField(20);
		boutons[3]=new JButton("Chercher");
		boutons[3].addActionListener(this);
		boutons[3].addMouseListener(this);
		boutons[3].setSize(20, 2);
		boutons[3].setBackground(Color.DARK_GRAY);
		boutons[3].setForeground(Color.white);
		panelSearsh.add(labels[5]);
		panelSearsh.add(labels[6]);
		panelSearsh.add(labels[7]);
		panelSearsh.add(inputs[5]);
		panelSearsh.add(labels[8]);
		panelSearsh.add(inputs[6]);
		panelSearsh.add(labels[9]);
		panelSearsh.add(inputs[7]);
		panelSearsh.add(new JLabel(""));
		panelSearsh.add(boutons[3]);
		panelTab=new JPanel();
		scrollpane=new JScrollPane();
		panelTab.add(scrollpane);
		
		//input 3,4,5,7:l'utilisateur a le droit d entrer juste les nombres
		inputs[3].addKeyListener(this);
		inputs[4].addKeyListener(this);
		inputs[5].addKeyListener(this);
		inputs[7].addKeyListener(this);

		DefaultTableModel model = new DefaultTableModel(colums, 0);
		table=new JTable(model);
		table.addMouseListener(this);
		table.setSize(800,500); 
		scrollpane.setViewportView(table);
		//panelTab.add(table);
		this.add(panelAjout,BorderLayout.EAST);
		this.add(panelBtns,BorderLayout.CENTER);
		this.add(panelSearsh,BorderLayout.WEST);
		this.add(panelTab,BorderLayout.SOUTH);
		content.add(this, BorderLayout.CENTER);
		//f.pack();
		f.setSize(900, 400);
		f.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Interface_Voiture iv =new Interface_Voiture();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel modele=(DefaultTableModel) table.getModel();
		JButton b=(JButton)e.getSource();
		if(b.getText().equals("Ajouter")) {
		boolean ajout=true;
		for(int i=0;i<5;i++)
		{
			if(inputs[i].getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Remplisser tous les champs!", "Champ vide", JOptionPane.ERROR_MESSAGE);
				ajout=false;
				break;
			}
			
		}
		
		if(ajout)
		{
			Voiture v=new Voiture(inputs[1].getText(),inputs[2].getText(),Integer.parseInt(inputs[3].getText()),
					Integer.parseInt(inputs[4].getText()),inputs[0].getText()	);
			if(agence.containsV(v.getMatricule()))
			{
				JOptionPane.showMessageDialog(this, "Cette voiture existe!", "Voiture existe", JOptionPane.ERROR_MESSAGE);
				 inputs[0].requestFocus();//cursor

			}
			else {
			agence.ajouterVoiture(v);
			modele.addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;
			agence.afficherVoiture();
			viderInputs(0,5);
			 
			}
		}
	}
		else	 if(b.getText().equals("Supprimer"))
		{

			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				agence.supprimerVoiture(o);
				modele.removeRow(ligne);
				viderInputs(0,5);
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);

		}
		else if(b.getText().equals("Modifier"))
		{
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				for(int i=0;i<5;i++)
				modele.setValueAt(inputs[i].getText(), ligne, i);
				agence.getVoiture(o).setMatricule(inputs[0].getText());
				agence.getVoiture(inputs[0].getText()).setMarque(inputs[1].getText());
				agence.getVoiture(inputs[0].getText()).setModele(inputs[2].getText());
				agence.getVoiture(inputs[0].getText()).setAnneeProd(Integer.parseInt(inputs[3].getText()));
				agence.getVoiture(inputs[0].getText()).setPrix(Integer.parseInt(inputs[4].getText()));
				
				
				viderInputs(0,5);
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);

		}
		else if(b.getText().equals("Chercher"))
		{
			boolean verif=true;
			 
			 for(int i=5;i<8;i++)
			 {
				 if(!(inputs[i].getText().equals(""))) verif=false;
			 }
			 if(!verif)
			 {
				 InterCritere criteres=new InterCritere();
				 
				 //critere Annee
				 if(!(inputs[5].getText().equals("")))
				 {
					 System.out.println("******prix****"+Integer.parseInt(inputs[5].getText()));

					 CritereAnnee c=new CritereAnnee(Integer.parseInt(inputs[5].getText()));
					 criteres.addCritere(c);
				 }
				 //critere marque
				 if(!(inputs[6].getText().equals("")))
				 {
					 System.out.println("*******merque******"+inputs[6].getText());
					 CritereMarque c=new CritereMarque(inputs[6].getText());
					 criteres.addCritere(c);
				 }
				 //critere Prix
				 if(!(inputs[7].getText().equals("")))
				 {
					 System.out.println("******prix****"+Integer.parseInt(inputs[7].getText()));
					 CriterePrix c=new CriterePrix(Integer.parseInt(inputs[7].getText()));
					 criteres.addCritere(c);
				 }
				 Iterator<Voiture> iter=agence.selectionne(criteres);
				 SupprimerTable();
				 Voiture v;
				 while(iter.hasNext())
				 {
					v=iter.next();
					 modele.addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;
					//viderInputs(5,8);
				 }
				 
			 }
			 else JOptionPane.showMessageDialog(this, "Remplir au moins une critere !", "no selection", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void viderInputs(int a,int b) {
		for(int i=a;i<b;i++) inputs[i].setText("");
		inputs[a].requestFocus();//cursor
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2)
			{
				SupprimerTable();
				remplirTableau();
			}
		
		DefaultTableModel modele=(DefaultTableModel) table.getModel();
		int ligne=table.getSelectedRow();
		if(ligne!=-1)
		{
			String o= (String) table.getValueAt(ligne,0);
			inputs[0].setText(agence.getVoiture(o).getMatricule());
			inputs[1].setText(agence.getVoiture(o).getMarque());
			inputs[2].setText(agence.getVoiture(o).getModele());
			inputs[3].setText(Integer.toString(agence.getVoiture(o).getAnneeProd()));
			inputs[4].setText(Integer.toString(agence.getVoiture(o).getPrix()));
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
	
	public void remplirTableau()
	{
		Iterator<Voiture> iter=agence.getVoitures();
		Voiture v;
		while(iter.hasNext())
		{
			DefaultTableModel modele=(DefaultTableModel) table.getModel();
			v=iter.next();
			 modele.addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;

		}
	}
	public void SupprimerTable()
	{
		DefaultTableModel modele = (DefaultTableModel) table.getModel();
		int rowCount = modele.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
		    modele.removeRow(i);
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
	
	
}
