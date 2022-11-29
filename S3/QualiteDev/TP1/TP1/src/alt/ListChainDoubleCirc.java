public class ListChainDoubleCirc implements ListChain {
 
    public Cell head;
    public int size;
 
    public ListChainDoubleCirc() {
	head = null;
	size = 0;
    }
 
    public Cell find(int value) {
	if( head == null) return null;
	Cell c = head;
	while (c.value != value) {
	    c = ((CellDoubleChain)c).next;
	    if (c == head) return null;
	}
	return c;
    }
 
    public Cell get(int index) {	
	if ((index <0) || (index >= size)) return null;
	
	Cell c = head;
	if (index <= size/2) {
	    for(int i=0;i<index;i++) {
		c = ((CellDoubleChain)c).next;
	    }
	}
	else {
	    for(int i=size;i>index;i--) {
		c = ((CellDoubleChain)c).prev;
	    }
	}
	return c;
    }

    public Cell prepend(int value) {
	Cell c = append(value);
	head = c;

	return c;
    }
 
    public Cell append(int value) {
	Cell newCell = new CellDoubleChain(value);
	if (size == 0) {
	    ((CellDoubleChain)newCell).next = newCell;
	    ((CellDoubleChain)newCell).prev = newCell;
	    head = newCell;
	}
	else {
	    /* NB : les changements se font en utilisant une suite
	       d'affectations différentes que dans la solution basique
	       pour éviter de rendre le code trop illisible
	     */
	    Cell end = ((CellDoubleChain)head).prev;
	    ((CellDoubleChain)newCell).prev = end;
	    ((CellDoubleChain)newCell).next = head;
	    ((CellDoubleChain)end).next = newCell;
	    ((CellDoubleChain)head).prev = newCell;
	}
	size += 1;
	return newCell;
    }
 
    public Cell insert(int value, int index) {

	Cell newCell = null;
	if (index >= size) {
	    newCell = append(value);
	}
	else if (index <= 0) {
	    newCell = prepend(value);
	}
	else {	    
	    newCell = new CellDoubleChain(value);
	    /* NB : les changements se font en utilisant une suite
	       d'affectations différentes que dans la solution basique
	       pour éviter de rendre le code trop illisible
	     */
	    Cell c = get(index);
	    ((CellDoubleChain)newCell).prev = ((CellDoubleChain)c).prev;
	    ((CellDoubleChain)newCell).next = c;
	    ((CellDoubleChain)(((CellDoubleChain)c).prev)).next = newCell;
	    ((CellDoubleChain)c).prev = newCell;
	    size += 1;
	}
	return newCell;
    }
 
    public Cell replace(int value, int index) {
	Cell c = get(index);
	if (c != null) {
	    c.value = value;
	}
	return c;
    }
 
    public Cell removeAt(int index) {
	Cell c = get(index);
	if (c != null) {
	    ((CellDoubleChain)(((CellDoubleChain)c).prev)).next = ((CellDoubleChain)c).next;
	    ((CellDoubleChain)(((CellDoubleChain)c).next)).prev = ((CellDoubleChain)c).prev;
	    if (c == head) {
		head = ((CellDoubleChain)c).next;
	    }
	    size -= 1;
	}
	return c;
    }
 
    public Cell remove(int value) {
	Cell c = find(value);
	if (c != null) {
	    ((CellDoubleChain)(((CellDoubleChain)c).prev)).next = ((CellDoubleChain)c).next;
	    ((CellDoubleChain)(((CellDoubleChain)c).next)).prev = ((CellDoubleChain)c).prev;
	    if (c == head) {
		head = ((CellDoubleChain)c).next;
	    }
	    size -= 1;
	}
	return c;
    } 

    public void print() {
	Cell c = head;
	for(int i=0;i<size;i++) {
	    System.out.print(c.value+" -> ");
	    c = ((CellDoubleChain)c).next;
	}
	System.out.println();
    }
}
