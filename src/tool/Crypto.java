package tool;

import javax.xml.bind.DatatypeConverter;

public class Crypto {
	
	public static String encodeChaine(String str) {
		String encoded = DatatypeConverter.printBase64Binary(str.getBytes());		
		return encoded;
	}
	
	public static String decodeChaine(String str) {
		String decoded = "";
		if (str != null) {
			decoded = new String(DatatypeConverter.parseBase64Binary(str));
		}
			
		return decoded;
	}
}
