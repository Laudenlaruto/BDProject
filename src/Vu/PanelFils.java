package Vu;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modele.SQL;

public class PanelFils extends JPanel implements ActionListener
{
	//Déclaration 
	//-----------------
	//Tableaux (valeurs pour table)
	String[] titre = {"NomProd","Qte","PrixU","PrixTot"}; //Valeurs du tableau
	//Object[][] exemple = {{"Chèvre","4000","1","4000"}}; //Exemple de valeur
	//Table
	JTable chTable = new JTable();	//Label
	//JLabel
	JLabel labelMontantTot = new JLabel("Montant total :");
	JLabel labelMontantVal = new JLabel("0");
	JLabel labelLogin = new JLabel ("Login     :");
	JLabel labelMDP = new JLabel ("Mdp        :");
	JLabel labelCB = new JLabel ("Carte Bleu :");
	JLabel labelSolde = new JLabel("Votre solde :");
	JLabel labelSoldeMontant = new JLabel("0 €");
	//JLabel labelArticle = new JLabel(); //label à setText selon BD
	//ComboBox
	JComboBox comboArticle;
	//TextField
	JTextField fieldLogin = new JTextField(7);
	JTextField fieldMDP = new JTextField(7);
	JTextField fieldCB = new JTextField(10);
	JTextField fieldQte = new JTextField("Qte",3);
	//JtextArea
	JTextArea fieldHisto = new JTextArea();
	//Boutons
	//JButton boutonPrec = new JButton("<");
	//JButton boutonSuiv = new JButton(">");
	JButton boutonAdd = new JButton("Add");
	JButton boutonRem = new JButton("Del");
	JButton boutonCo = new JButton("  Connexion  ");
	JButton boutonDeco = new JButton("Deconnexion");
	JButton boutonPayer = new JButton("  Payez !  ");
	//SQL
	SQL serveurLocal;
	//String
	String chLog;
	String chMdp;
	String[] listProduit;
	//Bool
	boolean chCo = false;
	//int
	int index = 0;
	int idPanierCo =0;
	//Panier
	Panier panier = new Panier(titre,idPanierCo);
	public PanelFils(SQL parServeur) throws SQLException{
		
		serveurLocal = parServeur;
		//chTable.setModel(new TableDuMois(agenda,indexEvt));
		
		chTable.setModel(panier);
		
		listProduit = serveurLocal.addproduit();
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
		boutonCo.addActionListener(this);
		cont.gridy=1;
		add(boutonDeco,cont);
		boutonDeco.addActionListener(this);
		cont.gridy=0;
		cont.gridx=6;
		cont.fill = GridBagConstraints.VERTICAL;
		//add(boutonPrec,cont);
		//boutonPrec.addActionListener(this);
		cont.gridx=6;
		cont.fill = GridBagConstraints.BOTH;
		comboArticle = new JComboBox(listProduit);
		add(comboArticle,cont);
		//comboArticle.se(listProduit[index]);
		
		cont.fill = GridBagConstraints.VERTICAL;
		cont.gridx=8;
		//add(boutonSuiv,cont);
		//boutonSuiv.addActionListener(this);
		cont.fill = GridBagConstraints.BOTH;
		cont.gridy=1;
		add(boutonRem,cont);
		boutonRem.addActionListener(this);
		cont.gridx=8;
		cont.gridy=0;
		cont.fill = GridBagConstraints.VERTICAL;
		add(fieldQte,cont);
		cont.gridy=1;
		cont.gridx=6;
		cont.fill = GridBagConstraints.BOTH;
		add(boutonAdd,cont);
		boutonAdd.addActionListener(this);
		cont.gridx=3;
		cont.gridy=0;
		add(labelCB,cont);
		cont.gridx=4;
		cont.gridwidth=2;
		add(fieldCB,cont);
		cont.gridwidth=1;
		cont.gridx=3;
		cont.gridy=1;
		cont.fill = GridBagConstraints.VERTICAL;
		add(boutonPayer,cont); 
		boutonPayer.addActionListener(this);
		cont.gridx=4;
		add(labelSolde,cont);
		cont.gridx=5;
		add(labelSoldeMontant,cont);
		cont.fill = GridBagConstraints.BOTH;
		cont.gridheight = 3;
		cont.gridwidth=5;
		cont.gridx=0;
		cont.gridy=3;
		
		add(new JScrollPane(chTable),cont);
		cont.gridx=5;
		cont.gridy=2;
		cont.gridwidth =0;
		cont.gridheight =0;
		add(fieldHisto,cont);
		fieldHisto.setEditable(false);
		
		
	} //PanelFils()

	public void actionPerformed(ActionEvent parEvt) 
	{
		if (parEvt.getSource() == boutonCo) //Connection
		{
			String log = fieldLogin.getText();
			String mdp = fieldMDP.getText();

				
					try {
						if (serveurLocal.connection(log,mdp)){
							 boutonCo.setBackground(new Color(0,255,255));
							 fieldLogin.setEditable(false);
							 fieldMDP.setEditable(false);
							 chCo= true;
							 chLog = log;
							 chMdp = mdp;
							 fieldHisto.append("Connection validée \n");
							 fieldHisto.append("Chargment du pannier \n");
							 idPanierCo = serveurLocal.idPanier(log,mdp);
							 chTable.setModel(new Panier(titre, idPanierCo));
							 // TODO afficher le panier
						 }
						 else
						 {
							 fieldHisto.append("Connection refusée \n");
						 }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
		}
		
		if(parEvt.getSource() == boutonPayer && chCo == true) // Payer
		{
			String codeCB = fieldCB.getText();
			try {
				if (serveurLocal.verifCB(codeCB, chLog))
				{
					fieldHisto.append("Payement Validé \n");
					
					
				}
				else
					fieldHisto.append("Code carte bancaire erroné\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*if(parEvt.getSource() == boutonSuiv){
			if (index == listProduit.length-1){
				index=0;
				labelArticle.setText(listProduit[index]);
			}
			else{
				index++;
				labelArticle.setText(listProduit[index]);
			}
		}
		if(parEvt.getSource() == boutonPrec){
			if (index == 0){
				index=listProduit.length-1;
				labelArticle.setText(listProduit[index]);
			}
			else{
				index--;
				labelArticle.setText(listProduit[index]);
			}
		}
		*/
		
		if (parEvt.getSource()== boutonAdd && chCo == true){
			try {
				serveurLocal.ajoutPanier(comboArticle.getSelectedItem().toString(),Integer.valueOf(fieldQte.getText()),idPanierCo);
				chTable.setModel(new Panier(titre, idPanierCo));
				//panier.setModele(idPanierCo,titre);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	} //actionPerformed 
}
