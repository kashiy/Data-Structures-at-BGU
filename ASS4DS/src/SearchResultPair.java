
public class SearchResultPair {

	private BTreeNode node ;
	private int index ;
	
	
	/**
	 * 
	 * constructs pair for Search Result
	 * 
     * @param node
     * @param index of String in array
     * 
     */
	public SearchResultPair(BTreeNode node, int index) {
		this.node=node;
		this.index=index;
	}
	
	public BTreeNode getNode() {
		return node;
	}
	
	public int getIndex () {
		return index;
	}
	
}
