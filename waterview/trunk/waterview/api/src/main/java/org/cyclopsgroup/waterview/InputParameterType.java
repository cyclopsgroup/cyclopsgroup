package org.cyclopsgroup.waterview;

/**
 * Type of input parameter
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public enum InputParameterType
{
    /**
     * Input parameter as part of query string
     */
    PARAMETER, 
    /**
     * Variable in context defined by other part of code 
     */
    VARIABLE, 
    /**
     * Variable coming from http header 
     */
    HEADER;
}
