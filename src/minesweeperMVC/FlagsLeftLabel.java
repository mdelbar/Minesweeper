/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Matthias
 */
public class FlagsLeftLabel extends JLabel implements ChangeListener {
    private MineSweeperInfoModel infoModel;

    public FlagsLeftLabel(String label, MineSweeperInfoModel model)
    {
        super(label);
        infoModel = model;
        infoModel.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        setText("Flags Left: " + infoModel.getFlagsLeft());
    }

    public void changeModel(MineSweeperInfoModel model) {
        infoModel = model;
        infoModel.addChangeListener(this);
        setText("Flags Left: " + infoModel.getFlagsLeft());
    }
}
