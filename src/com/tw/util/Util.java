package com.tw.util;

import com.tw.view.MainFrame;

/**
 *
 * @author Toni
 */
public class Util {

    private MainFrame mainFrame;
    static String msg;

    public Util() {
    }

    public static void setMessage(String msg) {
        Util.msg = msg;
    }

    public static String getMessage() {

        return Util.msg;
    }

    public static void promptUser(String text) {
    }
}
