import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class test {
	

	public static void writer(String toPrint, String url)
	{
		try {
			File newfile= new File(url);
			if(newfile.exists()) {
				writerAppendNextLine(toPrint, url);
			}
			else {
				newfile.createNewFile();
			}
			FileWriter filew= new FileWriter(newfile);
			BufferedWriter writer = new BufferedWriter(filew);
			writer.write(toPrint);
			writer.newLine();
			writer.close();
		}
		catch (IOException e){
			System.out.println("problem in writer");
			
		}
		
	}
	
	
	public static void writerAppendNextLine(String toPrint, String url)
	{
		try {
			File newfile= new File(url);
			if(!newfile.exists()) {
				writer(toPrint, url);
			}
			FileWriter filew = new FileWriter(url,true);
			BufferedWriter writer = new BufferedWriter(filew);
			writer.write(toPrint);
			writer.newLine();
			writer.close();
		}
		catch (IOException e){
			System.out.println("problem in writerAppendNextLine");
			
		}
		
	}
	
	public static void main(String[] args) {
		
	
		
	//	BloomFilter A= new BloomFilter(10, "C:/Users/Yuval Kashi/Documents/projects/ASS4DS/src/hash_functions.txt");
	//	System.out.println("user.dir: "+System.getProperty("user.dir"));
		BloomFilter A= new BloomFilter("32", System.getProperty("user.dir")+"/hash_functions.txt");
		/*
		
		System.out.println("=====Test for functionsToString: ===");
		A.functionsToString();
		
		System.out.println();
		System.out.println("=====Test for HornerConvert: ===");
		long result=Methods.HornerConvert("ABXY");
		if(result==10783543)
			System.out.println("pass");
		else if(result==1094867033)
			System.out.println("result is with no modulu %: "+result );
		else 
			System.out.println("fail & result: "+result );
		System.out.println();
		*/
		System.out.println("=====Test for BloomFilter: ===");
		A.updateTable(System.getProperty("user.dir")+"/bad_passwords.txt");
		A.toStringBloom();
		
		System.out.println();
		
		System.out.println("=====Test for HushTable: ===");
		
		
		HashTable hashTable = new HashTable("32");
		hashTable.updateTable(System.getProperty("user.dir")+"/bad_passwords.txt");
		hashTable.toStringhashTable();
		
		
		System.out.println("========FalsePositivePercentage is:=========");
		System.out.println(A.getFalsePositivePercentage(hashTable,System.getProperty("user.dir")+"/requested_passwords.txt"));
		System.out.println();
		System.out.println("=====RejectedPasswordsAmount is:=====");
		System.out.println(A.getRejectedPasswordsAmount(System.getProperty("user.dir")+"/requested_passwords.txt").contentEquals("14"));
		System.out.println(A.getRejectedPasswordsAmount(System.getProperty("user.dir")+"/requested_passwords.txt"));
		
		
		System.out.println();
		System.out.println();
		
		
		
		System.out.println("=====Test for BTree Insertion: ===");
		
		BTree btree = new BTree("2");
		btree.createFullTree(System.getProperty("user.dir")+"/bad_passwords.txt");
		System.out.println();
		System.out.println(btree.toString());
		System.out.println();
		
		
		System.out.println("=====Test for BTree Search: ===");
		
				
		SearchResultPair result2= btree.search("Loggggggggggg");
		System.out.println(result2==null);
		
		
		
		LinkedList<String> lines = Methods.reader(System.getProperty("user.dir")+"/bad_passwords.txt");
		for(String line: lines) {
			System.out.println(line + ": " );
			SearchResultPair result= btree.search(line);
			System.out.println(Arrays.toString(result.getNode().passwords) + " -> found in index: " + result.getIndex());
			System.out.println();
		}
		
		
		System.out.println("=====Test for BTree inOrder: ===");
		System.out.println(btree.toString().equals("111111_2,123456_1,donald_2,google_0,hellow_2,login_1,password_2,querty_1,starwars_2,welcome_2,zxcvbnm_2"));
		 
		
		System.out.println();
		System.out.println("=====Test for BTree SEARCH: ===");
		String toPrint = btree.getSearchTime(System.getProperty("user.dir")+"/requested_passwords.txt")+"_"+hashTable.getSearchTime(System.getProperty("user.dir")+"/requested_passwords.txt");
		writerAppendNextLine(toPrint,System.getProperty("user.dir")+"/MyOutput1Test.txt");
		
		
		System.out.println("=======!  Test for DELETion: !======");
		btree.deleteKeysFromTree(System.getProperty("user.dir")+"/delete_keys.txt");
		
		
		
		System.out.println();
		System.out.println("=======!  Test for OutputFile Looks: !======");
		LinkedList<String> lines2 = Methods.reader(System.getProperty("user.dir")+"/MyOutput1Test.txt");
		for(String line: lines2) {
			System.out.println(line);
		}
		
		
	}
}
