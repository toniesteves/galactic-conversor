package com.tw.model;

import com.tw.service.ConversorService;
import com.tw.util.Messages;
import com.tw.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 *
 * @author Toni
 */
public class Metals {

    private String SPACE = "\\s";
    private Map<String, Double> metals;
    private GalacticNumerals galacticNumerals;
    private ConversorService conversorService = ConversorService.getInstance();

    public Metals(GalacticNumerals galacticNumerals) {
        this.galacticNumerals = galacticNumerals;
        metals = new HashMap<String, Double>();
    }

    Metals() {
    }

    public void saveInfo(String userInput) {
        Matcher matcher = conversorService.getCreditsMatcher(userInput);

        if (matcher.matches()) {
            String[] galacticNumeralsToCheck = matcher.group(1).split(SPACE);
            String name = matcher.group(2);
            int bulkValue = Integer.parseInt(matcher.group(3));

            if (!galacticNumerals.areGalacticalNumeralsPresent(galacticNumeralsToCheck)) {
                Util.setMessage(Messages.UNDECLARED_GALACTIC_NUMBER.getMessage());
                return;
            }

            //units is float to get metalUnitValue in double. 
            float units = galacticNumerals.getArabicNumeral(galacticNumerals.createRomanNumeral(galacticNumeralsToCheck));

            float metalUnitValue = bulkValue / units;
            addMetal(name, metalUnitValue);
        }
    }

    public double getMetalsUnitValue(String metalName) {
        double value = metals.get(metalName);
        return metals.get(metalName);
    }

    protected void addMetal(String metalName, double metalUnitValue) {
        metals.put(metalName, metalUnitValue);
    }

    public boolean isPresent(String name) {
        boolean result = metals.containsKey(name);

        if (!result) {
            Util.setMessage(name + Messages.METAL_NOT_PRESENT.getMessage());
        }

        return result;
    }
}
