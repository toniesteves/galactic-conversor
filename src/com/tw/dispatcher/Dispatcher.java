package com.tw.dispatcher;

import com.tw.model.GalacticNumerals;
import com.tw.model.Metals;
import com.tw.service.ConversorService;
import com.tw.service.InputType;
import com.tw.service.QuestionHandler;
import com.tw.util.Util;
import com.tw.util.Messages;
import java.util.Scanner;

/**
 *
 * @author Toni
 */
public class Dispatcher {
    
    private Scanner inputScanner;
    private GalacticNumerals galacticNumerals;
    private Metals metals;
    private QuestionHandler questionHandler;
    private ConversorService conversorService = ConversorService.getInstance();
    private Util util;

    public Dispatcher() {
        inputScanner = new Scanner(System.in);
        galacticNumerals = new GalacticNumerals();
        metals = new Metals(galacticNumerals);
        questionHandler = new QuestionHandler(galacticNumerals, metals);
        util = new Util();
    }

    public void Dispatcher() {
        while (inputScanner.hasNext()) {
            processInput(inputScanner.nextLine());
        }

        inputScanner.close();
    }

    public void processInput(String line) {
        InputType inputType = conversorService.getInputType(line);

        switch (inputType) {
            case ASSIGNMENT:
                // assignment processing.
                saveAssignment(line);
                break;

            case CREDITSINFO:
                // CreditsInfo processing.
                metals.saveInfo(line);
                break;

            case QUESTION_HOW_MANY:
                // How many types processing.
                answerQuestion(line, inputType);
                break;

            case QUESTION_HOW_MUCH:
                // How much types processing.
                answerQuestion(line, inputType);
                break;

            case NONE:
                util.setMessage(Messages.NO_IDEA.getMessage());
                break;
            // I have no idea what you are talking about.
        }
    }

    private void answerQuestion(String userInput, InputType inputType) {
        String answer = questionHandler.handleQuestion(userInput, inputType);
        if (answer != null) {
            Util.promptUser(answer);
        }
    }

    private void saveAssignment(String line) {
        String[] operands = getAssignmentOperands(line);
        galacticNumerals.addToMap(operands[0], operands[1]);
    }

    private String[] getAssignmentOperands(String line) {
        String[] parts = line.split(" ");
        return new String[]{parts[0], parts[2]};
    }
}
