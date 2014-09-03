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
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	JLabel	lbDateOfAgreement = new JLabel("Date of Agreement:");
	JLabel	lbNameOfCourse = new JLabel("Course :");
	JLabel	lbtrainingPeriod = new JLabel("Training period (month) :");
	JLabel	lbDateOfStart = new JLabel("Start education :");
	JLabel	lbcostTraining = new JLabel("Total Cost of Course :");
	JLabel	lbcostTrainingP1 = new JLabel("Cost of Course (part 1) :");
	JLabel	lbcostTrainingP2 = new JLabel("Cost of Course (part 2) :");
	JLabel	lbcostTrainingP3 = new JLabel("Cost of Course (part 3) :");
	JLabel	lbSurname = new JLabel("Surname:");
	JLabel	lbName = new JLabel("Name:");
	JLabel	lbSecondName = new JLabel("Second name:");
	JLabel	lbPassportNumber = new JLabel("Passport Number:");
	JLabel	lbPassportIssuance = new JLabel("Passport Issuance:");
	JLabel	lbInn = new JLabel("INN:");
	JLabel	lbAdress = new JLabel("Adress:");
	JLabel	lbEMail = new JLabel("E-mail:");
	
	
	
	// Describing TEXT forms 	
	JTextField	dateOfAgreement = new JTextField(50);
	JTextField	nameOfCourse = new JTextField(50);
	JTextField	dateOfStart = new JTextField(50);
	JTextField	trainingPeriod = new JTextField(50);
	JTextField	costTraining = new JTextField(50);
	JTextField	costTrainingP1 = new JTextField("---",50);
	JTextField	costTrainingP2 = new JTextField("---",50);
	JTextField	costTrainingP3 = new JTextField("---",50);
	JTextField	surname = new JTextField(50);
	JTextField	name = new JTextField(50);
	JTextField	secondName = new JTextField(50);
	JTextField	passportNumber = new JTextField(50);
	JTextField	passportIssuance = new JTextField(50);
	JTextField	inn = new JTextField(50);
	JTextField	adress = new JTextField(50);
	JTextField	eMail = new JTextField(50);
	
	
	
		// Describing Panels
	JPanel 		toolPanel 	= new JPanel();
	JPanel 		enterPanel 	= new JPanel();
	JPanel 		labelsPanel	= new JPanel();
	JPanel 		datePanel	= new JPanel();
	JPanel 		donePanel 	= new JPanel();
    
	/**
	 * Create the frame.
	 */
	public mainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Agreement Maker");
		setBounds(100, 100, 850, 420);
		//setResizable(false);
		
		toolPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		labelsPanel.setLayout(new GridLayout(16,1));
		datePanel.setLayout(new GridLayout(16,1));
		enterPanel.setLayout(new GridLayout(16,1));
		
		add(enterPanel, BorderLayout.CENTER);
		add(toolPanel, BorderLayout.NORTH);
		add(labelsPanel,BorderLayout.WEST );
		add(datePanel,BorderLayout.EAST );
		add(donePanel, BorderLayout.SOUTH);
		
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
			trainingPeriod.setText(null);
			surname.setText(null);
			name.setText(null);
			secondName.setText(null);
			passportNumber.setText(null);
			passportIssuance.setText(null);
			inn.setText(null);
			adress.setText(null);
			eMail.setText(null);
		    }
		});
		
			
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Ct=Integer.parseInt (costTraining.getText());
				int CtP1= Integer.parseInt (costTraining.getText());
				int CtP2= Integer.parseInt (costTraining.getText());
				int CtP3= Integer.parseInt (costTraining.getText());
				
				String CtToText= IntInText.convert(Ct);  
				System.out.println(CtToText);
			}
		});
				
				
	}

}
