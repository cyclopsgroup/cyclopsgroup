package org.cyclopsgroup.fiar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeAdapter
    extends XmlAdapter<String, DateTime>
{
    private final DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();

    private final DateTimeFormatter printer = ISODateTimeFormat.dateTime();

    /**
     * @inheritDoc
     */
    @Override
    public String marshal( DateTime v )
        throws Exception
    {
        return printer.print( v );
    }

    /**
     * @inheritDoc
     */
    @Override
    public DateTime unmarshal( String v )
        throws Exception
    {
        return parser.parseDateTime( v );
    }
}
