/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 *
 * Licensed under the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE
 * (CDDL) Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/cddl1.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.waterview.web.taglib;

import org.apache.commons.jelly.XMLOutput;

import com.cyclopsgroup.waterview.RunData;
import com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag;
import com.cyclopsgroup.waterview.spi.LookAndFeelService;
import com.cyclopsgroup.waterview.spi.Theme;

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 *
 * Icon tag
 */
public class IconTag
    extends BaseJellyControlTag
{
    private static final String CONTROL_PATH = "/waterview/control/IconControl.jelly";

    private String alt;

    private String file;

    private int size = 16;

    private String url;

    /**
     * Constructor for class IconTag
     */
    public IconTag()
    {
        setScript( CONTROL_PATH );
    }

    /**
     * Overwrite or implement method processTag()
     *
     * @see com.cyclopsgroup.waterview.jelly.taglib.BaseJellyControlTag#processTag(org.apache.commons.jelly.XMLOutput)
     */
    protected void processTag( XMLOutput output )
        throws Exception
    {
        requireAttribute( "file" );
        LookAndFeelService laf = (LookAndFeelService) getServiceManager().lookup( LookAndFeelService.ROLE );
        RunData data = getRunData();
        Theme theme = laf.getRuntimeTheme( data );
        setUrl( theme.getIconSet().getUrl( data, getFile(), getSize() ) );
        super.processTag( output );
    }

    /**
     * Getter method for property alt
     *
     * @return Returns the alt.
     */
    public String getAlt()
    {
        return alt;
    }

    /**
     * Getter method for property file
     *
     * @return Returns the file.
     */
    public String getFile()
    {
        return file;
    }

    /**
     * Getter method for property size
     *
     * @return Returns the size.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Setter method for property alt
     *
     * @param alt The alt to set.
     */
    public void setAlt( String alt )
    {
        this.alt = alt;
    }

    /**
     * Setter method for property file
     *
     * @param file The file to set.
     */
    public void setFile( String file )
    {
        this.file = file;
    }

    /**
     * Setter method for property size
     *
     * @param size The size to set.
     */
    public void setSize( int size )
    {
        this.size = size;
    }

    /**
     * Getter method for property url
     *
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * Setter method for property url
     *
     * @param url The url to set.
     */
    public void setUrl( String url )
    {
        this.url = url;
    }
}
