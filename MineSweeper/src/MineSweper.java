import javax.swing.*;

public class MineSweper {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Board b = new Board();
        b.setVisible(true);
        frame.add(b);

        frame.setResizable(false);
        frame.setLocation(400, 0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("MineSweeper");

        frame.pack();
        frame.setVisible(true);
    }
}
