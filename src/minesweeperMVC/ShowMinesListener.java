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
public class ShowMinesListener implements ActionListener {
    private MineSweeperInfoModel infoModel;

    public ShowMinesListener(MineSweeperInfoModel model) {
        infoModel = model;
    }

    public void changeModel(MineSweeperInfoModel model) {
        infoModel = model;
    }

    public void actionPerformed(ActionEvent e) {
        infoModel.showMines();
        infoModel.setGameOver(true);
    }

}
