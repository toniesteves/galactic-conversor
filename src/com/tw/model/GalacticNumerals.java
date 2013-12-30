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

    private ConversorService conversorService;
    private Map<String, String> galacticNumeralsMap;

    public GalacticNumerals() {
        galacticNumeralsMap = new HashMap<String, String>();
        conversorService = ConversorService.getInstance();
    }

    public void addToMap(String galacticNumeral, String romanNumeral) {
        if (conversorService.isWord(galacticNumeral) && !isGalacticNumeralPresent(galacticNumeral)) {
            galacticNumeralsMap.put(galacticNumeral, romanNumeral);
            Util.setMessage(Messages.VALUE_ASSIGNED.getMessage());
        }
    }

    public String createRomanNumeral(String[] galacticNumeral) {
        StringBuilder romanNumeralBuilder = new StringBuilder(galacticNumeral.length);

        for (String current : galacticNumeral) {
            romanNumeralBuilder.append(galacticNumeralsMap.get(current));
        }

        return romanNumeralBuilder.toString();
    }

    protected boolean isGalacticNumeralPresent(String galacticNumeral) {
        return galacticNumeralsMap.containsKey(galacticNumeral);
    }

    public boolean areGalacticalNumeralsPresent(String[] numeral) {
        for (String s : numeral) {
            if (!isGalacticNumeralPresent(s)) {
                Util.setMessage(s + Messages.NO_GALACT_NUMERAL.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public int getArabicNumeral(String romanNumeral) {
        return super.getArabicNumeral(romanNumeral);
    }
}
