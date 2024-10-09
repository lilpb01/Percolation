import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats 
{
	private double[] results;
	private Percolation p;
	private int t;
	private double time;
	
	public PercolationStats(int n, int trials)
	{
		Stopwatch stopwatch = new Stopwatch();
		if(n <= 0 || trials <= 0)
		{
			throw new IllegalArgumentException();
		}
		t = trials;
		results = new double[trials];
		
		for(int i = 0; i < trials; i++)
		{
			p = new Percolation(n);
			
			while(p.percolates() == false)
			{
				int r = StdRandom.uniformInt(1, n + 1);
				int c = StdRandom.uniformInt(1, n + 1);
				
				p.open(r, c);
				
				
			}
			
			//results[i] = (double) (p.numberOfOpenSites() /(n * n));
			results[i] = p.numberOfOpenSites() / (double) ((n * n));
		}
		
		time = stopwatch.elapsedTime();
		
	}
	
	public double mean()
	{
		return StdStats.mean(results);
	}
	
	public double stddev()
	{
		return StdStats.stddev(results);
	}
	
	public double confidenceLo()
	{
		return mean() - 1.96 * stddev() / (double) (t);
	}
	
	public double confidenceHi()
	{
		return mean() + 1.96 * stddev() / (double) (t);
	}
	

	
	

	
	
	public static void main(String[] args)
	{
		
		PercolationStats ps = new PercolationStats(200, 100);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo()
		+ ", " + ps.confidenceHi() + "]");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
