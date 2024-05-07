package Algorithm;
import java.awt.*;


public class Insertion extends Panel {
    private int A = -1;
    private int B = -1;

    public Insertion(String name, int width, int height) {
        super(name, width, height);
    }

    @Override
    public void reset() {
        A = -1;
        B = -1;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < list.length; i++) {
                Thread.sleep(24);
                B = i;
                A = B;
                int k;
                for (k = i - 1; k >= 0 && list[k] > list[k + 1]; k--) {
//                    Thread.sleep(1);
                    repaint();
                    A = k + 1;
                    repaint();
//                    Thread.sleep(1);
                    int tmp = list[k + 1];
                    list[k + 1] = list[k];
                    list[k] = tmp;
                }
                A = k + 1;
                repaint();
            }
           A = -1;
        } catch (Exception e) {
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = (B == -1 ? 0 : B); i < list.length; i++) {
            Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
            FontMetrics nameFontMetrix = getFontMetrics(nameFont);
            g.setFont(nameFont);
            g.setColor(Color.MAGENTA);
            endtime = System.currentTimeMillis();
            double Time = (endtime - starttime) / 1000.0;
            time = Double.toString(Time);
            g.drawString(time, 10, 15);
            String name = "Insertion";

            g.drawString(name, ((getWidth() - nameFontMetrix.stringWidth(name)) / 2), (BORDER_WIDTH + nameFontMetrix.getAscent() / 3));
            g.drawOval(i /5, getHeight() - (list[i] /5)+20, 1, 1);
        }
        for (int i = 0; i <= B; i++) {
            g.setColor(Color.magenta);
            g.drawOval(i /5, getHeight() - (list[i] /5)+20, 1, 1);
        }
    }
}
