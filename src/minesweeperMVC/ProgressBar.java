/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Matthias
 */
public class ProgressBar extends JProgressBar implements ChangeListener {
    private MineSweeperInfoModel infoModel;

    public ProgressBar(int min, int max, MineSweeperInfoModel model) {
        super(min, max);
        infoModel = model;
        setValue(infoModel.getFlagsLeft());
        infoModel.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        setValue(infoModel.getFlagsLeft());
    }

    public void changeModel(MineSweeperInfoModel model) {
        infoModel = model;
        infoModel.addChangeListener(this);
        setValue(infoModel.getFlagsLeft());
    }
}
