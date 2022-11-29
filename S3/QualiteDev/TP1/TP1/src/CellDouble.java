public class CellDouble {
 
    public CellDouble prev;
    public int value;
    public CellDouble next;

 
    public CellDouble(int value) {
	this.value = value;
	next = null;
	prev = null;
    }
}
