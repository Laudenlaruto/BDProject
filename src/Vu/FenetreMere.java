package Vu;
import java.sql.SQLException;

import javax.swing.JFrame;

import Modele.SQL;

public class FenetreMere extends JFrame 
{
	public static void main(String[] args)
	{
		new FenetreMere("Animalerie Lafamille");
	}
	
	public FenetreMere(String parTitre)
	{
		super(parTitre);
		try {
			SQL server = new SQL();
			PanelFils contentPane = new PanelFils(server);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setContentPane(contentPane);
			setSize(750,625);
			setVisible(true); setLocation(200,0);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}