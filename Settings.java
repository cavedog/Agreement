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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	JButton	clearRegister = new JButton ("Очистити реєстр договорів");
	JButton	saveButton = new JButton ("Зберегти");
	JButton BrowseButton1 = new JButton("Вибрати");
	JButton BrowseButton2 = new JButton("Вибрати");
	JButton BrowseButton3 = new JButton("Вибрати");
	JLabel	lbPathToAgreements = new JLabel("Путь до папки з договорами:");
	JLabel	lbPathToTemplate = new JLabel("Шаблон договору:");
	JLabel	lbPpathToWordApplication = new JLabel("Путь до програми Word:");
	
	Connection conn=null;
	Statement stmt=null;
	private String os;
	
	public Settings() {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		String[] osSelectAr  = { "Windows", "Linux"};
		JComboBox osSelect = new JComboBox(osSelectAr);
		osSelect.setEditable(true);
		osSelect.setSelectedIndex(0);
		osSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JComboBox cb = (JComboBox)e.getSource();
			        os = (String)cb.getSelectedItem();
			           	} catch (Exception d) {
					d.printStackTrace();
				}
			}
		});
		  
		
		
		
		lbPanel.add(lbPathToAgreements);
		lbPanel.add(lbPathToTemplate);
		lbPanel.add(lbPpathToWordApplication);
		lbPanel.add (osSelect);
		lbPanel.add (clearRegister);
		
		contentPanel.add(pathToAgreements);
		contentPanel.add(pathToTemplate);
		contentPanel.add(pathToWordApplication);
		
		browsePanel.add(BrowseButton1);
		browsePanel.add(BrowseButton2);
		browsePanel.add(BrowseButton3);
		
	    savePanel.add(saveButton);
		// Insert in fields Path from DB
	    try {
			Class.forName("org.sqlite.JDBC");
			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
					
			stmt = conn.createStatement();
			String sql = "select * from PathTable";
		    ResultSet rs= stmt.executeQuery(sql);
		    
		    while (rs.next()) {
	    	  	pathToAgreements.setText(rs.getString("pathToAgreements"));
		    	pathToTemplate.setText(rs.getString("pathToTemplate"));
		    	pathToWordApplication.setText(rs.getString("pathToWordApplication"));
		    	osSelect.setSelectedItem(rs.getString("operationSystem"));
		    	
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
	    	    	k = k.replaceAll("\\\\","\\\\\\\\");
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
	            	String x= selectedFile.getAbsolutePath();
		              x = x.replaceAll("\\\\","\\\\\\\\");
	            	pathToTemplate.setText(x);
	             }
	        }
	     });		
		
		BrowseButton3.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.showOpenDialog(BrowseButton3);
	            File selectedFile = fileChooser.getSelectedFile();
	            if(selectedFile != null) {
	              String y= selectedFile.getAbsolutePath();
	              y = y.replaceAll("\\\\","\\\\\\\\");
	              pathToWordApplication.setText(y);
	             }
	        }
	     });
		clearRegister.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	        		        int result = JOptionPane.showConfirmDialog(null,
	                        "Ви дійсно бажаєте очистити Реєстр Договорів ?"
	                        , "Питання : ", JOptionPane.YES_NO_OPTION);
	                    if (result == JOptionPane.YES_OPTION) {
	                    	try {
	                    	Connection conn=null;
			    			Statement stmt=null;
			    			Class.forName("org.sqlite.JDBC");
			    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
			    			stmt = conn.createStatement();
			    			String sql = "DELETE FROM Agreements";
			    		    stmt.executeUpdate(sql);	
			    		    stmt = conn.createStatement();
			    			sql = "update sqlite_sequence set seq=0 WHERE Name='Agreements'";
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
		
		saveButton.addActionListener(new ActionListener()  {
	        public void actionPerformed(ActionEvent e)   {
	        	      	 	try {
	                    	Connection conn=null;
			    			Statement stmt=null;
			    			Class.forName("org.sqlite.JDBC");
			    			conn= DriverManager.getConnection("jdbc:sqlite:Agreement.db");
			    			stmt = conn.createStatement();
			    			String sql = "DELETE FROM PathTable";
			    		    stmt.executeUpdate(sql);	
			    			stmt = conn.createStatement();
			    			sql = "insert into PathTable (pathToAgreements, pathToTemplate, pathToWordApplication, operationSystem) "
			    					+ "values ('"+ pathToAgreements.getText()+"','"+pathToTemplate.getText()+"','" + pathToWordApplication.getText()+"','"+os+"')";
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
	             
	     });
	}
}

