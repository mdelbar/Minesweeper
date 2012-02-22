package minesweeperMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Matthias
 */
public class CoordJButton extends JButton implements ActionListener {

    private int locX,  locY;
    private MineSweeperInfoModel infoModel;

    public CoordJButton(int x, int y, ImageIcon image, MineSweeperInfoModel model) {
        super(image);
        locX = x;
        locY = y;
        infoModel = model;
        addActionListener(this);
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public void actionPerformed(ActionEvent e) {
        // The game is NOT over
        if (!infoModel.getGameOver()) {
            // Shift NOT pressed down
            if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == 0) {
                // If you've hit a bomb
                if (infoModel.getVelden(locX, locY) == 1) {
                    infoModel.showMines();
                    changeImage(locX, locY, 11);
                    infoModel.setGameOver(true);
                } // If you've hit a blank field
                if ((infoModel.getVelden(locX, locY) == 0) && (infoModel.getBuren(locX, locY) == 0)) {
                    showSurrounding(locX, locY);
                    infoModel.decreaseBlankFieldsLeft();
                } // If you've hit a field that's already been clicked, or a flagged field
                if ((infoModel.getVelden(locX, locY) > 5) || (infoModel.getVelden(locX, locY) == -1))/*&& (infoModel.getBuren(locX, locY) == 0)*/ {
                    // Do nothing, obviously
                } // If you've hit a field with neighbours
                if (infoModel.getVelden(locX, locY) == 0 && infoModel.getBuren(locX, locY) > 0) {
                    int amount = infoModel.getBuren(locX, locY);
                    changeImage(locX, locY, amount);
                }
            } // Shift IS pressed down
            else {
                int old = infoModel.getVelden(locX, locY);
                if (old != -1) {
                    if (old < 5) {
                        changeImage(locX, locY, 12);
                        infoModel.setVelden(locX, locY, (old + 10));
                        infoModel.decreaseFlagsLeft();
                    } else {
                        changeImage(locX, locY, 9);
                        infoModel.setVelden(locX, locY, (old - 10));
                        infoModel.increaseFlagsLeft();
                    }
                }
            }

            if (infoModel.getBlankFieldsLeft() == 0) {
                infoModel.showMines();
            }
        } // The game IS over
        else {
            // React to nothing anymore, obviously
        }
    }

