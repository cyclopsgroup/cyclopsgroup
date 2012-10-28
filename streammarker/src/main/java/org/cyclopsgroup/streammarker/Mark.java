package org.cyclopsgroup.streammarker;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;

public class Mark
{
    public static enum Kind
    {
        COUNT( "C" ), LEVEL( "L" );

        private final String value;

        private Kind( String value )
        {
            this.value = value;
        }

        public final String getShortName()
        {
            return value;
        }
    }

    public static final Pattern PATTERN_WORD = Pattern.compile( "^[a-zA-Z]\\w*$" );

    /**
     * Create mark for count with value 1
     *
     * @param name Name of mark
     * @return New mark
     */
    public static Mark count( String name )
    {
        return count( name, 1 );
    }

    /**
     * Create a mark for a count
     *
     * @param name Name of mark
     * @param count Count value
     * @return New mark
     */
    public static Mark count( String name, int count )
    {
        return new Mark( name, count, Kind.COUNT );
    }

    /**
     * Create a mark for a level
     *
     * @param name Name of mark
     * @param value Value of level
     * @return New mark
     */
    public static Mark level( String name, long value )
    {
        return new Mark( name, value, Kind.LEVEL );
    }

    private final Kind kind;

    private final String name;

    private final Set<String> tags = new HashSet<String>();

    private final long value;

    private Mark( String name, long value, Kind kind )
    {
        Validate.notNull( name, "Mark name can't be NULL" );
        Validate.isTrue( PATTERN_WORD.matcher( name ).find(), "Name " + name + " is not valid" );
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

    public final Set<String> getTags()
    {
        return Collections.unmodifiableSet( tags );
    }

    public final long getValue()
    {
        return value;
    }

    public Mark tag( Iterable<String> tags )
    {
        Validate.notNull( tags, "Tags can't be NULL" );
        for ( String tag : tags )
        {
            Validate.isTrue( PATTERN_WORD.matcher( tag ).find(), "Tag " + tag + " is not valid" );
        }
        for ( String tag : tags )
        {
            this.tags.add( tag );
        }
        return this;
    }

    /**
     * Add an array of tags to mark
     *
     * @param tags Array of tags
     * @return The mark itself
     */
    public Mark tag( String... tags )
    {
        return tag( new ArrayIterable<String>( tags ) );
    }
}
