package com.tw.util;

/**
 *
 * @author Toni
 */
public enum Messages { NO_IDEA("I have no idea what you are talking about."),
                       NO_GALACT_NUMERAL(" is not a Galactic Numeral or has not been added."),
                       INCORRECT_ROMAN_NUMERAL("Incorrect Roman Numeral."),
                       ROMAN_SUBTRACTION_NOT_MET("Roman Numeral Subtraction condition not met."),
                       ROMAN_NUMBER_FORMATION("The number entered violates Roman Number formation."),
                       METAL_NOT_PRESENT("Metal is not present."),
                       MUST_A_WORD("Alien numeral must be a word."),
                       UNDECLARED_GALACTIC_NUMBER("Undeclared Galactic Numeral was used, input ignored."),
                       VALUE_ASSIGNED("Value has assigned.");

    private final String message;
    
    private Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

   
}
