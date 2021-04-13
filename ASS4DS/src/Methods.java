import java.io.*;

public class Methods {
	
	
	
	/**
	 * 
	 * Gets a String and uses horners rule (with mod prime 15486907) in base 256 to convert the string to a long
	 * 
     * @param k the string it gets
     * 
     */
	public static long HornerConvert (String k) {
		Methods.isIllegalString(k);
		long y = (long) k.charAt(0);
		for (int i=1; i<k.length() ; i=i+1) {
			long scalar = (long) k.charAt(i);
			y = (scalar+256*y) % (15486907) ;
		}
		return y;
	}
	
	
	
	/**
	 * 
	 * reads the file and returns a LinkedList<String> of the lines
	 * 
     * @param url of the txt
     * 
     */
	public static LinkedList<String> reader (String url) {
		LinkedList<String> lines = new LinkedList<String>();
		try {
			FileReader file= new FileReader(url);
			BufferedReader reader = new BufferedReader(file);
				
			String line = reader.readLine();
			
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e){
			System.out.println("File not Found");
			
		}
		return lines;
	}
	
	
	/**
	 * 
	 * is Illegal Int
	 * 
     * @param number
     * 
     */
	public static void isIllegalInt(int number) {
		if(number<=0) {
			throw new RuntimeException("You entered illegal value for the initialization");
		}
	}
	
	/**
	 * 
	 * is Illegal String
	 * 
     * @param str String
     * 
     */
	public static void isIllegalString(String str) {
		if(str==null) {
			throw new RuntimeException("You entered a null string");
		}
	}
	
	
	
}
