package AgreementMaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jdesktop.swingx.JXDatePicker;

public class mainFrame extends JFrame {
	
// Describing Buttons
	JButton 	addAgreementButton = new JButton("+ New Agreement");
	JButton 	viewRegisterButton = new JButton("Register");
	JButton 	viewAgreementButton = new JButton("View Agreement");
	JButton 	formAgreementButton = new JButton("DONE");
	JButton 	clearButton = new JButton("Clear");
	JButton 	SettingsButton 	= new JButton("Settings");
	JButton 	ExitButton = new JButton("Exit");
	JButton 	test = new JButton("test");
	
	//Labels
	
	JLabel	lbDateOfAgreement = new JLabel("Дата Договору:");
	JLabel	lbNameOfCourse = new JLabel("Курс :");
	JLabel	lbtrainingPeriod = new JLabel("Тривалiсть курсу (мiс) :");
	JLabel	lbDateOfStart = new JLabel("Початок навчання :");
	JLabel	lbcostTraining = new JLabel("Загальна вартiсть навчання :");
	JLabel	lbcostTrainingP1 = new JLabel("Перша частина сплати :");
	JLabel	lbcostTrainingP2 = new JLabel("Друга частина сплати :");
	JLabel	lbcostTrainingP3 = new JLabel("Третя частина сплати :");
	JLabel	lbSurname = new JLabel("Прiзвище:");
	JLabel	lbName = new JLabel("Iм'я:");
	JLabel	lbSecondName = new JLabel("По-батьковi:");
	JLabel	lbPassportNumber = new JLabel("Номер паспорта:");
	JLabel	lbPassportIssuance = new JLabel("Паспорт виданий:");
	JLabel	lbInn = new JLabel("IПН: ");
	JLabel	lbAdress = new JLabel("Адреса: ");
	JLabel	lbPhone = new JLabel("Телефон:");
	JLabel	lbEMail = new JLabel("E-mail:");
	
	
	
	
	// Describing TEXT forms 	
	JTextField	dateOfAgreement = new JTextField(50);
	JTextField	nameOfCourse = new JTextField(50);
	JTextField	dateOfStart = new JTextField(50);
	JTextField	trainingPeriod = new JTextField("3",50);
	JTextField	costTraining = new JTextField("0",50);
	JTextField	costTrainingP1 = new JTextField("0",50);
	JTextField	costTrainingP2 = new JTextField("0",50);
	JTextField	costTrainingP3 = new JTextField("0",50);
	JTextField	surname = new JTextField(50);
	JTextField	name = new JTextField(50);
	JTextField	secondName = new JTextField(50);
	JTextField	passportNumber = new JTextField(50);
	JTextField	passportIssuance = new JTextField(50);
	JTextField	inn = new JTextField(50);
	JTextField	adress = new JTextField(50);
	JTextField	phone = new JTextField(50);
	JTextField	eMail = new JTextField(50);
	
	
	
		// Describing Panels
	JPanel 		toolPanel 	= new JPanel();
	JPanel 		enterPanel 	= new JPanel();
	JPanel 		labelsPanel	= new JPanel();
	JPanel 		datePanel	= new JPanel();
	JPanel 		donePanel 	= new JPanel();
    
	/**
	 * Create the MAIN_Frame.
	 */
	public mainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Agreement Maker");
		setBounds(100, 100, 850, 420);
		//setResizable(false);
		
		toolPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		labelsPanel.setLayout(new GridLayout(17,1));
		datePanel.setLayout(new GridLayout(17,1));
		enterPanel.setLayout(new GridLayout(17,1));
		
		add(enterPanel, BorderLayout.CENTER);
		add(toolPanel, BorderLayout.NORTH);
		add(labelsPanel,BorderLayout.WEST );
		add(datePanel,BorderLayout.EAST );
		add(donePanel, BorderLayout.SOUTH);
		
		dateOfAgreement.setEditable(false);
		dateOfStart.setEditable(false);
		
		// Date choosers
		final SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		final JXDatePicker dateAgrPicker;
		  dateAgrPicker = new JXDatePicker ();
		  ActionListener al = new ActionListener () {
		        public void actionPerformed (ActionEvent e) {
		        	Date dateAgr=dateAgrPicker.getDate();
		        	String resultDateAgr=formatter.format(dateAgr);
		        	dateOfAgreement.setText(resultDateAgr);
		        }
		       };
		  dateAgrPicker.addActionListener (al);
			  
		  final JXDatePicker dateStartPicker;
		  dateStartPicker = new JXDatePicker ();
		  ActionListener a2 = new ActionListener () {
		        public void actionPerformed (ActionEvent e) {
		        	Date dateStart=dateStartPicker.getDate();
		        	String resultDateStart=formatter.format(dateStart);
		        	dateOfStart.setText(resultDateStart);
		        }
		       };
		  dateStartPicker.addActionListener (a2); 
		
