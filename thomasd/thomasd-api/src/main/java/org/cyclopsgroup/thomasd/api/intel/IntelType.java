package org.cyclopsgroup.thomasd.api.intel;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum IntelType
{
    DOCUMENT( OperationType.READ ), CIPHER( OperationType.ENCRYPT, OperationType.DECRYPT, OperationType.READ );

    private final Set<OperationType> supportedOperationTypes;

    private IntelType( OperationType... supportedOperationTypes )
    {
        this.supportedOperationTypes =
            Collections.unmodifiableSet( new HashSet<OperationType>( Arrays.asList( supportedOperationTypes ) ) );
    }

    public final Set<OperationType> getSupportedOperationTypes()
    {
        return supportedOperationTypes;
    }
}
