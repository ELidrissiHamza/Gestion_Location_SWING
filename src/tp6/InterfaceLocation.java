package tp6;
//43, 100, 122 strong   113, 195, 227

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import tp3.Agence;
import tp3.Client;
import tp3.Voiture;

public class InterfaceLocation extends JPanel implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	public Agence agence;
	private JPanel panelAjout;
	private JPanel panelBtns;
	private JPanel panelTab;
	private JTextField[] inputs; 
	private JButton[] boutons;
	private JLabel[] labels;
	private JScrollPane scrollpane;
	private JTable table;
	private Filtrage filtre;
	JRadioButton Homme,Femme;
	ButtonGroup groupeHF;
	ControlerVoitures CV;
	String[] colums= {"CIN","NOM","PRENOM","Matricule","Marque","Modele","Annee","Prix"};
	public InterfaceLocation(Agence agence, ControlerVoitures cv)
	{
		this.agence=agence;
		CV=cv;
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Container content = this.getContentPane();
		 this.setLayout(new BorderLayout());
		
		panelAjout=new JPanel(new GridLayout(5,2,0,5));
		panelAjout.setBackground(NosCouleur.COLOR1);
		inputs=new JTextField[8];
		for(int i=0;i<5;i++)
			inputs[i]=new JTextField(20);
		labels=new JLabel[5];
		labels[4]=new JLabel("CIVILITE");
		
		labels[0]=new JLabel("CIN");
		labels[1]=new JLabel("NOM");
		labels[2]=new JLabel("PRENOM");
		labels[3]=new JLabel("MATRICULE");
		//TODO :: treat the civility 
		
		groupeHF = new ButtonGroup();
		Homme = new JRadioButton("Homme");
		Homme.setMnemonic (KeyEvent.VK_4);Homme.setBackground(NosCouleur.COLOR1);Homme.setForeground(Color.BLACK);
		panelAjout.add (Homme); groupeHF.add (Homme);
		Homme.setSelected(true);
		Femme = new JRadioButton("Femme");Femme.setBackground(NosCouleur.COLOR1);Femme.setForeground(Color.BLACK);
		Femme.setMnemonic (KeyEvent.VK_6);
		panelAjout.add (Femme); groupeHF.add (Femme);
		
		
		for(int i=0;i<4;)
		{	labels[i].setForeground(Color.BLACK);
			panelAjout.add(labels[i]);
			panelAjout.add(inputs[i]);
			i++;
		}
			
		
		panelBtns=new JPanel(new  BorderLayout(5,20));
		
		
		boutons=new JButton[4];
		boutons[0]=new JButton("Louer");
		boutons[0].addActionListener(this);
		boutons[1]=new JButton("Rendre");
		boutons[1].addActionListener(this);
		boutons[2]=new JButton("Modifier");
		boutons[2].addActionListener(this);
		panelBtns.add(boutons[0],BorderLayout.NORTH);
		boutons[0].setBackground(NosCouleur.COLOR2);
		boutons[0].setForeground(Color.BLACK);
		panelBtns.add(boutons[1],BorderLayout.CENTER);
		boutons[1].setBackground(NosCouleur.COLOR2);
		boutons[1].setForeground(Color.BLACK);
		panelBtns.add(boutons[2],BorderLayout.SOUTH);
		boutons[2].setBackground(NosCouleur.COLOR2);
		boutons[2].setForeground(Color.BLACK);
		panelBtns.setBackground(NosCouleur.COLOR1);
		
		panelTab=new JPanel();

		 Label titreLocation=new Label("\tListe des location ",1);
		 titreLocation.setFont(new Font("", Font.BOLD, 18 ));
		 titreLocation.setForeground(Color.BLACK);
		 panelTab.add(titreLocation,BorderLayout.NORTH);
		panelTab.setBackground(NosCouleur.COLOR1);
		scrollpane=new JScrollPane();
		panelTab.add(scrollpane);
		DefaultTableModel model = new DefaultTableModel(colums, 0);
		table=new JTable(model);
        table.setFillsViewportHeight(true);
        table.setBackground(NosCouleur.COLOR1);
		//table.setPreferredSize(new Dimension(400,600));
		scrollpane.setPreferredSize(new Dimension(510,600));

		table.getTableHeader().setDefaultRenderer(new MyHeaderRenderer());
		table.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? NosCouleur.COLOR1 : NosCouleur.COLOR2);
                c.setForeground(Color.BLACK);        
                return c;
            }
        });
		
        // Remove table borders
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("",Font.ITALIC,13));
        table.setRowHeight(30);
		table.addMouseListener(this);
		scrollpane.setViewportView(table);
		
		
		filtre=new Filtrage(agence);
		filtre.setBackground(NosCouleur.COLOR1);
		//------ main Front works
        JSplitPane panSplit=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panSplit.setDividerLocation(200);
        panSplit.setDividerSize(0);
		JPanel panelCrud=new JPanel();
		JPanel panelFiltEtCrud=new JPanel();
		panelFiltEtCrud.setLayout(new BorderLayout());
		
		panelCrud.add(panelAjout);
		panelCrud.add(panelBtns);
		panelCrud.setBackground(NosCouleur.COLOR1);

		 JSplitPane panSplit2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	        panSplit.setDividerLocation(470);
	        panSplit.setDividerSize(0);
	        
		
	   Label titreFiltre=new Label("\tFiltrer pour selection une voiture",1);
	   titreFiltre.setFont(new Font("", Font.BOLD, 17 ));
	   titreFiltre.setForeground(Color.BLACK);
		panelFiltEtCrud.add(titreFiltre,BorderLayout.NORTH);
		panelFiltEtCrud.add(filtre,BorderLayout.CENTER);
		panelFiltEtCrud.setBackground(NosCouleur.COLOR1);
		panSplit2.add(panelCrud);
		panSplit2.setBackground(NosCouleur.COLOR1);
		panSplit2.add(panelFiltEtCrud);
		panSplit.add(panSplit2);

		
		
		panSplit.add(panelTab);
		this.add(panSplit);
		
		//recupererAgence();
		RemplirTableau();
		
		
		this.setSize(1000,500);
		//this.setResizable(false);
		this.setVisible(true);
		this.setLocation(150, 100);

		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel modele=(DefaultTableModel) table.getModel();
		String buttonLable=((JButton)e.getSource( )).getText();
		switch (buttonLable) {
		case "Louer": {
			boolean ajout=true;
			for(int i=0;i<4;i++)
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
				String civilite = Homme.isSelected()?"Mr.":"Mme.";
				Voiture v=this.agence.getVoiture(inputs[3].getText());
				int test=this.agence.loueVoiture(new Client(inputs[0].getText(),inputs[1].getText(), inputs[2].getText(),civilite),v ) ;
				
				String mssge =(test==1 )?"client loueur":((test==2)?"la voiture est deja loue ":"la voiture n'est pas dans l'agence ");
				if(test==0)
				{
					modele.addRow(new Object[] {
							inputs[0].getText(),inputs[1].getText(), inputs[2].getText(),
							v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()
					}) ;
					JOptionPane.showMessageDialog(this, "Location ajoute avec succe", "ajout ", JOptionPane.OK_OPTION);
					CV.colorerVoitureLouees();
				}
				else 
				{
					JOptionPane.showMessageDialog(this,mssge, "Impossible d'effectuer l'allocation", JOptionPane.ERROR_MESSAGE);
					 inputs[0].requestFocus();//cursor
				}
				viderInputs();
				break;
			}
		}
		case "Rendre": {
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				agence.rendVoiture(agence.getClient(o));
				modele.removeRow(ligne);
				viderInputs();
				CV.colorerVoitureLouees();

			}
			else JOptionPane.showMessageDialog(this, "Selectionner une location pour la rendre!", "no selection", JOptionPane.ERROR_MESSAGE);
			break;
		}
		case "Modifier": {
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				for(int i=0;i<3;i++)
				modele.setValueAt(inputs[i].getText(), ligne, i);
				
				agence.getClient(o).setCIN(inputs[0].getText());
				agence.getClient(inputs[0].getText()).setNom(inputs[1].getText());
				agence.getClient(inputs[0].getText()).setPrenom(inputs[2].getText());
				String civilite = Homme.isSelected()?"Mr.":"Mme.";
				agence.getClient(inputs[0].getText()).setCivilite(civilite);
				
				
				viderInputs();
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une location pour la modifier!", "no selection", JOptionPane.ERROR_MESSAGE);

		}}
		
		
	}

	private void viderInputs() {
		for(int i=0;i<5;i++) inputs[i].setText("");
		inputs[0].requestFocusInWindow();//cursor
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		int ligne=table.getSelectedRow();
		if(ligne!=-1)
		{
			String o= (String) table.getValueAt(ligne,0);
			Client c=agence.getClient(o);
			inputs[0].setText(c.getCIN());
			inputs[1].setText(c.getNom());
			inputs[2].setText(c.getPrenom());
			inputs[3].setText(agence.locations.get(c).getMatricule());
		}
	}
	


	public void RemplirTableau() {
	
		String matricule;
		String[] ch;
		DefaultTableModel modelee = (DefaultTableModel) table.getModel();
		this.agence.locations.forEach(
				(k, v) -> 
				modelee.addRow(new Object[]
						{k.getCIN(),
						k.getNom(),k.getPrenom(),
						v.getMatricule(),
						v.getMarque(),v.getModele(),
						v.getAnneeProd(),v.getPrix()}) 
				);
		

			

	}

	public void mousePressed(MouseEvent e) {}public void mouseReleased(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}public void mouseExited(MouseEvent e) {}
	/*
	 * public static void main(String[] args) { Agence agence =new Agence();
	 * 
	 * new InterfaceLocation(agence); }
	 */
}