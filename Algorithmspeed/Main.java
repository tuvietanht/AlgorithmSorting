import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.*;
import Algorithm.*;
import Algorithm.Panel;

public class Main extends JApplet {
    private Panel[] sortPanels = new Panel[6];
    private String animationName = "";
    public Main() {
        SortPanelsHolder sortPanelHolder = new SortPanelsHolder();
        sortPanelHolder.setLayout(new GridLayout(2, 3));
        sortPanelHolder.setBackground(Color.BLACK);
        int width = 427;
        int height = 360;
        sortPanels[4] = new Selection("5th-Selection", width, height);
        sortPanels[3] = new Insertion("4th-Insertion", width, height);
        sortPanels[2] = new Merge("3rd-Merge", width, height);
        sortPanels[0] = new Quick("1st-Quick",  width, height);
        sortPanels[1] = new Heap("2nd-Heap",  width, height);
        sortPanels[5] = new Bubble("6th-Bubble",  width, height);
        for (int i = 0; i < sortPanels.length; i++) {
            sortPanels[i].setVisible(false);
            sortPanelHolder.add(sortPanels[i]);
        }
        add(sortPanelHolder);
    }
    class SortPanelsHolder extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            Font animationNameFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
            FontMetrics animationNameFontFontMetrix = getFontMetrics(animationNameFont);
            g.setFont(animationNameFont);
            int x = (getWidth() - animationNameFontFontMetrix.stringWidth(animationName)) / 2;
            int y = (getHeight() - animationNameFontFontMetrix.getLeading()) / 2;
            g.drawString(animationName, x, y);
        }
    }
    public void beginAnimation(String animationName){
        try {
            this.animationName = animationName;
            repaint();
            Thread.sleep(2500);
            this.animationName = "";
            repaint();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void beginAnimation(String animationName, int[] list) {
        try {
            this.animationName = animationName;
            repaint();
            Thread.sleep(2500);
            this.animationName = "";
            repaint();
            for (int i = 0; i < sortPanels.length; i++) {
                sortPanels[i].setList(list);
                sortPanels[i].setVisible(true);
            }
            ExecutorService executor = Executors.newFixedThreadPool(sortPanels.length);
            for (int i = 0; i < sortPanels.length; i++) {
                executor.execute(sortPanels[i]);
            }
            executor.shutdown();

            while(!executor.isTerminated()) {}
            Thread.sleep(2500);
            for (int i = 0; i < sortPanels.length; i++) {
                sortPanels[i].setVisible(false);
            }
            this.beginAnimation("Thanks for waching my final test");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame();
        Main main = new Main();
        frame.add(main);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        int[] list = new int[2000];
        for (int i = 0; i < list.length; i++) {
            list[i] = i + 1;
        }
        for (int i = 0; i < list.length; i++) {
            int index = (int) (Math.random() * (list.length));
            int temp = list[i];
            list[i] = list[index];
            list[index] = temp;
        }
        main.beginAnimation("Algorithm Sorting Test", list);
    }
}