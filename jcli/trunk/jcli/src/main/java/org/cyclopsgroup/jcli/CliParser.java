package org.cyclopsgroup.jcli;

import java.beans.IntrospectionException;
import java.io.PrintWriter;

/**
 * Parser that parse string array argument and pass result to given Java bean
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@SuppressWarnings("deprecation")
public interface CliParser extends org.cyclopsgroup.jcli.annotation.CliParser
{
    /**
     * Parse arguments and pass result to given Java bean
     * 
     * @param args String array of arguments
     * @param bean Java bean where result is set
     * @return True if anything has been changed
     * @throws IntrospectionException Thrown when Java bean structure has problem
     */
    boolean parse( String[] args, Object bean )
        throws IntrospectionException;

    /**
     * Print out usage of given Java bean
     * 
     * @param beanType Type of Java bean
     * @param output Output for message
     * @throws IntrospectionException Thrown when Java bean structure has problem
     */
    void printUsage( Class<?> beanType, PrintWriter output )
        throws IntrospectionException;
}
