package Algorithm;
import java.awt.*;

public class Heap extends Panel {

    private int A = -1;
    private int B = -1;
    private java.util.ArrayList<Integer> heapList = new java.util.ArrayList<Integer>();
    public Heap(String name, int width, int height) {
        super(name, width, height);
    }
    @Override
    public void reset() {
        A = -1;
        B = -1;
        heapList.clear();
    }
    @Override
    public void run() {
        try {
            // Add elements to the heap
            for (int i = 0; i < list.length; i++) {
                add(list[i]);
                repaint();
            }
            // Remove elements from the heap
            B = size;
            for (int i = list.length - 1; i >= 0; i--) {
                removeAndShow(i);
                repaint();
            }
        } catch (InterruptedException e) {
        }
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int columnWidth = (getWidth() - 1 * BORDER_WIDTH) / size;
        int columnHeight = (getHeight() - 1 * BORDER_WIDTH) / size;
        for (int i = heapList.size(); i < list.length; i++) {
            Font nameFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
            FontMetrics nameFontMetrix = getFontMetrics(nameFont);
            g.setFont(nameFont);
            g.setColor(Color.YELLOW);
            endtime = System.currentTimeMillis();
            double Time = (endtime-starttime)/1000.0;
            time = Double.toString(Time);
            g.drawString(time,10, 15);
            String name = "Heap";

            g.drawString(name, ((getWidth() - nameFontMetrix.stringWidth(name)) / 2), (BORDER_WIDTH + nameFontMetrix.getAscent() / 3));
            g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);        }
        for (int i = 0; i < heapList.size(); i++) {
            g.setColor(Color.YELLOW);
            g.drawOval(i/5,getHeight()-(list[i]/5)+20,1,1);        }
        }

    /** Add a new object into the heap
     * @throws InterruptedException */
    public void add(Integer newObject) throws InterruptedException {
        heapList.add(newObject);
        repaint();
        int currentIndex = heapList.size() - 1;
        A = currentIndex;
        while (currentIndex > 0) {
            repaint();
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (heapList.get(currentIndex).compareTo(heapList.get(parentIndex)) > 0) {
                repaint();
                Integer temp = heapList.get(currentIndex);
                heapList.set(currentIndex, heapList.get(parentIndex));
                heapList.set(parentIndex, temp);
            } else {
                break;
            }
            currentIndex = parentIndex;
            A = currentIndex;
        }
        A = -1;
    }

    /** Remove the root from the heap
     * @throws InterruptedException */
    public void removeAndShow(int showIndex) throws InterruptedException {
        if (heapList.size() == 0)
            return;
        repaint();
        Integer removedObject = heapList.get(0);
        heapList.set(0, heapList.get(heapList.size() - 1));
        heapList.remove(heapList.size() - 1);
        B--;
        list[showIndex] = removedObject;

        int currentIndex = 0;
        while (currentIndex < heapList.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= heapList.size())
                break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < heapList.size()) {
                repaint();
                if (heapList.get(maxIndex).compareTo(heapList.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (heapList.get(currentIndex).compareTo(heapList.get(maxIndex)) < 0) {
                Integer temp = heapList.get(maxIndex);
                heapList.set(maxIndex, heapList.get(currentIndex));
                heapList.set(currentIndex, temp);
                currentIndex = maxIndex;
                repaint();
                Thread.sleep(1);
            } else {
                break;
            }
        }
    }

}
