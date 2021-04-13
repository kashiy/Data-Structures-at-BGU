

public class BTreeNode {
	protected int keysSum;
	public String[] passwords;
	protected BTreeNode[] pointers;
	protected boolean isLeaf;
	protected int t;
	public boolean isRoot;
	
	
	
	/**
	 * 
	 * Constructor of the BTreeNode
	 * 
     * @param tVal size of t
     * 
     */
	public BTreeNode(int tVal) {
        this.keysSum=0;
        this.t=tVal;
        this.passwords=new String[2*t+1];
        this.pointers= new BTreeNode[2*t+1];
        this.isLeaf=true;
        this.isRoot=false;
        
    }
	
	/**
	 * 
	 * sets the field this.isLeaf according to the check if this is a leaf
     * 
     */
	private void isleaf() {
		this.isLeaf=true;
		for(int i=1; i<=keysSum+1;i++) {
			if(pointers[i] != null) {				
				isLeaf=false;
				break;
			}
		}
	}
	
	
	/**
	 * 
	 * sets the field this.isRoot to be true
     * 
     */
	public void letItBeRoot() {
		this.isRoot=true;
	}
	
	/**
	 * 
	 * insersts the password for a non full node scenario
	 * 
     * @param newPassword
     * 
     */
	public void insertNonFull(String newPassword) {
		int i=keysSum;		
		if (isLeaf) {			
			while(i>=1 && newPassword.compareTo(passwords[i])<0) {				
				passwords[i+1]=passwords[i];
				i=i-1;
			}
			passwords[i+1]=newPassword;
			keysSum = keysSum+1;
		}
		else {
			insertNonFullToNonLeaf( i , newPassword);
			
		}
				
	}
	
	
	/**
	 * 
	 * when the node is not a leaf insert the password for a non full node scenario that is not a leaf
	 * 
	 * @param i that is the keysSum from the insertNonFull function
     * @param newPassword
     * 
     */
	public void insertNonFullToNonLeaf(int i, String newPassword) {
			while(i>=1 && newPassword.compareTo(passwords[i])<0) {
				i=i-1;
				
			}
			i=i+1;
			if (pointers[i].keysSum == (2*t-1)) {
				splitChild(i);
				isLeaf=false; //must
				if(newPassword.compareTo(passwords[i])>0) {
					i=i+1;
				}
			}
			pointers[i].insertNonFull(newPassword);
			
	}
	
	
	/**
	 * 
	 * splits the Child at index i
	 * 
     * @param i index of the child to split
     * 
     */
	public void splitChild(int i) {
		BTreeNode y= pointers[i];
		BTreeNode z=new BTreeNode(t);
		z.isLeaf=y.isLeaf;
		z.keysSum=t-1;
		for(int j=1; j<=t-1; j=j+1) {
			z.passwords[j]=y.passwords[j+t];
		}
		if (!y.isLeaf) {
			for(int j=1; j<=t; j=j+1) {
				z.pointers[j]=y.pointers[j+t];
			}
		}
		
		doTheChangesAtTheFatherOfYandZ( i , y , z);
		
		y.keysSum=t-1;
	}
	
	
	/**
	 * 
	 * make the changes at this father node of y and z
	 * 
     * @param i index of the child to split
     * @param y the node that is splitted
     * @param z the new node
     * 
     */
	public void doTheChangesAtTheFatherOfYandZ(int i, BTreeNode y, BTreeNode z) {
		for(int j=keysSum+1; j>=(i+1); j=j-1) {
			pointers[j+1]=pointers[j];
		}
		pointers[i+1]=z;
		for(int j=keysSum; j>=i; j=j-1) {
			passwords[j+1]=passwords[j];
		}
		passwords[i]=y.passwords[t];
		keysSum=keysSum+1;
		
	}
	
