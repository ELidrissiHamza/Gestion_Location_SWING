package tp6;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import tp3.Voiture;



public class AffichageVoiture  extends JPanel{
	private JTable table;
	private static  String[] titres= new String[] {"MATRICULE","MARQUE","MODELE","PRIX","ANNEE"};
	private static final long serialVersionUID = -7029700824422688255L;
	public AffichageVoiture(List<Voiture> voitures) {
		
		table=new JTable(new DefaultTableModel(titres,0));
		table.getTableHeader().setDefaultRenderer(new MyHeaderRenderer());
		table.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ?  NosCouleur.COLOR1 : NosCouleur.COLOR2);
                c.setForeground(Color.BLACK);        
                return c;
            }
        });
		
        // Remove table borders
        table.setShowGrid(false);
        table.setFillsViewportHeight(true);
        table.setBackground(NosCouleur.COLOR1);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("",Font.ITALIC,13));
        table.setRowHeight(30);
        JScrollPane pan=new JScrollPane(table);
		this.add(pan);
		pan.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		table.setBackground(NosCouleur.COLOR1);
		this.setBackground(NosCouleur.COLOR1);
		for(Voiture v : voitures) {
			((DefaultTableModel )table.getModel() ).addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;

		}
			
	}
	public JTable getTable() {
		return table;
	}
	
	public void setTable(JTable tab) {
		this.table = tab;
	}
}
