import javax.swing.*;
import java.awt.*;

public class StartScreen extends JButton{

    private int amount;
    private boolean pressed = false;
    public JButton easy = new JButton("easy");
    public JButton medium = new JButton("medium");
    public JButton hard = new JButton("hard");
    public JButton custom = new JButton("custom");
    public JButton random = new JButton("random");
    public JTextField textField = new JTextField("Amount");

    public StartScreen(){
        setPreferredSize(new Dimension(600, 700));
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(null);

        textField.setSize(new Dimension(150, 50));
        textField.setLocation(200, 325);
        textField.setFocusable(true);
        textField.setVisible(false);
        textField.selectAll();

        easy.setVisible(true);
        medium.setVisible(true);
        hard.setVisible(true);
        custom.setVisible(true);
        random.setVisible(true);

        easy.setSize(new Dimension(200, 50));
        medium.setSize(new Dimension(200, 50));
        hard.setSize(new Dimension(200, 50));
        custom.setSize(new Dimension(200, 50));
        random.setSize(new Dimension(200, 50));

        easy.setLocation(200, 25);
        medium.setLocation(200, 175);
        hard.setLocation(200, 325);
        custom.setLocation(200, 475);
        random.setLocation(200, 625);
        add(easy);
        add(medium);
        add(hard);
        add(custom);
        add(random);
        add(textField);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, 600, 700);
    }

    public int getAmount(){return amount;}

    public boolean isPressed(){return pressed;}

    public void makeNotPressed(){pressed = false;}
}
