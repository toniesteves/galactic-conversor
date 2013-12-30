package com.tw.model;

import com.tw.service.ConversorService;
import com.tw.util.Messages;
import com.tw.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Toni
 */
public class RomanNumerals{

    private ConversorService conversorService;
    private static Map<Character, Integer> table = new HashMap<Character, Integer>() {
        {

            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public RomanNumerals() {

        this.conversorService = ConversorService.getInstance();
    }

    private int getNumeralFromChar(char numeral) {
        int number = -1;

        for (Entry current : table.entrySet()) {

            if (current.getKey().equals(numeral)) {

                number = Integer.parseInt(current.getValue().toString());
            }
        }

        return number;
    }

    public int getArabicNumeral(String romanNumeral) {
        List<Integer> numbers = getNumerals(romanNumeral.toCharArray());

        if (numbers != null) {
            numbers = conversorService.setElementPositionsToSubstract(numbers);

            int finalNumber = 0;

            if (!conversorService.checkRomanFormat(romanNumeral)) {
                if (!conversorService.violatesSubtraction(numbers)) {
                    for (int i : numbers) {
                        finalNumber += i;
                    }
                    return finalNumber;
                } else {
                    Util.setMessage(Messages.ROMAN_SUBTRACTION_NOT_MET.getMessage());
                }
            }

        }
        return -1;
    }

    private List<Integer> getNumerals(char[] parts) {

        int result;
        List<Integer> numbers = new ArrayList<Integer>();

        for (char current : parts) {
            result = getNumeralFromChar(current);
            if (result != -1) {
                numbers.add(result);
            } else {
                Util.setMessage(Messages.INCORRECT_ROMAN_NUMERAL.getMessage());
                numbers = null;
                break;
            }
        }
        return numbers;
    }

    

    
}
