package com.tw.model;

import junit.framework.TestCase;

/**
 *
 * @author Toni
 */
public class GalaticNumeralsTest extends TestCase {

    GalacticNumerals galacticNumerals;

    public GalaticNumeralsTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        galacticNumerals = new GalacticNumerals();

        galacticNumerals.addToMap("glob", "I");
        galacticNumerals.addToMap("prok", "V");
        galacticNumerals.addToMap("pish", "X");
        galacticNumerals.addToMap("tegj", "L");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMethods() {
    }

    public void testExistsGalacticNumeralsPresent() {

        assertEquals("Is expected a positive value", true, galacticNumerals.areGalacticalNumeralsPresent(new String[]{"glob", "prok"}));
        assertEquals("Isn't expected a positive value", false, galacticNumerals.areGalacticalNumeralsPresent(new String[]{"glob", "prok", "another"}));
    }

    public void testCreateRomanNumeralFromGalacticNumeral() {

        assertEquals("Is expected a roman number with IV value", galacticNumerals.createRomanNumeral(new String[]{"glob","prok"}));
        assertEquals("Is expected a roman number with VI value", galacticNumerals.createRomanNumeral(new String[]{"prok","glob"}));
        assertEquals("Is expected a roman number with XLII value", galacticNumerals.createRomanNumeral(new String[]{"pish", "tegj", "glob", "glob"}));
    }
}
