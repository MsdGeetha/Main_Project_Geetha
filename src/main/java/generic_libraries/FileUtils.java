package generic_libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {
	/**
	 * This method which is used to read the inputs from the Property file
	 * @author GEETHA
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromProperties(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream(IpathConstants.filePath);
		Properties pObj = new Properties();
		pObj.load(fis);
		
		String value=pObj.getProperty(key);
		return value;
	}
}
