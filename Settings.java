package AgreementMaker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Settings extends JFrame {

	JTextField	pathToAgreements = new JTextField(30);
	JTextField	pathToTemplates = new JTextField(30);
	JTextField	pathToWordApplication = new JTextField(30);
	JButton	clearRegister = new JButton ("Clear  the Register");
	JButton	saveButton = new JButton ("Save settings");
	
	JLabel	lbPathToAgreements = new JLabel("Path to Folder with Agreements:");
	JLabel	lbPathToTemplates = new JLabel("Path to Templates Folder :");
	JLabel	lbPpathToWordApplication = new JLabel("Path to Word application:");
	
	
	/**
	 * Create the dialog.
	 */
	public Settings() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Settings");
		setBounds(100, 100, 600, 150);
		setResizable(false);
		
		
		JPanel lbPanel = new JPanel();
		JPanel contentPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		
		
		add(contentPanel, BorderLayout.CENTER);
		add(lbPanel,BorderLayout.WEST );
		add(savePanel, BorderLayout.SOUTH);
		
		lbPanel.setLayout(new GridLayout(4,1));
		contentPanel.setLayout(new GridLayout(4,1));
		savePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		lbPanel.add(lbPathToAgreements);
		lbPanel.add(lbPathToTemplates);
		lbPanel.add(lbPpathToWordApplication);
		lbPanel.add (clearRegister);
		
		contentPanel.add(pathToAgreements);
		contentPanel.add(pathToTemplates);
		contentPanel.add(pathToWordApplication);
		
		savePanel.add(saveButton);
				
			
		}
	}

