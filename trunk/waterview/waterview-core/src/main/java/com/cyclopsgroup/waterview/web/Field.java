/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.web;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Form field object
 */
public class Field
{
    /** Empty field array */
    public static final Field[] EMPTY_ARRAY = new Field[0];

    private String errorMessage;

    private boolean invalid = false;

    private String name;

    private boolean password;

    private boolean required;

    private String title;

    private Class type;

    private FieldValidator validator;

    private String value = StringUtils.EMPTY;

    /**
     * Constructor for class Field
     *
     * @param name Field name
     * @param type Field type
     */
    public Field( String name, Class type )
    {
        this.name = name;
        this.type = type;
    }

    /**
     * @return Returns the errorMessage.
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Get field name
     *
     * @return Field name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for property title
     *
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @return Returns the type.
     */
    public Class getType()
    {
        return type;
    }

    /**
     * @return Returns the validator.
     */
    public FieldValidator getValidator()
    {
        return validator;
    }

    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @return Returns the invalid.
     */
    public boolean isInvalid()
    {
        return invalid;
    }

    /**
     * Getter method for property password
     *
     * @return Returns the password.
     */
    public boolean isPassword()
    {
        return password;
    }

    /**
     * @return Returns the required.
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * @param errorMessage The errorMessage to set.
     */
    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

    /**
     * @param invalid The invalid to set.
     */
    public void setInvalid( boolean invalid )
    {
        this.invalid = invalid;
    }

    /**
     * Setter method for property password
     *
     * @param password The password to set.
     */
    public void setPassword( boolean password )
    {
        this.password = password;
    }

    /**
     * @param required The required to set.
     */
    public void setRequired( boolean required )
    {
        this.required = required;
    }

    /**
     * Setter method for property title
     *
     * @param title The title to set.
     */
    public void setTitle( String title )
    {
        this.title = title;
    }

    /**
     * @param validator The validator to set.
     */
    public void setValidator( FieldValidator validator )
    {
        this.validator = validator;
    }

    /**
     * @param value The value to set.
     */
    public void setValue( String value )
    {
        this.value = value;
    }

    /**
     * validate this field
     */
    public void validate()
    {
        if ( StringUtils.isEmpty( getValue() ) )
        {
            if ( isRequired() )
            {
                setInvalid( true );
                setErrorMessage( "Field value is equired !" );
            }
            else
            {
                setInvalid( false );
                setErrorMessage( StringUtils.EMPTY );
            }
            return;
        }
        ValidationResult result = TypeFieldValidator.INSTANCE.validate( this, getValue() );
        if ( result.isSuccess() && getValidator() != null )
        {
            result = getValidator().validate( this, getValue() );
        }
        if ( result.isSuccess() )
        {
            setInvalid( false );
            setErrorMessage( StringUtils.EMPTY );
        }
        else
        {
            setInvalid( true );
            setErrorMessage( result.getErrorMessage() );
        }
    }
}