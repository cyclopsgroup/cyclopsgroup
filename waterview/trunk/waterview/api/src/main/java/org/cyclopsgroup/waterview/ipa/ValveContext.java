package org.cyclopsgroup.waterview.ipa;

import java.io.IOException;
import java.util.Map;

import org.cyclopsgroup.waterview.ActionRedirection;
import org.cyclopsgroup.waterview.WebContext;

public interface ValveContext
{
    WebContext getWebContext();

    boolean invokeNext( ValveContext context )
        throws IOException;

    void setActionRedirection( ActionRedirection redirection );

    Map<String, Object> variables();
}
