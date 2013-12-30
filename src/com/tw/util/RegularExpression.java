/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tw.util;

/**
 *
 * @author Toni
 */
public enum RegularExpression {IS_WORD_REG_EXP("^[a-z]+"),
                               CREDITS_INFO_REG_EXP("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$"),
                               HOW_MUCH_REG_EXP("^how much is ((?:\\w+[^0-9] )+)\\?$"),
                               HOW_MANY_REG_EXP("^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$");

    private final String regularExpression;
    
    private RegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public String getRegularExpression() {
        return regularExpression;
    }
    
}
