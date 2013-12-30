package com.tw.service;

import com.tw.util.InputType;
import com.tw.util.Input;
import com.tw.util.Messages;
import com.tw.util.Roman;
import com.tw.util.RegularExpression;
import com.tw.util.Util;
import com.tw.util.ViolatesMessages;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Toni
 */
public class ConversorService {

    private Matcher matcher;
    private Pattern pattern;
    private Util util;
    private static ConversorService conversorService;

    public ConversorService() {

        util = new Util();
    }

    public static ConversorService getInstance() {
        if (conversorService == null) {
            conversorService = new ConversorService();
        }

        return conversorService;
    }

    public InputType getInputType(String input) {

        for (Input current : Input.values()) {

            pattern = getPattern(current.getRegex());
            matcher = getMatcher(pattern, input);

            if (matcher.matches()) {
                switch (current.getType()) {
                    case ASSIGNMENT:
                        return InputType.ASSIGNMENT;

                    case CREDITSINFO:
                        return InputType.CREDITSINFO;

                    case QUESTION_HOW_MANY:
                        return InputType.QUESTION_HOW_MANY;

                    case QUESTION_HOW_MUCH:
                        return InputType.QUESTION_HOW_MUCH;
                }
            }
        }

        return InputType.NONE;
    }

    public Pattern getPattern(String line) {
        return Pattern.compile(line);
    }

    public Matcher getMatcher(Pattern pattern, String line) {
        return pattern.matcher(line);
    }

    public boolean checkRomanFormat(String romanNumeral) {
        String regex = "";
        String[] repetitionTest = {"(IIII+)", "(XXXX+)", "(CCCC+)", "(MMMM+)", "(DD+)", "(LL+)", "(VV+)"};

        for (int i = 0; i < repetitionTest.length; i++) {
            regex = repetitionTest[i];
            Matcher matcher = getMatcher(getPattern(regex), romanNumeral);

            if (matcher.matches()) {
                Util.setMessage(Messages.ROMAN_NUMBER_FORMATION.getMessage());
                return true;
            }
        }
        return false;
    }

    public boolean isWord(String numeral) {
        String wordRegex = RegularExpression.IS_WORD_REG_EXP.getRegularExpression();
        matcher = getMatcher(getPattern(wordRegex), (numeral.toLowerCase()));
        if (!matcher.matches()) {
            Util.setMessage(Messages.MUST_A_WORD.getMessage());
        }
        return (matcher.matches() ? true : false);
    }

    public Matcher getHowMuchMatcher(String line) {
        String howMuchRegEx = RegularExpression.HOW_MUCH_REG_EXP.getRegularExpression();
        return getMatcher(getPattern(howMuchRegEx), line);
    }

    public Matcher getHowManyMatcher(String line) {
        String howManyRegex = RegularExpression.HOW_MANY_REG_EXP.getRegularExpression();
        return getMatcher(getPattern(howManyRegex), line);
    }

    public Matcher getCreditsMatcher(String line) {
        String creditsInfoRegEx = RegularExpression.CREDITS_INFO_REG_EXP.getRegularExpression();
        return getMatcher(getPattern(creditsInfoRegEx), line);
    }

    public boolean violatesSubtraction(List<Integer> numbers) {
        int number, nextNumber;
        for (int i = 0; i < numbers.size() - 1; i++) {
            number = numbers.get(i);
            nextNumber = Math.abs(numbers.get(i + 1));

            if (number == -Roman.V.getNumber() || number == -Roman.L.getNumber()
                    || number == -Roman.D.getNumber()) {
                Util.setMessage(ViolatesMessages.VLD_NEVER_SUBTRACTED.getMessage());
                return true;
            }

            switch (Math.abs(number)) {
                case 1:

                    if (nextNumber > Roman.I.getNumber() && nextNumber != Roman.V.getNumber() && nextNumber != Roman.X.getNumber() && nextNumber != number) {
                        Util.setMessage(ViolatesMessages.SUBTRACTED_FROM_VX.getMessage());
                        return true;
                    }
                    break;

                case 10:
                    if (nextNumber > Roman.X.getNumber() && nextNumber != Roman.L.getNumber() && nextNumber != Roman.C.getNumber() && nextNumber != number) {
                        Util.setMessage(ViolatesMessages.X_FROM_LC.getMessage());
                        return true;
                    }
                    break;

                case 100:
                    if (nextNumber > Roman.C.getNumber() && nextNumber != Roman.D.getNumber() && nextNumber != Roman.M.getNumber() && nextNumber != number) {
                        Util.setMessage(ViolatesMessages.C_FROM_DM.getMessage());
                        return true;
                    }
                    break;
            }
        }

        return false;
    }

    public List<Integer> setElementPositionsToSubstract(List<Integer> numbers) {
        int currentElement;

        for (int i = 0; i < numbers.size() - 1; i++) {
            currentElement = numbers.get(i);
            if (currentElement < numbers.get(i + 1)) {
                numbers.set(i, -currentElement);
            }
        }
        return numbers;
    }
}
