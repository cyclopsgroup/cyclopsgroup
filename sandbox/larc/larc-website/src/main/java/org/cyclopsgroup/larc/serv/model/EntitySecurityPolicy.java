package org.cyclopsgroup.larc.serv.model;

/**
 * Policy applied when one entity access another
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public enum EntitySecurityPolicy
{
    /**
     * Not accessible
     */
    NOT_GRANTED( false, false ), 
    /**
     * Reading is allowed, but not writing 
     */
    READ_ONLY( true, false ), 
    /**
     * Read and write 
     */
    READ_WRITE( true, true );

    private final boolean readable;

    private final boolean writeable;

    private EntitySecurityPolicy( boolean readable, boolean writeable )
    {
        this.readable = readable;
        this.writeable = writeable;
    }

    /**
     * @return Value of field readable
     */
    public final boolean isReadable()
    {
        return readable;
    }

    /**
     * @return Value of field writeable
     */
    public final boolean isWriteable()
    {
        return writeable;
    }
}