	/**
	 * 
	 * shifthig one password From Left Brother To Right
	 * 
     * @param i index of the node at the father
     * 
     */
	public void shifthigFromLeftBroToRight(int i) {
		BTreeNode v= pointers[i]; //me
		BTreeNode u= pointers[i-1]; //my left brother
				
		//shift from left brother to right
		if (u != null && u.keysSum>=t) {

			for(int j=v.keysSum; j>=1; j=j-1) { //move forwards
				v.passwords[j+1]=v.passwords[j];
				v.pointers[j+2]=v.pointers[j+1];
			}
			v.pointers[2]=v.pointers[1];//another one for pointers
			v.passwords[1]=passwords[i-1]; //from father to right sun
			v.pointers[1]=u.pointers[u.keysSum+1];
			passwords[i-1]=u.passwords[u.keysSum];//from left sun to father
			v.keysSum= v.keysSum +1;
			u.keysSum= u.keysSum -1;
		}
	}
	
	
	/**
	 * 
	 * shifthig one password From Right Brother To Left
	 * 
     * @param i index of the node at the father
     * 
     */
	public void shifthigFromRightBroToLeft(int i) {
		BTreeNode v= pointers[i]; //me
		BTreeNode z= pointers[i+1]; //my right brother
		
		//shift from right brother to left
		if (z != null && z.keysSum>=t){
			
			v.passwords[v.keysSum +1]=passwords[i]; //from father to left sun
			v.pointers[v.keysSum +2]=z.pointers[1];
			passwords[i]=z.passwords[1];//from right sun to father
			v.keysSum= v.keysSum +1;
			for(int j=1; j<z.keysSum; j=j+1) {
				z.passwords[j]=z.passwords[j+1];
				z.pointers[j]=z.pointers[j+1];
			}
			z.pointers[z.keysSum]=z.pointers[z.keysSum+1];//another one for pointers
			z.keysSum= z.keysSum -1;
		
		}
	}
	
	/**
	 * 
	 * Merge the node With Right Brother
	 * 
     * @param i index of the node at the father
     * 
     */
	//K is in place i, need to merge his two chiled
	public void MergeWithRightBrother(int i) {
		//move all to y
		BTreeNode y= pointers[i];// me
		BTreeNode z= pointers[i+1]; // right brother
		
		
		for(int j=1; j<=t-1; j=j+1) { //move passwords of brother
			y.passwords[j+t]=z.passwords[j];
		}
		y.passwords[t]=passwords[i];
		if (!z.isLeaf) { //move pointers of brother
			for(int j=1; j<=t; j=j+1) {
				y.pointers[j+t]=z.pointers[j];
			}
		}
		
		MergeWithRightBrotherActionsOnFather( i, y, z);
		
		y.keysSum=2*t-1;
		y.isleaf();
		
	}
	
	/**
	 * 
	 * Actions On Father after the merging
	 * 
     * @param i index of the node at this father
     * @param y the node in place i
     * @param z the right brother
     * 
     */
	private void MergeWithRightBrotherActionsOnFather(int i, BTreeNode y, BTreeNode z) {
		
		if((keysSum-1)!=0) { //so it is not root
			
			for(int j=i+1; j<=keysSum; j=j+1) { //move pointers of father
				pointers[j]=pointers[j+1];
			}
			
			for(int j=i; j<keysSum; j=j+1) { //move passwords of father
				passwords[j]=passwords[j+1];
			}
			keysSum=keysSum-1; //keysSum-1 for father
			
		}
		else {
			keysSum=keysSum-1;
			y.letItBeRoot();
			z.isRoot=false;
			
		}
		
	}
	
	
	/**
	 * 
	 * Merge the node With Left Brother
	 * 
     * @param i index of the node at the father
     * 
     */
	//K is in place i, need to merge his two chiled
	public void MergeWithLeftBrother(int i) {
		//move all to y
		BTreeNode y= pointers[i]; //me
		BTreeNode z= pointers[i-1]; //left brother
		
		
		for(int j=1; j<=t-1; j=j+1) { //move passwords of me
			z.passwords[j+t]=y.passwords[j];
		}
		z.passwords[t]=passwords[i-1];
		
		y.passwords=z.passwords; //take all passwords and put in me
		
		if (!y.isLeaf) { //move pointers of me
			for(int j=1; j<=t; j=j+1) {
				z.pointers[j+t]=y.pointers[j];
			}
		}
		
		y.pointers=z.pointers;//take all pointers and put in me , now y is actually z. so y is the merged
		
		MergeWithLeftBrotherActionsOnFather( i, y, z);
		
		y.keysSum=2*t-1;
		y.isleaf();
	}
				
	
	/**
	 * 
	 * Actions On Father after the merging
	 * 
     * @param i index of the node at this father
     * @param y the node in place i
     * @param z the left brother
     * 
     */
	
