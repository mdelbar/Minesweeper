package minesweeperMVC;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Matthias
 */
public class MineSweeperInfoModel extends Model {

    private int[][] velden,  buren;
    private CoordJButton[][] buttonTable;
    private int blankFieldsLeft, flagsLeft, timePlayed;
    private static ImageIcon[] images;
    private int HEIGHT, WIDTH, AMOUNT_OF_MINES;
    private Random rg;
    private boolean gameOver;

    public MineSweeperInfoModel(int height, int width, int amountMines) {
        // Initialize various variables
        HEIGHT = height;
        WIDTH = width;
        AMOUNT_OF_MINES = amountMines;
        timePlayed = 0;
        velden = new int[HEIGHT][WIDTH];
        buren = new int[HEIGHT][WIDTH];
        blankFieldsLeft = HEIGHT * WIDTH;
        flagsLeft = 0;
        rg = new Random();
        buttonTable = new CoordJButton[HEIGHT][WIDTH];
        gameOver = false;

        createMines();

        // Initialize table with images
        images = new ImageIcon[13];
        for (int i = 0; i <= 8; i++) {
            images[i] = new ImageIcon(MineSweeperController.class.getResource("Mines" + i + ".gif"));
        }
        images[9] = new ImageIcon(MineSweeperController.class.getResource("Minesz.gif"));
        images[10] = new ImageIcon(MineSweeperController.class.getResource("Minesb.gif"));
        images[11] = new ImageIcon(MineSweeperController.class.getResource("Minesn.gif"));
        images[12] = new ImageIcon(MineSweeperController.class.getResource("Minesf.gif"));
    }

    public void createMines() {
        flagsLeft = 0;
        blankFieldsLeft = HEIGHT * WIDTH;
        int count = 0;
        while (count < AMOUNT_OF_MINES) {
            int x = rg.nextInt(HEIGHT-1);
            int y = rg.nextInt(WIDTH-1);
            if (getVelden(x, y) != 1) {
                addMine(x, y);
                count++;
            }
        }
    }

    public void addMine(int x, int y) {
        // Set given field as Bomb field
        setVelden(x,y,1);
        // Increase the bomb's neighbours
        incNeighbours(x,y);
        // Increase the amount of flags
        increaseFlagsLeft();
        // Decrease the blank fields by one
        decreaseBlankFieldsLeft();
    }

    public void showMines() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if(velden[i][j] > 5)
                    velden[i][j] = (velden[i][j] - 10);
                if (velden[i][j] == 1) {
                    buttonTable[i][j].changeImage(i,j,10);
                } else {
                    buttonTable[i][j].changeImage(i,j,buren[i][j]);
                }
            }
        }
        flagsLeft = 0;
        fireStateChanged();
    }

    public void incNeighbours(int x, int y)  {
        // Decrease the amount of blank fields left, if necessary
        if(getBuren(x,y) == 0)
            decreaseBlankFieldsLeft();

        // Start the actual increasing of neighbours
        if (x > 0 && y > 0) {
            increaseBuren(x - 1, y - 1);
            increaseBuren(x - 1, y);
            increaseBuren(x - 1, y + 1);

            increaseBuren(x, y - 1);
            increaseBuren(x, y + 1);

            increaseBuren(x + 1, y - 1);
            increaseBuren(x + 1, y);
            increaseBuren(x + 1, y + 1);
        }

        if (x > 0 && y == 0) {
            increaseBuren(x - 1, y);
            increaseBuren(x - 1, y + 1);

            increaseBuren(x, y + 1);

            increaseBuren(x + 1, y);
            increaseBuren(x + 1, y + 1);
        }

        if (x == 0 && y > 0) {
            increaseBuren(x, y - 1);
            increaseBuren(x, y + 1);

            increaseBuren(x + 1, y - 1);
            increaseBuren(x + 1, y);
            increaseBuren(x + 1, y + 1);
        }

        if (x == 0 && y == 0) {
            increaseBuren(x, y + 1);

            increaseBuren(x + 1, y);
            increaseBuren(x + 1, y + 1);
        }
    }

    public void increaseBuren(int x, int y) {
        buren[x][y]++;
    }

    public void decreaseBlankFieldsLeft() {
        blankFieldsLeft--;
    }

    public void decreaseFlagsLeft() {
        flagsLeft--;
        fireStateChanged();
    }

    public void increaseFlagsLeft() {
        flagsLeft++;
        fireStateChanged();
    }

    public void resetVelden() {
        velden = new int[HEIGHT][WIDTH];
    }

    public void resetBuren() {
        buren = new int[HEIGHT][WIDTH];
    }


    // Getters and Setters
    // -------------------

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getAMOUNT_OF_MINES() {
        return AMOUNT_OF_MINES;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean isItOver) {
        gameOver = isItOver;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void resetTimePlayed() {
        timePlayed = 0;
    }

    public void incTimePlayed() {
        timePlayed++;
    }

    public CoordJButton[][] getButtonTable() {
        return buttonTable;
    }

    public void setButtonTable(int x, int y, CoordJButton button) {
        buttonTable[x][y] = button;
    }

    public int getBlankFieldsLeft() {
        return blankFieldsLeft;
    }

    public void setBlankFieldsLeft(int blankFieldsLeft) {
        this.blankFieldsLeft = blankFieldsLeft;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public void setFlagsLeft(int flagsLeft) {
        this.flagsLeft = flagsLeft;
        fireStateChanged();
    }

    public int getVelden(int x, int y) {
        return velden[x][y];
    }

    public void setVelden(int x, int y, int value) {
        velden[x][y] = value;
    }

    public int getBuren(int x, int y) {
        return buren[x][y];
    }

    public void setBuren(int x, int y, int value) {
        buren[x][y] = value;
    }

    public ImageIcon getImage(int i) {
        return images[i];
    }
}
