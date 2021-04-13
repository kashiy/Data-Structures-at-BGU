
public class HashList {
	
	// ---------------------- fields ----------------------
    private HashListElement first;
    
	public HashList() {
		first = null;
	}

	// ---------------------- methods ----------------------


    //Returns the number of elements in this list
    public int size() {
        int counter = 0;
        for (HashListElement curr = first; curr != null; curr = curr.getNext())
            counter = counter + 1;
        return counter;
    }

    //Returns true if this list contains no elements.
    public boolean isEmpty() {
        return first == null;
    }

    //Adds element to the beginning of this list
    public void addFirst(int element) {
        first = new HashListElement(element, first);
    }

    
    //Removes first element of this list
    public int remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        HashListElement current = first;
        HashListElement prev = current;
        while (index > 0) {
            index = index - 1;
            prev = current;
            current = current.getNext();
        }
        int ans = (int) current.getData();
        if (first == current) {
            first = first.getNext();
        } else {
            prev.setNext(current.getNext());
        }
        return ans;
    }

    //Returns the element at the specified position in this list.
    public int get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        HashListElement current = first;
        while (index > 0) {
            index = index - 1;
            current = current.getNext();
        }
        return (int) current.getData();
    }

    //Returns a String representing this object
    public String toString() {
        String output = "<";
        HashListElement current = first;
        while (current != null) {
            output = output + current.toString();
            current = current.getNext();
            if (current != null)
                output = output + ", ";
        }
        output = output + ">";
        return output;
    }

    //Remove the first Link which contains toRemove, if such one exists
    public boolean remove(HashListElement toRemove) {
        HashListElement current = first;
        HashListElement prev = current;
        boolean removed = false;
        while (current != null & !removed) {
            if ((current.getData())==(toRemove.getData())) {
                // if the first link should be removed
                if (first == current) {
                    first = first.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                removed = true;
            } else {
                prev = current;
                current = current.getNext();
            }
        }
        return removed;
    }

    //Returns true if this list contains the specified element
    public boolean contains(int element) {
        boolean output = false;
        for (HashListElement curr = first; curr != null & !output; curr = curr.getNext())
            output = element==curr.getData();
        return output;
    }


    
    //Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    public int indexOf(int element) {
        int output = -1;
        int index = 0;
        for (HashListElement curr = first; curr != null & output == -1; curr = curr.getNext())
            if (curr.getData()== element)
                output = index;
            else
                index = index + 1;
        return output;
    }

    //Appends the specified element to the end of this list
    public boolean add(int element) {
        HashListElement newLink = new HashListElement(element);
        if (isEmpty()) {
            first = newLink;
        } else {
            HashListElement current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newLink);
        }
        return true;
    }

    
}