package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
	public Connection connLocal;
	
	 public SQL () throws SQLException, ClassNotFoundException
	{ 

	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@setna:1521:info", "bsanvois", "azerty");
	    connLocal = conn;
	}
	 
	public boolean connection(String chLog,String chMdp) throws SQLException {
		Statement stmt = connLocal.createStatement ();
	    ResultSet rset2 = stmt.executeQuery ("select LOGIN from BDUSER where LOGIN = "+ "'" +  chLog + "'"  );
	    rset2.next ();
	    if (rset2.getBoolean(0)){
	    	ResultSet rset = stmt.executeQuery ("select MDP from BDUSER where LOGIN = "+ "'" +  chLog + "'"  );
	    	rset.next ();
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
	 
	 
/*	  
	    
	    Statement stmt = conn.createStatement ();
	    ResultSet rset = stmt.executeQuery ("select NBCARTECREDIT from BDUSER");
	    while (rset.next ())
	      System.out.println (rset.getString (1));
*/
	    

	
}
