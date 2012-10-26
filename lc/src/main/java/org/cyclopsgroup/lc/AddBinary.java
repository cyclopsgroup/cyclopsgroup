package org.cyclopsgroup.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddBinary
{
    public String addBinary( String a, String b )
    {
        int digits = Math.max( a.length(), b.length() );
        int aOffset = digits - a.length();
        int bOffset = digits - b.length();
        List<Character> result = new ArrayList<Character>();

        int remaining = 0;
        for ( int i = digits - 1; i >= 0; i-- )
        {
            int da = ( i >= aOffset && a.charAt( i - aOffset ) == '1' ) ? 1 : 0;
            int db = ( i >= bOffset && b.charAt( i - bOffset ) == '1' ) ? 1 : 0;

            int sum = remaining + da + db;
            result.add( ( sum % 2 == 0 ) ? '0' : '1' );
            remaining = sum > 1 ? 1 : 0;
        }
        if ( remaining > 0 )
        {
            result.add( '1' );
        }
        Collections.reverse( result );
        StringBuilder s = new StringBuilder();
        for ( char c : result )
        {
            s.append( c );
        }
        return s.toString();
    }
}
