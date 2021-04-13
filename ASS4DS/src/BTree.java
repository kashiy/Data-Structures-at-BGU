

public class BTree {
	
	protected BTreeNode root;
	protected int t;

	
	/**
	 * 
	 * Constructor of the BTree
	 * 
     * @param tVal size of t
     * 
     */
    public BTree(String tVal) {
    	Methods.isIllegalString(tVal);
        root = null;
        this.t = Integer.parseInt(tVal);
        Methods.isIllegalInt(t);
        root = new BTreeNode(t);
     
        
    }
    
    /**
	 * 
	 * fills up the BTree
	 * 
     * @param url to get the passwords from txt
     * 
     */
    public void createFullTree(String url) {
    	Methods.isIllegalString(url);
    	LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
			insert(line.toLowerCase());
		}
    }
    
    
    /**
	 * 
	 * is Empty BTree
	 * 
     * @return boolean
     * 
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    
    /**
	 * 
	 * insert to the BTree
	 * 
     * @param password
     * 
     */
    public void insert(String password) {
        if (root.getKeysSum()==2*t-1) {
        	BTreeNode newroot = new BTreeNode(t);
        	root.isRoot=false;
        	newroot.setPointer(root, 1);
        	newroot.letItBeRoot();
        	root = newroot;
        	root.splitChild(1);        	
        	root.insertNonFull(password);
        }
        else {        	
        	root.insertNonFull(password);
        }        
    }
    
    
    
    /**
	 * 
	 * delete all passwords in the txt from the BTree
	 * 
     * @param url to get the passwords to delete
     * 
     */
    public void deleteKeysFromTree(String url) {
    	Methods.isIllegalString(url);
    	LinkedList<String> lines = Methods.reader(url);
		for(String line: lines) {
			remove(line.toLowerCase());
			checkIfRootEmpty();
		}
				
    }
    
    
    /**
	 * 
	 * check If Root Empty - if it is empty you need to point on a new root of the tree.
	 * this method search in the root is one of the sons and changes respectively.
	 * 
     * 
     */
    public void checkIfRootEmpty() {
    	if(root.keysSum==0) {
    		for(BTreeNode son: root.pointers) {
    			if(son!=null) {
    				if(son.isRoot==true ) {
    					this.root=son;
    				}
    			}
    		}
    	}
    }
    
    /**
	 * 
	 * remove from the BTree
	 * 
     * @param password toRemove
     * 
     */
    public void remove(String toRemove) {
        if (!isEmpty()) {    	
            	root.delete(toRemove, null, 0);
            }
    }
    
    /**
	 * 
	 * 
	 * 
     * @return toString of the tree 
     * 
     */
    public String toString() {
    	
    	if (!isEmpty() && root.keysSum!=0) {
    		return root.toString(0, "").substring(0, root.toString(0, "").length()-1);
    	}
    	else {
    		return "";
    	}
    }
    
    /**
	 * 
	 * returns a string of Search Time in btree for all the passwords
	 * 
     * @param url of the requested passwords
     * 
     */
    public String getSearchTime(String url) {
    	Methods.isIllegalString(url);
    	LinkedList<String> lines = Methods.reader(url);
    	long start = System.nanoTime();
		for(String line: lines) {
			SearchResultPair result= search(line);
		}
		long end = System.nanoTime();
		return Double.toString((end-start)/1000000.0).substring(0, 6);
    }
    
    
    /**
	 * 
	 * searches in the btree
	 * 
     * @return SearchResultPair or null if empty tree
     * 
     */
    public SearchResultPair search(String Password) {
        if (isEmpty())
            return null;
        else {        	
            return root.search(Password);
        }
        	
    }
    
}
