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

/**
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 * 
 * Ordered array searcher
 */
public final class ArraySearcher
{

    private static int internalSearch( int[] array, int value, int startIndex, int endIndex )
    {
        if ( endIndex - startIndex <= 1 )
        {
            return -1;
        }
        int middleIndex = ( endIndex + startIndex ) / 2;
        int middleValue = array[middleIndex];
        if ( middleValue == value )
        {
            return middleIndex;
        }
        if ( middleValue > value )
        {
            return internalSearch( array, value, startIndex, middleIndex );
        }
        else
        {
            return internalSearch( array, value, middleIndex, endIndex );
        }
    }

    public static int searchOrderedArray2( int[] array, int value )
    {
        if ( array[0] > value || array[array.length - 1] < value )
        {
            return -1;
        }

        if ( array[0] == value )
        {
            return 0;
        }

        if ( array[array.length - 1] == value )
        {
            return array.length - 1;
        }
        return internalSearch( array, value, 0, array.length - 1 );
    }

    public static int searchOrderedArray( int[] array, int value )
    {
        if ( array[0] > value || array[array.length - 1] < value )
        {
            return -1;
        }

        if ( array[0] == value )
        {
            return 0;
        }

        if ( array[array.length - 1] == value )
        {
            return array.length - 1;
        }

        int startIndex = 0;
        int endIndex = array.length - 1;

        while ( ( endIndex - startIndex ) > 1 )
        {
            int middleIndex = ( endIndex + startIndex ) / 2;
            int middleValue = array[middleIndex];
            if ( middleValue == value )
            {
                return middleIndex;
            }
            if ( middleValue > value )
            {
                endIndex = middleIndex;
            }
            else
            {
                startIndex = middleIndex;
            }
        }

        return -1;
    }

    public static int searchArray( int[] array, int value )
    {
        for ( int i = 0; i < array.length; i++ )
        {
            int v = array[i];
            if ( v == value )
            {
                return i;
            }
        }
        return -1;
    }
}
