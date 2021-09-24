import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Tile extends JButton {

    private int Xcord;
    private int Ycord;
    private boolean bomb = false;
    private int number = -1;
    private boolean flagged = false;

    private boolean redBomb = false;

    private boolean whenClicked = false;

    private boolean wronglyFlagged = false;

    public Tile(int x, int y){
        Xcord = x;
        Ycord = y;
        setLocation(x * 30, y * 30 + 100);
        setPreferredSize(new Dimension(30, 30));
        setVisible(true);
    }

    public int getXcord(){
        return Xcord;
    }

    public int getYcord(){
        return Ycord;
    }

    public boolean isBomb(){return bomb;}

    public boolean isNumber(){return number != -1;}

    public void makeBomb(){bomb = true;}

    public void makeNumber(int amount){number = amount;}

    public int getNumber(){return number;}

    public void makeFlagged(boolean s){flagged = s;}

    public boolean isFlagged(){return flagged;}

    public void setWhenClicked(){whenClicked = true;}

    public void setRed(){redBomb = true;}

    public boolean getWhenClicked(){return whenClicked;}

    public void setWronglyFlagged(){wronglyFlagged = true;}

    public void clear(){
        bomb = false;
        number = -1;
        flagged = false;
        redBomb = false;
        whenClicked = false;
        wronglyFlagged = false;
        MouseListener[] mouseListeners = this.getMouseListeners();
        for(MouseListener l: mouseListeners)
             this.removeMouseListener(l);
        setEnabled(true);
    }

    public boolean isEmpty(){
        return !isNumber() && !isBomb();
    }

    public void paintComponent(Graphics g){
        BufferedImage img = null;
        if(whenClicked && isBomb() && redBomb){
            try {
                img = ImageIO.read(getClass().getResource("/redbomb.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
        }
        else if(whenClicked && isBomb() && !flagged) {
            try {
                img = ImageIO.read(getClass().getResource("/bomb.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
        }
        else if(wronglyFlagged) {
            try {
                img = ImageIO.read(getClass().getResource("/wrongflag.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
        }
        else if(flagged){
            try {
                img = ImageIO.read(getClass().getResource("/flag.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert img != null;
            g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
        }
        else {
            if(whenClicked && isNumber()){
                if(number == 0){
                    try {
                        img = ImageIO.read(getClass().getResource("/empty.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 1) {
                    try {
                        img = ImageIO.read(getClass().getResource("/one.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 2) {
                    try {
                        img = ImageIO.read(getClass().getResource("/two.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 3) {
                    try {
                        img = ImageIO.read(getClass().getResource("/three.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 4) {
                    try {
                        img = ImageIO.read(getClass().getResource("/four.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 5) {
                    try {
                        img = ImageIO.read(getClass().getResource("/five.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 6) {
                    try {
                        img = ImageIO.read(getClass().getResource("/six.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 7) {
                    try {
                        img = ImageIO.read(getClass().getResource("/seven.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
                else if(number == 8) {
                    try {
                        img = ImageIO.read(getClass().getResource("/eight.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert img != null;
                    g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
                }
            }
            else {
                try {
                    img = ImageIO.read(getClass().getResource("/tile.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert img != null;
                g.drawImage(img.getScaledInstance(30, 30, Image.SCALE_DEFAULT), 0, 0, null);
            }
        }
    }


}
