package crud;

public class GenerateNumber {
	
	public static String randomNumber(int length) {
		String numberSet = "1234567890";
		char[] rand = new char[length];
		for(int i=0; i<length; i++) {
			int r = (int)(Math.random() * numberSet.length());
			rand[i] = numberSet.charAt(r);
		}
		
		return new String(rand);
	}
	

}
