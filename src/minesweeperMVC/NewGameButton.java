/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import javax.swing.JButton;

/**
 *
 * @author Matthias
 */
public class NewGameButton extends JButton {
    

    public NewGameButton(String text, NewGameListener listener) {
        super(text);
        addActionListener(listener);
    }
}