		// Course Name ComboBox
		  String[] nameCourseAr  = { "QA Manual", "QA Automation", "Java", "Front-end", ".NET" };
			JComboBox nameCourse = new JComboBox(nameCourseAr);
			nameCourse.setEditable(true);
			nameCourse.setSelectedIndex(1);
			nameCourse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JComboBox cb = (JComboBox)e.getSource();
				        String nC = (String)cb.getSelectedItem();
				        nameOfCourse.setText(nC);
				       	} catch (Exception d) {
						d.printStackTrace();
					}
				}
			});
		  		  
		  // Training Period ComboBox
		  String[] trainingPeriodAr  = { "1", "2", "3", "4", "5", "6" };
			JComboBox trPeriod = new JComboBox(trainingPeriodAr);
			trPeriod.setEditable(true);
			trPeriod.setSelectedIndex(2);
			trPeriod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JComboBox cb = (JComboBox)e.getSource();
				        String tP = (String)cb.getSelectedItem();
				        trainingPeriod.setText(tP);
				       	} catch (Exception d) {
						d.printStackTrace();
					}
				}
			});
			  
		  
		  
				  
		toolPanel.add(addAgreementButton);
		toolPanel.add(viewRegisterButton);
		toolPanel.add(viewAgreementButton);
		toolPanel.add(SettingsButton);
		toolPanel.add(ExitButton);
		
		
		
		labelsPanel.add(lbNameOfCourse);
		labelsPanel.add(lbDateOfAgreement);
		labelsPanel.add(lbDateOfStart);
		labelsPanel.add(lbtrainingPeriod);
		labelsPanel.add(lbcostTraining);
		labelsPanel.add(lbcostTrainingP1);
		labelsPanel.add(lbcostTrainingP2);
		labelsPanel.add(lbcostTrainingP3);
		labelsPanel.add(lbSurname);
		labelsPanel.add(lbName);
		labelsPanel.add(lbSecondName);
		labelsPanel.add(lbPassportNumber);
		labelsPanel.add(lbPassportIssuance);
		labelsPanel.add(lbInn);
		labelsPanel.add(lbAdress);
		labelsPanel.add(lbPhone);
		labelsPanel.add(lbEMail);
		
		
		enterPanel.add(nameOfCourse);
		enterPanel.add(dateOfAgreement);
		enterPanel.add(dateOfStart);
		enterPanel.add(trainingPeriod);
		enterPanel.add(costTraining);
		enterPanel.add(costTrainingP1);
		enterPanel.add(costTrainingP2);
		enterPanel.add(costTrainingP3);
		enterPanel.add(surname);
		enterPanel.add(name);
		enterPanel.add(secondName);
		enterPanel.add(passportNumber);
		enterPanel.add(passportIssuance);
		enterPanel.add(inn);
		enterPanel.add(adress);
		enterPanel.add(phone);
		enterPanel.add(eMail);
	
		datePanel.add (nameCourse);
		datePanel.add (dateAgrPicker);
		datePanel.add (dateStartPicker);
		datePanel.add (trPeriod);
		datePanel.add (test);
		
		donePanel.add(formAgreementButton);
		donePanel.add(clearButton);
	
		
		
		
	// Adding Listeners
		SettingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Settings dialog = new Settings();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception d) {
					d.printStackTrace();
				}
			}
		});
		
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                System.exit(0);
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
		    
		   public void actionPerformed(ActionEvent e) {
			dateOfAgreement.setText(null);
			nameOfCourse.setText(null);
			dateOfStart.setText(null);
			trainingPeriod.setText("3");
			surname.setText(null);
			name.setText(null);
			secondName.setText(null);
			passportNumber.setText(null);
			passportIssuance.setText(null);
			inn.setText(null);
			adress.setText(null);
			phone.setText(null);
			eMail.setText(null);
			costTraining.setText("0");
			costTrainingP1.setText("0");
			costTrainingP2.setText("0");
			costTrainingP3.setText("0");
		    }
		});
		
		addAgreementButton.addActionListener(new ActionListener() {
		    
			   public void actionPerformed(ActionEvent e) {
				dateOfAgreement.setText(null);
				nameOfCourse.setText(null);
				dateOfStart.setText(null);
				trainingPeriod.setText("3");
				surname.setText(null);
				name.setText(null);
				secondName.setText(null);
				passportNumber.setText(null);
				passportIssuance.setText(null);
				inn.setText(null);
				adress.setText(null);
				phone.setText(null);
				eMail.setText(null);
				costTraining.setText("0");
				costTrainingP1.setText("0");
				costTrainingP2.setText("0");
				costTrainingP3.setText("0");
			    }
			});
			
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Ct=Integer.parseInt (costTraining.getText());
				int CtP1= Integer.parseInt (costTrainingP1.getText());
				int CtP2= Integer.parseInt (costTrainingP2.getText());
				int CtP3= Integer.parseInt (costTrainingP3.getText());
				
				String CtToText= IntInText.convert(Ct);  
				costTraining.setText(IntInText.convert(Ct));
				costTrainingP1.setText(IntInText.convert(CtP1));
				costTrainingP2.setText(IntInText.convert(CtP2));
				costTrainingP3.setText(IntInText.convert(CtP3));
			
			}
		});
		
		formAgreementButton.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e) {
				   				   
				   String dateOfAgreementP=dateOfAgreement.getText();
				   String surnameP=surname.getText();
				   String nameP=name.getText();
				   String SecondNameP=secondName.getText();
				   String passportNumberP=passportNumber.getText();
				   String passportIssuanceP=passportIssuance.getText();
					String innP=inn.getText();
					String adressP=adress.getText();
					String phoneP=adress.getText();
					String eMailP=eMail.getText();
					String nameOfCourseP=nameOfCourse.getText();
					String dateOfStartP=dateOfStart.getText();
					int trainingPeriodP=0;
					double costTrainingP=0;
					double costTrainingPart1P = 0;
					double costTrainingPart2P=0;
					double costTrainingPart3P=0;					;
					
					try {
					trainingPeriodP=Integer.parseInt (trainingPeriod.getText());
					} catch (Exception d) {
					JOptionPane.showMessageDialog(null, "Введiть коректно 'Тривалiсть курсу (мiс)'");
					}
					try {
					costTrainingP=Double.parseDouble (costTraining.getText());
					} catch (Exception d) {
						JOptionPane.showMessageDialog(null, "Введiть коректно 'Загальна вартiсть навчання'");
						}
					try {
					costTrainingPart1P=Double.parseDouble (costTrainingP1.getText());
					} catch (Exception d) {
						JOptionPane.showMessageDialog(null, "Введiть коректно 'Перша частина сплати'");
						}
					try {
					costTrainingPart2P=Double.parseDouble (costTrainingP2.getText());
			   } catch (Exception d) {
					JOptionPane.showMessageDialog(null, "Введiть коректно 'Друга частина сплати'");
					}
					try {
					costTrainingPart3P=Double.parseDouble (costTrainingP3.getText());
					} catch (Exception d) {
						JOptionPane.showMessageDialog(null, "Введiть коректно 'Третя частина сплати'");
					}
				   if ((costTrainingPart1P+costTrainingPart2P+costTrainingPart3P)==costTrainingP) {
					  
			        	
			    		try {
			    			Connection conn=null;
			    			Statement stmt=null;
			    			Class.forName("org.sqlite.JDBC");
			    			System.out.println("Connection to db ....");
			    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
			    			System.out.println("Connection to db OK2");
			    			stmt = conn.createStatement();
			    			String sql = "insert into Agreements (dateOfAgreement, surname, name, secondName, passportNumber, "
			    					+ "passportIssuance, inn, adress, phone, eMail, nameOfCourse, dateOfStart, trainingPeriod, costTraining, "
			    					+ "costTrainingPart1, costTrainingPart2 ,costTrainingPart3) "
			    					+ "values ('"+ dateOfAgreementP+"','"+surnameP+"','" + nameP+"','"+ SecondNameP+"','"+ passportNumberP
			    					+"','"+ passportIssuanceP+"','"+ innP+"','"+adressP+"','"+ phoneP+"','"+ eMailP+"','"+ nameOfCourseP+"','"
			    					+dateOfStartP+"',"+ trainingPeriodP+"," + costTrainingP+","+ costTrainingPart1P+","
			    					+costTrainingPart2P+","+costTrainingPart3P + ")";
			    		   stmt.executeUpdate(sql);	
			    		    stmt.close();
			    			conn.close();
			    			
			    			
					    }  catch (ClassNotFoundException ex)  {
					    	ex.printStackTrace();
					    }
					    		catch (SQLException ey){
					    			ey.printStackTrace();
					    		} 
			    		//HelperWord helper = new HelperWord();
			           // helper.createWord();
			            JOptionPane.showMessageDialog(null, "Договір збережений ");  
				   } else JOptionPane.showMessageDialog(null, "Перевiрте розбиття оплати");
				   }
			});
		viewRegisterButton.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e) {
				try {
					register dialog = new register();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception d) {
					d.printStackTrace();
				}			   
				   
					
			    }
			});		
	}

}
