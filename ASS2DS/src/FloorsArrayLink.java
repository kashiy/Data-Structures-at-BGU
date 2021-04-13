public class FloorsArrayLink {
    //@TODO: add fields
	double key;
	int arrSize;
	FloorsArrayLink[] forwards;
	FloorsArrayLink[] backwards;
	
    public FloorsArrayLink(double key, int arrSize){
        //@TODO: implement
    	this.key=key;
    	this.arrSize=arrSize;
    	this.forwards=new FloorsArrayLink[arrSize];
    	this.backwards=new FloorsArrayLink[arrSize];
    }

    public double getKey() {
        //return the key
        return key;
    }

    public FloorsArrayLink getNext(int i) {
        //pull from memory of array in O(1), and if illegal index return null
    	if(i>arrSize | i<1)
    		return null;
    	else
    		return forwards[i-1];
    }

    public FloorsArrayLink getPrev(int i) {
    	//pull from memory of array in O(1), and if illegal index return null
    	if(i>arrSize | i<1)
    		return null;
    	else
    		return backwards[i-1];
    }

    public void setNext(int i, FloorsArrayLink next) {
        //if i is a legal index so change the pointer to next, otherwise do nothing
    	if(i>=1 & i<=arrSize)
    		forwards[i-1]=next;
    }

    public void setPrev(int i, FloorsArrayLink prev) {
    	//if i is a legal index so change the pointer to prev, otherwise do nothing
    	if(i>=1 & i<=arrSize)
    		backwards[i-1]=prev;
    }

    public int getArrSize(){
        //return arrSize
        return arrSize;
    }
    
    //homemade
    public String toString() {
        return "key: " + key + ", arrSize: " + arrSize;
    }
}

