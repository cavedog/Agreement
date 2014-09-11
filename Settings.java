package AgreementMaker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Settings extends JFrame {

	
    
    JTextField	pathToAgreements = new JTextField(30);
	JTextField	pathToTemplate = new JTextField(30);
	JTextField	pathToWordApplication = new JTextField(30);
	JButton	clearRegister = new JButton ("Clear the Register");
	JButton	saveButton = new JButton ("Save settings");
	JButton BrowseButton1 = new JButton("Browse");
	JButton BrowseButton2 = new JButton("Browse");
	JButton BrowseButton3 = new JButton("Browse");
	
	JLabel	lbPathToAgreements = new JLabel("Path to Folder with Agreements:");
	JLabel	lbPathToTemplate = new JLabel("Path to Template of the Agreement :");
	JLabel	lbPpathToWordApplication = new JLabel("Path to Word application:");
	
	
	/**
	 * Create the dialog.
	 */
	public Settings() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Settings");
		setBounds(100, 100, 800, 150);
		setResizable(false);
		
		
		JPanel lbPanel = new JPanel();
		JPanel contentPanel = new JPanel();
		JPanel savePanel = new JPanel();
		JPanel browsePanel=new JPanel();
		
		
		add(contentPanel, BorderLayout.CENTER);
		add(lbPanel,BorderLayout.WEST );
		add(savePanel, BorderLayout.SOUTH);
		add(browsePanel, BorderLayout.EAST);
		
		lbPanel.setLayout(new GridLayout(5,1));
		contentPanel.setLayout(new GridLayout(5,1));
		savePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		browsePanel.setLayout(new GridLayout(5,1));
		
		
		lbPanel.add(lbPathToAgreements);
		lbPanel.add(lbPathToTemplate);
		lbPanel.add(lbPpathToWordApplication);
		lbPanel.add (clearRegister);
		
		contentPanel.add(pathToAgreements);
		contentPanel.add(pathToTemplate);
		contentPanel.add(pathToWordApplication);
		
		browsePanel.add(BrowseButton1);
		browsePanel.add(BrowseButton2);
		browsePanel.add(BrowseButton3);
		
	    savePanel.add(saveButton);
		
	    
	    // Describing Browse Buttons
	    
	    BrowseButton1.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	        	JFileChooser chooser = new JFileChooser(); 
	    	    chooser.setCurrentDirectory(new java.io.File("."));
	    	    chooser.setDialogTitle("Get Path to Folder with Agreements ");
	    	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    chooser.setAcceptAllFileFilterUsed(false);
	    	    if (chooser.showOpenDialog(pathToAgreements) == JFileChooser.APPROVE_OPTION) { 
	    	    	String k=chooser.getSelectedFile().toString();
	    	    	pathToAgreements.setText(k);
	    	            }
	         }
	     });
	        
	    BrowseButton2.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.showOpenDialog(BrowseButton2);
	            File selectedFile = fileChooser.getSelectedFile();
	            if(selectedFile != null) {
	            	pathToTemplate.setText(selectedFile.getAbsolutePath());
	             }
	        }
	     });		
		
		BrowseButton3.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.showOpenDialog(BrowseButton3);
	            File selectedFile = fileChooser.getSelectedFile();
	            if(selectedFile != null) {
	              pathToWordApplication.setText(selectedFile.getAbsolutePath());
	             }
	        }
	     });
		clearRegister.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	        		        int result = JOptionPane.showConfirmDialog(null,
	                        "�� ����� ������ �������� ����� �������� ?"
	                        , "������� : ", JOptionPane.YES_NO_OPTION);
	                    if (result == JOptionPane.YES_OPTION) {
	                    	try {
	                    	Connection conn=null;
			    			Statement stmt=null;
			    			Class.forName("org.sqlite.JDBC");
			    			System.out.println("Connection to db ....");
			    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
			    			System.out.println("Connection to db OK3");
			    			stmt = conn.createStatement();
			    			String sql = "DELETE FROM Agreements";
			    		    stmt.executeUpdate(sql);	
			    		    stmt.close();
			    			conn.close();
			    		 }  catch (ClassNotFoundException ex)  {
					    	ex.printStackTrace();
					    }
					    		catch (SQLException ey){
					    			ey.printStackTrace();
					    		} 
	                    }
	          }
	       
	     });
				
	}
}

