import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
	
	private int top = 0;
	private int[][] grid;
	private int size;
	private int bottom;
	private int count;
	private WeightedQuickUnionUF qf;
	
	public Percolation(int n)
	{
		if(n <= 0)
		{
			throw new IllegalArgumentException();
		}
		
		grid = new int[n][n];
		size = n;
		bottom = n * n + 1;
		qf = new WeightedQuickUnionUF(n * n + 2);
		count = 0;
	}
	
	public int size()
	{
		return size;
	}
	private void checkException(int row, int col)
	{
		if(row <= 0 || row > size || col <= 0 || col > size)
		{
			throw new IllegalArgumentException();
		}
	}
	
	private int getIndex(int row, int col)
	{
		return size * (row - 1) + col;
	}
	
	
	public void open(int row, int col)
	{
		checkException(row, col);
		
		if(isOpen(row, col) == false)
		{
			grid[row - 1][col - 1] = 1;
			count++;
			
			
			
			if(row == 1)
			{
				qf.union(getIndex(row, col), top);
			}
			
			if(row == size)
			{
				qf.union(getIndex(row, col), bottom);
			}
			
			if(row > 1 && isOpen(row - 1, col))
			{
				qf.union(getIndex(row, col), getIndex(row - 1, col));
			}
			
			if(row < size && isOpen(row + 1, col))
			{
				qf.union(getIndex(row, col), getIndex(row + 1, col));
			}
			
			if(col > 1 && isOpen(row, col - 1))
			{
				qf.union(getIndex(row, col), getIndex(row, col - 1));
			}
			
			if(col < size && isOpen(row, col + 1))
			{
				qf.union(getIndex(row, col), getIndex(row, col + 1));
			}
		}
		
		
		
	}
	
	public boolean isOpen(int row, int col)
	{
		checkException(row, col);
		return grid[row - 1][col - 1] == 1;
	}
	
	public int numberOfOpenSites()
	{
		return count;
	}
	
	public boolean isFull(int row, int col)
	{
		if((row > 0 && row <= size) && (col > 0 && col <= size))
		{
			return qf.find(top) == qf.find(getIndex(row, col));
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public boolean percolates()
	{
		return qf.find(top) == qf.find(bottom);
	}
	
	public static void main(String[] args)
	{
		Percolation p = new Percolation(3);
		
		p.open(1, 1);
		p.open(2, 1);
		p.open(3, 2);
		System.out.println(p.percolates());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
