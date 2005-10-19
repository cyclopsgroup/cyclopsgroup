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
     * @return Returns the to.
     */
    public String getAction()
    {
        return action;
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
            formTag.addButtonTag( this );
        }
        if ( !FormTag.isControlsHidden( getContext() ) )
        {
            JellyContext jc = new JellyContext( getContext() );
            jc.setVariable( "submit", this );
            getBody().run( jc, output );
        }
    }

    /**
     * @param to The to to set.
     */
    public void setAction( String to )
    {
        this.action = to;
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
