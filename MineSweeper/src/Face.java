import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Face extends JButton {

    private int faceType;
    public Face(){
        setLocation(270, 15);
        setVisible(false);
        setPreferredSize(new Dimension(75, 75));
        faceType = 1;
    }

    public void paintComponent(Graphics g){
        BufferedImage img = null;
        if(faceType == 1){
            try {
                img = ImageIO.read(getClass().getResource("/normalFace.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(75, 75, Image.SCALE_DEFAULT), 0, 0, null);
        }
        else if(faceType == 2){
            try {
                img = ImageIO.read(getClass().getResource("/deadFace.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(75, 75, Image.SCALE_DEFAULT), 0, 0, null);
        }
        else{
            try {
                img = ImageIO.read(getClass().getResource("/sunglasses.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(75, 75, Image.SCALE_DEFAULT), 0, 0, null);
        }
    }

    public void setFaceType(int type){
        faceType = type;
    }

    public int getFaceType(){
        return faceType;
    }
}
