public class PercolationStats {
    //Global Variables
    private double[] bound;
    private int t;
   // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        if (N < 1 || T < 1){
             throw new IllegalArgumentException();}
         t = T;
         bound = new double[T];
         
         for (int i = 0; i < bound.length; i++)
         {
             bound[i] = calcuLator(N);
         }
    }
    private double calcuLator(int x){
        double counter = 0;
        int i, j;
        Percolation MyPerc = new Percolation(x);
        while (!MyPerc.percolates())
        {
            i = StdRandom.uniform(x)+1;
            j = StdRandom.uniform(x)+1;
            if (!MyPerc.isOpen(i, j))
            {
                counter++;
                MyPerc.open(i, j);
            }
        }
        return counter / (x*x);
    }
   // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(bound);
    }                  
   // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(bound);
    }                  
   // returns lower bound of the 95% confidence interval
    public double confidenceLo(){
       return mean() - (1.96*stddev())/(Math.sqrt(t));
    }   
   // returns upper bound of the 95% confidence interval
    public double confidenceHi(){
       return mean() + (1.96*stddev())/(Math.sqrt(t));
    }
   // test client, described below
   public static void main(String[] args){
       int x = Integer.parseInt(args[0]);
       int y = Integer.parseInt(args[1]);
       System.out.println((x)+", "+(y));
       PercolationStats MyPerc = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       System.out.println("mean                    = " + MyPerc.mean());
       System.out.println("stddev                  = " + MyPerc.stddev());
       System.out.println("95% confidence interval = " + MyPerc.confidenceLo() + 
                ", " + MyPerc.confidenceHi());
  }
   
}