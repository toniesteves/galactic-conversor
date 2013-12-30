package com.tw.service;

/**
 *
 * @author Toni
 */
public enum Input {

    ASSIGNMENT("^([a-z]+) is ([I|V|X|L|C|D|M])$", InputType.ASSIGNMENT),
    CREDITSINFO("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$", InputType.CREDITSINFO),
    QUESTION_HOW_MANY("^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$", InputType.QUESTION_HOW_MANY),
    QUESTION_HOW_MUCH("^how much is ((?:\\w+[^0-9] )+)\\?$", InputType.QUESTION_HOW_MUCH);
   
    private String regex;
    private InputType inputType;

    Input(String regex, InputType lineType) {
        this.regex = regex;
        this.inputType = lineType;
    }

    public InputType getType() {
        return this.inputType;
    }

    public String getRegex() {
        return this.regex;
    }
}