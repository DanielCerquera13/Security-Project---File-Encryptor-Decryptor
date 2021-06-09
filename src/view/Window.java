package view;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import herramientas.FileController;

public class Window extends JFrame {
	
	private JButton encryptButton;
	private JButton decryptButton;
	
	
	public Window() {
		
		FileController.createDir();
		
		setTitle("Proyecto Final Seguridad");
		setLayout(new GridLayout(1,2));
		setSize(400,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		components();
		listener();
		
	}
	
	
	private void components() {
		
		encryptButton = new JButton("Encriptar");
		encryptButton.setVerticalTextPosition(AbstractButton.CENTER);
		encryptButton.setHorizontalTextPosition(AbstractButton.RIGHT);
		add(encryptButton);
		
		
		decryptButton = new JButton("Desencriptar");
		decryptButton.setVerticalTextPosition(AbstractButton.CENTER);
		decryptButton.setHorizontalTextPosition(AbstractButton.RIGHT);
		add(decryptButton);
		
		
	}
	
	private void listener() {
		
		encryptButton.addActionListener(e -> {
			
			
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(this);
			
			try {
				
				if(file.getSelectedFile() !=null) {
					
					String password = JOptionPane.showInputDialog("Por favor ingresa la contraseña para encriptar el archivo seleccionado");
					
					if(password != null && !password.equals("")) {
						
						
						
					}
					
				}
				
			} catch(Exception ex) {
				
			}
			
		});
		
	}

}
