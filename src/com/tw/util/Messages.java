package com.tw.util;

/**
 *
 * @author Toni
 */
public enum Messages { NO_IDEA("I have no idea what you are talking about."),
                       NO_GALACT_NUMERAL("is not a Galactic Numeral or has not been added."),
                       INCORRECT_ROMAN_NUMERAL("Incorrect Roman Numeral."),
                       ROMAN_SUBTRACTION_NOT_MET("Roman Numeral Subtraction condition not met."),
                       METAL_NOT_PRESENT("metal is not present.");

    private final String message;
    
    private Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

   
}
