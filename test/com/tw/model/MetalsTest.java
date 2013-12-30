package com.tw.model;

import junit.framework.TestCase;

/**
 *
 * @author Toni
 */
public class MetalsTest extends TestCase {

    Metals metals;

    public MetalsTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        metals = new Metals();

        metals.addMetal("glob", 1.0);
        metals.addMetal("prok", 1.1);
        metals.addMetal("pish", 1.2);
        metals.addMetal("tegj", 1.3);

        metals.saveInfo("glob glob Silver is 34 Credits");
        metals.saveInfo("pish pish Iron is 3910 Credits");
        metals.saveInfo("glob prok Gold is 57800 Credits");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCheckMetalPresent() {
        assertEquals("Is expected that exists a metal called Silver", true, metals.isPresent("Silver"));
        assertEquals("Is expected that exists a metals called Gold", true, metals.isPresent("Gold"));
        assertEquals("Is expected that exists a metals called Iron", true, metals.isPresent("Iron"));

    }
}
