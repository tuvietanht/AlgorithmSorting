package Algorithm;
import java.awt.*;


public class Quick extends Panel {
    private int A = -1;
    private int B = -1;
    private int C = -1;
    private int D = -1;

    public Quick(String name, int width, int height) {
        super(name, width, height);
    }
    @Override
    public void reset() {
        A = -1; //A
        B = -1; //B
        C = -1; //C
        D = -1; //D
    }
    @Override
    public void run() {
        try {
            quicksort(0, list.length - 1);
        } catch (InterruptedException e) {
        }
        A = -1;
        B = -1;
        D = -1;
        C = size - 1;
        repaint();
    }
    private void quicksort(int low, int high) throws InterruptedException {
        int i = low;
        int j = high;
        repaint();
        int pivot = list[low + (high - low) / 2];
        A = low + (high - low) / 2;

        while (i <= j) {
            while (list[i] < pivot) {
                i++;
                B = i;
                repaint();
            }
            while (list[j] > pivot) {
                j--;
                D = j;
                repaint();
            }

            if (i <= j) {
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                if(i == A) {
                    A = j;
                } else if (j == A) {
                    A = i;
                }
                Thread.sleep(1);
                repaint();
                i++;
                j--;
            }
        }

        if (low < j) {
            quicksort(low, j);
        }
        if (i < high) {
            quicksort(i, high);
        }
        if(low > C) {
            C = low;
            B = -1;
            D = -1;
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = (C == -1 ? 0 : C); i < list.length; i++) {
            Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
            FontMetrics nameFontMetrix = getFontMetrics(nameFont);
            g.setFont(nameFont);
            g.setColor(Color.CYAN);
            endtime = System.currentTimeMillis();
            double Time = (endtime-starttime)/1000.0;
            time = Double.toString(Time);
            g.drawString(time,10, 15);
            String name = "Quick";
            g.drawString(name, ((getWidth() - nameFontMetrix.stringWidth(name)) / 2), (BORDER_WIDTH + nameFontMetrix.getAscent() / 3));
            g.drawOval(i/5,(getHeight()-(list[i]/5))+20,1,1);
        }
        for (int i = 0; i <= C; i++) {
            Font nameFont = new Font(Font.MONOSPACED, Font.ITALIC, 15);
            FontMetrics nameFontMetrix = getFontMetrics(nameFont);
            g.setColor(Color.CYAN);
            g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);
        }
    }

}