package com.tw.model;

import junit.framework.TestCase;

/**
 *
 * @author Toni
 */
public class RomanNumeralsTest extends TestCase {

    private RomanNumerals romanNumerals;

    public RomanNumeralsTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        romanNumerals = new RomanNumerals();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConverRomanNumberWithOneNumberIntoArabicNumber() {

        assertEquals("Is expected a arabic number with 1 value", 1, romanNumerals.getArabicNumeral("I"));

    }

    public void testConvertRomanNumberWithTwoEqualNumbersIntoArabicNumber() {

        assertEquals("Is expected a arabic number with 2 value", 2, romanNumerals.getArabicNumeral("II"));

    }

    public void testConvertRomanNumberWithThreeEqualNumbersIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 2 value", 3, romanNumerals.getArabicNumeral("III"));
    }

    public void testConvertRomanNumberWithTwoDifferentNumbersHighestToLowestIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 11 value", 11, romanNumerals.getArabicNumeral("XI"));

    }

    public void testConvertRomanNumberWithTwoDifferentNumbersLowestToHighestIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 4 value", 4, romanNumerals.getArabicNumeral("IV"));

    }

    public void testConvertRomanNumberWithFourNumbersTwoByTwoIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 22 value", 22, romanNumerals.getArabicNumeral("XXII"));

    }

    public void testConvertRomanNumberWithThreeDifferentNumbersIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 16 value", 16, romanNumerals.getArabicNumeral("XVI"));
    }

    public void testConvertRomanNumberWithFourDifferentNumbersIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 1515 value", 1515, romanNumerals.getArabicNumeral("MDXV"));
    }

    public void testConvertRomanNumberWithThreeDifferentNumbersLowestToHighestIntoArabicNumber() {

        assertEquals("Is expected a arabic number with 14 value", 14, romanNumerals.getArabicNumeral("XIV"));
    }

    public void testConvertRomanNumberWithFourDifferentNumbersLowestToHighestIntoArabicNumber() {
        assertEquals("Is expected a arabic number with 24 value", 24, romanNumerals.getArabicNumeral("XXIV"));
    }
}

