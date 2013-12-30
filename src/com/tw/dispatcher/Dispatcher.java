package com.tw.dispatcher;

import com.tw.model.GalacticNumerals;
import com.tw.model.Metals;
import com.tw.service.ConversorService;
import com.tw.util.InputType;
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

    public Dispatcher() {
        inputScanner = new Scanner(System.in);
        galacticNumerals = new GalacticNumerals();
        metals = new Metals(galacticNumerals);
        questionHandler = new QuestionHandler(galacticNumerals, metals);
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
                saveAssignment(line);
                break;

            case CREDITSINFO:
                metals.saveInfo(line);
                break;

            case QUESTION_HOW_MANY:
                answerQuestion(line, inputType);
                break;

            case QUESTION_HOW_MUCH:
                answerQuestion(line, inputType);
                break;

            case NONE:
                Util.setMessage(Messages.NO_IDEA.getMessage());
                break;
        }
    }

    private void answerQuestion(String userInput, InputType inputType) {
        String answer = questionHandler.getAnswer(userInput, inputType);
        if (answer != null) {
            Util.setMessage(answer);
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
