/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Matthias
 */
public class NewGameListener implements ActionListener {
    private MineSweeperController controller;

    public NewGameListener(MineSweeperController controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        controller.resetField();
    }

}
