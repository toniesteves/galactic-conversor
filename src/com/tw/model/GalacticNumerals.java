package com.tw.model;

import com.tw.service.ConversorService;
import com.tw.util.Messages;
import com.tw.util.Util;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Toni
 */
public class GalacticNumerals extends RomanNumerals {

    //private static GalacticNumerals alienNumeral;
    private ConversorService conversorService = ConversorService.getInstance();
    private Map<String, String> galacticNumeralsMap;

    public GalacticNumerals() {
        galacticNumeralsMap = new HashMap<String, String>();
    }

    public void addToMap(String galacticNumeral, String romanNumeral) {
        if (conversorService.isWord(galacticNumeral) && !isPresent(galacticNumeral)) {
            galacticNumeralsMap.put(galacticNumeral, romanNumeral);
            Util.setMessage(Messages.VALUE_ASSIGNED.getMessage());
        }
    }

    public String toRomanNumeral(String[] aNumeral) {
        StringBuilder romanNumeralBuilder = new StringBuilder(aNumeral.length);

        for (String current : aNumeral) {
            romanNumeralBuilder.append(galacticNumeralsMap.get(current));
        }

        return romanNumeralBuilder.toString();
    }

    private boolean isPresent(String aNumeral) {
        return galacticNumeralsMap.containsKey(aNumeral);
    }

    public boolean arePresent(String[] numeral) {
        for (String s : numeral) {
            if (!isPresent(s)) {
                Util.setMessage(s + Messages.NO_GALACT_NUMERAL.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public int toArabicNumber(String romanNumeral) {
        return super.toArabicNumber(romanNumeral);
    }
}
