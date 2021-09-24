import javax.swing.*;
import java.awt.*;

public class numberOfBombsBox extends JLabel {

    private int bombAmount = 0;

    public numberOfBombsBox(){
        this.bombAmount = bombAmount;
        setPreferredSize(new Dimension(150, 75));
        setVisible(false);
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,150,75);
        g.setColor(Color.BLACK);
        g.drawLine(0,0, 150, 0);
        g.drawLine(0,0,0, 75);
        g.drawLine(0, 74, 149, 74);
        g.drawLine(149, 0, 149, 74);
        g.drawString(Integer.toString(bombAmount), 15, 60);
    }

    public void minusBomb(){
        bombAmount--;
    }

    public void plusBomb(){
        bombAmount++;
    }

    public void setBombAmount(int amount) {bombAmount = amount;}
}
