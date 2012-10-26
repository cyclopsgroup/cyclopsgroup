package org.cyclopsgroup.streammarker;

import org.apache.commons.lang.Validate;

public class Mark
{
    public static enum Kind
    {
        COUNT, LEVEL;
    }

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static Mark ofCount( String name, int count )
    {
        return new Mark( name, count, Kind.COUNT );
    }

    public static Mark ofValue( String name, long value )
    {
        return new Mark( name, value, Kind.LEVEL );
    }

    private final Kind kind;

    private final String name;

    private String[] tags = EMPTY_STRING_ARRAY;

    private final long value;

    private Mark( String name, long value, Kind kind )
    {
        Validate.notNull( name, "Mark name can't be NULL" );
        this.name = name;
        this.kind = kind;
        this.value = value;
    }

    public final Kind getKind()
    {
        return kind;
    }

    public final String getName()
    {
        return name;
    }

    public final String[] getTags()
    {
        return tags;
    }

    public final long getValue()
    {
        return value;
    }

    public Mark tag( String... tags )
    {
        Validate.notNull( tags, "Tags can't be NULL" );
        this.tags = tags;
        return this;
    }
}
