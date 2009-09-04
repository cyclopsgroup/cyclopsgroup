package org.cyclopsgroup.spee;

public class Predicates
{
    public static <T> Predicate<T> a(final Class<T> type)
    {
        return new Predicate<T>()
        {
            @Override
            public boolean evaluate(T evaluatee)
            {
                return type.isAssignableFrom(evaluatee.getClass());
            }
        };
    }
    
    public static <T> Predicate<T> eq(final T expectation)
    {
        return new Predicate<T>()
        {
            public boolean evaluate(T evaluatee)
            {
                if(expectation == null)
                {
                    return evaluatee == null;
                }
                return expectation.equals(evaluatee);
            }
        };
    }
}
