package com.tw.model;

import com.tw.service.ConversorService;
import com.tw.util.Messages;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 *
 * @author Toni
 */
public class Metals {

    private Map<String, Double> metals;
    private GalacticNumerals galacticNumerals;
    private ConversorService conversorService = ConversorService.getInstance();

    public Metals(GalacticNumerals galacticNumerals) {
        this.galacticNumerals = galacticNumerals;
        metals = new HashMap<String, Double>();
    }

    public void saveInfo(String userInput) {
        Matcher matcher = conversorService.getCreditsMatcher(userInput);

        if (matcher.matches()) {
            String[] galacticNumeralsToCheck = matcher.group(1).split("\\s");
            String name = matcher.group(2);
            int bulkValue = Integer.parseInt(matcher.group(3));

            if (!galacticNumerals.arePresent(galacticNumeralsToCheck)) {
                conversorService.setMessage("Undeclared Galactic Numeral was used, input ignored.");
                return;
            }

            //units is float to get metalUnitValue in double. 
            float units = galacticNumerals.toArabicNumber(galacticNumerals.toRomanNumeral(galacticNumeralsToCheck));

            float metalUnitValue = bulkValue / units;
            addMetal(name, metalUnitValue);
        }
    }

    public double getMetalsUnitValue(String metalName) {
        double value = metals.get(metalName);
        return metals.get(metalName);
    }

    private void addMetal(String metalName, double metalUnitValue) {
        metals.put(metalName, metalUnitValue);
    }

    public boolean isPresent(String name) {
        boolean result = metals.containsKey(name);

        if (!result) {
            conversorService.setMessage(name + Messages.METAL_NOT_PRESENT.getMessage());
        }

        return result;
    }
}
