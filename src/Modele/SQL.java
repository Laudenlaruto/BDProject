package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
	public Connection connLocal;
	public int NBArticle; 
	 public SQL () throws SQLException, ClassNotFoundException
	{ 

	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@setna:1521:info", "bsanvois", "azerty");
	    connLocal = conn;
	    Statement stmt = connLocal.createStatement ();
		ResultSet rset = stmt.executeQuery ("select NOMARTICLE from BDARTICLE" );
		while(rset.next()){
			NBArticle++;
		}
	    
	}
	 
	public boolean connection(String chLog,String chMdp) throws SQLException {
			Statement stmt = connLocal.createStatement ();
	    	ResultSet rset = stmt.executeQuery ("select MDP from BDUSER where LOGIN = "+ "'" +  chLog + "'"  );
	    	if (rset.next ()){
	    			if (chMdp.equalsIgnoreCase(rset.getString(1))){
	    				return true;
	    			}
	    	}
    		return false;
	    	
	    		
	}
	
	public boolean verifCB (String codeCB, String chLog) throws SQLException
	{
		Statement stmt = connLocal.createStatement ();
		ResultSet rset = stmt.executeQuery ("select NBCARTECREDIT from BDUSER where LOGIN = "+ "'" +  chLog + "'"  );
		rset.next ();
	    if (codeCB.equalsIgnoreCase(rset.getString(1))){
	    	return true;
	    }
		return false;
	}

	public String[] addproduit() throws SQLException {
		Statement stmt = connLocal.createStatement ();
		ResultSet rset = stmt.executeQuery ("select NOMARTICLE from BDARTICLE" );
		int i =0;
		String[] listProduit = new String[NBArticle];
		while(rset.next()){
			listProduit[i]= (rset.getString(1));
			i++;
		}
		return listProduit;
	}
	public void ajoutPanier(String parNomProd,Integer chQte, int paridPanierCo) throws SQLException{
		int Prix;
		int idProduitR;
		Statement stmt = connLocal.createStatement ();
		ResultSet rset = stmt.executeQuery ("select PRIXU from BDARTICLE where NOMARTICLE = '" +parNomProd+"'");
		rset.next();Prix = rset.getInt(1);
		ResultSet rset3 = stmt.executeQuery ("select IDARTICLE from BDARTICLE where NOMARTICLE = '" +parNomProd+"'");
		rset3.next();idProduitR = rset3.getInt(1);
		ResultSet rset2 = stmt.executeQuery ("insert into BDPANIER values("+paridPanierCo+","+idProduitR+","+chQte+"," + (Prix*chQte.intValue())+")" );
	}

	public int idPanier(String chLog, String chmdp) {
    	try {
    		Statement stmt = connLocal.createStatement ();
			ResultSet rset = stmt.executeQuery ("select IDPANIER from BDUSER where LOGIN = "+ "'" +  chLog + "'" +"and MDP = "+ "'"+chmdp+"'");
			rset.next();
			return rset.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
	}
	 

	    

	
}
