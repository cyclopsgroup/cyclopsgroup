package org.cyclopsgroup.lc;

import java.util.Arrays;

public class ThreeClosest
{
    public int threeSumClosest( int[] num, int target )
    {
        Arrays.sort( num );
        int best = num[0] + num[1] + num[2];
        int offset = Math.abs( best - target );
        for ( int i = 0; i < num.length - 2; i++ )
        {
            int small = num[i];

            // Way off
            if ( small * 3 - target > offset )
            {
                continue;
            }
            for ( int j = i + 1; j < num.length - 1; j++ )
            {
                int middle = num[j];

                // Way off
                if ( middle * 2 + small - target > offset )
                {
                    continue;
                }

                // Determine range of large number
                int smallestLarge = Math.max( middle, target - offset - small - middle );
                int largestLarge = target + offset - small - middle;

                for ( int k = j + 1; k < num.length; k++ )
                {
                    int large = num[k];
                    if ( large < smallestLarge )
                    {
                        continue;
                    }
                    if ( large > largestLarge )
                    {
                        break;
                    }

                    int sum = small + middle + large;
                    if ( Math.abs( sum - target ) < offset )
                    {
                        best = sum;
                        offset = Math.abs( sum - target );
                        largestLarge = target + offset - small - middle;
                    }
                }
            }
        }
        return best;
    }
}
