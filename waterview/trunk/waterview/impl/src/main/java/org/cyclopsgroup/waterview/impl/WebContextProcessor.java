package org.cyclopsgroup.waterview.impl;

import java.io.IOException;

import org.cyclopsgroup.waterview.WebContext;

public interface WebContextProcessor
{
    void process( WebContext context )
        throws IOException;
}
