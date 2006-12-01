package com.cyclopsgroup.laputa.identity;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * TODO: May not be a good idea
 *
 * @author <a href="mailto:jiaqi@amazon.com>jiaqi</a>
 *
 */
public interface FilterConfigurable
{
    void configure( FilterConfig config )
        throws ServletException;
}
