package Algorithm;
import java.awt.*;


public class Merge extends Panel {

    private int Start = -1;
    private int Finish = -1;

    public Merge(String name, int width, int height) {
        super(name,  width, height);
    }

    @Override
    public void reset() {
        Start = -1;
        Finish = -1;
    }

    @Override
    public void run() {
        try {
            mergeSort(0, list.length - 1);
            Start = 0;
            Finish = size - 1;
        } catch (InterruptedException e) {
        }
        repaint();
    }

    public void mergeSort(int start, int fin) throws InterruptedException {
        if ((fin - start) > 0) {
            mergeSort(start, start + (fin - start) / 2);
            mergeSort(start + (fin - start) / 2 + 1, fin);
            merge(start, start + (fin - start) / 2, start + (fin - start) / 2 + 1, fin);
        }
    }

    public void merge(int start1, int fin1, int start2, int fin2) throws InterruptedException {
        int[] list1 = new int[fin1 - start1 + 1];
        int[] list2 = new int[fin2 - start2 + 1];
        int[] tmp = new int[list1.length + list2.length];
        System.arraycopy(list, start1, list1, 0, list1.length);
        System.arraycopy(list, start2, list2, 0, list2.length);
        repaint();
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            repaint();
            if (list1[current1] < list2[current2]) {
                tmp[current3++] = list1[current1++];
            } else {
                tmp[current3++] = list2[current2++];
            }
            repaint();
        }

        while (current1 < list1.length) {
            tmp[current3++] = list1[current1++];
            repaint();
        }

        while (current2 < list2.length) {
            tmp[current3++] = list2[current2++];
            repaint();
        }

        Start = start1;
        for (int i = 0; i < tmp.length; i++) {
            Finish = start1 + i;
            list[start1 + i] =  tmp[i];
            Thread.sleep(1);
            repaint();
        }
        Start = -1;
        Finish = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < list.length; i++) {
            Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
            FontMetrics nameFontMetrix = getFontMetrics(nameFont);
            g.setFont(nameFont);
            g.setColor(Color.GREEN);
            endtime = System.currentTimeMillis();
            double Time = (endtime-starttime)/1000.0;
            time = Double.toString(Time);
            g.drawString(time,10, 15);
            String name = "Merge";

            g.drawString(name, ((getWidth() - nameFontMetrix.stringWidth(name)) / 2), (BORDER_WIDTH + nameFontMetrix.getAscent() / 3));
            g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);        }
        if((Start != -1)&&(Finish != -1)) {
            for (int i = Start; i <= Finish; i++) {
                g.setColor(Color.GREEN);
                g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);            }
        }
//

    }

}
