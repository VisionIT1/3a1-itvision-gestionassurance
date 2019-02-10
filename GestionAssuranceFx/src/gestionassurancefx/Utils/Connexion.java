/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Ahmed Derbel
 */
public class Connexion {
   		private  static final String URL="jdbc:mysql://127.0.0.1:3306/assurance";
		private  static final String user="root";
		private  static final String password="";
		
		private Connection con;//élement de connexion
		
		static Connexion C; // Premiere régle du singleton: créer une variable static du nom de la classe
		
		private Connexion() // 2éme: mettre le constructeur privée
		{
			 try
			{
			con=DriverManager.getConnection(URL, user, password);
				System.out.println("Connexion ok");
			}catch(SQLException e){
				System.out.println("Erreur: "+e.getMessage());
			}
			
		}

		public Connection getCon() {
			return con;
		}
		public static Connexion getInstance()
		{
			if(C==null){
				C=new Connexion();}
			return C;
		}

}
