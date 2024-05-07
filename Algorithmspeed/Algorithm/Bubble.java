package Algorithm;
import java.awt.*;

public class Bubble extends Panel {
    private int a = -1;
    public Bubble(String name, int width, int height) {
        super(name, width, height);
    }
    @Override
    public void reset() {
        a = -1;
    }
    @Override
    public void run() {
        try {
            boolean needNextPass = true;
            for (int k = 1; k < list.length && needNextPass; k++) {
                Thread.sleep( 30);
                needNextPass = false;
                for (int i = 0; i < list.length - k; i++) {
                    repaint();
//                    Thread.sleep( 1);
                    if (list[i] > list[i + 1]) {
                        int temp = list[i];
                        list[i] = list[i + 1];
                        list[i + 1] = temp;
                        repaint();
//                        Thread.sleep( 1);
                        needNextPass = true;
                    }
                }
                a = size - k;
            }
            a = 0;
        } catch (Exception e) {
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < (a == -1 ? list.length : a); i++) {

            g.setColor(Color.PINK);

            g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);        }
        if(a != -1) {
            for (int i = a ; i < list.length; i++) {
                Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
                FontMetrics nameFontMetrix = getFontMetrics(nameFont);
                g.setFont(nameFont);

                g.setColor(Color.pink);
                endtime = System.currentTimeMillis();
                double Time = (endtime-starttime)/1000.0;
                time = Double.toString(Time);
                g.drawString(time,10, 15);
                String name = "Bubble";
                g.drawString(name, ((getWidth() - nameFontMetrix.stringWidth(name)) / 2), (BORDER_WIDTH + nameFontMetrix.getAscent() / 3));
                g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);

            }
        }
    }

}
