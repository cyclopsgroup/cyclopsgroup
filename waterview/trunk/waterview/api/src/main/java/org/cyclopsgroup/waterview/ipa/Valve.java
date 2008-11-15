package org.cyclopsgroup.waterview.ipa;

import java.io.IOException;

public interface Valve
{
    void invoke( ValveContext context )
        throws IOException;
}
