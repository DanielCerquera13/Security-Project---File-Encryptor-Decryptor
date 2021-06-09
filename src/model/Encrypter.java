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


public class Encrypter {
	
	private String password;
	private File file;
	private Key key;
	
	
	public Encrypter(String password, File file) {
		
		this.password = password;
		this.file = file;
		key = new Key(password);
		
	}
	
	
	public void encryptFile() {
		
		try {
			
			Path path = Paths.get(file.getPath());
			byte[] contents = Files.readAllBytes(path);
			
			String hash = Checksum.checksum(file.getPath(), MessageDigest.getInstance("SHA-1"));
			System.out.println("Hash: "+ hash);
			System.out.println(hash.getBytes().length);
			
			byte[] byteHash = hash.getBytes();
			byte[] contentsWithHash = new byte[contents.length+byteHash.length];
			
			int count = 0;
			
			for(int i=0; i<contentsWithHash.length; i++) {
				
				if(i< contents.length) {
					contentsWithHash[i] = contents[i];
				}else {
					
					contentsWithHash[i] = byteHash[count];
					count++;
				}
				
			}
			
		byte[] raw = key.getBytePassword();
		SecretKeySpec secretKey = new SecretKeySpec(raw, "AES");
		Cipher cip = Cipher.getInstance("AES");
		cip.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypt = cip.doFinal(contentsWithHash);
		String folderEncrypt = FileController.PATH + "ArchivosEncriptados/";
		File folder = new File(folderEncrypt);
		folder.mkdirs();
		
		final Path newFile = Paths.get(folderEncrypt + file.getName());
		System.out.println("Encriptado "+ encrypt.toString());
		Files.write(newFile, encrypt, StandardOpenOption.CREATE);
		System.out.println(newFile);
		
		JOptionPane.showMessageDialog(null, "El archivo ha sido guardado en: "+ FileController.PATH + "/ArchivosEncriptados/", "Archivo Encriptado", JOptionPane.INFORMATION_MESSAGE);
		
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se ha podido encriptar el archivo seleccionado");
			
		}
		
		
	}

}
