import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private Tile[][] Tiles = new Tile[20][20];
    private boolean gameOver = false;
    private boolean paused = false;
    private int bombAmount;
    private int origBombAmount;
    private Face face = new Face();
    private numberOfBombsBox bombsBox;
    private ArrayList<Tile> flagged = new ArrayList<Tile>();
    private ArrayList<Tile> numbers = new ArrayList<Tile>();
    private ArrayList<Tile> bombs = new ArrayList<Tile>();
    private miniWindow mwindow = new miniWindow();

    private boolean beginning = true;
    StartScreen startScreen = new StartScreen();

    ChangeDifficultyButton changeDifficultyButton = new ChangeDifficultyButton();

    //this is for startscreen so that it doesnt ask for a new game if you havnt clicked anything yet
    private boolean gameStart;

    public Board() {
        setPreferredSize(new Dimension(600, 700));
        setLayout(null);

        bombsBox = new numberOfBombsBox();
        bombsBox.setSize(new Dimension(150, 75));
        bombsBox.setLocation(30, 15);
        bombsBox.setFont(new Font("Serif", Font.PLAIN, 80));
        add(bombsBox);

        startScreen.setVisible(true);
        startScreen.setSize(new Dimension(600, 700));
        startScreen.setLocation(0, 0);
        initStartScreen();
        add(startScreen);

        changeDifficultyButton.setSize(new Dimension(75, 75));
        makeBackButton();
        add(changeDifficultyButton);

        face.setSize(new Dimension(75, 75));
        makeFaceButton();
        add(face);

        mwindow.setSize(new Dimension(300, 150));
        mwindow.setLocation(150, 310);
        add(mwindow);

        addAllTiles();
    }

    public void addAllTiles(){
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                Tiles[y][x] = new Tile(x, y);
                Tiles[y][x].setSize(new Dimension(30, 30));
                add(Tiles[y][x]);
            }
        }
    }

    public void initStartScreen() {
        startScreen.easy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startScreen.setVisible(false);
                startGame(50);
                gameOver = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        startScreen.medium.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startScreen.setVisible(false);
                startGame(100);
                gameOver = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        startScreen.hard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startScreen.setVisible(false);
                startGame(150);
                gameOver = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        startScreen.custom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startScreen.easy.setVisible(false);
                startScreen.medium.setVisible(false);
                startScreen.hard.setVisible(false);
                startScreen.custom.setVisible(false);
                startScreen.random.setVisible(false);
                startScreen.textField.setVisible(true);
                startScreen.textField.requestFocusInWindow();
                startScreen.textField.selectAll();
                startScreen.textField.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int temp = -1;
                        try{
                            temp = Integer.parseInt(startScreen.textField.getText());
                        }
                        catch(NumberFormatException a){

                        }
                        if(e.getKeyCode() == KeyEvent.VK_ENTER && temp != -1){
                            startScreen.easy.setVisible(true);
                            startScreen.medium.setVisible(true);
                            startScreen.hard.setVisible(true);
                            startScreen.custom.setVisible(true);
                            startScreen.random.setVisible(true);
                            startScreen.setVisible(false);
                            startGame(temp);
                            startScreen.textField.setText("Amount");
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                gameOver = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        startScreen.random.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startScreen.setVisible(false);
                startGame(-1);
                gameOver = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void startGame(int b){
        if(b < 0 && b != -1)
            bombAmount = 0;
        else if(b >= 400)
            bombAmount = 398;
        else if(b == -1)
            bombAmount = (int)(Math.random() * 200);
        else
            bombAmount = b;

        origBombAmount = bombAmount;

        bombsBox.setBombAmount(bombAmount);
        bombsBox.setVisible(true);

        changeDifficultyButton.setVisible(true);

        face.setFaceType(1);
        face.setVisible(true);

        gameStart = true;

        initBeginningTiles();
    }

    private void makeBackButton() {
        changeDifficultyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameOver || gameStart){
                    changeDifficultyButton.setVisible(false);
                    bombsBox.setVisible(false);
                    face.setVisible(false);
                    for(int y = 0; y < 20; y++){
                        for(int x = 0; x < 20; x++) {
                            Tiles[y][x].clear();
                        }
                    }
                    flagged.clear();
                    numbers.clear();
                    bombs.clear();
                    gameOver = false;
                    gameStart = true;
                    startScreen.setVisible(true);
                }
                else{
                    paused = true;
                    mwindow.yes.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            changeDifficultyButton.setVisible(false);
                            bombsBox.setVisible(false);
                            face.setVisible(false);
                            for(int y = 0; y < 20; y++){
                                for(int x = 0; x < 20; x++) {
                                    Tiles[y][x].clear();
                                }
                            }
                            flagged.clear();
                            numbers.clear();
                            bombs.clear();
                            gameOver = false;
                            gameStart = true;
                            startScreen.setVisible(true);
                            paused = false;
                            mwindow.setVisible(false);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                    mwindow.no.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            paused = false;
                            mwindow.setVisible(false);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                    mwindow.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void initBeginningTiles(){
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                Tile t = Tiles[y][x];
                t.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(SwingUtilities.isLeftMouseButton(e)) {
                            if (!paused)
                                initTiles(t.getXcord(), t.getYcord());
                            gameStart = false;
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
    }

    public void makeFaceButton(){
        face.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    if(face.getFaceType() == 1) {
                        paused = true;
                        mwindow.yes.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                restart();
                                paused = false;
                                mwindow.setVisible(false);
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                            }
                        });
                        mwindow.no.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                mwindow.setVisible(false);
                                paused = false;
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                            }
                        });
                        mwindow.setVisible(true);
                    }
                    else {
                        restart();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void restart(){
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                Tiles[y][x].clear();
            }
        }
        gameOver = false;
        gameStart = true;
        bombAmount = origBombAmount;
        bombsBox.setBombAmount(bombAmount);
        face.setFaceType(1);
        flagged.clear();
        numbers.clear();
        bombs.clear();
        initBeginningTiles();
        repaint();
    }

    public void paintComponent(Graphics g){
        // establishing grid
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 600, 100);
        for (int i = 0; i < 20; i++) {
            g.drawLine(30 * i, 100, 30 * i, 700);
            g.drawLine(0, 30 * i + 100, 600, 30 * i + 100);
        }
    }

    public void initTiles(int firstX, int firstY){
        // establishing bombs, i is how many bombs
        for(int y = 0; y < 20; y++) {
            for(int x = 0; x < 20; x++){
                Tiles[y][x].clear();
            }
        }
        for(int i = 0; i < bombAmount; i++){
            boolean done = false;
            while(!done) {
                int Y = (int) (Math.random() * 20);
                int X = (int) (Math.random() * 20);
                if(Tiles[Y][X].isEmpty() && !(Y == firstY && X == firstX)){
                    Tiles[Y][X].makeBomb();
                    Tile t = Tiles[Y][X];
                    t.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(!gameOver && !paused) {
                                if (SwingUtilities.isRightMouseButton(e)) {
                                    if(t.isFlagged()) {
                                        flagged.remove(t);
                                        bombsBox.plusBomb();
                                        bombsBox.repaint();
                                    }
                                    else {
                                        flagged.add(t);
                                        bombsBox.minusBomb();
                                        bombsBox.repaint();
                                    }
                                    t.makeFlagged(!t.isFlagged());
                                    t.repaint();
                                } else if (!t.isFlagged()) {
                                    t.setWhenClicked();
                                    t.setRed();
                                    t.repaint();
                                    GameOver();
                                }
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                    bombs.add(Tiles[Y][X]);
                    done = true;
                }
            }
        }

        // establishing numbers
            // top left corner
        if(Tiles[0][0].isEmpty()){
            int temp = 0;
            if(Tiles[0][1].isBomb())
                temp++;
            if(Tiles[1][0].isBomb())
                temp++;
            if(Tiles[1][1].isBomb())
                temp++;
            Tiles[0][0].makeNumber(temp);
            Tiles[0][0].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!gameOver && !paused) {
                        if (SwingUtilities.isRightMouseButton(e) && !Tiles[0][0].getWhenClicked()) {
                            if(Tiles[0][0].isFlagged()) {
                                flagged.remove(Tiles[0][0]);
                                bombsBox.plusBomb();
                                bombsBox.repaint();
                            }
                            else {
                                flagged.add(Tiles[0][0]);
                                bombsBox.minusBomb();
                                bombsBox.repaint();
                            }
                            Tiles[0][0].makeFlagged(!Tiles[0][0].isFlagged());
                            Tiles[0][0].repaint();
                            Tiles[0][0].setEnabled(false);

                        } else if (!Tiles[0][0].isFlagged()) {
                            numbers.remove(Tiles[0][0]);
                            checkIfAllNumbersClicked();
                            Tiles[0][0].setWhenClicked();
                            Tiles[0][0].repaint();
                            if(Tiles[0][0].getNumber() == 0)
                                unlocks(0, 0);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            numbers.add(Tiles[0][0]);
        }
            // top right corner
        if(Tiles[0][19].isEmpty()){
            int temp = 0;
            if(Tiles[0][18].isBomb())
                temp++;
            if(Tiles[1][18].isBomb())
                temp++;
            if(Tiles[1][19].isBomb())
                temp++;
            Tiles[0][19].makeNumber(temp);
            Tiles[0][19].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!gameOver && !paused) {
                        if (SwingUtilities.isRightMouseButton(e) && !Tiles[0][19].getWhenClicked()) {
                            if(Tiles[0][19].isFlagged()) {
                                flagged.remove(Tiles[0][19]);
                                bombsBox.plusBomb();
                                bombsBox.repaint();
                            }
                            else {
                                flagged.add(Tiles[0][19]);
                                bombsBox.minusBomb();
                                bombsBox.repaint();
                            }
                            Tiles[0][19].makeFlagged(!Tiles[0][19].isFlagged());
                            Tiles[0][19].repaint();
                            Tiles[0][19].setEnabled(false);
                        } else if (!Tiles[0][19].isFlagged()) {
                            Tiles[0][19].setWhenClicked();
                            numbers.remove(Tiles[0][19]);
                            checkIfAllNumbersClicked();
                            Tiles[0][19].repaint();
                            if(Tiles[0][19].getNumber() == 0)
                                unlocks(19, 0);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            numbers.add(Tiles[0][19]);
            add(Tiles[0][19]);
        }
            // bottom left corner
        if(Tiles[19][0].isEmpty()){
            int temp = 0;
            if(Tiles[18][0].isBomb())
                temp++;
            if(Tiles[18][1].isBomb())
                temp++;
            if(Tiles[19][1].isBomb())
                temp++;
            Tiles[19][0].makeNumber(temp);
            Tiles[19][0].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!gameOver && !paused) {
                        if (SwingUtilities.isRightMouseButton(e) && !Tiles[19][0].getWhenClicked()) {
                            if(Tiles[19][0].isFlagged()) {
                                flagged.remove(Tiles[19][0]);
                                bombsBox.plusBomb();
                                bombsBox.repaint();
                            }
                            else {
                                flagged.add(Tiles[19][0]);
                                bombsBox.minusBomb();
                                bombsBox.repaint();
                            }
                            Tiles[19][0].makeFlagged(!Tiles[19][0].isFlagged());
                            Tiles[19][0].repaint();
                            Tiles[19][0].setEnabled(false);
                        } else if (!Tiles[19][0].isFlagged()) {
                            Tiles[19][0].setWhenClicked();
                            numbers.remove(Tiles[19][0]);
                            checkIfAllNumbersClicked();
                            Tiles[19][0].repaint();
                            if(Tiles[19][0].getNumber() == 0)
                                unlocks(0, 19);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            numbers.add(Tiles[19][0]);
        }
            // bottom right corner
        if(Tiles[19][19].isEmpty()){
            int temp = 0;
            if(Tiles[19][18].isBomb())
                temp++;
            if(Tiles[18][18].isBomb())
                temp++;
            if(Tiles[18][19].isBomb())
                temp++;
            Tiles[19][19].makeNumber(temp);
            Tiles[19][19].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!gameOver && !paused) {
                        if (SwingUtilities.isRightMouseButton(e) && !Tiles[19][19].getWhenClicked()) {
                            if(Tiles[19][19].isFlagged()) {
                                flagged.remove(Tiles[19][19]);
                                bombsBox.plusBomb();
                                bombsBox.repaint();
                            }
                            else {
                                flagged.add(Tiles[0][0]);
                                bombsBox.minusBomb();
                                bombsBox.repaint();
                            }
                            Tiles[19][19].makeFlagged(!Tiles[19][19].isFlagged());
                            Tiles[19][19].repaint();
                            Tiles[19][19].setEnabled(false);
                        } else if (!Tiles[19][19].isFlagged()) {
                            Tiles[19][19].setWhenClicked();
                            Tiles[19][19].repaint();
                            numbers.remove(Tiles[19][19]);
                            checkIfAllNumbersClicked();
                            if(Tiles[19][19].getNumber() == 0)
                                unlocks(19, 19);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            numbers.add(Tiles[19][19]);
        }

        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                if(Tiles[y][x].isEmpty()) {
                    int temp = 0;
                    if (y == 0) {
                        //top
                        if(Tiles[y][x - 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x - 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x].isBomb())
                            temp++;
                        if(Tiles[y + 1][x + 1].isBomb())
                            temp++;
                        if(Tiles[y][x + 1].isBomb())
                            temp++;
                    }
                    else if (x == 0) {
                        //left
                        if(Tiles[y - 1][x].isBomb())
                            temp++;
                        if(Tiles[y - 1][x + 1].isBomb())
                            temp++;
                        if(Tiles[y][x + 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x + 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x].isBomb())
                            temp++;
                    }
                    else if (y == 19) {
                        //bottom
                        if(Tiles[y][x - 1].isBomb())
                            temp++;
                        if(Tiles[y - 1][x - 1].isBomb())
                            temp++;
                        if(Tiles[y - 1][x].isBomb())
                            temp++;
                        if(Tiles[y - 1][x + 1].isBomb())
                            temp++;
                        if(Tiles[y][x + 1].isBomb())
                            temp++;
                    }
                    else if (x == 19) {
                        //right
                        if(Tiles[y - 1][x].isBomb())
                            temp++;
                        if(Tiles[y - 1][x - 1].isBomb())
                            temp++;
                        if(Tiles[y][x - 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x - 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x].isBomb())
                            temp++;
                    }
                    else{
                        if(Tiles[y - 1][x - 1].isBomb())
                            temp++;
                        if(Tiles[y - 1][x].isBomb())
                            temp++;
                        if(Tiles[y - 1][x + 1].isBomb())
                            temp++;
                        if(Tiles[y][x - 1].isBomb())
                            temp++;
                        if(Tiles[y][x + 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x - 1].isBomb())
                            temp++;
                        if(Tiles[y + 1][x].isBomb())
                            temp++;
                        if(Tiles[y + 1][x + 1].isBomb())
                            temp++;
                    }
                    Tiles[y][x].makeNumber(temp);
                    Tile t = Tiles[y][x];
                    t.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(!gameOver && !paused) {
                                if (SwingUtilities.isRightMouseButton(e) && !t.getWhenClicked()) {
                                    if(t.isFlagged()) {
                                        flagged.remove(t);
                                        bombsBox.plusBomb();
                                        bombsBox.repaint();
                                    }
                                    else {
                                        flagged.add(t);
                                        bombsBox.minusBomb();
                                        bombsBox.repaint();
                                    }
                                    t.makeFlagged(!t.isFlagged());
                                    t.repaint();
                                    t.setEnabled(false);
                                } else if (!t.isFlagged()) {
                                    t.setWhenClicked();
                                    numbers.remove(t);
                                    checkIfAllNumbersClicked();
                                    t.repaint();
                                    if(t.getNumber() == 0)
                                        unlocks(t.getXcord(), t.getYcord());
                                }
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                    numbers.add(Tiles[y][x]);
                }
            }
        }
        Tiles[firstY][firstX].setWhenClicked();
        numbers.remove(Tiles[firstY][firstX]);
        checkIfAllNumbersClicked();
        Tiles[firstY][firstX].repaint();
        if(Tiles[firstY][firstX].getNumber() == 0)
            unlocks(firstX, firstY);
    }

    private void checkIfAllNumbersClicked() {
        if(numbers.isEmpty()){
            GameWon();
        }
    }

    private void GameWon() {
        for(int i = 0; i < bombs.size(); i++){
            if(!bombs.get(i).isFlagged()){
                bombs.get(i).makeFlagged(true);
                bombs.get(i).repaint();
                bombsBox.setBombAmount(0);
            }
        }
        gameOver = true;
        face.setFaceType(3);
        face.repaint();
    }

    private void GameOver() {
        gameOver = true;
        for(int i = 0; i < flagged.size(); i++){
            if(flagged.get(i).isNumber()){
                flagged.get(i).setWronglyFlagged();
                flagged.get(i).repaint();
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).setWhenClicked();
            bombs.get(i).repaint();
        }
        face.setFaceType(2);
        face.repaint();
    }

    // This unlocks all white spaces in the vecinity of one
    private void unlocks(int x, int y){
        if(x == 0){
            if(y == 0) {
                if (!Tiles[y + 1][x].getWhenClicked() && !Tiles[y + 1][x].isFlagged()) {
                    Tiles[y + 1][x].setWhenClicked();
                    Tiles[y + 1][x].repaint();
                    numbers.remove(Tiles[y + 1][x]);
                    if(Tiles[y + 1][x].getNumber() == 0)
                        unlocks(x, y + 1);
                }
                if (!Tiles[y + 1][x + 1].getWhenClicked() && !Tiles[y + 1][x + 1].isFlagged()) {
                    Tiles[y + 1][x + 1].setWhenClicked();
                    Tiles[y + 1][x + 1].repaint();
                    numbers.remove(Tiles[y + 1][x + 1]);
                    if(Tiles[y + 1][x + 1].getNumber() == 0)
                        unlocks(x + 1, y + 1);
                }
                if (!Tiles[y][x + 1].getWhenClicked() && !Tiles[y][x + 1].isFlagged()) {
                    Tiles[y][x + 1].setWhenClicked();
                    Tiles[y][x + 1].repaint();
                    numbers.remove(Tiles[y][x + 1]);
                    if(Tiles[y][x + 1].getNumber() == 0)
                        unlocks(x + 1, y);
                }
            }
            else if(y == 19){
                if (!Tiles[y - 1][x].getWhenClicked() && !Tiles[y - 1][x].isFlagged()) {
                    Tiles[y - 1][x].setWhenClicked();
                    Tiles[y - 1][x].repaint();
                    numbers.remove(Tiles[y - 1][x]);
                    if(Tiles[y - 1][x].getNumber() == 0)
                        unlocks(x, y - 1);
                }
                if (!Tiles[y - 1][x  + 1].getWhenClicked() && !Tiles[y - 1][x + 1].isFlagged()) {
                    Tiles[y - 1][x + 1].setWhenClicked();
                    Tiles[y - 1][x + 1].repaint();
                    numbers.remove(Tiles[y - 1][x + 1]);
                    if(Tiles[y - 1][x + 1].getNumber() == 0)
                        unlocks(x + 1, y - 1);
                }
                if (!Tiles[y][x + 1].getWhenClicked() && !Tiles[y][x + 1].isFlagged()) {
                    Tiles[y][x + 1].setWhenClicked();
                    Tiles[y][x + 1].repaint();
                    numbers.remove(Tiles[y][x + 1]);
                    if(Tiles[y][x + 1].getNumber() == 0)
                        unlocks(x + 1, y);
                }

            }
            else{
                if(!Tiles[y - 1][x].getWhenClicked() && !Tiles[y - 1][x].isFlagged()) {
                    Tiles[y - 1][x].setWhenClicked();
                    Tiles[y - 1][x].repaint();
                    numbers.remove(Tiles[y - 1][x]);
                    if(Tiles[y - 1][x].getNumber() == 0)
                        unlocks(x, y - 1);
                }
                if(!Tiles[y - 1][x + 1].getWhenClicked() && !Tiles[y - 1][x + 1].isFlagged()) {
                    Tiles[y - 1][x + 1].setWhenClicked();
                    Tiles[y - 1][x + 1].repaint();
                    numbers.remove(Tiles[y - 1][x + 1]);
                    if(Tiles[y - 1][x + 1].getNumber() == 0)
                        unlocks(x + 1, y - 1);
                }
                if(!Tiles[y][x + 1].getWhenClicked() && !Tiles[y][x + 1].isFlagged()) {
                    Tiles[y][x + 1].setWhenClicked();
                    Tiles[y][x + 1].repaint();
                    numbers.remove(Tiles[y][x + 1]);
                    if(Tiles[y][x + 1].getNumber() == 0)
                        unlocks(x + 1, y);
                }
                if(!Tiles[y + 1][x + 1].getWhenClicked() && !Tiles[y + 1][x + 1].isFlagged()) {
                    Tiles[y + 1][x + 1].setWhenClicked();
                    Tiles[y + 1][x + 1].repaint();
                    numbers.remove(Tiles[y + 1][x + 1]);
                    if(Tiles[y + 1][x + 1].getNumber() == 0)
                        unlocks(x + 1, y + 1);
                }
                if(!Tiles[y + 1][x].getWhenClicked() && !Tiles[y + 1][x].isFlagged()) {
                    Tiles[y + 1][x].setWhenClicked();
                    Tiles[y + 1][x].repaint();
                    numbers.remove(Tiles[y + 1][x]);
                    if(Tiles[y + 1][x].getNumber() == 0)
                        unlocks(x, y + 1);
                }
            }
        }
        else if(x == 19){
            if(y == 0) {
                if (!Tiles[y + 1][x].getWhenClicked() && !Tiles[y + 1][x].isFlagged()) {
                    Tiles[y + 1][x].setWhenClicked();
                    Tiles[y + 1][x].repaint();
                    numbers.remove(Tiles[y + 1][x]);
                    if(Tiles[y + 1][x].getNumber() == 0)
                        unlocks(x, y + 1);
                }
                if (!Tiles[y + 1][x - 1].getWhenClicked() && !Tiles[y + 1][x - 1].isFlagged()) {
                    Tiles[y + 1][x - 1].setWhenClicked();
                    Tiles[y + 1][x - 1].repaint();
                    numbers.remove(Tiles[y + 1][x - 1]);
                    if(Tiles[y + 1][x - 1].getNumber() == 0)
                        unlocks(x - 1, y + 1);
                }
                if (!Tiles[y][x - 1].getWhenClicked() && !Tiles[y][x - 1].isFlagged()) {
                    Tiles[y][x - 1].setWhenClicked();
                    Tiles[y][x - 1].repaint();
                    numbers.remove(Tiles[y][x - 1]);
                    if(Tiles[y][x - 1].getNumber() == 0)
                        unlocks(x - 1, y);
                }
            }
            else if(y == 19){
                if(!Tiles[y - 1][x].getWhenClicked() && !Tiles[y - 1][x].isFlagged()) {
                    Tiles[y - 1][x].setWhenClicked();
                    Tiles[y - 1][x].repaint();
                    numbers.remove(Tiles[y - 1][x]);
                    if(Tiles[y - 1][x].getNumber() == 0)
                        unlocks(x, y - 1);
                }
                if(!Tiles[y - 1][x - 1].getWhenClicked() && !Tiles[y - 1][x - 1].isFlagged()) {
                    Tiles[y - 1][x - 1].setWhenClicked();
                    Tiles[y - 1][x - 1].repaint();
                    numbers.remove(Tiles[y - 1][x - 1]);
                    if(Tiles[y - 1][x - 1].getNumber() == 0)
                        unlocks(x - 1, y - 1);
                }
                if(!Tiles[y][x - 1].getWhenClicked() && !Tiles[y][x - 1].isFlagged()) {
                    Tiles[y][x - 1].setWhenClicked();
                    Tiles[y][x - 1].repaint();
                    numbers.remove(Tiles[y][x - 1]);
                    if(Tiles[y][x - 1].getNumber() == 0)
                        unlocks(x - 1, y);
                }
            }
            else{
                if(!Tiles[y - 1][x].getWhenClicked() && !Tiles[y - 1][x].isFlagged()) {
                    Tiles[y - 1][x].setWhenClicked();
                    Tiles[y - 1][x].repaint();
                    numbers.remove(Tiles[y - 1][x]);
                    if(Tiles[y - 1][x].getNumber() == 0)
                        unlocks(x, y - 1);
                }
                if(!Tiles[y - 1][x - 1].getWhenClicked() && !Tiles[y - 1][x - 1].isFlagged()) {
                    Tiles[y - 1][x - 1].setWhenClicked();
                    Tiles[y - 1][x - 1].repaint();
                    numbers.remove(Tiles[y - 1][x - 1]);
                    if(Tiles[y - 1][x - 1].getNumber() == 0)
                        unlocks(x - 1, y - 1);
                }
                if(!Tiles[y][x - 1].getWhenClicked() && !Tiles[y][x - 1].isFlagged()) {
                    Tiles[y][x - 1].setWhenClicked();
                    Tiles[y][x - 1].repaint();
                    numbers.remove(Tiles[y][x - 1]);
                    if(Tiles[y][x - 1].getNumber() == 0)
                    unlocks(x - 1, y);
                }
                if(!Tiles[y + 1][x - 1].getWhenClicked() && !Tiles[y + 1][x - 1].isFlagged()) {
                    Tiles[y + 1][x - 1].setWhenClicked();
                    Tiles[y + 1][x - 1].repaint();
                    numbers.remove(Tiles[y + 1][x - 1]);
                    if(Tiles[y + 1][x - 1].getNumber() == 0)
                        unlocks(x - 1, y + 1);
                }
                if(!Tiles[y + 1][x].getWhenClicked() && !Tiles[y + 1][x].isFlagged()) {
                    Tiles[y + 1][x].setWhenClicked();
                    Tiles[y + 1][x].repaint();
                    numbers.remove(Tiles[y + 1][x]);
                    if(Tiles[y + 1][x].getNumber() == 0)
                        unlocks(x, y + 1);
                }
            }
        }
        else if(y == 0){
            if(!Tiles[y][x - 1].getWhenClicked() && !Tiles[y][x - 1].isFlagged()) {
                Tiles[y][x - 1].setWhenClicked();
                Tiles[y][x - 1].repaint();
                numbers.remove(Tiles[y][x - 1]);
                if(Tiles[y][x - 1].getNumber() == 0)
                    unlocks(x - 1, y);
            }
            if(!Tiles[y + 1][x - 1].getWhenClicked() && !Tiles[y + 1][x - 1].isFlagged()) {
                Tiles[y + 1][x - 1].setWhenClicked();
                Tiles[y + 1][x - 1].repaint();
                numbers.remove(Tiles[y + 1][x - 1]);
                if(Tiles[y + 1][x - 1].getNumber() == 0)
                    unlocks(x - 1, y + 1);
            }
            if(!Tiles[y + 1][x].getWhenClicked() && !Tiles[y + 1][x].isFlagged()) {
                Tiles[y + 1][x].setWhenClicked();
                Tiles[y + 1][x].repaint();
                numbers.remove(Tiles[y + 1][x]);
                if(Tiles[y + 1][x].getNumber() == 0)
                    unlocks(x, y + 1);
            }
            if(!Tiles[y + 1][x + 1].getWhenClicked() && !Tiles[y + 1][x + 1].isFlagged()) {
                Tiles[y + 1][x + 1].setWhenClicked();
                Tiles[y + 1][x + 1].repaint();
                numbers.remove(Tiles[y + 1][x + 1]);
                if(Tiles[y + 1][x + 1].getNumber() == 0)
                    unlocks(x + 1, y + 1);
            }
            if(!Tiles[y][x + 1].getWhenClicked() && !Tiles[y][x + 1].isFlagged()) {
                Tiles[y][x + 1].setWhenClicked();
                Tiles[y][x + 1].repaint();
                numbers.remove(Tiles[y][x + 1]);
                if(Tiles[y][x + 1].getNumber() == 0)
                    unlocks(x + 1, y);
            }
        }
        else if(y == 19){
            if(!Tiles[y][x - 1].getWhenClicked() && !Tiles[y][x - 1].isFlagged()) {
                Tiles[y][x - 1].setWhenClicked();
                Tiles[y][x - 1].repaint();
                numbers.remove(Tiles[y][x - 1]);
                if(Tiles[y][x - 1].getNumber() == 0)
                    unlocks(x - 1, y);
            }
            if(!Tiles[y - 1][x - 1].getWhenClicked() && !Tiles[y - 1][x - 1].isFlagged()) {
                Tiles[y - 1][x - 1].setWhenClicked();
                Tiles[y - 1][x - 1].repaint();
                numbers.remove(Tiles[y - 1][x - 1]);
                if(Tiles[y - 1][x - 1].getNumber() == 0)
                    unlocks(x - 1, y - 1);
            }
            if(!Tiles[y - 1][x].getWhenClicked() && !Tiles[y - 1][x].isFlagged()) {
                Tiles[y - 1][x].setWhenClicked();
                Tiles[y - 1][x].repaint();
                numbers.remove(Tiles[y - 1][x]);
                if(Tiles[y - 1][x].getNumber() == 0)
                    unlocks(x, y - 1);
            }
            if(!Tiles[y - 1][x + 1].getWhenClicked() && !Tiles[y - 1][x + 1].isFlagged()) {
                Tiles[y - 1][x + 1].setWhenClicked();
                Tiles[y - 1][x + 1].repaint();
                numbers.remove(Tiles[y - 1][x + 1]);
                if(Tiles[y - 1][x + 1].getNumber() == 0)
                    unlocks(x + 1, y - 1);
            }
            if(!Tiles[y][x + 1].getWhenClicked() && !Tiles[y][x + 1].isFlagged()) {
                Tiles[y][x + 1].setWhenClicked();
                Tiles[y][x + 1].repaint();
                numbers.remove(Tiles[y][x + 1]);
                if(Tiles[y][x + 1].getNumber() == 0)
                    unlocks(x + 1, y);
            }
        }
        else{
            if(!Tiles[y - 1][x - 1].getWhenClicked() && !Tiles[y - 1][x - 1].isFlagged()) {
                Tiles[y - 1][x - 1].setWhenClicked();
                Tiles[y - 1][x - 1].repaint();
                numbers.remove(Tiles[y - 1][x - 1]);
                if(Tiles[y - 1][x - 1].getNumber() == 0)
                    unlocks(x - 1, y - 1);
            }
            if(!Tiles[y - 1][x].getWhenClicked() && !Tiles[y - 1][x].isFlagged()) {
                Tiles[y - 1][x].setWhenClicked();
                Tiles[y - 1][x].repaint();
                numbers.remove(Tiles[y - 1][x]);
                if(Tiles[y - 1][x].getNumber() == 0)
                    unlocks(x, y - 1);
            }
            if(!Tiles[y - 1][x + 1].getWhenClicked() && !Tiles[y - 1][x + 1].isFlagged()) {
                Tiles[y - 1][x + 1].setWhenClicked();
                Tiles[y - 1][x + 1].repaint();
                numbers.remove(Tiles[y - 1][x + 1]);
                if(Tiles[y - 1][x + 1].getNumber() == 0)
                    unlocks(x + 1, y - 1);
            }
            if(!Tiles[y][x - 1].getWhenClicked() && !Tiles[y][x - 1].isFlagged()) {
                Tiles[y][x - 1].setWhenClicked();
                Tiles[y][x - 1].repaint();
                numbers.remove(Tiles[y][x - 1]);
                if(Tiles[y][x - 1].getNumber() == 0)
                    unlocks(x - 1, y);
            }
            if(!Tiles[y][x + 1].getWhenClicked() && !Tiles[y][x + 1].isFlagged()) {
                Tiles[y][x + 1].setWhenClicked();
                Tiles[y][x + 1].repaint();
                numbers.remove(Tiles[y][x + 1]);
                if(Tiles[y][x + 1].getNumber() == 0)
                    unlocks(x + 1, y);
            }
            if(!Tiles[y + 1][x - 1].getWhenClicked() && !Tiles[y + 1][x - 1].isFlagged()) {
                Tiles[y + 1][x - 1].setWhenClicked();
                Tiles[y + 1][x - 1].repaint();
                numbers.remove(Tiles[y + 1][x - 1]);
                if(Tiles[y + 1][x - 1].getNumber() == 0)
                    unlocks(x - 1, y + 1);
            }
            if(!Tiles[y + 1][x].getWhenClicked() && !Tiles[y + 1][x].isFlagged()) {
                Tiles[y + 1][x].setWhenClicked();
                Tiles[y + 1][x].repaint();
                numbers.remove(Tiles[y + 1][x]);
                if(Tiles[y + 1][x].getNumber() == 0)
                    unlocks(x, y + 1);
            }
            if(!Tiles[y + 1][x + 1].getWhenClicked() && !Tiles[y + 1][x + 1].isFlagged()) {
                Tiles[y + 1][x + 1].setWhenClicked();
                Tiles[y + 1][x + 1].repaint();
                numbers.remove(Tiles[y + 1][x + 1]);
                if (Tiles[y + 1][x + 1].getNumber() == 0)
                    unlocks(x + 1, y + 1);
            }
        }
        checkIfAllNumbersClicked();
    }
}