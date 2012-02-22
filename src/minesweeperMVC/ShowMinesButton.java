package minesweeperMVC;

import javax.swing.JButton;

/**
 *
 * @author Matthias
 */
public class ShowMinesButton extends JButton {

    private ShowMinesListener listener;

    public ShowMinesButton(String text, ShowMinesListener listener) {
        super(text);
        addActionListener(listener);
    }

    public void changeModel(MineSweeperInfoModel model) {
        listener.changeModel(model);
    }
}