	public void MergeWithLeftBrotherActionsOnFather(int i, BTreeNode y, BTreeNode z) {
		//move all to y
		
		if((keysSum-1)!=0) { //so it is not root
			for(int j=i-1; j<=keysSum; j=j+1) { //move pointers of father
				pointers[j]=pointers[j+1];
			}
			
			for(int j=i-1; j<keysSum; j=j+1) { //move passwords of father
				passwords[j]=passwords[j+1];
			}
			keysSum=keysSum-1; //keysSum-1 for father
		}
		else { //so it is root
			keysSum=keysSum-1;
			y.letItBeRoot();
			z.isRoot=false;
			
		}
	}
		
	/**
	 * 
	 * Max Of Predecessor will go to father, change in this node and the delete will be recursive
	 * 
     * @param i index of the node at the father
     * 
     */
	//gets the actual place in internal node where key is there
	public void MaxOfPredecessor(int i) { 
		BTreeNode y= pointers[i];		
		String max=y.passwords[keysSum];
		y.delete(max, this, keysSum);
		passwords[i]=max;
	}
	
	
	/**
	 * 
	 * Min Of Successor will go to father, change in this node and the delete will be recursive
	 * 
     * @param i index of the node at the father
     * 
     */
	//gets the actual place in internal node where key is there
	public void MinOfSuccessor(int i) { 
		BTreeNode y= pointers[i+1];		
		String min=y.passwords[1];
		y.delete(min, this, 1);
		passwords[i]=min;
	}
	
	
	/**
	 * 
	 * Min Of Successor will go to father, change in this node and the delete will be recursive
	 * 
	 * @param toDelete password to delete
     * @param father father of this node
     * @param myIndexAtFather Index of this node At Fathers pointers
     * 
     * 
     */
	public void delete(String toDelete, BTreeNode father, int myIndexAtFather) {

		if(father!=null){	//root is the only time when it is null
		//1.If node X has less than t keys (t-1 keys) //# SEND the FATHER for fixing tree structure! 
			if(keysSum==(t-1)) {
				shitingOrMergingIfThereIsMinimalSumOfKeys(father, myIndexAtFather);
			}
		}
		int i=1;
		while(i<keysSum && passwords[i].compareTo(toDelete)<0) { //seach if toDelete exists
			i=i+1;
		}
		//steps 2,3 inside the node
		if(passwords[i].compareTo(toDelete)==0) {
			
			toDeleteIsInNode(toDelete, father, i);
		}
		//4.else - k is not in X
		else {
			if(isLeaf==false) {
				if(passwords[i].compareTo(toDelete)<0) {
					i=i+1;
					pointers[i].delete(toDelete, this, i); //current will be the next father
				}
				else { // so it is > because already checked it is not </=
					
					pointers[i].delete(toDelete, this, i); //current will be the next father
				}
			}
		}
	}
	
	
	/**
	 * 
	 * Collections of steps when 1.If node X has less than t keys
	 * 
     * 
     */
	private void shitingOrMergingIfThereIsMinimalSumOfKeys(BTreeNode father, int myIndexAtFather) { 
		
		//Please keep in mind that all the range checks for the myIndexAtFather are a must for the function not to fail
		
		//shifting - if node X has a sibling Y with t keys (left/right)		
		
		if(((myIndexAtFather-1)>=1 & (myIndexAtFather-1)<=2*t) && father.pointers[myIndexAtFather-1] != null && father.pointers[myIndexAtFather-1].keysSum>=t) {
			
			father.shifthigFromLeftBroToRight(myIndexAtFather);
		}
		else if(((myIndexAtFather+1)>=1 & (myIndexAtFather+1)<=2*t) && father.pointers[myIndexAtFather+1] != null && father.pointers[myIndexAtFather+1].keysSum>=t) {
			
			father.shifthigFromRightBroToLeft(myIndexAtFather);
			
		}
		//merging - node X has both siblings with only t-1 keys
		else {
			if(((myIndexAtFather-1)>=1 & (myIndexAtFather-1)<=2*t) && father.pointers[myIndexAtFather-1] != null) {
				father.MergeWithLeftBrother(myIndexAtFather);		
				
			}
			else if(((myIndexAtFather+1)>=1 & (myIndexAtFather+1)<=2*t) && father.pointers[myIndexAtFather+1] != null) {
				
				father.MergeWithRightBrother(myIndexAtFather);
				}
			
		}
	}
	
