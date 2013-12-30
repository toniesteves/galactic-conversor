package com.tw.util;

import com.tw.view.MainFrame;
/**
 *
 * @author Toni
 */
public class Util {

    private MainFrame mainFrame;
    private String msg;

    public Util() {

        this.mainFrame = new MainFrame();
    }

    public void setMessage(String msg) {

        mainFrame.getLabelErro().setText(msg);

    }

    public static void promptUser(String text) {
    }

    
}
