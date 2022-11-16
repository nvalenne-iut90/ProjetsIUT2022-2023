public class ListSimple {
 
    public Cell head;
    public int size;
 
    public ListSimple() {
	head = null;
	size = 0;
    }
 
    public Cell find(int value) {
        Cell c = head;
        while((c != null) && (c.value != value)){
            c = c.next;
        }
        return c;
         
    }
 
    public Cell get(int index) {
        Cell c = head;
        int i = 0;
        while((i != 0) && (i < index)){
            c = c.next;
            i +=1;
        }
        return c;
        
    }
 
    public Cell append(int value) {
        Cell c = null;
        Cell newCell = new Cell(value);
        if (size ==0){
            head = newCell;
        }else{
            c = get(size-1);
            c.next = newCell;
        }
        size =+ 1;
        return newCell;
    }
 
    public Cell insert(int value, int index) {
        Cell c = null;
        Cell newCell = new Cell(value);
        if(index > size)
            index = size;
        if(size == 0)
            head = newCell;
        else if(index == 0){
            newCell.next = head;
            head = newCell;
        }
        else{
            c = get(index-1);
            newCell.next = c.next;
            c.next = newCell;
        }            
        size += 1;
        return(newCell); 
    }
 
    public Cell replace(int value, int index) {
        if((index < 0) || (index >= size)){
            return null;
        }
        Cell c = get(index);
        c.value = value;
        return c;
    }
 
    public Cell removeAt(int index) {
        if((index < 0) || (index >=size))
            return null;
        Cell remouved = null;
        if(index ==0){
            remouved = head;
            head = head.next;
        }else{
            Cell c = get(index - 1);
            remouved = c.next;
            c.next = remouved.next;
        }
        size -=1;
        return remouved;
    }
 
    public Cell remove(int value) {
        Cell c = head;
        Cell p = null; //previous
        while((c != null) && (c.value != value)){
            p = c;
            c = c.next;
        }
        if(c != null){
            if(c == head)
                head = c.next;
            else
                p.next = c.next;
            size --;
        }
        return c;
    }
 
    public void print() {
        
    }
}




// Set: is a group of non indexed elements, each element is a 
// Lint: is a group of indexed elements, you can have ... ... of the same elements
// Map: is a group of ... object, ... indec, each element is .............
// Queue: is a group of non indexed elements, with an access ... of type LIFO / FIFO
