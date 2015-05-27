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
	    
	    if (idPanierCo !=0){
		ResultSet rset = stmt.executeQuery ("select IDARTICLE,QTE,SUMARTICLE from BDPANIER where IDPANIER ='"+idPanierCo+"'" );
		int Ligne =1;
		while (rset.next()){
			int IdArticle = rset.getInt(1);
			Integer idAr = new Integer(IdArticle);
			ResultSet rset2 = stmt.executeQuery ("select NOMARTICLE from BDARTICLE where IDARTICLE='"+IdArticle+"'");
			// TODO joindre la resultatset rset et rset2
			rset2.next();
			setValueAt(rset2.getObject(1), 0, Ligne);//Nom article
			setValueAt(rset.getObject(2),1,Ligne);// QTE
			
			ResultSet rset3 = stmt.executeQuery ("select PRIXU from BDARTICLE where IDARTICLE='"+IdArticle+"'");
			rset3.next();
			setValueAt(rset3.getObject(1), 3, Ligne);
			setValueAt(rset.getObject(4), 2, Ligne);//Sum Total
		}
		}
		
	}
}