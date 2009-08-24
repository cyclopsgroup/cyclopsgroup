package org.cyclopsgroup.caff.format;

/**
 * Defines should field value align left or right
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public enum AlignPolicy
{
    /**
     * Left is default value, truncate right.
     */
    LEFT
    {
        @Override
        public CharSequence trim( CharSequence input, char empty )
        {
            int lengthToKeep = input.length();
            while ( lengthToKeep > 0 && input.charAt( lengthToKeep - 1 ) == empty )
            {
                lengthToKeep--;
            }
            return input.subSequence( 0, lengthToKeep );
        }
    },
    /**
     * Align right, truncate left.
     */
    RIGHT
    {
        @Override
        public CharSequence trim( CharSequence input, char empty )
        {
            int start = 0;
            while ( start < input.length() && input.charAt( start ) == empty )
            {
                start++;
            }
            return input.subSequence( start, input.length() );
        }
    };

    /**
     * Trim the leading or trailing empty characters based on alignment
     *
     * @param input Content that possibly has empty character
     * @param empty Character that stands for empty
     * @return Trimmed result
     */
    protected abstract CharSequence trim( CharSequence input, char empty );
}
