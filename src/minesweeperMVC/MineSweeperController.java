package minesweeperMVC;

import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matthias
 */
public class MineSweeperController {

    private JPanel totalPanel, minesPanel, infoPanel;
    private FlagsLeftLabel flagsLeftLabel;
    private JLabel blankLabel;
    private ShowMinesButton giveUp;
    private NewGameButton newGame;
    private NewGameListener newGameListener;
    private ShowMinesListener showMinesListener;
    private MineSweeperInfoModel infoModel;
    private MineSweeperController controller;
    private ProgressBar progressBar;
    private static final Insets inset = new Insets(-2,-2,-2,-2);

    public MineSweeperController(final JFrame window) {
        infoModel = new MineSweeperInfoModel(10, 10, 35);
        
        showMinesListener = new ShowMinesListener(infoModel);
        newGameListener = new NewGameListener(this);

        totalPanel = new JPanel();
        minesPanel = new JPanel();
        infoPanel = new JPanel();

        newGame = new NewGameButton("New Game", newGameListener);

        giveUp = new ShowMinesButton("Show Mines", showMinesListener);
        flagsLeftLabel = new FlagsLeftLabel("Flags Left: " + infoModel.getFlagsLeft(), infoModel);
        blankLabel = new JLabel("");
        progressBar = new ProgressBar(0, infoModel.getAMOUNT_OF_MINES(), infoModel);

        minesPanel.setLayout(new GridLayout(infoModel.getHEIGHT(), infoModel.getWIDTH()));
        infoPanel.setLayout(new GridLayout(6, 1));

        initButtons();

        infoPanel.add(newGame);
        infoPanel.add(blankLabel);
        infoPanel.add(giveUp);
        infoPanel.add(blankLabel);
        infoPanel.add(progressBar);
        infoPanel.add(flagsLeftLabel);

        totalPanel.add(infoPanel);
        totalPanel.add(minesPanel);
        controller = this;
    }

    public void resetField() {
        int height = infoModel.getHEIGHT();
        int width = infoModel.getWIDTH();
        int amountOfMines = infoModel.getAMOUNT_OF_MINES();

        totalPanel.remove(minesPanel);
        minesPanel.removeAll();

        infoModel = new MineSweeperInfoModel(height, width, amountOfMines);
        initButtons();
        showMinesListener.changeModel(infoModel);
        progressBar.changeModel(infoModel);

        flagsLeftLabel.changeModel(infoModel);

        totalPanel.add(minesPanel);
        totalPanel.updateUI();
    }

    public ShowMinesListener getShowMinesListener() {
        return showMinesListener;
    }

    public NewGameListener getNewGameListener() {
        return newGameListener;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JPanel getMinesPanel() {
        return minesPanel;
    }

    public MineSweeperController getController() {
        return controller;
    }

    public void setController(MineSweeperController c) {
        controller = c;
    }

    public void initButtons() {
        for (int i = 0; i < infoModel.getHEIGHT(); i++) {
            for (int j = 0; j < infoModel.getWIDTH(); j++) {
                CoordJButton button = new CoordJButton(i, j, infoModel.getImage(9), infoModel);
                button.setMargin(inset);
                minesPanel.add(button);
                infoModel.setButtonTable(i, j, button);
            }
        }
    }

    public static void main(String[] args) {
        MainFrame window = new MainFrame("MineSweeper (c) 2009 MD");
        MineSweeperController controller = new MineSweeperController(window).getController();
        window.setJMenuBar(window.createMenuBar(controller));
        window.setContentPane(controller.totalPanel);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
