package tp6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class interfaceAccueil extends JPanel implements ActionListener{

	private JButton voitures,location;
	private InterfacePrincipale IP;
	public interfaceAccueil(InterfacePrincipale ip)
	{
		IP=ip;
		voitures=new JButton("Gestion Voitures");
		location=new JButton("Gestion Locations");
		voitures.addActionListener(this);
		location.addActionListener(this);
		this.add(voitures);
		this.add(location);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b.getText().equals("Gestion Voitures"))
			IP.tablePane.setSelectedComponent(IP.CV.interfaceVoitures);
		else IP.tablePane.setSelectedComponent(IP.interfaceLocation);
			
	}
	
	
}
