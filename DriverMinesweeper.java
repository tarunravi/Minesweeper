import javax.swing.JFrame;

/**
 * Driver Class tor draw the Heat Map
 */
public class DriverMinesweeper
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Heat Map");
        frame.setSize(1000, 1000);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MinesweeperGUI());
        frame.setVisible(true);
    }
}