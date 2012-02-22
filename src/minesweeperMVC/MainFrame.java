/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeperMVC;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Matthias
 */
public class MainFrame extends JFrame {
    public MainFrame(String text) {
        super(text);
    }

    public JMenuBar createMenuBar(MineSweeperController controller) {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Game...");
        menu.setMnemonic(KeyEvent.VK_G);
        menu.add(newGameItem(controller));
        menu.add(showMinesItem(controller));
        menubar.add(menu);
        return menubar;
    }

    public JMenuItem newGameItem(MineSweeperController controller) {
        JMenuItem item = new JMenuItem("New Game");
        item.addActionListener(controller.getNewGameListener());
        item.setMnemonic(KeyEvent.VK_N);
        return item;
    }

    public JMenuItem showMinesItem(MineSweeperController controller) {
        JMenuItem item = new JMenuItem("Show Mines");
        item.addActionListener(controller.getShowMinesListener());
        item.setMnemonic(KeyEvent.VK_S);
        return item;
    }
}
