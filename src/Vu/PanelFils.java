package Vu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelFils extends JPanel implements ActionListener
{
	//Déclaration 
	//-----------------
	//Tableaux (valeurs pour table)
	String[] titre = {"NomProd","Qte","PrixU","PrixTot"}; //Valeurs du tableau
	Object[][] exemple = {{"Chèvre","4000","1","4000"}}; //Exemple de valeur
	//Table
	JTable table = new JTable(exemple,titre);
	//Label
	JLabel labelLogin = new JLabel ("Login     :");
	JLabel labelMDP = new JLabel ("Mdp        :");
	JLabel labelCB = new JLabel ("Carte Bleu :");
	JLabel labelArticle = new JLabel(" Article à defiler"); //label à setText selon BD
	//TextField
	JTextField fieldLogin = new JTextField(8);
	JTextField fieldMDP = new JTextField(8);
	JTextField fieldCB = new JTextField(10);
	JTextField fieldQte = new JTextField("   Qte   ");
	//Boutons
	JButton boutonPrec = new JButton("<");
	JButton boutonSuiv = new JButton(">");
	JButton boutonAdd = new JButton("Add");
	JButton boutonRem = new JButton("Del");
	JButton boutonCo = new JButton("  Connexion  ");
	JButton boutonRefresh = new JButton("Refresh");
	JButton boutonPayer = new JButton("  Payez !  ");
	public PanelFils()
	{
		//GridBagLayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints cont = new GridBagConstraints(); //cont = contrainte
		cont.insets = new Insets (5,5,5,5); //bordure (haut,gauche,bas droite)
		cont.fill = GridBagConstraints.BOTH;
		//Positionnement avec contrainte
		cont.gridx=0;
		cont.gridy=0;
		add(labelLogin,cont);
		cont.gridy=1;
		add(labelMDP,cont);
		cont.gridx=1;
		add(fieldMDP,cont);
		cont.gridy=0;
		add(fieldLogin,cont);
		cont.gridx=2;
		add(boutonCo,cont);
		cont.gridy=1;
		add(boutonRefresh,cont);
		cont.gridy=0;
		cont.gridx=6;
		cont.fill = GridBagConstraints.VERTICAL;
		add(boutonPrec,cont);
		cont.gridx=7;
		cont.fill = GridBagConstraints.BOTH;
		add(labelArticle,cont);
		cont.fill = GridBagConstraints.VERTICAL;
		cont.gridx=8;
		add(boutonSuiv,cont);
		cont.fill = GridBagConstraints.BOTH;
		cont.gridy=1;
		add(boutonRem,cont);
		cont.gridx=7;
		cont.fill = GridBagConstraints.VERTICAL;
		add(fieldQte,cont);
		cont.gridx=6;
		cont.fill = GridBagConstraints.BOTH;
		add(boutonAdd,cont);
		cont.gridx=3;
		cont.gridy=0;
		add(labelCB,cont);
		cont.gridx=4;
		cont.gridwidth=2;
		add(fieldCB,cont);
		cont.gridx=3;
		cont.gridy=1;
		cont.fill = GridBagConstraints.VERTICAL;
		add(boutonPayer,cont);
		cont.fill = GridBagConstraints.BOTH;
		cont.gridheight = 3;
		cont.gridwidth=5;
		cont.gridx=0;
		cont.gridy=3;
		add(new JScrollPane(table),cont);
		
		
	} //PanelFils()

	public void actionPerformed(ActionEvent parEvt) 
	{
	
		
		
	} //actionPerformed
}
