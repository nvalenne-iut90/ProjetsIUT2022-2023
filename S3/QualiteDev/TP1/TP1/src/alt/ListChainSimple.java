public class ListChainSimple implements ListChain {
 
    public Cell head = null;
    public int size = 0;

    public ListChainSimple() {
	head = null;
	size = 0;
    }

    public Cell find(int value) {
	Cell c = head;
	while ((c != null) && (c.value != value)) {
	    c = ((CellSimpleChain)c).next;
	}
	return c;
    }
 
    public Cell get(int index) {
	if ((index <0) || (index >= size)) return null;
	
	Cell c = head;
	for(int i=0;i<index;i++) {
	    c = ((CellSimpleChain)c).next;
	}
	return c;
    }

    public Cell prepend(int value) {
	Cell newCell = new CellSimpleChain(value);
	if (size == 0) {
	    head = newCell;
	}
	else {
	    ((CellSimpleChain)newCell).next = ((CellSimpleChain)head).next;
	    head = newCell;
	}
	size += 1;
	return newCell;
    }
    
    public Cell append(int value) {
	Cell c = null;
	Cell newCell = new CellSimpleChain(value);
	if (size == 0) {
	    head = newCell;
	}
	else {
	    c = get(size-1);
	    ((CellSimpleChain)c).next = newCell;
	}
	size += 1;
	return newCell;
    }
    
    public Cell insert(int value, int index) {
	Cell c = null;
	Cell newCell = new CellSimpleChain(value);
	if (index < 0 ) {
	    index = 0;
	}
	else if (index > size) {
	    index = size;
	}
	
	if (size == 0) {
	    head = newCell;
	}
	else if (index == 0) {
	    ((CellSimpleChain)newCell).next = head;
	    head = newCell;
	}
	else {
	    c = get(index-1);
	    ((CellSimpleChain)newCell).next = ((CellSimpleChain)c).next;
	    ((CellSimpleChain)c).next = newCell;
	}       
	size += 1;
	
	return newCell;
    }
 
    public Cell replace(int value, int index) {
	if ((index >= 0) && (index < size)) {
	    Cell c = get(index);
	    c.value = value;
	    return c;
	}
	return null;
    }
 
    public Cell removeAt(int index) {
 
	Cell tmp = null;
	if ((index <0) || (index >= size)) return null;

	if (index == 0) {
	    tmp = head;
	    head = ((CellSimpleChain)head).next;
	}
	else {
	    Cell c = get(index-1);
	    tmp = ((CellSimpleChain)c).next;
	    ((CellSimpleChain)c).next = ((CellSimpleChain)tmp).next;
	}
	size -= 1;
	return tmp;
    }

    public Cell remove(int value) {
    
	Cell c = head;
	Cell tmp = null;
	int i = 0;
	while ((c != null) && (c.value != value)) {
	    c = ((CellSimpleChain)c).next;
	    i += 1;
	}
	if (c == null) {
	    return null;
	}
	else {
	    return removeAt(i);
	}
    }
    /*

      version alternative, avec un seul parcours de la liste
      ------------------------------------------------------

    public Cell remove(int value) {
    
	Cell c = head;
	Cell prev = null;
	Cell tmp = null;
	while ((c != null) && (c.value != value)) {
	    prev = c;
	    c = ((CellSimpleChain)c).next;
	}
	if (c == null) {
	    return null;
	}
	else {
	    if (prev == null) {
		// la tête est supprimée
		tmp = head;
		head = ((CellSimpleChain)head).next;
	    }
	    else {
		tmp = c;
		((CellSimpleChain)prev).next = ((CellSimpleChain)c).next;
	    }
	}
	return tmp;
    }
    */
    public void print() {
	Cell c = head;
	while (c != null) {
	    System.out.print(c.value+" -> ");
	    c = ((CellSimpleChain)c).next;
	}
	System.out.println();
 
    }
}
