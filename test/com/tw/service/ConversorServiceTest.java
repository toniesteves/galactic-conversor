package com.tw.service;

import com.tw.util.InputType;
import junit.framework.TestCase;

/**
 *
 * @author Toni
 */
public class ConversorServiceTest extends TestCase {

    ConversorService conversorService;

    public ConversorServiceTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        conversorService = new ConversorService();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetInputFromUser() {

        assertEquals(InputType.ASSIGNMENT, conversorService.getInputType("glob is I"));
        assertEquals(InputType.CREDITSINFO, conversorService.getInputType("glob glob Silver is 34 Credits"));
        assertEquals(InputType.QUESTION_HOW_MANY, conversorService.getInputType("how many Credits is glob prok Gold ?"));
        assertEquals(InputType.QUESTION_HOW_MUCH, conversorService.getInputType("how much is glob glob glob glob ?"));
        assertEquals(InputType.NONE, conversorService.getInputType("how much wood could a woodchuck chuck if a woodchu"));
    }

    public void testValidateRomanRepitionTest() {
        assertEquals("The number entered violates Roman Number formation.", true, conversorService.checkRomanFormat("IIII"));
        assertEquals("The number entered violates Roman Number formation.", true, conversorService.checkRomanFormat("XXXX"));
        assertEquals("The number entered violates Roman Number formation.", true, conversorService.checkRomanFormat("CCCC"));
        assertEquals("The number entered violates Roman Number formation.", true, conversorService.checkRomanFormat("MMMM"));
        assertEquals("The number entered violates Roman Number formation.", true, conversorService.checkRomanFormat("DD"));
    }

    public void testCheckisWord() {
        assertEquals("Is expected a word", true, conversorService.isWord("word"));
        assertEquals("Isn't expected a word", false, conversorService.isWord("123"));
        assertEquals("Isn't expected a word", false, conversorService.isWord("word123"));
    }
}
