public class ListSimple {
 
    public Cell head;
    public int size;
 
    public ListSimple() {
	head = null;
	size = 0;
    }

    public Cell find(int value) {
	Cell c = head;
	while ((c != null) && (c.value != value)) {
	    c = c.next;
	}
	return c;
    }
 
    public Cell get(int index) {
	if ((index <0) || (index >= size)) return null;
	
	Cell c = head;
	for(int i=0;i<index;i++) {
	    c = c.next;
	}
	return c;
    }
    
    public Cell append(int value) {
	Cell c = null;
	Cell newCell = new Cell(value);
	if (size == 0) {
	    head = newCell;
	}
	else {
	    c = get(size-1);
	    c.next = newCell;
	}
	size += 1;
	return newCell;
    }
    
    public Cell insert(int value, int index) {
	Cell c = null;
	Cell newCell = new Cell(value);
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
	    newCell.next = head;
	    head = newCell;
	}
	else {
	    c = get(index-1);
	    newCell.next = c.next;
	    c.next = newCell;
	}       
	size += 1;
	return newCell;
    }
 
    public Cell replace(int value, int index) {
	if ((index < 0) || (index >= size)) return null;
	Cell c = get(index);
	c.value = value;
	return c;
    }
 
    public Cell removeAt(int index) {
 
	Cell tmp = null;
	if ((index <0) || (index >= size)) return null;

	if (index == 0) {
	    tmp = head;
	    head = head.next;
	}
	else {
	    Cell c = get(index-1);
	    tmp = c.next;
	    c.next = c.next.next;
	}
	size -= 1;
	return tmp;
    }

    public Cell remove(int value) {
    
	Cell c = head;
	Cell tmp = null;
	int i = 0;
	while ((c != null) && (c.value != value)) {
	    c = c.next;
	    i += 1;
	}
	if (c != null) {
	    c = removeAt(i);
	}
	return c;
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
	    c = c.next;
	}
	if (c == null) {
	    return null;
	}
	else {
	    if (prev == null) {
		// la tête est supprimée
		tmp = head;
		head = head.next;
	    }
	    else {
		tmp = c;
		prev.next = prev.next.next;
	    }
	}
	return tmp;
    }
    */
    public void print() {
	Cell c = head;
	while (c != null) {
	    System.out.print(c.value+" -> ");
	    c = c.next;
	}
	System.out.println();
 
    }
}
