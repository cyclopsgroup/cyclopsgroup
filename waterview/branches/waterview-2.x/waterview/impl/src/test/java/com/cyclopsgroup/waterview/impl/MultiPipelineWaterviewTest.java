package com.cyclopsgroup.waterview.impl;

import java.util.regex.Pattern;

import junit.framework.TestCase;

public class MultiPipelineWaterviewTest
    extends TestCase
{
    public void testRegExp()
    {
        Pattern p = Pattern.compile( ".*\\.vm$" );
        System.out.println( p.matcher( "abc.xyz" ).matches() );
        System.out.println( p.matcher( "abc.vm" ).matches() );
    }
}
