package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class Checksum {
	
	public static String checksum(String filepath, MessageDigest message) throws IOException{
		
		try (DigestInputStream inputStream = new DigestInputStream(new FileInputStream(filepath),message)){
			
			while(inputStream.read() != -1) {
				message = inputStream.getMessageDigest();
			}
			
		}
		
		StringBuilder check = new StringBuilder();
		for(byte num : message.digest()) {
			
			check.append(String.format("%02x", num));
			
		}
		
		return check.toString();
	}

}
