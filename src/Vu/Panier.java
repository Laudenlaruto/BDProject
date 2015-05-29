package Vu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import Modele.SQL;

public class Panier extends DefaultTableModel{ 
	
public static Connection connLocal;
	
	public Panier(String[] titres, int idPanierCo) throws SQLException{
		setColumnIdentifiers(titres);
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@setna:1521:info", "bsanvois", "azerty");
	    connLocal = conn;
	    Statement stmt = connLocal.createStatement ();
	    setColumnCount(4);
		
	    if (idPanierCo !=0){
	    setRowCount(100);
		ResultSet rset = stmt.executeQuery ("select NOMARTICLE,QTE,SUMARTICLE,PRIXU from BDPANIER,BDARTICLE where BDPANIER.IDARTICLE = BDARTICLE.IDARTICLE and IDPANIER ='"+idPanierCo+"'" );
		int Ligne =0;
		while (rset.next()){
			setValueAt(rset.getObject(1),Ligne, 0);//Nom article
			setValueAt(rset.getObject(2),Ligne,1);// QTE
			setValueAt(rset.getObject(3), Ligne, 3);//Sum Total
			setValueAt(rset.getObject(4), Ligne, 2);//prix unitaire
			Ligne++;
		}
		}
		
	}
}