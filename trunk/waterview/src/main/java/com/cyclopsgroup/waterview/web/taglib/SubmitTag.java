/*
 * Copyright (c) 1999-2004 Evavi, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Evavi, Inc.
 * Use is subject to license terms. License Agreement available at
 * <a href="http://www.evavi.com" target="_blank">www.evavi.com</a>
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.JellyContext;
import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.spi.taglib.TagSupport;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Submit tag
 */
public class SubmitTag
    extends TagSupport
{
    private String action;

    private boolean forceValidation = false;

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.utils.TagSupportBase#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "action" );
        FormTag formTag = (FormTag) findAncestorWithClass( FormTag.class );
        if ( formTag != null )
        {
            formTag.addSubmitTag( this );
        }
        JellyContext jc = new JellyContext( getContext() );
        jc.setVariable( "submit", this );
        StringBuffer onclick = new StringBuffer( "this.form.action='" ).append( getAction() ).append( "'; " );
        if ( isForceValidation() )
        {
            onclick.append( "if(this.form.force_validation) " );
            onclick.append( " {this.form.force_validation.value='true'; }" );
        }
        onclick.append( "this.disabled=true; " );
        onclick.append( "this.form.submit(); return false; " );
        jc.setVariable( "onclick", onclick.toString() );
        getBody().run( jc, output );
    }

    /**
     * @return Returns the to.
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @param to The to to set.
     */
    public void setAction( String to )
    {
        this.action = to;
    }

    /**
     * Getter method for field forceValidation
     *
     * @return Returns the forceValidation.
     */
    public boolean isForceValidation()
    {
        return forceValidation;
    }

    /**
     * Setter method for field forceValidation
     *
     * @param forceValidation The forceValidation to set.
     */
    public void setForceValidation( boolean forceValidation )
    {
        this.forceValidation = forceValidation;
    }
}
