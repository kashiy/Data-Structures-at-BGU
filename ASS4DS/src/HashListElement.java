
public class HashListElement{
	
	// ---------------------- fields ----------------------
	private int key;
    private HashListElement next;
    
    // ---------------------- constructors ----------------------
	public HashListElement(int key, HashListElement next) {
		this.key = key;
        this.next = next;
	}
	

    public HashListElement(int key) {
    	this(key, null);
    }

    // ---------------------- Methods ----------------------
    public HashListElement getNext() {
        return next;
    }

    public void setNext(HashListElement next) {
        this.next = next;
    }

    public int getData() {
        return key;
    }

    public int setData(int key) {
        int tmp = this.key;
        this.key = key;
        return tmp;
    }

    public String toString() {
        return String.valueOf(key);
    }
}