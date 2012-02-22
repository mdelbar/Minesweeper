/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Matthias
 */
public class TimePlayedLabel extends JLabel {
    private static Timer timer;

    public TimePlayedLabel(String label, MineSweeperInfoModel model) {
        super(label);
        timer = new Timer(1000, new TimePlayedTimer(this, model));
        timer.start();
    }
}
