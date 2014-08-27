package AgreementMaker;

public class GUI {
        public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				createGui();
			}
		});	
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
