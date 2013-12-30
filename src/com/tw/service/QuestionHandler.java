package com.tw.service;

import com.tw.model.GalacticNumerals;
import com.tw.model.Metals;
import java.util.regex.Matcher;

/**
 *
 * @author Toni
 */
public class QuestionHandler {
    
    private ConversorService conversorService = ConversorService.getInstance();
    private GalacticNumerals galacticNumeral;
    private Metals metals;

    public QuestionHandler(GalacticNumerals galacticNumeral, Metals metals) {
        this.galacticNumeral = galacticNumeral;
        this.metals = metals;
    }

    public String handleQuestion(String userInput, InputType inputType) {
        
        String answer = "";
        
        if (inputType == InputType.QUESTION_HOW_MANY) {
            answer = handleHowManyQuestion(userInput);
        } else if (inputType == InputType.QUESTION_HOW_MUCH) {
            answer = handleHowMuchQuestion(userInput);
        }
        return answer;
    }

    private String handleHowMuchQuestion(String userInput) {
       
        String answer = null;
        Matcher matcher = conversorService.getHowMuchMatcher(userInput);

        if (matcher.matches()) {
            String inputNumerals = matcher.group(1);
            String[] galacticNumerals = matcher.group(1).split(" ");
            if (galacticNumeral.arePresent(galacticNumerals)) {
                int number = galacticNumeral.toArabicNumber(galacticNumeral.toRomanNumeral(galacticNumerals));
                if (number != -1) {
                    answer = inputNumerals + " is " + number + ".";
                }
            }
        }
        return answer;
    }

    private String handleHowManyQuestion(String userInput) {
        
        String answer = null;
        Matcher matcher = conversorService.getHowManyMatcher(userInput);

        if (matcher.matches()) {
            String creditName = matcher.group(1);
            String[] alienNumerals = matcher.group(2).split("\\s");
            String barName = matcher.group(3);

            if (galacticNumeral.arePresent(alienNumerals) && metals.isPresent(barName)) {
                int barQuantity = galacticNumeral.toArabicNumber(galacticNumeral.toRomanNumeral(alienNumerals));

                if (barQuantity != -1) {
                    double totalCredits = barQuantity * metals.getMetalsUnitValue(barName);
                    StringBuilder result = new StringBuilder();
                    result.append(matcher.group(2)).append(barName).append(" is ").append(totalCredits).append(" ").append(creditName);
                    answer = result.toString();
                }
            }
        }

        return answer;
    }
}
