import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class miniWindow extends JButton {

    public JButton yes = new JButton("Yes");
    public JButton no = new JButton("No");

    public miniWindow(){
        setPreferredSize(new Dimension(300, 150));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder());
        setVisible(false);
        add(yes, BorderLayout.EAST);
        add(no, BorderLayout.WEST);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,300, 150);
        g.setColor(Color.BLACK);
        g.drawLine(0,0, 300, 0);
        g.drawLine(0, 0, 0, 150);
        g.drawLine(0, 149, 299, 299);
        g.drawLine(299, 0, 299, 149);
        setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString("New Game?", 75, 75);
    }
}
