/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author Matthias
 */
public class TimePlayedTimer implements ActionListener {
    private MineSweeperInfoModel infoModel;
    private JLabel label;

    public TimePlayedTimer(TimePlayedLabel label, MineSweeperInfoModel model) {
        infoModel = model;
        this.label = label;
    }

    public void actionPerformed(ActionEvent e) {
        if(!infoModel.getGameOver()) {
            infoModel.incTimePlayed();
            label.setText("Time Played: " + infoModel.getTimePlayed());
        }
    }

}
