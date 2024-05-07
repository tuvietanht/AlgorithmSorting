package Algorithm;
import java.awt.*;

public class Selection extends Panel {
    private int a,b,c = -1;
    public Selection(String name, int width, int height) {
        super(name, width, height);
    }
    @Override
    public void reset() {
        a = -1;
        b = -1;
        c = -1;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < list.length - 1; i++) {
                int currentMinIndex = i;
                a = currentMinIndex;
                for (int j = i + 1; j < list.length; j++) {
                    b = j;
                    repaint();
//                    Thread.sleep(1);
                    if (list[currentMinIndex] > list[j]) {
                        currentMinIndex = j;
                        a = currentMinIndex;
                        repaint();
                    }
                }
                if (currentMinIndex != i) {
                    int tmp = list[currentMinIndex];
                    list[currentMinIndex] = list[i];
                    list[i] = tmp;
                    repaint();
                    Thread.sleep( 28);
                }
                c++;
                repaint();
            }
            c++;
            a = -1;
            b = -1;
        } catch (Exception e) {
        }
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = (c == -1 ? 0 : c); i < list.length; i++) {
            Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
            FontMetrics nameFontMetrix = getFontMetrics(nameFont);
            g.setFont(nameFont);
            g.setColor(Color.RED);
            endtime = System.currentTimeMillis();
            double Time = (endtime-starttime)/1000.0;
            time = Double.toString(Time);
            g.drawString(time,10, 15);
            String name = "Selection";

            g.drawString(name, ((getWidth() - nameFontMetrix.stringWidth(name)) / 2), (BORDER_WIDTH + nameFontMetrix.getAscent() / 3));
            g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);
        }

        for (int i = 0; i <= c; i++) {
            g.setColor(Color.RED);
            g.drawOval(i/5,getHeight()-(list[i]/5),1,1);
        }
        for (int i = (c == -1 ? 0 : c); i < list.length; i++) {
            if (a != -1) {
                g.setColor(Color.RED);
                g.drawOval(i / 5, getHeight() - (list[i] / 5)+20, 1, 1);
            }
        }

    }

}