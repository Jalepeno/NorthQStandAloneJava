package com.northqstandalone.maven.services;

import com.northqstandalone.maven.NorthQ.AppTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TokenServiceTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TokenServiceTest( String TokenServiceTest )
    {
        super( TokenServiceTest );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TokenServiceTest.class );
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testTokenService()
    {
        assertTrue( true );
    }
}
