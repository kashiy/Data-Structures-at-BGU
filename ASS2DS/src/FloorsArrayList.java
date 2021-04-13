public class FloorsArrayList implements DynamicSet {
	public FloorsArrayLink first,last;
	private int maxLinksNum;
	private int floorsSize;

    public FloorsArrayList(int N){
    	this.maxLinksNum = N; //initialize value
    	first=new FloorsArrayLink(Double.NEGATIVE_INFINITY , maxLinksNum+1); //POSITIVE_INFINITY with more than N cells
    	last=new FloorsArrayLink(Double.POSITIVE_INFINITY, maxLinksNum+1);   //NEGATIVE_INFINITY with more than N cells
    	for(int i=1; i<=maxLinksNum+1; i=i+1) {
    		first.setNext(i, last);
    		last.setPrev(i, first);
    	}
    	
    	this.floorsSize=0; //starts in plus minus infinity but it says that the size is 0
    }

    @Override
    public int getSize(){
    	//return the field
        return floorsSize;
    }
   
    @Override
    public void insert(double key, int arrSize) {
    	
    	FloorsArrayLink prev=first;//minus infinity
    	FloorsArrayLink input=new FloorsArrayLink(key , arrSize);
    	FloorsArrayLink next;
    	//need floorsSize+1
    	for (int i=input.arrSize; i>=1; i=i-1) {
    		if(first.getNext(i)==last) {
    			input.setNext(i, last);
        		first.setNext(i, input);
        		input.setPrev(i, first);
        		last.setPrev(i, input);
    		}
    		else {			
		    		if(prev.getKey()<key) {//if you less than the key search right place, first time you are minus inf
		    			while(prev.getNext(i).getKey()<=key) {
			    				prev=prev.getNext(i); //the next link
		    			}
		    			input.setNext(i, prev.getNext(i));
		        		next=prev.getNext(i); //save front , relay on the ADT that i find the correct place for next by arrsize
		        		prev.setNext(i, input);
		        		input.setPrev(i, next.getPrev(i));
		        		next.setPrev(i, input);
		    		}
		    	
    		}
    	}
    }

    @Override
    public void remove(FloorsArrayLink toRemove) {
    	//minus -1 to floorsSize
    	
    	floorsSize=floorsSize-1;
    	for (int i=1; i<=toRemove.arrSize; i=i+1) {
    		toRemove.getPrev(i).setNext(i, toRemove.getNext(i));
    		toRemove.getNext(i).setPrev(i, toRemove.getPrev(i));
    	}
    	
    }

    @Override
    public FloorsArrayLink lookup(double key) {
        
    	
    	FloorsArrayLink current=first;//minus infinity
    	int innersize=current.getArrSize();
    	
    	while (innersize!=0) { //if the inner size is 0 we know that we tried all the next values of this link, and key not found
    		if(current.getKey()==key) {
    			return current;
    		}
    		else if(current.getKey()<key) {//if you less than the key
    			if(current.getNext(innersize).getKey()<=key) { // if the next is <= so go to it and change to new innersize
    				current=current.getNext(innersize); //the next link
    				innersize=current.getArrSize();
    			}
    			else { // if the next is bigger so go to down the array
    				innersize=innersize-1;
    				}
    		}
    	}
        return null;
    }

    @Override
    public double successor(FloorsArrayLink link) {
    	//use forwards array in cell 0
    	
    	if (link.getNext(1) != null)//this is shit
    		return link.getNext(1).getKey();
    	return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double predecessor(FloorsArrayLink link) {
    	//use backwards array in cell 0
    	
    	if (link.getPrev(1) != null)
    		return link.getPrev(1).getKey();
    	return Double.POSITIVE_INFINITY;
    }

    @Override
    public double minimum() {
        //@TODO: implement
    	if (floorsSize==0)
    		return Double.POSITIVE_INFINITY;
    	return first.getNext(1).getKey();
    }

    @Override
    public double maximum() {
        //@TODO: implement
    	return last.getPrev(1).getKey();
    }
    
    public String toString() {
    	FloorsArrayLink link=first;
    	String output= first.toString()+ "\n";
    	
    	while (link.getNext(1) != last) {
    		link=link.getNext(1);
    		output= output + link.toString()+ "\n";
    	}
    	return output+ last.toString();
    }
}
