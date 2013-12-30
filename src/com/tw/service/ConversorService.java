package com.tw.service;

import com.tw.util.Numeral;
import com.tw.util.Util;
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
                Util.promptUser("The number entered violates Roman Number formation.");
                return true;
            }
        }
        return false;
    }

    public boolean isWord(String numeral) {
        String wordRegex = "^[a-z]+";
        matcher = getMatcher(getPattern(wordRegex), (numeral.toLowerCase()));
        if (!matcher.matches()) {
            Util.promptUser("Alien numeral must be a word.");
        }
        return (matcher.matches() ? true : false);
    }

    public Matcher getHowMuchMatcher(String line) {
        String howMuchRegEx = "^how much is ((?:\\w+[^0-9] )+)\\?$";
        return getMatcher(getPattern(howMuchRegEx), line);
    }

    public Matcher getHowManyMatcher(String line) {
        String howManyRegex = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
        return getMatcher(getPattern(howManyRegex), line);
    }

    public Matcher getCreditsMatcher(String line) {
        String creditsInfoRegEx = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
        return getMatcher(getPattern(creditsInfoRegEx), line);
    }

    public boolean violatesSubtraction(List<Integer> numbers) {
        int number, nextNumber;
        for (int i = 0; i < numbers.size() - 1; i++) {
            number = numbers.get(i);
            nextNumber = Math.abs(numbers.get(i + 1));

            if (number == -Numeral.V.getNumber() || number == -Numeral.L.getNumber()
                    || number == -Numeral.D.getNumber()) {
                setMessage("V, L, and D can never be subtracted.");
                return true;
            }

            switch (Math.abs(number)) {
                case 1:

                    if (nextNumber > Numeral.I.getNumber() && nextNumber != Numeral.V.getNumber() && nextNumber != Numeral.X.getNumber() && nextNumber != number) {
                        setMessage("I can be subtracted from V and X only.");
                        return true;
                    }
                    break;

                case 10:
                    if (nextNumber > Numeral.X.getNumber() && nextNumber != Numeral.L.getNumber() && nextNumber != Numeral.C.getNumber() && nextNumber != number) {
                        setMessage("X can be subtracted from L and C only.");
                        return true;
                    }
                    break;

                case 100:
                    if (nextNumber > Numeral.C.getNumber() && nextNumber != Numeral.D.getNumber() && nextNumber != Numeral.M.getNumber() && nextNumber != number) {
                        setMessage("C can be subtracted from D and M only.");
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

    public void setMessage(String msg) {

        this.util.setMessage(msg);
    }
}
