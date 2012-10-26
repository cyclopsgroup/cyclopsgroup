package org.cyclopsgroup.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Anagrams
{
    private static class Summary
    {
        private final Map<Character, Integer> occurrance;

        private final int hashCode;

        private Summary( String s )
        {
            Map<Character, Integer> m = new HashMap<Character, Integer>();
            for ( Character c : s.toCharArray() )
            {
                if ( m.containsKey( c ) )
                {
                    m.put( c, m.get( c ) + 1 );
                }
                else
                {
                    m.put( c, 1 );
                }
            }
            this.occurrance = Collections.unmodifiableMap( m );
            this.hashCode = occurrance.hashCode();
        }

        @Override
        public int hashCode()
        {
            return hashCode;
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( obj == null || !( obj instanceof Summary ) )
            {
                return false;
            }
            return occurrance.equals( ( (Summary) obj ).occurrance );
        }
    }

    public ArrayList<String> anagrams( String[] strs )
    {
        Map<Summary, String> map = new HashMap<Summary, String>();
        for ( String s : strs )
        {
            map.put( new Summary( s ), s );
        }
        return new ArrayList<String>( map.values() );
    }
}
