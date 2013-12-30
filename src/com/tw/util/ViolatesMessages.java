/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tw.util;

/**
 *
 * @author Toni
 */
public enum ViolatesMessages {VLD_NEVER_SUBTRACTED("V, L, and D can never be subtracted."),
                              SUBTRACTED_FROM_VX("I can be subtracted from V and X only."),
                              X_FROM_LC("X can be subtracted from L and C only."),
                              C_FROM_DM("C can be subtracted from D and M only.");

    private final String message;
    
    private ViolatesMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
