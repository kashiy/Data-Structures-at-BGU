import java.util.Arrays;

public class BloomFilter {
	
	private LinkedList<int[]> AiBiValues;
	private int m1Size;
	private String url;
	private int[] bloomArray;
	private int CounterbadRequestedPassInBloom;
	private double FalsePositive;
	
	/**
	 * 
	 * constructs the Bloom filter using the bad passwords from txt file on the functions.
	 * 
     * @param m1 BloomFilter size
     * @param url of the functions
     * 
     */
	public BloomFilter(String m1, String url) {
		Methods.isIllegalString(m1);
		Methods.isIllegalString(url);
		this.m1Size= Integer.parseInt(m1);
		Methods.isIllegalInt(m1Size);
		this.url=url;
		bloomArray = new int[m1Size];
		for(int i=0 ;i<m1Size ;i=i+1) {
			bloomArray[i]= 0;
		}
				
		this.AiBiValues =new LinkedList<int[]>();
		FunctionsReader();
	}
	
	
	//public static void main(String[] args) {
	
	/**
	 * 
	 * reads the file and add int[] 2slots arrays to LinkedList of functions
	 * 
	 */
	private void FunctionsReader() {
		LinkedList<String> lines = Methods.reader(url);
		String[] tempLineValues;
		
		for(String line: lines) {
			tempLineValues=line.split("_");
			int[] IntegerValues= new int[2];
			IntegerValues[0]= Integer.parseInt(tempLineValues[0]);
			IntegerValues[1]= Integer.parseInt(tempLineValues[1]);
			AiBiValues.add(IntegerValues);
		}
	}
	
	/**
	 * 
	 * Prints the pairs of the ai,bi of the functions.
     * 
     */
	public void functionsToString() {
		int i=1;
		for(int[] link: AiBiValues) {
			System.out.println("Function "+i+" is:  A: "+link[0]+" & B: "+link[1]);
			i++;
		}
	}
		
	
	
	
	/**
	 * running the key of the password on all the functions and calls addToFilterBloomArray function
	 * 
     * @param key of the password
     * 
     */
	private void runFunctions(int key){
		
		for(int[] link: AiBiValues) {
			int ai=link[0];
			int bi=link[1];
			int indexValueResult= ((ai*key+bi) % (15486907)) % m1Size;
			addToFilterBloomArray(indexValueResult);
		}
	}
	
	/**
	 * It adds the values to the bloomfilter according to index
	 * 
     * @param index of where i should add 1 to the array
     * 
     */
	private void addToFilterBloomArray(int index){
		bloomArray[index]= 1;
	}
	
	
	/**
	 * constructs the Bloom filter.
	 * It reads the bad passwords from txt file on the functions,
	 * then it adds the values to the bloomfilter
	 * 
     * @param url of the bad_passwords.txt
     * 
     */
	public void updateTable(String url){
		Methods.isIllegalString(url);
		LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
				long key= Methods.HornerConvert(line);
				runFunctions((int) key);
			}
	}
	
	
	
	
	/**
	 * prints the BloomFilter
     * 
     */
	public void toStringBloom() {
		System.out.println(Arrays.toString(bloomArray));
			
	}
	
	
	/**
	 * Checks if the key exists by all functions in the bloomfilter
	 * 
     * @param key of the password
     * 
     */
	private boolean isBadByBloom(int key){
		boolean isBad=true;
		for(int[] link: AiBiValues) {
			int ai=link[0];
			int bi=link[1];
			int indexValueResult= ((ai*key+bi) % (15486907)) % m1Size;
			if(bloomArray[indexValueResult]== 0) {
				isBad=false;
				break;
			}
		}
		return isBad;
	}
	
	
	/**
	 * Checks if the key exists by hashindex in the HashTable
	 * 
     * @param key of the password
     * 
     */
	private boolean isBadByHash(int key, HashTable hashTable){
		
		int index = hashTable.hashFunction(key);
		boolean isBad = hashTable.getHashList()[index].contains(key);
		return isBad;
	}
	
		
	/**
	 * calculate the False Positive Percentage by taking the FalseReject passwords by bloom and devide in sum of the good in Hash
	 * 
     * @param hashTable
     * @param url of the requested_passwords.txt
     * 
     */
	public void calculateFalsePositivePercentage(HashTable hashTable, String url) {
		double sumGoodInHash=0, FalseReject=0;
		LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
				long key= Methods.HornerConvert(line);
				if(!isBadByHash((int) key, hashTable)) { //count the good passwords according to HASH
					sumGoodInHash= sumGoodInHash+1;
					if(isBadByBloom((int) key)) { //if this Password also bad by bloom, sum it up by FR counter
						FalseReject = FalseReject + 1;
					}
				}
		}
		this.FalsePositive= FalseReject/sumGoodInHash;
	}
	
	/**
	 * a getter of the False Positive Percentage
	 * 
     * @param hashTable
     * @param url of the requested_passwords.txt
     * 
     */
	public String getFalsePositivePercentage(HashTable hashTable, String url) {
		Methods.isIllegalString(url);
		calculateFalsePositivePercentage(hashTable, url);
		return Double.toString(FalsePositive);
	}
		
		
	
	/**
	 * calculate the sum of the bad Requested Passwards by bloom
	 * 
     * @param url of the requested_passwords.txt
     * 
     */
	public void InitializeCounterOfbadRequestedPasswards(String url) {
		CounterbadRequestedPassInBloom=0;
		LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
				long key= Methods.HornerConvert(line);
				if(isBadByBloom((int) key))
					CounterbadRequestedPassInBloom = CounterbadRequestedPassInBloom + 1;
		}
	}
	
	
	/**
	 * a getter Rejected Passwords Amount (rejected by bloom)
	 * 
     * @param url of the requested_passwords.txt
     * 
     */
	public String getRejectedPasswordsAmount(String url) {
		Methods.isIllegalString(url);
		InitializeCounterOfbadRequestedPasswards(url);
		return Integer.toString(CounterbadRequestedPassInBloom);
	}
}
