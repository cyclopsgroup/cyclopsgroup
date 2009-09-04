package org.cyclopsgroup.spee;

public interface Flow<T>
{
    T execute(ExecutionContext exe);
}
