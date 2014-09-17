package AgreementMaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.model.fields.merge.MailMerger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jdesktop.swingx.JXDatePicker;

public class mainFrame extends JFrame {
	
    // Describing Buttons
	JButton 	addAgreementButton = new JButton("Додати Договiр");
	JButton 	viewRegisterButton = new JButton("Список Договорiв");
	JButton 	viewAgreementButton = new JButton("Редактор .docx");
	JButton 	formAgreementButton = new JButton("Сформувати Договiр");
	JButton 	clearButton = new JButton("Очистити");
	JButton 	SettingsButton 	= new JButton("Налаштування");
	JButton 	ExitButton = new JButton("Вихiд");
	JButton 	ddateButton = new JButton("Термiни сплати");
	
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
    
	Connection conn=null;
	Statement stmt=null;
	private String	pathToAgreementsQ;
	private String	pathToTemplateQ;
	private String	pathToWordApplicationQ;
	private String	operationSystemQ;
	private int numberOA=0;
	/**
	 * Create the MAIN_Frame.
	 */
	public mainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("FABRIKA Agreement Maker");
		setBounds(100, 100, 850, 420);
		setResizable(false);
		
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
		datePanel.add (ddateButton);
				
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
		
		viewAgreementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
						Class.forName("org.sqlite.JDBC");
						conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
												
