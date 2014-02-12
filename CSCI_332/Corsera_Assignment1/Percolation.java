public class Percolation {
    // Declare instance variables
    private int x;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF Wuf;
    private WeightedQuickUnionUF anotherWuf;
    private byte[] site; 
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N){
        x = N;
        Wuf = new WeightedQuickUnionUF(x*x + 2);
        anotherWuf = new WeightedQuickUnionUF(x*x + 2);
        site = new byte[x*x];
        top = x*x;
        bottom = x*x + 1;
    }
    // open site (row i, column j) if it is not already
    public void open(int i, int j){
        isInBounds(i, j);
        if (isOpen(i, j)){
            return;
        }
        int currentSite = convert2dTo1dCoord(i, j); 
        this.site[currentSite] = 1;
        
        // union with top virtuall cell
        if (i == 1 && !Wuf.connected(currentSite, top)){
            Wuf.union(currentSite, top);
            anotherWuf.union(currentSite, top);
        }
        // Union with bottom artificial cell
        if (i == x){
            anotherWuf.union(currentSite, bottom);
        }
        // Union with bottom cell
        if (i < x){
            if (isOpen(i+1, j)){
                Wuf.union(currentSite, convert2dTo1dCoord(i+1, j));
                anotherWuf.union(currentSite, convert2dTo1dCoord(i+1, j));
            }
        }
        // Union with upper cell
        if (i > 1){
            if (isOpen(i-1, j)){
                Wuf.union(currentSite, convert2dTo1dCoord(i-1, j));
                anotherWuf.union(currentSite, convert2dTo1dCoord(i-1, j));
            }
        }
        // Union with left cell
        if (j > 1){
            if (isOpen(i, j-1)){
                Wuf.union(currentSite, convert2dTo1dCoord(i, j-1));
                anotherWuf.union(currentSite, convert2dTo1dCoord(i, j-1));
            }
        }
        // Union with left cell
        if (j < x){
            if (isOpen(i, j+1)){
                Wuf.union(currentSite, convert2dTo1dCoord(i, j+1));
                anotherWuf.union(currentSite, convert2dTo1dCoord(i, j+1));
            }
        }
    }
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        isInBounds(i, j);
        if (site[convert2dTo1dCoord(i, j)] == 1)
            return true;
        
        return false;
    }
    // is site (row i, column j) full?
    public boolean isFull(int i, int j){
        isInBounds(i, j);
        if (!isOpen(i, j))
            return false;
        int currentSite = convert2dTo1dCoord(i, j);
        if (Wuf.connected(top, currentSite))
            return true;
        return false;
    }
    // Do we have percolation?
    public boolean percolates(){
        // If their connected return true and done!
        if (anotherWuf.connected(top, bottom)){return true;}
        
        return false;
    }
    // check that we are within bounds
    private boolean isInBounds(int i, int j){
        if (i < 1 || i > x || j < 1 || j > x){
            throw new IndexOutOfBoundsException();
        }
        return true;
    }
    private int convert2dTo1dCoord(int i, int j){
        int pos = x*(i - 1) + j - 1;
        return pos;
    }
}