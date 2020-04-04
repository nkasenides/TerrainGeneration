import javax.swing.*;
import java.awt.*;

public class ViewForm extends JPanel {

    private static JFrame jFrame = new JFrame("Canvas");
    private static MyCanvas canvas;
    public static void main(String[] args) {
        canvas = new MyCanvas(1000, 1000);
//        jFrame.setUndecorated(true);
        jFrame.add(canvas);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}