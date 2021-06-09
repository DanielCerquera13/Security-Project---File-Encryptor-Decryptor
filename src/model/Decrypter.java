package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import herramientas.FileController;

public class Decrypter {
	
	private String password;
	private File file;
	private Key key;
	
	public Decrypter(String password, File file) {
		
		this.password = password;
		this.file = file;
		key = new Key(password);
	}
	
	public void decryptFile() {
		
		try {
			
			Path path = Paths.get(file.getPath());
			final byte[] contents = Files.readAllBytes(path);
			byte[] raw = key.getBytePassword();
			SecretKeySpec secretKey = new SecretKeySpec(raw, "AES");
			Cipher cip = Cipher.getInstance("AES");
			cip.init(Cipher.DECRYPT_MODE, secretKey);
			
			byte[] decrypt = cip.doFinal(contents);
			System.out.println(""+ decrypt.length);
			byte[] byteHash = new byte[40];
			byte[] data = new byte[decrypt.length - byteHash.length];
			
			int count=0;
			for(int i=0; i<decrypt.length; i++) {
				
				if(i<data.length) {
					
					data[i] = decrypt[i];
					
				}else {
					
					byteHash[count] = decrypt[i];
					count++;
					
				}
				
			}
			
		String folderDecrypt = FileController.PATH + "ArchivosDesencriptados/";
		File folder = new File(folderDecrypt);
		folder.mkdirs();
		
		final Path newFile = Paths.get(folderDecrypt + file.getName());
		System.out.println("Desencriptado" + decrypt.toString());
		Files.write(newFile, data, StandardOpenOption.CREATE);
		System.out.println(newFile);
		
		JOptionPane.showMessageDialog(null, "El archivo desencriptado ha sido guardado en: " + FileController.PATH + "/ArchivosDesencriptados/", "Archivo Desencriptado", JOptionPane.INFORMATION_MESSAGE);
		
		String beforeEncrypt = new String(byteHash);
		String laterDesencrypt = Checksum.checksum(folderDecrypt + file.getName(), MessageDigest.getInstance("SHA-1"));
			
		if(beforeEncrypt.equals(laterDesencrypt)) {
			
			JOptionPane.showMessageDialog(null, "El archivo no ha sufrido alteraciones al desencriptarse", "Desencriptado Exitoso", JOptionPane.INFORMATION_MESSAGE);
			
		}else {
			
			JOptionPane.showMessageDialog(null, "El archivo ha sufrido alteraciones al desencriptarse", "Desencriptado No Exitoso", JOptionPane.INFORMATION_MESSAGE);
			
		}
			
		} catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "No se ha podido desencriptar el archivo seleccionado");
			
		}
		
	}

}
