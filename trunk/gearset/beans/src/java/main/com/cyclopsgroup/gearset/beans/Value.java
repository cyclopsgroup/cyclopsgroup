/*
 * Open Software License v2.1
 */
package com.cyclopsgroup.gearset.beans;

import java.util.Date;

/**
 * Generic value interface
 *
 * @author <a href="mailto:g-cyclops@users.sourceforge.net">Jiaqi Guo</a>
 */
public interface Value
{
    /**
     * Get original value
     *
     * @return String value
     */
    String getValue();

    /**
     * Get value method which returns a Date
     * 
     * @return Date type of value
     */
    Date getValueAsDate();

    /** Get value method which returns a Date
     * 
     * @param Default value
     * @return Date type of value or the default value
     */
    Date getValueAsDate(Date defaultValue);

    /**
     * Get value method which returns a double
     * 
     * @return double type of value
     */
    double getValueAsDouble();

    /** Get value method which returns a double
     * 
     * @param Default value
     * @return double type of value or the default value
     */
    double getValueAsDouble(double defaultValue);

    /**
     * Get value method which returns a float
     * 
     * @return float type of value
     */
    float getValueAsFloat();

    /** Get value method which returns a float
     * 
     * @param Default value
     * @return float type of value or the default value
     */
    float getValueAsFloat(float defaultValue);

    /**
     * Get value method which returns a int
     * 
     * @return int type of value
     */
    int getValueAsInt();

    /** Get value method which returns a int
     * 
     * @param Default value
     * @return int type of value or the default value
     */
    int getValueAsInt(int defaultValue);

    /**
     * Get value method which returns a long
     * 
     * @return long type of value
     */
    long getValueAsLong();

    /** Get value method which returns a long
     * 
     * @param Default value
     * @return long type of value or the default value
     */
    long getValueAsLong(long defaultValue);

    /**
     * Get value method which returns a short
     * 
     * @return short type of value
     */
    short getValueAsShort();

    /** Get value method which returns a short
     * 
     * @param Default value
     * @return short type of value or the default value
     */
    short getValueAsShort(short defaultValue);

    /**
     * Get value method which returns a String
     * 
     * @return String type of value
     */
    String getValueAsString();

    /** Get value method which returns a String
     * 
     * @param Default value
     * @return String type of value or the default value
     */
    String getValueAsString(String defaultValue);
}