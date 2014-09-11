package AgreementMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GUI {
        public static void main(String[] args) {
		
        	Connection conn=null;
    		Statement stmt=null;
        	
    		
    		try {
    			Class.forName("org.sqlite.JDBC");
    			System.out.println("Connection to db ....");
    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
    			System.out.println("Connection to db OK");
    			
    			createAndFillDb (conn);
    			    
    			conn.close();
    		}
    		catch (ClassNotFoundException ex)  {
    			ex.printStackTrace();
    		}
    				catch (SQLException e){
    					e.printStackTrace();
    				}
    		       	
        	javax.swing.SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				createGui();
			}
		});	
	}
        private static void createAndFillDb (Connection c) throws SQLException{
			
			String sql = "create table if not exists Agreements "
					+ "(numberOfAgreement integer primary key autoincrement,dateOfAgreement text, surname text, name text, secondName text, "
					+ "passportNumber text, passportIssuance text, inn text, adress text, phone text, eMail text, nameOfCourse text,"
					+ "dateOfStart text,trainingPeriod integer,costTraining real, costTrainingPart1 real, "
					+ "costTrainingPart2 real,costTrainingPart3 real )";
			Statement stmt= c.createStatement();
			stmt.executeUpdate(sql);
				}		
        
        public static  void createGui() {
			try {
				mainFrame frame = new mainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}    
        
        }
        
        
}
