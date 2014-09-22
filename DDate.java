package AgreementMaker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class DDate extends JFrame{
	    
	private static String ddate1P;
	private static String ddate2P;
	private static String ddate3P;
	
	
	    JTextField	ddate1 = new JTextField(15);
		JTextField	ddate2 = new JTextField(15);
		JTextField	ddate3 = new JTextField(15);
		
		JButton	saveButton = new JButton ("Зберегти");
		
		JLabel	lbddate1 = new JLabel("Термiн сплати 1 частини :");
		JLabel	lbddate2 = new JLabel("Термiн сплати 2 частини :");
		JLabel	lbddate3 = new JLabel("Термiн сплати 3 частини :");
		
		public DDate() {
			super();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Термiни сплати");
			setBounds(955, 240, 400, 150);
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
			
			final SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("dd.MM.yyyy");
			
			final JXDatePicker ddate1Picker;
			  ddate1Picker = new JXDatePicker ();
			  ActionListener a3 = new ActionListener () {
			        public void actionPerformed (ActionEvent e) {
			        	Date ddate1D=ddate1Picker.getDate();
			        	String resultDate1P=formatter.format(ddate1D);
			        	ddate1.setText(resultDate1P);
			        }
			       };
			       ddate1Picker.addActionListener (a3);
			
			 final JXDatePicker ddate2Picker;
				ddate2Picker = new JXDatePicker ();
				ActionListener a4 = new ActionListener () {
					public void actionPerformed (ActionEvent e) {
					       Date ddate2D=ddate2Picker.getDate();
					       String resultDate2P=formatter.format(ddate2D);
					       ddate2.setText(resultDate2P);
					        }
					       };
				    ddate2Picker.addActionListener (a4);	
			
		    final JXDatePicker ddate3Picker;
			     ddate3Picker = new JXDatePicker ();
			     ActionListener a5 = new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						       Date ddate3D=ddate3Picker.getDate();
						       String resultDate3P=formatter.format(ddate3D);
						       ddate3.setText(resultDate3P);
						        }
						       };
					    ddate3Picker.addActionListener (a5);
			
			
			
			
			
			
			lbPanel.add(lbddate1);
			lbPanel.add(lbddate2);
			lbPanel.add(lbddate3);
			
			
			contentPanel.add(ddate1);
			contentPanel.add(ddate2);
			contentPanel.add(ddate3);
			
			browsePanel.add(ddate1Picker);
			browsePanel.add(ddate2Picker);
			browsePanel.add(ddate3Picker);
			
		    savePanel.add(saveButton);
			
		    
		    // Describing Browse Buttons
		    
		    saveButton.addActionListener(new ActionListener()  {
		        public void actionPerformed(ActionEvent e)   {
		        	ddate1P=ddate1.getText();
		        	ddate2P=ddate2.getText();
		        	ddate3P=ddate3.getText();
		        	dispose();
		         }
		     });
		        
		   
		}

		public static String getDdate1P() {
			return ddate1P;
		}

		public void setDdate1P(String ddate1p) {
			ddate1P = ddate1p;
		}

		public static String getDdate2P() {
			return ddate2P;
		}

		public void setDdate2P(String ddate2p) {
			ddate2P = ddate2p;
		}

		public static String getDdate3P() {
			return ddate3P;
		}

		public void setDdate3P(String ddate3p) {
			ddate3P = ddate3p;
		}
	
	
	
	
	

}
