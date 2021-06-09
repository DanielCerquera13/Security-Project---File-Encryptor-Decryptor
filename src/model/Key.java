package model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;


public class Key {
	
	private String password;
	private char[] charPassword;
	private byte[] bytePassword;
	private String salt;
	private byte[] byteSalt;
	private int iterations;
	private int length;
	
	
	
	public Key (String password) {
		
		this.password = password;
		charPassword = password.toCharArray();
		salt = "1234";
		byteSalt = salt.getBytes();
		iterations = 10000;
		length = 128;
		
		password();
		showSecretKeyGenerated();
	}
	
	
	private void password() {
		
		try {
			
			SecretKeyFactory key = SecretKeyFactory.getInstance("PBkdf2WithHmacSHA1");
			PBEKeySpec pbe = new PBEKeySpec(charPassword, byteSalt, iterations, length);
			SecretKey secretKey = key.generateSecret(pbe);
			byte[] result = secretKey.getEncoded();
			this.bytePassword = result;
			
		}catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
	private void showSecretKeyGenerated() {
		
		String keyGenerated = Hex.encodeHexString(bytePassword);
		System.out.println("Key Generada: " + keyGenerated);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public char[] getCharPassword() {
		return charPassword;
	}


	public void setCharPassword(char[] charPassword) {
		this.charPassword = charPassword;
	}


	public byte[] getBytePassword() {
		return bytePassword;
	}


	public void setBytePassword(byte[] bytePassword) {
		this.bytePassword = bytePassword;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}


	public byte[] getByteSalt() {
		return byteSalt;
	}


	public void setByteSalt(byte[] byteSalt) {
		this.byteSalt = byteSalt;
	}


	public int getIterations() {
		return iterations;
	}


	public void setIterations(int iterations) {
		this.iterations = iterations;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}
	
	

}
