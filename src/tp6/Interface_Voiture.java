package tp6;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import tp3.Agence;
import tp3.Voiture;

public class Interface_Voiture extends JPanel {

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
		//JFrame f=new JFrame("Gestion Des Voitures");
		//f.setResizable(false);
		//f.setLocation(150, 10);
		 //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //Container content = f.getContentPane();
		// content.setLayout(new BorderLayout());
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
		boutons[1]=new JButton("Supprimer");
		boutons[2]=new JButton("Modifier");
		panelBtns.add(boutons[0],BorderLayout.NORTH);
		boutons[0].setBackground(Color.DARK_GRAY);
		boutons[0].setForeground(Color.white);
		panelBtns.add(boutons[1],BorderLayout.CENTER);
		boutons[1].setBackground(Color.DARK_GRAY);
		boutons[1].setForeground(Color.white);
		panelBtns.add(boutons[2],BorderLayout.SOUTH);
		panelBtns.setBackground(Color.LIGHT_GRAY);
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
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setSize(500, 100);
		//input 3,4,5,7:l'utilisateur a le droit d entrer juste les nombres
		
		scrollpane.getViewport().setPreferredSize(new Dimension(800,400));
		DefaultTableModel model = new DefaultTableModel(colums, 0);
		table=new JTable(model);
				
		scrollpane.setViewportView(table);
		//panelTab.add(table);
		//colorer les rows/////////////////
		table.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);

		        return c;
		    }
		});
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(Color.DARK_GRAY);
		tableHeader.setForeground(Color.WHITE);
		//remove borders/////////////////
		table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("",Font.ITALIC,13));
        table.setShowGrid(false);
        table.setRowHeight(30);
        this.setBackground(Color.LIGHT_GRAY);
		this.add(panelAjout,BorderLayout.EAST);
		this.add(panelBtns,BorderLayout.CENTER);
		this.add(panelSearsh,BorderLayout.WEST);
		this.add(panelTab,BorderLayout.SOUTH);
		//content.add(this, BorderLayout.CENTER);
		//f.pack();
		//f.setSize(900, 650);
		//f.setVisible(true);
		
	}
	
	public JPanel getPanelAjout() {
		return panelAjout;
	}

	public void setPanelAjout(JPanel panelAjout) {
		this.panelAjout = panelAjout;
	}

	public JPanel getPanelBtns() {
		return panelBtns;
	}

	public void setPanelBtns(JPanel panelBtns) {
		this.panelBtns = panelBtns;
	}

	public JPanel getPanelSearsh() {
		return panelSearsh;
	}

	public void setPanelSearsh(JPanel panelSearsh) {
		this.panelSearsh = panelSearsh;
	}

	public JPanel getPanelTab() {
		return panelTab;
	}

	public void setPanelTab(JPanel panelTab) {
		this.panelTab = panelTab;
	}

	public JTextField[] getInputs() {
		return inputs;
	}

	public void setInputs(JTextField[] inputs) {
		this.inputs = inputs;
	}

	public JButton[] getBoutons() {
		return boutons;
	}

	public void setBoutons(JButton[] boutons) {
		this.boutons = boutons;
	}

	public JLabel[] getLabels() {
		return labels;
	}

	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}

	public JScrollPane getScrollpane() {
		return scrollpane;
	}

	public void setScrollpane(JScrollPane scrollpane) {
		this.scrollpane = scrollpane;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void viderInputs(int a,int b) {
		for(int i=a;i<b;i++) inputs[i].setText("");
		inputs[a].requestFocus();//cursor
	}


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
	public void recupererVoitures() {
		try {
			// On cree un flux
			DataInputStream dis = new DataInputStream(new FileInputStream("donnees.txt"));
			String chaine;
			try {
				String matricule;
				String[] ch;
				DefaultTableModel modelee=(DefaultTableModel) getTable().getModel();

				for (int i = 0; i < 6; i++) {
					matricule = dis.readLine();
					
					 ch = matricule.split(" ");
					
					this.agence.ajouterVoiture(new Voiture(ch[1], ch[2], Integer.parseInt(ch[3]), Integer.parseInt(ch[4]), ch[0]));
					modelee.addRow(new Object[] {ch[0], ch[1],ch[2], Integer.parseInt(ch[3]), Integer.parseInt(ch[4])}) ;

				}

			} finally {
				dis.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
