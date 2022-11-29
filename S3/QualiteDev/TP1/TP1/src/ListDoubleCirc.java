public class ListDoubleCirc {
 
    public CellDouble head;
    public int size;
 
    public ListDoubleCirc() {
	head = null;
	size = 0;
    }
 
    public CellDouble find(int value) {
	if( head == null) return null;
	CellDouble c = head;
	while (c.value != value) {
	    c = c.next;
	    if (c == head) return null;
	}
	return c;
    }
 
    public CellDouble get(int index) {	
	if ((index <0) || (index >= size)) return null;
	
	CellDouble c = head;
	if (index <= size/2) {
	    for(int i=0;i<index;i++) {
		c = c.next;
	    }
	}
	else {
	    for(int i=size;i>index;i--) {
		c = c.prev;
	    }
	}
	return c;
    }

    public CellDouble prepend(int value) {
	CellDouble c = append(value);
	head = c;

	return c;
    }
 
    public CellDouble append(int value) {
	CellDouble newCell = new CellDouble(value);
	if (size == 0) {
	    newCell.next = newCell;
	    newCell.prev = newCell;
	    head = newCell;
	}
	else {
	    newCell.prev = head.prev;
	    newCell.next = head;
	    newCell.next.prev = newCell;
	    newCell.prev.next = newCell;
	}
	size += 1;
	return newCell;
    }
 
    public CellDouble insert(int value, int index) {

	if (index >= size) {
	    return append(value);
	}
	else if (index <= 0) {
	    return prepend(value);
	}
	else {
	    CellDouble newCell = new CellDouble(value);
	    CellDouble c = get(index);
	    newCell.prev = c.prev;
	    newCell.next = c;
	    newCell.prev.next = newCell;
	    newCell.next.prev = newCell;
	    size += 1;
	    return newCell;
	}
    }
 
    public CellDouble replace(int value, int index) {
	CellDouble c = get(index);
	if (c != null) {
	    c.value = value;
	}
	return c;
    }
 
    public CellDouble removeAt(int index) {
	CellDouble c = get(index);
	if (c != null) {
	    c.prev.next = c.next;
	    c.next.prev = c.prev;
	    if (c == head) {
		head = c.next;
	    }
	    size -= 1;
	}
	return c;
    }
 
    public CellDouble remove(int value) {
	CellDouble c = find(value);
	if (c != null) {
	    c.prev.next = c.next;
	    c.next.prev = c.prev;
	    if (c == head) {
		head = c.next;
	    }
	    size -= 1;
	}
	return c;
    } 

    public void print() {
	CellDouble c = head;
	for(int i=0;i<size;i++) {
	    System.out.print(c.value+" -> ");
	    c = c.next;
	}
	System.out.println();
    }
}
