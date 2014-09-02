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
	
	//Labels
	
	JLabel	lbDateOfAgreement = new JLabel("Date of Agreement:");
	JLabel	lbDateOfStart = new JLabel("Start education :");
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
	JTextField	dateOfStart = new JTextField(50);
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
		setBounds(100, 100, 850, 370);
		//setResizable(false);
		
		toolPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		labelsPanel.setLayout(new GridLayout(10,1));
		datePanel.setLayout(new GridLayout(10,1));
		enterPanel.setLayout(new GridLayout(10,1));
		
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
				  
				  
		toolPanel.add(addAgreementButton);
		toolPanel.add(viewRegisterButton);
		toolPanel.add(viewAgreementButton);
		toolPanel.add(SettingsButton);
		toolPanel.add(ExitButton);
		
		
		labelsPanel.add(lbDateOfAgreement);
		labelsPanel.add(lbDateOfStart);
		labelsPanel.add(lbSurname);
		labelsPanel.add(lbName);
		labelsPanel.add(lbSecondName);
		labelsPanel.add(lbPassportNumber);
		labelsPanel.add(lbPassportIssuance);
		labelsPanel.add(lbInn);
		labelsPanel.add(lbAdress);
		labelsPanel.add(lbEMail);
		
		enterPanel.add(dateOfAgreement);
		enterPanel.add(dateOfStart);
		enterPanel.add(surname);
		enterPanel.add(name);
		enterPanel.add(secondName);
		enterPanel.add(passportNumber);
		enterPanel.add(passportIssuance);
		enterPanel.add(inn);
		enterPanel.add(adress);
		enterPanel.add(eMail);
	
		datePanel.add (dateAgrPicker);
		datePanel.add (dateStartPicker);
		
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
			dateOfStart.setText(null);
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
		
		
		
	}

}
