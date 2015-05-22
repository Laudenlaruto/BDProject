package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args)  throws SQLException, ClassNotFoundException {
		// Chargement du driver JDBC Oracle
	    // Utiliser une des deux méthodes suivante. Ici, ,la 2ème est utilisée

	    // Class.forName ("oracle.jdbc.driver.OracleDriver");
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

	    // Connexion

	    Connection conn =
	      DriverManager.getConnection ("jdbc:oracle:thin:@setna:1521:info", "bsanvois", "azerty");

	    // Traitement

	    Statement stmt = conn.createStatement ();
	    ResultSet rset = stmt.executeQuery ("select NBCARTECREDIT from BDUSER");
	    while (rset.next ())
	      System.out.println (rset.getString (1));

	    
	    //okokokoknonononono

	}

}
