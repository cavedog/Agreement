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
        	
    		ArrayList<Agreement> agreemnt=new ArrayList<Agreement>();
    		try {
    			Class.forName("org.sqlite.JDBC");
    			System.out.println("Connection to db ....");
    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
    			System.out.println("Connection to db OK");
    			
    			createAndFillDb (conn);
    				
    			stmt=conn.createStatement();
    		    String sql = "select * from Agreements";
    		    ResultSet rs= stmt.executeQuery(sql);
    		    
    		    
    		    while (rs.next()) {
    		    			    	  	
    		    	int numberOfAgreementP=rs.getInt("numberOfAgreement");
    		    	String dateOfAgreementP=rs.getString("dateOfAgreement");
    		    	String surnameP=rs.getString("surname");;
    		    	String nameP=rs.getString("name");;
    		    	String secondNameP=rs.getString("SecondName");;
    		    	String passportNumberP=rs.getString("passportNumber");;
    		    	String passportIssuanceP=rs.getString("passportIssuance");;
    		    	String innP=rs.getString("inn");;
    		    	String adressP=rs.getString("adress");;
    		    	String eMailP=rs.getString("eMail");;
    		    	String nameOfCourseP=rs.getString("nameOfCourse");;
    		    	String dateOfStartP=rs.getString("dateOfStart");;
    		    	int trainingPeriodP=rs.getInt("trainingPeriod");
    		    	double costTrainingP=rs.getDouble("costTraining");
    		    	double costTrainingPart1P=rs.getDouble("costTrainingPart1");
    		    	double costTrainingPart2P=rs.getDouble("costTrainingPart2");
    		    	double costTrainingPart3P=rs.getDouble("costTrainingPart3");
    		    	
    		    	    		    	
    		    	Agreement e= new Agreement(numberOfAgreementP, dateOfAgreementP, surnameP, nameP, secondNameP, passportNumberP, passportIssuanceP, innP,
    		    			adressP, eMailP, nameOfCourseP, dateOfStartP, trainingPeriodP, costTrainingP, costTrainingPart1P,
    		    			costTrainingPart2P, costTrainingPart3P);
    		    	agreemnt.add(e);
    		    	 
    		    	}
    		    
    			stmt.close();
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
					+ "passportNumber text, passportIssuance text, inn text, adress text, eMail text, nameOfCourse text,"
					+ "dateOfStart text,trainingPeriod integer,costTraining real, costTrainingPart1 real, "
					+ "costTrainingPart2 real,costTrainingPart3 real )";
			Statement stmt= c.createStatement();
			stmt.execute(sql);
			/*sql = "insert into Agreements (dateOfAgreement, surname, name, secondName, passportNumber, passportIssuance, inn, adress, eMail, nameOfCourse, dateOfStart, trainingPeriod, costTraining, costTrainingPart1, costTrainingPart2 ,costTrainingPart3) values ('fb', 'dgfh',  'dfgh', 'SecondNameP', 'passportNumberP', 'passportIssuanceP', 'innP','adressP', 'eMailP', 'nameOfCourseP','dateOfStartP', 3, 100, 50,25,25)";
		   stmt.execute(sql);	 */
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
