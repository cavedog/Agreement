package AgreementMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GUI {
        public static void main(String[] args) {
		
        	Connection conn=null;
    		Statement stmt=null;
    		String pathToAgreements=null;
    		String pathToTemplate=null;
    		String pathToWordApplication=null;
    		String operationSystem=null;
    		
    		javax.swing.SwingUtilities.invokeLater(new Runnable () {
				public void run() {
					createGui();
				}
			});	
    		    		
    		try {
    			Class.forName("org.sqlite.JDBC");
    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
    			    			
    			createAndFillDb (conn);
    			createAndFillPath (conn); 
    			
    			stmt = conn.createStatement();
    			String sql = "select * from PathTable";
    		    ResultSet rs= stmt.executeQuery(sql);
    		    while (rs.next()) {
		    	  	
    		    	pathToAgreements=rs.getString("pathToAgreements");
    		    	pathToTemplate=rs.getString("pathToTemplate");
    		    	pathToWordApplication=rs.getString("pathToWordApplication");
    		    	operationSystem=rs.getString("operationSystem");
    		    }
    		    if (pathToAgreements == null && pathToTemplate ==null && pathToWordApplication ==null && operationSystem==null) {
    		    	JOptionPane.showMessageDialog(null, "Виберіть необхідні настройки");
        			createPathTable();	
        			
    		    } 
    		    
    			rs.close();
    			stmt.close();
    			conn.close();
    		}
    		catch (ClassNotFoundException ex)  {
    			ex.printStackTrace();
    		}
    				catch (SQLException e){
    					e.printStackTrace();
    				}
    		       	
        
	}
        private static void createAndFillDb (Connection c) throws SQLException{
			
			String sql = "create table if not exists Agreements "
					+ "(numberOfAgreement integer primary key autoincrement,dateOfAgreement text, surname text, name text, secondName text, "
					+ "passportNumber text, passportIssuance text, inn text, adress text, phone text, eMail text, nameOfCourse text,"
					+ "dateOfStart text,trainingPeriod integer,costTraining real, costTrainingPart1 real, ddate1 text, "
					+ "costTrainingPart2 real, ddate2 text, costTrainingPart3 real, ddate3 text )";
			Statement stmt= c.createStatement();
			stmt.executeUpdate(sql);
				}		
        private static void createAndFillPath (Connection c) throws SQLException{
			
			String sql = "create table if not exists PathTable "
					+ "(pathToAgreements text, pathToTemplate text, pathToWordApplication text, operationSystem text)";
			Statement stmt= c.createStatement();
			stmt.executeUpdate(sql);
				}		
        public static  void createPathTable() {
			try {
				Settings frameSet = new Settings();
				frameSet.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}  
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
