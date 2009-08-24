package org.cyclopsgroup.caff.format;

import static org.junit.Assert.assertEquals;

import org.cyclopsgroup.caff.ABean;
import org.junit.Test;

/**
 * Test case of {@link FixLengthImpl}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class FixLengthImplTest
{
    private FixLengthImpl<ABean> impl;

    /**
     * Verify result of populate
     */
    @Test
    public void testPopulate()
    {
        impl = new FixLengthImpl<ABean>( ABean.class );
        ABean bean = new ABean();
        impl.populate( bean, "03519691122Bender    Rod       1" );
        assertEquals( 35, bean.getAge() );
        assertEquals( "Bender", bean.getFirstName() );
        assertEquals( "Rod", bean.lastName );
    }
}