    public void showSurrounding(int x, int y) {
        if (infoModel.getVelden(x, y) == 0 && infoModel.getBuren(x, y) == 0) {
            // Show the surrounding BLANK fields
            if ((x > 0 && x < 9) && (y > 0 && y < 9)) {
                clickField(x, y);
                showSurrounding(x - 1, y - 1);
                showSurrounding(x - 1, y);
                showSurrounding(x - 1, y + 1);
                showSurrounding(x, y - 1);
                showSurrounding(x, y + 1);
                showSurrounding(x + 1, y - 1);
                showSurrounding(x + 1, y);
                showSurrounding(x + 1, y + 1);
            }
            if (x == 0) {
                if (y > 0 && y < 9) {
                    clickField(x, y);
                    showSurrounding(x, y - 1);
                    showSurrounding(x, y + 1);
                    showSurrounding(x + 1, y - 1);
                    showSurrounding(x + 1, y);
                    showSurrounding(x + 1, y + 1);
                }
                if (y == 0) {
                    clickField(x, y);
                    showSurrounding(x, y + 1);
                    showSurrounding(x + 1, y);
                    showSurrounding(x + 1, y + 1);
                }
                if (y == 9) {
                    clickField(x, y);
                    showSurrounding(x, y - 1);
                    showSurrounding(x + 1, y);
                    showSurrounding(x + 1, y - 1);
                }
            }
            if (x > 0 && x < 9) {
                if (y == 0) {
                    clickField(x, y);
                    showSurrounding(x - 1, y);
                    showSurrounding(x + 1, y);
                    showSurrounding(x - 1, y + 1);
                    showSurrounding(x, y + 1);
                    showSurrounding(x + 1, y + 1);
                }
                if (y == 9) {
                    clickField(x, y);
                    showSurrounding(x - 1, y - 1);
                    showSurrounding(x, y - 1);
                    showSurrounding(x + 1, y - 1);
                    showSurrounding(x - 1, y);
                    showSurrounding(x + 1, y);
                }
            }
            if (x == 9) {
                if (y > 0 && y < 9) {
                    clickField(x, y);
                    showSurrounding(x - 1, y - 1);
                    showSurrounding(x - 1, y);
                    showSurrounding(x - 1, y + 1);
                    showSurrounding(x, y - 1);
                    showSurrounding(x, y + 1);
                }
                if (y == 0) {
                    clickField(x, y);
                    showSurrounding(x - 1, y);
                    showSurrounding(x - 1, y + 1);
                    showSurrounding(x, y + 1);
                }
                if (y == 9) {
                    clickField(x, y);
                    showSurrounding(x, y - 1);
                    showSurrounding(x - 1, y - 1);
                    showSurrounding(x - 1, y);
                }
            }

            // Show the surrounding NON-BLANK fields
            if ((x > 0 && x < 9) && (y > 0 && y < 9)) {
//                clickField(x,y);
                clickField(x - 1, y - 1);
                clickField(x - 1, y);
                clickField(x - 1, y + 1);
                clickField(x, y - 1);
                clickField(x, y + 1);
                clickField(x + 1, y - 1);
                clickField(x + 1, y);
                clickField(x + 1, y + 1);
            }
            if (x == 0) {
                if (y > 0 && y < 9) {
//                    clickField(x,y);
                    clickField(x, y - 1);
                    clickField(x, y + 1);
                    clickField(x + 1, y - 1);
                    clickField(x + 1, y);
                    clickField(x + 1, y + 1);
                }
                if (y == 0) {
//                    clickField(x,y);
                    clickField(x, y + 1);
                    clickField(x + 1, y);
                    clickField(x + 1, y + 1);
                }
                if (y == 9) {
//                    clickField(x,y);
                    clickField(x, y - 1);
                    clickField(x + 1, y);
                    clickField(x + 1, y - 1);
                }
            }
            if (x > 0 && x < 9) {
                if (y == 0) {
//                    clickField(x,y);
                    clickField(x - 1, y);
                    clickField(x + 1, y);
                    clickField(x - 1, y + 1);
                    clickField(x, y + 1);
                    clickField(x + 1, y + 1);
                }
                if (y == 9) {
//                    clickField(x,y);
                    clickField(x - 1, y - 1);
                    clickField(x, y - 1);
                    clickField(x + 1, y - 1);
                    clickField(x - 1, y);
                    clickField(x + 1, y);
                }
            }
            if (x == 9) {
                if (y > 0 && y < 9) {
//                    clickField(x,y);
                    clickField(x - 1, y - 1);
                    clickField(x - 1, y);
                    clickField(x - 1, y + 1);
                    clickField(x, y - 1);
                    clickField(x, y + 1);
                }
                if (y == 0) {
//                    clickField(x,y);
                    clickField(x - 1, y);
                    clickField(x - 1, y + 1);
                    clickField(x, y + 1);
                }
                if (y == 9) {
//                    clickField(x,y);
                    clickField(x, y - 1);
                    clickField(x - 1, y - 1);
                    clickField(x - 1, y);
                }
            }
        }
    }

    public void clickField(int x, int y) {
        if (infoModel.getVelden(x, y) == 0) {
            changeImage(x, y, infoModel.getBuren(x, y));
            infoModel.setVelden(x, y, -1);
        }
    }

    public void changeImage(int x, int y, int value) {
        infoModel.getButtonTable()[x][y].setIcon(infoModel.getImage(value));
    }
}
