package org.cyclopsgroup.waterview;

/**
 * Type of input parameter
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public enum ParameterType
{
    /**
     * Variable coming from http header
     */
    HEADER,
    /**
     * Matrix parameter
     */
    MATRIX,
    /**
     * Part of request path
     */
    PATH,
    /**
     * Input parameter as part of query string
     */
    QUERY,
    /**
     * Variable in context defined by other part of code
     */
    VARIABLE;
}
