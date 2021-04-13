
public class test {
	
	public static void main(String[] args) {
		/*
		//FloorsArrayList list = new FloorsArrayList(5);
		FloorsArrayLink first= new FloorsArrayLink(Double.NEGATIVE_INFINITY, 3);
		FloorsArrayLink last= new FloorsArrayLink(4, 3);
		first.setNext(1, last);
		System.out.println(first.getNext(1).toString());
		first.setNext(2, last);
		System.out.println(first.getNext(2));
		//System.out.print(last.getKey());
		*/
		
		//need to test all list functions
		/*
		FloorsArrayList list=new FloorsArrayList(3);
		list.insert(1, 1);
		list.insert(3, 2);
		list.insert(4, 3);
		list.insert(8, 1);
		list.insert(16, 1);
		System.out.println(list.toString());
		
		System.out.println("check the next of 3 i=2, should be 4____ " +list.lookup(3).getNext(2).toString());
		System.out.println("check the prev of 4 i=2, should be 3____ " +list.lookup(4).getPrev(2).toString());
		System.out.println("check the next of 4 i=3, should be inf____ " +list.lookup(4).getNext(3).toString());
		System.out.println("check the Prev of 4 i=3, should be neginf____ " +list.lookup(4).getPrev(3).toString());
		
		
		System.out.println();
		
		list.remove(list.lookup(4));
		System.out.println(list.toString());
		
		
		System.out.println();
		
		System.out.println("check the next after the removal, should be inf positive____ " +list.first.getNext(3).toString());
		System.out.println("check the next of 3 after the removal, should be inf positive____ " +list.lookup(3).getNext(2).toString());
		
		System.out.println("check the prev after the removal, should be inf neg____ " +list.last.getPrev(3).toString());
		System.out.println("check the prev of 3 after the removal, should be inf neg____ " +list.lookup(3).getPrev(2).toString());
		System.out.println("check the prev of 3 after the removal, should be 1____ " +list.lookup(3).getPrev(1).toString());
		
		
		list.insert(5, 3);
		
		list.insert(-3, 3);
		
		System.out.println("check the next of -3, should be 5____ " +list.lookup(-3).getNext(3).toString());
		System.out.println("check the next of -3, should be 3____ " +list.lookup(-3).getNext(2).toString());
		System.out.println(list.toString());
		
		
		System.out.println(list.getSize());
		
		
		//System.out.println(list.first.getNext(1).toString());
		//FloorsArrayLink link=list.lookup(9);
		//System.out.println(link);//null
		//System.out.println(link.getPrev(1).toString());
		 * 
		 * 
		 */
		/*
		FloorsArrayList list=new FloorsArrayList(3);
		list.insert(5, 1);
		list.insert(24, 2);
		list.insert(29, 3);
		list.insert(33, 1);
		list.insert(0.36019846646461184, 1);
		System.out.println(list.toString());
		
		System.out.println(list.maximum());
		
		list.insert(-8, 1);
		System.out.println(list.minimum());
		*/
		FloorsArrayList list=new FloorsArrayList(3);
		list.insert(0.36019846646461184, 1);
		System.out.println(list.toString());
	}
}
