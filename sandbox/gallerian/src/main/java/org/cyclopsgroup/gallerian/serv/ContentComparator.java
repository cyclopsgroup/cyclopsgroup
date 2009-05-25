package org.cyclopsgroup.gallerian.serv;

import java.util.Comparator;

import org.cyclopsgroup.gallerian.Content;

/**
 * Content comparator for sorting
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
class ContentComparator
    implements Comparator<Content>
{
    /**
     * @inheritDoc
     */
    @Override
    public int compare( Content c1, Content c2 )
    {
        return c1.getName().compareTo( c2.getName() );
    }
}
