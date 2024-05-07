package Algorithm;
import java.awt.*;
import javax.swing.JPanel;

public abstract class Panel extends JPanel implements Runnable {
    protected static final int BORDER_WIDTH = 10;
    private Dimension prefferedDimension;
    protected int size;
    protected int[] list;
    private String name;
    public long starttime = System.currentTimeMillis();
    public long endtime;
    public String time;

    public Panel(String name, int width, int height) {
        prefferedDimension = new Dimension(width, height);
        this.name = name;
        setBackground(Color.BLACK);
    }

    public void setList(int[] list) {
        reset();
        this.size = list.length;
        this.list = java.util.Arrays.copyOf(list, size);
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return prefferedDimension;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    @Override
    public abstract void run();
    public abstract void reset();

}