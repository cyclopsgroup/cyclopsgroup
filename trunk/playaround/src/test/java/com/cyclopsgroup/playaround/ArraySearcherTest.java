/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.playaround;

import junit.framework.TestCase;

public class ArraySearcherTest
    extends TestCase
{
    public void testSearchOrderedArray()
    {
        int[] array = { 1, 3, 4, 6, 11, 18, 22, 25 };
        for ( int i = 0; i < array.length; i++ )
        {
            int value = array[i];
            assertEquals( i, ArraySearcher.searchOrderedArray( array, value ) );
        }
        assertEquals( -1, ArraySearcher.searchOrderedArray( array, -2 ) );
        assertEquals( -1, ArraySearcher.searchOrderedArray( array, 28 ) );
        assertEquals( -1, ArraySearcher.searchOrderedArray( array, 16 ) );
    }

    public void testSearchOrderedArray2()
    {
        int[] array = { 1, 3, 4, 6, 11, 18, 22, 25 };
        for ( int i = 0; i < array.length; i++ )
        {
            int value = array[i];
            assertEquals( i, ArraySearcher.searchOrderedArray2( array, value ) );
        }
        assertEquals( -1, ArraySearcher.searchOrderedArray2( array, -2 ) );
        assertEquals( -1, ArraySearcher.searchOrderedArray2( array, 28 ) );
        assertEquals( -1, ArraySearcher.searchOrderedArray2( array, 16 ) );
    }

    public void testSearchArray()
    {
        int[] array = { 1, 5, 99, 24, 13, 77 };
        for ( int i = 0; i < array.length; i++ )
        {
            int value = array[i];
            assertEquals( i, ArraySearcher.searchArray( array, value ) );
        }
        assertEquals( -1, ArraySearcher.searchOrderedArray( array, -2 ) );
    }
}