	/**
	 * 
	 * Collections of steps 2,3 when todelete inside the node
     * 
     */
	//this function handle when toDelete Is In this Node
	private void toDeleteIsInNode(String toDelete, BTreeNode father, int i) { 
		//int i is the index of K in the node
		if(isLeaf==false) {
			
			//2. if k is in X and X is an internal node:
					//at least t keys:
					//	Max from predeccesor
					if(pointers[i] != null && pointers[i].keysSum>=t) {
						
						MaxOfPredecessor(i);
					}
					// or min from successor
					
					else if(pointers[i+1] != null && pointers[i+1].keysSum>=t) {
						
						MinOfSuccessor(i);
					}
				//	merging - node X has both siblings with only t-1 keys
					else {
						//less than t, both Y and Z have t-1 keys
						//same as merging but send to another delete 
						
						MergeWithRightBrother(i); //merge y son with z son and x between
						pointers[i].delete(toDelete, this, i);
						isleaf();
					}
				}
			//3. else if k is in X and X is a leaf node:
				else {
					//delete k
					for(int j=i; j<keysSum; j=j+1) {
						passwords[j]=passwords[j+1];
						
					}
					keysSum=keysSum-1;
				}
	}
	
	/**
	 * 
	 * returns a Search Result Pair object if the password was found
	 * 
     * @param Password
     * 
     */
	public SearchResultPair search(String Password) {
		Password=Password.toLowerCase();		
		int i=1;
		while (i<=getKeysSum() && Password.compareTo(passwords[i])>0) {
			i=i+1;
			
		}
		if (i<=getKeysSum() && Password.compareTo(passwords[i])==0) 
			return new SearchResultPair(this, i);
		else if (isLeaf) {	
			return null;
		}
		else {			
			return pointers[i].search(Password);
		}
	}
	
	
	/**
	 * 
	 * getter for Keys Sum in the node
     * 
     */
	public int getKeysSum() {
		return keysSum;
	}
	
	/**
	 * 
	 * setter for Pointer, works on the node when you run node.setPointer
	 * 
	 * @param node that you want to set as a the pointer
	 * @param index of where you want to set the pointer
     * 
     */
	public void setPointer(BTreeNode node, int index) {
		pointers[index]= node;
		isleaf();
	}
	
	/**
	 * 
	 * toString for the node and depth
	 * 
	 * @param depth you should start in 0
	 * @param output you should start with ""
	 * 
	 * @return output the string of the passwords with the depth of node where they exist
     * 
     */
	public String toString(int depth,String output) { // start in depth 0 from the root and "" for the output
    	
		if (isLeaf) {
			String temp1="";
			for(int i=1; i<=keysSum; i=i+1) {
				temp1= temp1 + passwords[i] + "_" + depth + ",";
			}
			
			output =output+temp1;
		}
		else {
			String temp2="";
			for(int i=1; i<=keysSum+1; i=i+1) {
				temp2= temp2 + pointers[i].toString(depth+1,output);
				if(i != keysSum+1) {
					temp2= temp2 + passwords[i] + "_" + depth + ",";
				}
			}
			output =output+temp2;
		}
		
    	return output; 
    }
}
