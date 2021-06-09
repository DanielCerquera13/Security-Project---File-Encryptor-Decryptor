package herramientas;

import java.io.File;

public class FileController {

	public static final String PATH = System.getProperty("user.home") + "/Encrypt-Decrypt";
	
	public static void createDir() {
		
		File file = new File(PATH);
		file.mkdirs();
		
	}
}
