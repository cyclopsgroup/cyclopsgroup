package org.cyclopsgroup.doorman.service.storage;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

/**
 * Hibernate user type for JODA date time
 */
public class DateTimeUserType
    implements UserType
{
    private static final int[] SQL_TYPES = new int[] { Types.TIMESTAMP };

    /**
     * @inheritDoc
     */
    @Override
    public Object assemble( Serializable cached, Object target )
    {
        return cached;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object deepCopy( Object object )
    {
        return object;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Serializable disassemble( Object object )
    {
        return (Serializable) object;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals( Object a, Object b )
    {
        return ObjectUtils.equals( a, b );
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode( Object object )
    {
        return object.hashCode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isMutable()
    {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object nullSafeGet( ResultSet set, String[] strings, Object object )
        throws HibernateException, SQLException
    {
        Date timestamp = (Date) Hibernate.TIMESTAMP.nullSafeGet( set, strings[0] );
        if ( timestamp == null )
        {
            return null;
        }
        return new LocalDateTime( timestamp ).toDateTime( DateTimeZone.UTC );
    }

    /**
     * @inheritDoc
     */
    @Override
    public void nullSafeSet( PreparedStatement stat, Object value, int index )
        throws HibernateException, SQLException
    {
        if ( value == null )
        {
            Hibernate.TIMESTAMP.nullSafeSet( stat, null, index );
        }
        else
        {
            DateTime dt = ( (DateTime) value ).toDateTime( DateTimeZone.UTC ).toLocalDateTime().toDateTime();
            Hibernate.TIMESTAMP.nullSafeSet( stat, dt.toDate(), index );
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Object replace( Object original, Object target, Object owner )
        throws HibernateException
    {
        return original;
    }

    /**
     * @inheritDoc
     */
    @SuppressWarnings( "rawtypes" )
    @Override
    public Class returnedClass()
    {
        return DateTime.class;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int[] sqlTypes()
    {
        return SQL_TYPES;
    }
}
