public interface ListChain {
 
    public Cell find(int value);
 
    public Cell get(int index);
    
    public Cell prepend(int value);

    public Cell append(int value);
    
    public Cell insert(int value, int index);
 
    public Cell replace(int value, int index);
 
    public Cell removeAt(int index);

    public Cell remove(int value);

    public void print();
}
