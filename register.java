package AgreementMaker;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class register extends JFrame{

	JTable registerTable;
	
	    register(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 600);
		setTitle("AGREEMENTS REGISTER of 'FABRIKA'");
		
		JPanel panel = new JPanel(new BorderLayout());
		
		registerTable = new JTable(new DefaultTableModel());
				
		fillTable();
		JScrollPane center= new JScrollPane(registerTable);
		center.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		center.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		
		panel.add(center, BorderLayout.CENTER);
		
		this.add(panel);
		}
	
	public void fillTable(){
		Connection conn=null;
		Statement stmt=null;
		ArrayList<Agreement> agreemnt=new ArrayList<Agreement>();
		
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Connection to db ....");
			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
			System.out.println("Connection to db OK");
		
			stmt=conn.createStatement();
		    String sql = "select * from Agreements";
		    ResultSet rs= stmt.executeQuery(sql);
		    
		    
		    while (rs.next()) {
		    			    	  	
		    	int numberOfAgreementP=rs.getInt("numberOfAgreement");
		    	String dateOfAgreementP=rs.getString("dateOfAgreement");
		    	String surnameP=rs.getString("surname");
		    	String nameP=rs.getString("name");
		    	String secondNameP=rs.getString("SecondName");
		    	String passportNumberP=rs.getString("passportNumber");
		    	String passportIssuanceP=rs.getString("passportIssuance");
		    	String innP=rs.getString("inn");
		    	String adressP=rs.getString("adress");
		    	String phoneP=rs.getString("phone");
		    	String eMailP=rs.getString("eMail");
		    	String nameOfCourseP=rs.getString("nameOfCourse");
		    	String dateOfStartP=rs.getString("dateOfStart");
		    	int trainingPeriodP=rs.getInt("trainingPeriod");
		    	double costTrainingP=rs.getDouble("costTraining");
		    	double costTrainingPart1P=rs.getDouble("costTrainingPart1");
		    	double costTrainingPart2P=rs.getDouble("costTrainingPart2");
		    	double costTrainingPart3P=rs.getDouble("costTrainingPart3");
		    	
		    	    		    	
		    	Agreement e= new Agreement(numberOfAgreementP, dateOfAgreementP, surnameP, nameP, secondNameP, passportNumberP, passportIssuanceP, innP,
		    			adressP, phoneP, eMailP, nameOfCourseP, dateOfStartP, trainingPeriodP, costTrainingP, costTrainingPart1P,
		    			costTrainingPart2P, costTrainingPart3P);
		    	agreemnt.add(e);
		    	 
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
		       	
		Object[][] values = new Object[agreemnt.size()][];
		for(int i = 0; i < agreemnt.size(); ++i){
			ArrayList<Object> v = new ArrayList<Object>();
			v.add(agreemnt.get(i).getNumberOfAgreementP());
			v.add(agreemnt.get(i).getDateOfAgreementP());
			v.add(agreemnt.get(i).getSurnameP());
			v.add(agreemnt.get(i).getNameP());
			v.add(agreemnt.get(i).getAdressP());
			v.add(agreemnt.get(i).getPhoneP());
			v.add(agreemnt.get(i).geteMailP());
			v.add(agreemnt.get(i).getNameOfCourseP());
			v.add(agreemnt.get(i).getDateOfStartP());
			v.add(agreemnt.get(i).getCostTraining());
			v.add(agreemnt.get(i).getCostTrainingPart1P());
			v.add(agreemnt.get(i).getCostTrainingPart2P());
			v.add(agreemnt.get(i).getCostTrainingPart3P());
			
			values[i] = v.toArray();
		}
		
		String[] columnsNames = {"Номер", "Дата", "Прiзвище", "Iм'я","Адреса","Телефон","E-mail",
				"Курс","Початок навчання","Загальна вартiсть","Частина сплати 1","Частина сплати 2","Частина сплати 3"};
		
		DefaultTableModel tableModel = (DefaultTableModel) registerTable.getModel();
		tableModel.setDataVector(values, columnsNames);
		tableModel.fireTableDataChanged();
		
	
	
	
	
	
	

}
}
