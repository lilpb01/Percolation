import javax.swing.*;
import java.awt.*;

public class PercolationVisualizer extends JFrame {

    private static final int CELL_SIZE = 30; 
    private final Percolation p;
    private final int n;

    public PercolationVisualizer(Percolation p) 
    {
        this.p = p;
        this.n = p.size(); 
        initializeUI();
    }

    private void initializeUI() 
    {
        setTitle("Percolation Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(n * CELL_SIZE, n * CELL_SIZE);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) 
    {
        for (int row = 0; row < n; row++) 
        {
            for (int col = 0; col < n; col++) 
            {
                if (p.isOpen(row + 1, col + 1)) 
                { 
                    g.setColor(Color.CYAN); 
                }
                else 
                {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main(String[] args) 
    {
        Percolation p = new Percolation(8);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 2);
        
        PercolationVisualizer visualizer = new PercolationVisualizer(p);
    }
}
