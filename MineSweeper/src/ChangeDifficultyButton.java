import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChangeDifficultyButton extends JButton {

    public ChangeDifficultyButton(){
        setLocation(450, 15);
        setVisible(false);
        setPreferredSize(new Dimension(75, 75));
    }

    public void paintComponent(Graphics g){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResource("/backbutton.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        assert img != null;
        g.drawImage(img.getScaledInstance(75, 75, Image.SCALE_DEFAULT), 0, 0, null);
    }
}
