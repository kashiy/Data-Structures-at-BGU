
public class HashTable {
	private int m2Size;
	private HashList[] hashTable;
	
	
	/**
	 * 
	 * Constructor of the hashTable
	 * 
     * @param m2 size of hashTable
     * 
     */
	public HashTable(String m2) {
		Methods.isIllegalString(m2);
		this.m2Size= Integer.parseInt(m2);
		Methods.isIllegalInt(m2Size);
		hashTable = new HashList[m2Size];
		for(int i=0; i<hashTable.length; i++) {
			hashTable[i]=new HashList();
		}
	}
	
	
	/**
	 * 
	 * adds the key to the hashTable
	 * 
     * @param key
     * 
     */
	public int hashFunction(int key) {
		int indexValueResult= key % m2Size;
		return indexValueResult;
	}
	
	
	/**
	 * 
	 * updateTable with the bad_passwords keys to the table
	 * 
     * @param url of the bad_passwords.txt
     * 
     */
	public void updateTable(String url){
		Methods.isIllegalString(url);
		LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
				
				long key= Methods.HornerConvert(line);
				int indexValueResult= hashFunction((int) key);
				hashTable[indexValueResult].addFirst((int) key);
		}
	}
	
	/**
	 * 
     * prints the hashTable for my tests
     * 
     */
	public void toStringhashTable() {
		for( HashList list: hashTable) {
			System.out.println(list.toString());
		}
	}
	
	/**
	 * 
     * returns the getHashList for my tests
     * 
     */
	public HashList[] getHashList() {
		return hashTable;
	}
	
	
	/**
	 * 
	 * returns a string of Search Time in hushtable for all the passwords
	 * 
     * @param url of the requested passwords
     * 
     */
	public String getSearchTime(String url) {
		Methods.isIllegalString(url);
		long start = System.nanoTime();
    	LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
			searchInHash(line);
		}
		long end = System.nanoTime();
		return Double.toString((end-start)/1000000.0).substring(0, 6);
	}
	
	
	/**
	 * 
     * @param password string
     * 
     */
	private String searchInHash(String password) {
		
		long key= Methods.HornerConvert(password);
		return isInHash((int) key) ;
	}
	
	/**
	 * Checks if the key exists by hashindex in the HashTable
	 * 
     * @param key of the password
     * 
     */
	private String isInHash(int key){
		
		int index = hashFunction(key);
		boolean isBad = getHashList()[index].contains(key);
		return getHashList()[index].toString() +" is there " +key+ " ?"  + isBad;
	}
	
		
	
}
