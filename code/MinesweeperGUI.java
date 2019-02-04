import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MinesweeperGUI extends JPanel {

    public static final int N = 1000;
    private BufferedImage myImage;
    private Graphics myBuffer;
    private Timer t;
    public static int[][] grid;
    public static int[] loco = new int[2];
    public static ArrayList<Integer> locoRemember = new ArrayList<Integer>();

    public MinesweeperGUI() {

        int gridSize = Minesweeper.getGridSize();
        grid = Minesweeper.makeGrid(gridSize);
        Minesweeper.placeMines(gridSize, grid);
        Minesweeper.countAllSurroundingMines(grid);
        Minesweeper.printGrid(grid);

        addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {

                        for (int r = 1; r <= grid.length; r++) {
                            for (int c = 1; c <= grid.length; c++) {
                                myBuffer.setColor(Color.GRAY);

                                if (c * (N / grid.length) > me.getX()
                                        && r * (N / grid.length) > me.getY()) {
                                    loco[0] = r;
                                    loco[1] = c;
                                    c = grid.length + 1;
                                    r = grid.length + 1;
                                }
                            }
                        }
                    }
                });

        // buffer and timer
        myImage = new BufferedImage(N, N, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        t = new Timer(40, new Listener());
        t.start();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Listener implements ActionListener {
        private BufferedImage image;

        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(Color.WHITE);
            myBuffer.fillRect(0, 0, N, N);
            myBuffer.setColor(Color.BLACK);
            myBuffer.setFont(new Font("Serif", Font.BOLD, 50));

            int[][] temp = MinesweeperGUI.grid;

            for (int r = 0; r < temp.length; r++) {
                for (int c = 0; c < temp.length; c++) {
                    myBuffer.setColor(Color.GRAY);
                    myBuffer.fillRect(
                            c * (MinesweeperGUI.N / temp.length),
                            r * (MinesweeperGUI.N / temp.length),
                            MinesweeperGUI.N / temp.length,
                            MinesweeperGUI.N / temp.length);
                    myBuffer.setColor(Color.WHITE);
                    myBuffer.drawRect(
                            c * (MinesweeperGUI.N / temp.length),
                            r * (MinesweeperGUI.N / temp.length),
                            MinesweeperGUI.N / temp.length,
                            MinesweeperGUI.N / temp.length);
                }
            }

            myBuffer.setColor(Color.WHITE);

            locoRemember.add(MinesweeperGUI.loco[0] - 1);
            locoRemember.add(MinesweeperGUI.loco[1] - 1);

            for (int i = 0; i < locoRemember.size(); i += 2) {
                if (!((locoRemember.get(i)) == -1 || (locoRemember.get(i + 1)) == -1)) {
                    myBuffer.fillRect(
                            (locoRemember.get(i + 1)) * (MinesweeperGUI.N / temp.length),
                            (locoRemember.get(i)) * (MinesweeperGUI.N / temp.length),
                            MinesweeperGUI.N / temp.length,
                            MinesweeperGUI.N / temp.length);

                    myBuffer.setColor(Color.black);

                    int y = 0;
                    for (int r = 0; r < temp.length; r++) {
                        y += (MinesweeperGUI.N / (temp.length * 2));
                        if (r == locoRemember.get(i)) {
                            r = temp.length;
                        }
                        y += (MinesweeperGUI.N / (temp.length * 2));
                    }
                    int x = 0;
                    for (int r = 0; r < temp.length; r++) {
                        x += (MinesweeperGUI.N / (temp.length * 2));
                        if (r == locoRemember.get(i + 1)) {
                            r = temp.length;
                        }
                        x += (MinesweeperGUI.N / (temp.length * 2));
                    }

                    try {
                        myBuffer.drawImage(
                                ImageIO.read(
                                        new File(
                                                "C:\\Users\\User\\Pictures\\Java Projects\\Minesweeper\\"
                                                        + temp[locoRemember.get(i)][
                                                                locoRemember.get(i + 1)]
                                                        + ".png")),
                                x - 100,
                                y - 100,
                                MinesweeperGUI.N / temp.length,
                                MinesweeperGUI.N / temp.length,
                                null);

                    } catch (IOException ex) {
                        System.out.println("error");
                    }

                    myBuffer.setColor(Color.WHITE);
                }
            }
            repaint();
        }
    }
}
