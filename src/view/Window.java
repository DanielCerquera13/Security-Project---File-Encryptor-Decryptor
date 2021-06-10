package view;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import herramientas.FileController;
import model.Decrypter;
import model.Encrypter;

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
					
					String password = JOptionPane.showInputDialog("Ingresa la contraseña para encriptar el archivo seleccionado");
					
					if(password != null && !password.equals("")) {
						
						Encrypter encrypter = new Encrypter(password, file.getSelectedFile());
						encrypter.encryptFile();
						
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún archivo para encriptar", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch(Exception ex) {
				
			}
			
		});
		
		decryptButton.addActionListener(e -> {
			
			
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(this);
			
			try {
				
				if(file.getSelectedFile() !=null) {
					
					String password = JOptionPane.showInputDialog("Por favor ingresa la contraseña para desencriptar el archivo seleccionado");
					
					if(password != null && !password.equals("")) {
						
						Decrypter decrypter = new Decrypter(password, file.getSelectedFile());
						decrypter.decryptFile();					
						
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún archivo para desencriptar", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch(Exception ex) {
				
			}
			
		});
		
	}

}