						stmt = conn.createStatement();
						String sql = "select * from PathTable";
					    ResultSet rs= stmt.executeQuery(sql);
					    while (rs.next()) {
				    	  pathToWordApplicationQ=rs.getString("pathToWordApplication");
					  
					    }
			  			rs.close();
						stmt.close();
						conn.close();
					}
					catch (ClassNotFoundException ex)  {
						ex.printStackTrace();
					}
							catch (SQLException ey){
								ey.printStackTrace();
							}
			    Runtime r = Runtime. getRuntime();
				Process p = null;
				try {
					p = r.exec(pathToWordApplicationQ);
					}
				catch (Exception ee) {}
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
		
		ddateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DDate dialog = new DDate();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception d) {
					d.printStackTrace();
				}
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
				String phoneP=phone.getText();
				String eMailP=eMail.getText();
				String nameOfCourseP=nameOfCourse.getText();
				String dateOfStartP=dateOfStart.getText();
				int trainingPeriodP=0;
				double costTrainingP=0;
				double costTrainingPart1P = 0;
				double costTrainingPart2P=0;
				double costTrainingPart3P=0;					
				String ddate1P=DDate.getDdate1P();	
				String ddate2P=DDate.getDdate2P();	
				String ddate3P=DDate.getDdate3P();	
				
				
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
			    			Class.forName("org.sqlite.JDBC");
			    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
			    			stmt = conn.createStatement();
			    			String sql = "insert into Agreements (dateOfAgreement, surname, name, secondName, passportNumber, "
			    					+ "passportIssuance, inn, adress, phone, eMail, nameOfCourse, dateOfStart, trainingPeriod, costTraining, "
			    					+ "costTrainingPart1, ddate1, costTrainingPart2, ddate2, costTrainingPart3, ddate3) "
			    					+ "values ('"+ dateOfAgreementP+"','"+surnameP+"','" + nameP+"','"+ SecondNameP+"','"+ passportNumberP
			    					+"','"+ passportIssuanceP+"','"+ innP+"','"+adressP+"','"+ phoneP+"','"+ eMailP+"','"+ nameOfCourseP+"','"
			    					+dateOfStartP+"',"+ trainingPeriodP+"," + costTrainingP+","+ costTrainingPart1P+",'"+ddate1P+"',"
			    					+costTrainingPart2P+",'"+ddate2P+"',"+costTrainingPart3P +",'"+ddate3P+"')";
			    		   stmt.executeUpdate(sql);	
			    		  
			    		   stmt = conn.createStatement();
			    			ResultSet rs= stmt.executeQuery("select MAX(numberOfAgreement) AS lastNumber FROM Agreements");
			    			while (rs.next()) numberOA=rs.getInt("lastNumber");
			    			
			    			stmt = conn.createStatement();
			    			sql = "select * from PathTable";
			    		    ResultSet rsp= stmt.executeQuery(sql);
			    		    while (rsp.next()) {
					    	  	
			    		    pathToAgreementsQ=rsp.getString("pathToAgreements");
			    		    pathToTemplateQ=rsp.getString("pathToTemplate");
			    		    pathToWordApplicationQ=rsp.getString("pathToWordApplication");
			    		    operationSystemQ=rsp.getString("operationSystem");
			    			  
			    		    }
			    			rsp.close();
			    			rs.close();
			    			stmt.close();
			    			conn.close();
			    			
			    			
					    }  catch (ClassNotFoundException ex)  {
					    	ex.printStackTrace();
					    }
					    		catch (SQLException ey){
					    			ey.printStackTrace();
					    		} 
			    				    		
			    		//Prepare to Insert to WordFile
			    		int Ct=Integer.parseInt (costTraining.getText());
						int CtP1= Integer.parseInt (costTrainingP1.getText());
						int CtP2= Integer.parseInt (costTrainingP2.getText());
						int CtP3= Integer.parseInt (costTrainingP3.getText());
						
						String CtToText= IntInText.convert(Ct);  
						String CtToTextP1= IntInText.convert(CtP1);
						String CtToTextP2= IntInText.convert(CtP2);
						String CtToTextP3= IntInText.convert(CtP3);
						
						String numberOAStr=""+numberOA;
						String FIO= surname.getText()+" "+name.getText().charAt(0)+". "+ secondName.getText().charAt(0)+".";		
						
			    		
			    		try {
			                 // Извлекаем данные о существующем объекте MS Word
			                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(pathToTemplateQ));
			                // Создаем объект для вставки значений в поля слияния
			                List<Map<DataFieldName, String>> data = new ArrayList<Map<DataFieldName, String>>();
			                             
			                // Заполняем значения для полей слияния
			                Map<DataFieldName, String> map = new HashMap<DataFieldName, String>();
			                map.put(new DataFieldName("numberOfAgreement"), numberOAStr);
			                map.put(new DataFieldName("dateOfAgreement"), dateOfAgreement.getText());
			                map.put(new DataFieldName("surname"), surname.getText());
			                map.put(new DataFieldName("name"), name.getText());
			                map.put(new DataFieldName("secondName"), secondName.getText());
			                map.put(new DataFieldName("nameOfCourse"), nameOfCourse.getText());
			                map.put(new DataFieldName("trainingPeriod"), trainingPeriod.getText());
			                map.put(new DataFieldName("dateOfStart"), dateOfStart.getText());
			                map.put(new DataFieldName("costTraining"), costTraining.getText()); 
			                map.put(new DataFieldName("costTrainingTotalString"), CtToText);
			                map.put(new DataFieldName("costTrainingPart1"), costTrainingP1.getText());
			                map.put(new DataFieldName("costTrainingPart1String"), CtToTextP1);
			                map.put(new DataFieldName("dDate1"), DDate.getDdate1P());
			                map.put(new DataFieldName("costTrainingPart2"), costTrainingP2.getText());
			                map.put(new DataFieldName("costTrainingPart2String"), CtToTextP2);
			                map.put(new DataFieldName("dDate2"), DDate.getDdate2P());
			                map.put(new DataFieldName("costTrainingPart3"), costTrainingP3.getText());
			                map.put(new DataFieldName("costTrainingPart3String"), CtToTextP3);
			                map.put(new DataFieldName("dDate3"), DDate.getDdate3P());
			                map.put(new DataFieldName("passportNumber"), passportNumber.getText());
			                map.put(new DataFieldName("passportIssuance"), passportIssuance.getText());
			                map.put(new DataFieldName("adress"), adress.getText());
			                map.put(new DataFieldName("inn"), inn.getText());
			                map.put(new DataFieldName("phone"), phone.getText());
			                map.put(new DataFieldName("eMail"), eMail.getText());
			                map.put(new DataFieldName("FIO"), FIO);
			                
			                data.add(map);
			                // Создаем новый объект MS Word на основе существующего и значений полей слияния
			                WordprocessingMLPackage output =MailMerger.getConsolidatedResultCrude(wordMLPackage, data);
			                // Сохраняем объект в файл
			                output.save(new File(pathToAgreementsQ+ "\\Agreement" + "_" +surname.getText()+ ".docx"));
			                
			                if (operationSystemQ.equals("Windows")) {
			                	Runtime r = Runtime. getRuntime();
								Process p = null;
								try {
									p = r.exec("cmd.exe /r " + pathToAgreementsQ+ "\\Agreement" + "_" +surname.getText()+ ".docx");
									}
								catch (Exception ee) {
									JOptionPane.showMessageDialog(null, "Error in running of DOCx editor");
								}	
			                }
			                
						    
			            } catch (InvalidFormatException ex) {
			            	JOptionPane.showMessageDialog(null, "Illegal Format");
			                
			            } catch (Docx4JException ex) {
			            	JOptionPane.showMessageDialog(null, "Docx4J Problem"); 
			            }
			    		
			    		    		
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
