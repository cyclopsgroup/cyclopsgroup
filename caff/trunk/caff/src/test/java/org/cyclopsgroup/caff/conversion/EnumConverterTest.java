package org.cyclopsgroup.caff.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.lang.annotation.RetentionPolicy;

import org.cyclopsgroup.caff.NormalizedValue;
import org.junit.Test;

/**
 * Test to cover {@link EnumConverter}
 *
 * @author jiaqi
 */
public class EnumConverterTest
{
    /**
     * Verify the conversion for enum that implements {@link NormalizedValue}
     */
    @Test
    public void testConvertNormalizedEnum()
    {
        Converter<NormalizedEnum> c = new EnumConverter<NormalizedEnum>( NormalizedEnum.class );
        assertSame( NormalizedEnum.Y, c.fromCharacters( "2" ) );
        assertEquals( "2", c.toCharacters( NormalizedEnum.Y ).toString() );
        assertNull(c.fromCharacters( "100" ));
    }

    /**
     * Verify the conversion for enum that doesn't implement {@link NormalizedValue}
     */
    @Test
    public void testConvertSimpleEnum()
    {
        Converter<RetentionPolicy> c = new EnumConverter<RetentionPolicy>( RetentionPolicy.class );
        assertSame( RetentionPolicy.RUNTIME, c.fromCharacters( "RUNTIME" ) );
        assertEquals( "RUNTIME", c.toCharacters( RetentionPolicy.RUNTIME ).toString() );
        assertNull( c.fromCharacters( "NONEXIST" ) );
    }
}
